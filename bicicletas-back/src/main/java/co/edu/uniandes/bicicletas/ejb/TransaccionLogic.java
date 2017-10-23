/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.TransaccionEntity;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import co.edu.uniandes.bicicletas.persistence.TransaccionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que modela la logica de las transacciones.
 * @author jd.trujillom
 */
@Stateless
public class TransaccionLogic {
    
    @Inject
    TransaccionPersistence persistence;
    
    @Inject
    ReservaPersistence reservaPersistence;
    
    /**
     * Obtiene una transacción por medio de una reserva
     * @param reserva asociada a la transacción que se quiere obtener. 
     * @return la transaccion asociada a la reserva, sino existe, la crea y 
     * la retorna. 
     */
    public TransaccionEntity obtenerTransaccion(ReservaEntity reserva) throws BusinessLogicException{
        //Verifica si la reserva se encuentra en el estado correcto.
        if(reserva.getEstado() != ReservaEntity.FINALIZADA)
            throw new BusinessLogicException("La reserva debe haber finalizado para calcular la transaccion final");
        
        TransaccionEntity entity = reserva.getTransaccion();
        
        //Si no encuentra la transacción, la crea.
        if(entity == null){
            entity = createTransaccion(reserva);
        }
        
        return entity;
    }
    /**
     * Obtiene una transacción por medio de su id.
     * @param idReserva de la transaccion buscada.
     * @return la transaccion asociada a la reserva, null de lo contrario
     */
    public TransaccionEntity obtenerTransaccion(Long idReserva){
        
        return persistence.findTransaccion(idReserva);
    }
    
    
    /**
     * Crea una transaccion, asigando el tipo y el valor asociado a la transaccion.
     * @param reserva a la cuál está asociado la transacción.
     * @return la transaccion ya creada. 
     */
    public TransaccionEntity createTransaccion(ReservaEntity reserva){
        
        
        //Obteniendo la hora estimada, entrega e inicio.
        Integer horaInicio = reserva.getFechaInicio().getHours();
        Integer horaEstimadaEntrega = reserva.getFechaEntrega().getHours();
        Integer horaEntregaFinal = 0;
        
        //Calculado horas totales y horas de inicio
        Integer horasEstimadas = (horaEstimadaEntrega - horaInicio);
        Integer horasTotales = (horaEntregaFinal - horaInicio);
        
        Integer tipo = -1;
        Double valor = 0.0;
        if(horasEstimadas.equals(horasTotales)){
            tipo = TransaccionEntity.NO_NECESARIO;
        }
        
        else if(horasTotales > horasEstimadas){
            tipo = TransaccionEntity.PAGO_EXCEDENTE;
            valor =calcularValor(reserva, horasTotales-horasEstimadas);
            
        }
        
        else{
            tipo = TransaccionEntity.REEMBOLSO;
            valor = calcularValor(reserva, horasEstimadas-horasTotales);
        }
            
        
        //Crear transaccion
        TransaccionEntity entity = new TransaccionEntity();
        entity.setTipo(tipo);
        entity.setValor(valor);
        
        //Persiste la nueva transaccion
        persistence.createTransaccion(entity);
        
        //Le agrega la transaccion a la reserva
        reserva.setTransaccion(entity);
        reservaPersistence.update(reserva);
        
        return entity;

    }
    
    /**
     * Calcula el valor de la transaccion.
     * @param reserva a la cual está asociada la transacción.
     * @param horas con base a las cuales se calcula el valor.
     * @return el valor de la transacción.
     */
    public Double calcularValor(ReservaEntity reserva, Integer horas){
        int numeroBicicletas = reserva.getBicicletas().size();
        
        return new Double(numeroBicicletas*horas);
    }
}

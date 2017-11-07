/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.TransaccionEntity;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import co.edu.uniandes.bicicletas.persistence.TransaccionPersistence;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que modela la logica de las transacciones.
 *
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
     *
     * @param reserva asociada a la transacción que se quiere obtener.
     * @return la transaccion asociada a la reserva, sino existe, la crea y la
     * retorna.
     * @throws co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException
     */
    public TransaccionEntity obtenerTransaccion(ReservaEntity reserva) throws BusinessLogicException {
        //Verifica si la reserva se encuentra en el estado correcto.
        if (reserva.getEstado() != ReservaEntity.FINALIZADA) {
            throw new BusinessLogicException("La reserva debe haber finalizado para calcular la transaccion final");
        }

        TransaccionEntity entity = reserva.getTransaccion();

        //Si no encuentra la transacción, la crea.
        if (entity == null) {
            entity = createTransaccion(reserva);
        }

        return entity;
    }

    /**
     * Obtiene una transacción por medio de su id.
     *
     * @param idReserva de la transaccion buscada.
     * @return la transaccion asociada a la reserva, null de lo contrario
     */
    public TransaccionEntity obtenerTransaccion(Long idReserva) {

        return persistence.findTransaccion(idReserva);
    }

    /**
     * Crea una transaccion, asigando el tipo y el valor asociado a la
     * transaccion.
     *
     * @param reserva a la cuál está asociado la transacción.
     * @return la transaccion ya creada.
     */
    public TransaccionEntity createTransaccion(ReservaEntity reserva) {

        //Calcular el monto adicional
        int[] horasTotales = calcularHorasMinutosEntreFechas(reserva.getFechaInicio(), reserva.getFechaFinal());
        int cantidadBicicletas = reserva.getPago().getBicicletasPendientes();
        
        double precioHoras = cantidadBicicletas * PagoEntity.PRECIO_BICICLETA_HORA * horasTotales[0];
        double precioPorMinutos = Math.ceil(PagoEntity.PRECIO_BICICLETA_HORA / 60.0);
        double precioMinutos = cantidadBicicletas * precioPorMinutos * horasTotales[1];

        double valorTotal = precioHoras + precioMinutos;
        double diferencia = reserva.getPago().getMonto() - valorTotal;
        
        double valor = 0;
        int tipo = -1;
        
        //Si el costo excendente o faltante es menor a 500
        if(Math.abs(diferencia) <= 500){
            tipo = TransaccionEntity.NO_NECESARIO;
        }
        
        //Si existe un excedente
        else if(diferencia < 0){
             tipo = TransaccionEntity.PAGO_EXCEDENTE;
             valor = -diferencia;
        }
        
        //Si existe un reembolso
        else if(diferencia >= 0) {
            tipo = TransaccionEntity.REEMBOLSO;
            valor = diferencia;
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
     * Método que calcula la diferencia de horas y minutos entre dos fechas
     * @param fechaInicio
     * @param fechaFin
     * @return 
     */
    public int[] calcularHorasMinutosEntreFechas(Date fechaInicio, Date fechaFin){
        int[] horasMinutos = new int[2];
        
        horasMinutos[0]= fechaFin.getHours() - fechaInicio.getHours();
        int diferenciaMinutos = fechaFin.getMinutes()-fechaInicio.getMinutes();
        if(diferenciaMinutos < 0){
            horasMinutos[0] -=1;
            horasMinutos[1] = (60 - fechaInicio.getMinutes()) + fechaFin.getMinutes();
        }
        
        return horasMinutos;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.PagoDTO;
import co.edu.uniandes.bicicletas.dtos.PagoDetailDTO;
import co.edu.uniandes.bicicletas.ejb.PagoLogic;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que modela la relación que va desde Reserva hasta Pago.
 *
 * @author jd.trujillom
 */
@Produces("application/json")
@Consumes("application/json")
public class PagoReservaResource {

    /**
     * Puntos minimos con las que se pagan una bicicleta.
     */
    public static final int PUNTOS_MINIMOS = 10;

    /**
     * Persistencia de una reserva.
     */
    @Inject
    private ReservaPersistence reservaPersistence;

    /**
     * Logica de un pago.
     */
    @Inject
    private PagoLogic pagoLogic;

    /**
     * Verifica si una reserva ya posee un pago.
     * @param idReserva del cual se quiere saber si tiene pago.
     * @return el pago si existe.
     * @throws BusinessLogicException si no existe un pago asociado.  
     */
    @GET
    @Path("existePago")
    public PagoDTO existePago(@PathParam("idReserva") Long idReserva)throws BusinessLogicException {
        PagoDTO respuesta = null;
        
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();
        
        if(pago == null){
            throw new BusinessLogicException("No existe un pago asociado");
        }
        
        respuesta= new PagoDTO(pago);
        
        return respuesta;
        
    }
        //Si el pago no existe, lo crea
    
    
    /**
     * Genera y/o retorna el pago asociado a una reserva.
     * @param idReserva de la que se quiere obtener el pago.
     * @return el pago asociado a la reserva.
     */
    @GET
    public PagoDTO darPago(@PathParam("idReserva") Long idReserva) {
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        //Si el pago no existe, lo crea
        if (pago == null) {
            pago = new PagoEntity();
            
            reserva.setPrecioFinalNumBicicletas(reserva.getBicicletas().size());
            double monto = reserva.getPrecioFinal();
            if(reserva.getDescuento()){
                monto -= monto*0.05;
            }

            pago.setFecha(reserva.getFechaInicio());
            pago.setMonto(monto);
            pago.setIdUsuario(reserva.getUsuarioReserva().getDocumentoUsuario());

            //Verificar si reserva tiene la lista de bicicletas inicializada
            if (reserva.getBicicletas() != null) {
                pago.setBicicletasPendientes(reserva.getBicicletas().size());
            }

            pago = crearPago(reserva, pago);
        }

        return new PagoDTO(pago);
    }

    /**
     * Persiste un pago en la base de datos.
     * @param reserva a la que se le quiere asociar un pago.
     * @param pago al que se quiere asociar a la reserva.
     * @return el pago asociado a la reserva.
     */
    private PagoEntity crearPago(ReservaEntity reserva, PagoEntity pago) {
        //En este metodo es una buena idea usar a pagologic porque se puede
        //utilizar el método para crear un pago y asociarselo a una reserva

        
        pago.setEstado(PagoEntity.ESPERANDO_PAGO);
        pago.setReserva(reserva);

        pago = pagoLogic.crearPago(pago);

        reserva.setPago(pago);
        
        reservaPersistence.update(reserva);
        
        return pago;
    }

    /**
     * Invova a la logica de pago y permite realizar un pago de una bicicleta con puntos.
     * @param idReserva de la cual se quiere pagar con puntos.
     * @return el pago ya actualizado en formato DTO.
     * @throws BusinessLogicException si no se puede pagar con puntos.
     */
    @PUT
    @Path("pagarConPuntos")
    public PagoDetailDTO efectuarPagoConPuntos(@PathParam("idReserva") Long idReserva) throws BusinessLogicException {

        PagoEntity pago = pagoLogic.pagarReservaConPuntos(idReserva);

        return new PagoDetailDTO(pago);
    }

    /**
     * Método que permite realizar un pago, ya sea con tarjeta o con PSE.
     * @param idReserva que se quiere realizar el pago.
     * @param metodoDePago que es 1 para tarjeta y 2 para PSE
     * @param contrasenia de la tarjeta o la cuenta PSE (según sea el caso)
     * @return el pago actualizado en formato DTO
     * @throws BusinessLogicException si no se logra hacer el pago.
     */
    @PUT
    @Path("pagar")
    @Consumes("text/plain")
    public PagoDetailDTO efectuarPago(@PathParam("idReserva") Long idReserva, @QueryParam("metodo") int metodoDePago, String contrasenia) throws BusinessLogicException {

        if (metodoDePago != PagoEntity.TARJETA_DE_CREDITO && metodoDePago != PagoEntity.PSE) {
            throw new WebApplicationException("El recurso /reservas/" + idReserva + "/Pago/pagar?metodo=" + metodoDePago + "/ no existe. ", 404);
        }

        PagoEntity pago = pagoLogic.pagarReserva(idReserva, metodoDePago, contrasenia);
        PagoDetailDTO pagoRealizado = new PagoDetailDTO(pago);

        return pagoRealizado;
    }

    /**
     * Invoca a la logica de pago y permite solicitar un reembolso
     * @param idReserva del que se quiere solictar el reembolso.
     * @return el pago actualizado en formato DTO.
     * @throws BusinessLogicException si el estado que se encuentra el pago es
     * incorrecto.
     */
    @PUT
    @Path("solicitarReembolso")
    public PagoDetailDTO solicitarReembolso(@PathParam("idReserva") Long idReserva) throws BusinessLogicException {

        PagoEntity pago = pagoLogic.solicitarReembolso(idReserva);
        return new PagoDetailDTO(pago);

    }

}

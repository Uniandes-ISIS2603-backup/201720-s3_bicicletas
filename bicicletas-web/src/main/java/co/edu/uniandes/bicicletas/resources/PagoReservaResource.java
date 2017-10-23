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
import co.edu.uniandes.bicicletas.ejb.PuntoLogic;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.bicicletas.ejb.SistemaDePagosLogic;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
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

    public final static int PUNTOS_MINIMOS = 10;

    @Inject
    private ReservaPersistence reservaPersistence;

    @Inject
    private PagoLogic pagoLogic;

    @Inject
    private PuntoLogic puntoLogic;

    private SistemaDePagosLogic sistemaDePagos;

    @GET
    public PagoDTO darPago(@PathParam("idReserva") Long idReserva) {
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        //Si el pago no existe, lo crea
        if (pago == null) {
            pago = new PagoEntity();

            pago.setFecha(reserva.getFechaInicio());
            pago.setMonto(reserva.getPrecioFinal());
            pago.setIdUsuario(reserva.getUsuarioReserva().getDocumentoUsuario());

            //Verificar si reserva tiene la lista de bicicletas inicializada
            if (reserva.getBicicletas() != null) {
                pago.setBicicletasPendientes(reserva.getBicicletas().size());
            }

            pago = crearPago(reserva, pago);
        }

        return new PagoDTO(pago);
    }

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

    @PUT
    @Path("pagarConPuntos")
    public PagoDetailDTO efectuarPagoConPuntos(@PathParam("idReserva") Long idReserva) throws BusinessLogicException {
       
        PagoEntity pago = pagoLogic.pagarReservaConPuntos(idReserva);
        
        return new PagoDetailDTO(pago);
    }

    @PUT
    @Path("pagar")
    @Consumes("text/plain")
    public PagoDetailDTO efectuarPago(@PathParam("idReserva") Long idReserva, @QueryParam("metodo") int metodoDePago, String contrasenia) throws BusinessLogicException {
        
        if(metodoDePago != PagoEntity.TARJETA_DE_CREDITO && metodoDePago !=  PagoEntity.PSE)
            throw new WebApplicationException("El recurso /reservas/" + idReserva+"/Pago/pagar?metodo="+ metodoDePago + "/ no existe. ", 404);
        
        PagoEntity pago =  pagoLogic.pagarReserva(idReserva, metodoDePago, contrasenia);
        PagoDetailDTO pagoRealizado = new PagoDetailDTO(pago);

        return pagoRealizado;
    }

    @PUT
    @Path("solicitarReembolso")
    public PagoDetailDTO solicitarReembolso(@PathParam("idReserva") Long idReserva) throws BusinessLogicException {
       
        PagoEntity pago = pagoLogic.solicitarReembolso(idReserva);
        return new PagoDetailDTO(pago);

    }
    
    
}

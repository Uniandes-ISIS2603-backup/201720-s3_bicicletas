/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.PagoDTO;
import co.edu.uniandes.bicicletas.dtos.PagoDetailDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.PagoLogic;
import co.edu.uniandes.bicicletas.ejb.PuntoLogic;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity_;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import co.edu.uniandes.bicicletas.ejb.SistemaDePagosLogic;
import co.edu.uniandes.bicicletas.entities.PuntoEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import co.edu.uniandes.bicicletas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;

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
    public PagoDTO darPago(@PathParam("idReserva") Long idReserva) 
    {
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        //Si el pago no existe, lo crea
        if (pago == null) {
            pago = new PagoEntity();
            
            pago.setFecha(reserva.getFechaInicio());
            pago.setMonto(reserva.getPrecioFinal());
            pago.setIdUsuario(reserva.getUsuarioReserva().getDocumentoUsuario());
            
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
    public PagoDetailDTO efectuarPagoConPuntos(@PathParam("idReserva") Long idReserva) throws BusinessLogicException{
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        if (reserva.getPago() == null) {
            throw new BusinessLogicException("La reserva con id: " + idReserva
                    + " no tiene un pago asociado");
        }

        if (pago.getEstado() != PagoEntity.ESPERANDO_PAGO) {
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + " el estado que se encuentra el pago");
        }
        //Verificar si es posible el pago con puntos
        UsuarioEntity usuario = reserva.getUsuarioReserva();
        try{
            puntoLogic.deletePuntos(usuario.getDocumentoUsuario());
        } catch(Exception e){
            throw new BusinessLogicException("El usuario cuenta con " + 
                    usuario.getPuntos().size()+ " puntos  y son necesario " + 
                    PUNTOS_MINIMOS + " puntos para pagar la reserva");
        }
   
        sistemaDePagos = new SistemaDePagosLogic();

        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.PROCESANDO_PAGO);
        Long idTransaccion = sistemaDePagos.realizarPago();
        pago.setIdTransaccion(idTransaccion);
        if (reserva.getUsuarioReserva() != null) {
            pago.setIdUsuario(reserva.getUsuarioReserva().getDocumentoUsuario());
        }
        PagoEntity updatePago = pagoLogic.updatePago(pago);

        //Actualizar estado de la reserva
        reserva.setEstado(ReservaEntity.PAGO);
        reservaPersistence.update(reserva);
        

        return new PagoDetailDTO(updatePago);
        
        
    }
    

    @PUT
    @Path("pagar")
    public PagoDetailDTO efectuarPago(@PathParam("idReserva") Long idReserva) throws BusinessLogicException {
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        if (reserva.getPago() == null) {
            throw new BusinessLogicException("La reserva con id: " + idReserva
                    + " no tiene un pago asociado");
        }

        if (pago.getEstado() != PagoEntity.ESPERANDO_PAGO) {
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + "el estado que se encuentra el pago");
        }

        sistemaDePagos = new SistemaDePagosLogic();

        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.PROCESANDO_PAGO);
        Long idTransaccion = sistemaDePagos.realizarPago();
        pago.setIdTransaccion(idTransaccion);
        if (reserva.getUsuarioReserva() != null) {
            pago.setIdUsuario(reserva.getUsuarioReserva().getDocumentoUsuario());
        }
        PagoEntity updatePago = pagoLogic.updatePago(pago);

        //Actualizar estado de la reserva
        reserva.setEstado(ReservaEntity.PAGO);
        reservaPersistence.update(reserva);

        return new PagoDetailDTO(updatePago);

    }
    
    

    @PUT
    @Path("solicitarReembolso")
    public PagoDetailDTO solicitarReembolso(@PathParam("idReserva") Long idReserva) throws BusinessLogicException {
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        if (reserva.getPago() == null) {
            throw new BusinessLogicException("La reserva con id: " + idReserva
                    + " no tiene un pago asociado");
        }

        if (pago.getEstado() != PagoEntity.PAGADO) {
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + "el estado que se encuentra el pago");
        }

        sistemaDePagos = new SistemaDePagosLogic();

        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.PROCESANDO_REEMBOLSO);
        pagoLogic.updatePago(pago);

        //Actualizar estado de la reserva
        reserva.setEstado(ReservaEntity.REENBOLSADO);
        reservaPersistence.update(reserva);

        return new PagoDetailDTO(pago);

    }

}

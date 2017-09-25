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

/**
 * Clase que modela la relación que va desde Reserva hasta Pago. 
 * @author jd.trujillom
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class PagoReservaResource {
    
    @Inject
    private ReservaLogic reservaLogic;
    
    @Inject
    private PagoLogic pagoLogic;
    
    private
    SistemaDePagosLogic sistemaDePagos;

    @GET
    public PagoDetailDTO darPago(@PathParam("idReserva") Long idReserva){
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        PagoEntity pago = reserva.getPago();
        
        if(pago == null){
            throw new WebApplicationException("El recurso /Reserva/" + idReserva
                    + "/Pago no existe", 404 );
        }
        
        return new PagoDetailDTO(pago);
    }
    
    @POST
    public PagoDetailDTO crearPago(@PathParam("idReserva")Long idReserva, PagoEntity pago) throws BusinessLogicException{
        //En este metodo es una buena idea usar a pagologic porque se puede
        //utilizar el método para crear un pago y asociarselo a una reserva
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        pago.setEstado(PagoEntity.ESPERANDO_PAGO);
        
        if(reserva.getPago() != null)
           throw new BusinessLogicException("La reserva con id: " + idReserva + 
                   " ya tiene un pago asociado");
        
        reserva.setPago(pago);
        ReservaEntity nuevoReservaEntity = reservaLogic.actualizarReserva(reserva);
        
        return new PagoDetailDTO(pago);
    }
    
    @PUT
    @Path("pagar")
    public PagoDetailDTO efectuarPago(@PathParam("idReserva")Long idReserva)throws BusinessLogicException{
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        PagoEntity pago = reserva.getPago();
        
        if(reserva.getPago() == null)
           throw new BusinessLogicException("La reserva con id: " + idReserva + 
                   " no tiene un pago asociado");
 
        
        if(pago.getEstado() != PagoEntity.ESPERANDO_PAGO){
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + "el estado que se encuentra el pago");
        }
        
        sistemaDePagos = new SistemaDePagosLogic();
        
        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.PROCESANDO_PAGO);
        Long idTransaccion = sistemaDePagos.realizarPago();
        pago.setIdTransaccion(idTransaccion);
            if(reserva.getUsuarioReserva() != null)
        pago.setIdUsuario(reserva.getUsuarioReserva().getId());
        pagoLogic.updatePago(pago);
        
        //Actualizar estado de la reserva
        reserva.setEstado(ReservaEntity.PAGO);
        reservaLogic.actualizarReserva(reserva);
        
        return new PagoDetailDTO(pago);
        
    } 
    
    
    @PUT
    @Path(" ")
    public PagoDetailDTO solicitarReembolso(@PathParam("idReserva")Long idReserva)throws BusinessLogicException{
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        PagoEntity pago = reserva.getPago();
        
        if(reserva.getPago() == null)
           throw new BusinessLogicException("La reserva con id: " + idReserva + 
                   " no tiene un pago asociado");
 
        
        if(pago.getEstado() != PagoEntity.PAGADO){
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + "el estado que se encuentra el pago");
        }
        
        sistemaDePagos = new SistemaDePagosLogic();
        
        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.PROCESANDO_REEMBOLSO);
        pagoLogic.updatePago(pago);
        
        //Actualizar estado de la reserva
        reserva.setEstado(ReservaEntity.REENBOLSADO);
        reservaLogic.actualizarReserva(reserva);
        
        return new PagoDetailDTO(pago);
        
    } 
    
}

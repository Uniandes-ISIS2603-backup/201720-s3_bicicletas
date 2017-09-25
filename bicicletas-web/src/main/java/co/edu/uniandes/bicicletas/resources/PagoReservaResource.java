/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.PagoDTO;
import co.edu.uniandes.bicicletas.dtos.PagoDetailDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.PagoLogic;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity_;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que modela la relación que va desde Reserva hasta Pago. 
 * @author jd.trujillom
 */
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class PagoReservaResource {
    
    @Inject
    private ReservaLogic reservaLogic;
    
    @Inject
    private PagoLogic pagoLogic;

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
    public PagoDetailDTO crearPago(@PathParam("idReserva")Long idReserva, PagoEntity pago){
        //En este metodo es una buena idea usar a pagologic porque se puede
        //utilizar el método para crear un pago y asociarselo a una reserva
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        
        reserva.setPago(pago);
        reservaLogic.actualizarReserva(reserva);
        
        pago.setReserva(reserva);
        pagoLogic.crearPago(pago);
        
        return new PagoDetailDTO(pago);
    }
    
}

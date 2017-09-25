/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.PagoLogic;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;


/**
 * Esta clase modela el acceso a una reserva por medio de un pago
 * @author jd.trujillom
 */
@Produces("application/json")
@Consumes("application/json")
public class ReservaPagoResource {
    @Inject
    PagoLogic pagoLogic;
    
    @GET
    public ReservaDTO darReserva(Long idPago){
        return new ReservaDTO(pagoLogic.darReserva(idPago));
    }
    
    @PUT
    public ReservaDTO actualizarReserva(Long idPago, ReservaDTO reserva){
        
        Integer nuevoEstado = reserva.getEstado();
        ReservaEntity reservaActualizada = pagoLogic.actualizarEstadoReserva(idPago, nuevoEstado);
        return new ReservaDTO(reservaActualizada);
    }
    
    
}

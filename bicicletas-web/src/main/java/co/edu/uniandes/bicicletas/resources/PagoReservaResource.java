/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.PagoDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.PagoLogic;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * Clase que modela la relación que va desde Reserva hasta Pago. 
 * @author jd.trujillom
 */
@Produces("application/json")
@Consumes("application/json")
public class PagoReservaResource {
    
    @Inject
    ReservaLogic reservaLogic;
    
    @Inject
    PagoLogic pagoLogic;

    @GET
    public PagoDTO darPago(){
        return null;
    }
    
    @POST
    public PagoDTO crearPago(PagoDTO pago){
        //En este metodo es una buena idea usar a pagologic porque se puede
        //utilizar el método para crear un pago y asociarselo a una reserva
        return null;
    }
    
}

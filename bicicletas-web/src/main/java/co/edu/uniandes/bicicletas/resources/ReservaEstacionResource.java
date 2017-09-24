/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.EstacionDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ds.chacon
 */
public class ReservaEstacionResource {
    
    @Inject ReservaLogic logic ;
    @Inject EstacionLogic Estacionlogic ;
    
    /*
    Dar la estacion de la reserva
    */
    @GET
    public EstacionDTO  darEstacion (@PathParam("idReserva") Long idReserva, Integer llegada )throws BusinessLogicException{
        
        EstacionDTO Estacion = null;
        
        if(llegada==0){
            Estacion = new EstacionDTO(logic.getReserva(idReserva).getEstacionSalida()) ;
        }else if(llegada==1){
             Estacion = new EstacionDTO(logic.getReserva(idReserva).getEstacionLlegada());
        }else{
            throw new WebApplicationException("El recurso /reservas/" + idReserva + "/Estacion/" + llegada + " no existe", 404);
        }
     return Estacion;   
    }
    
    @POST
    @Path("{idEstacion: \\d+}")
    public void  setEstacion (@PathParam("idReserva") Long idReserva, Integer llegada ,@PathParam("idEstacion") Long idEstacion )throws BusinessLogicException{
      EstacionDTO Estacion = new EstacionDTO(Estacionlogic.getEstacion(idEstacion));
      EstacionEntity Entity =Estacion.toEntity();
       if(llegada==1){
             logic.getReserva(idReserva).setEstacionLlegada(Entity);
        }else{
            throw new WebApplicationException("El recurso /reservas/" + idReserva + "/Estacion/" + llegada + " no existe", 404);
        }  
    }
    
    
}

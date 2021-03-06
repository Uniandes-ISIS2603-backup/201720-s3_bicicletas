/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.EstacionDTO;
import co.edu.uniandes.bicicletas.dtos.EstacionDetailDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *Clase que modela la relacion reserva 
 * @author ds.chacon
 */
public class ReservaEstacionResource {
    
    @Inject ReservaLogic logic ;
    @Inject EstacionLogic estacionlogic ;
    
    /**
     * Constante para representar el recurso reservas
    */
    private static final String RECURSO_RESERVA = "El recurso /reservas/";
    
    /**
     * Dar estacion
     * @param idReserva
     * @param llegada
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    public EstacionDTO  darEstacion (@PathParam("idReserva") Long idReserva, Integer llegada )throws BusinessLogicException{
        
        EstacionDTO estacion = null;
        
        if(llegada==0){
            estacion = new EstacionDTO(logic.getReserva(idReserva).getEstacionSalida()) ;
        }else if(llegada==1){
             estacion = new EstacionDTO(logic.getReserva(idReserva).getEstacionSalida());  //CAMBIADO POR ESTACION SALIDA
        }else{
            throw new WebApplicationException(RECURSO_RESERVA + idReserva + "/Estacion/" + llegada + " no existe", 404);
        }
     return estacion;   
    }
    
    
    /**
     * Dar estacion llegada
     * @param idReserva
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("/llegada")
    public EstacionDetailDTO  darEstacionLlegada (@PathParam("idReserva") Long idReserva)throws BusinessLogicException{
        
        EstacionDetailDTO estacion;
        ReservaEntity reserva = logic.getReserva(idReserva);
        EstacionEntity una;
        if(reserva!=null){
            una = estacionlogic.getEstacion(reserva.getEstacionLlegada());
            estacion = new EstacionDetailDTO(una);
        }else{
            throw new WebApplicationException(RECURSO_RESERVA + idReserva + "/Estacion/ no existe", 404);
        }
     return estacion;   
    }
    
    /**
     *Colocar la reserva en la estacion 
     * @param idReserva
     * @param llegada
     * @param idEstacion
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{idEstacion: \\d+}")
    public void  setEstacion (@PathParam("idReserva") Long idReserva, Integer llegada ,@PathParam("idEstacion") Long idEstacion )throws BusinessLogicException{
      EstacionDTO estacion = new EstacionDTO(estacionlogic.getEstacion(idEstacion));
      EstacionEntity entity =estacion.toEntity();
       if(llegada==1){
             logic.getReserva(idReserva).setEstacionSalida(entity); //CAMBIADO POR ESTACION SALIDA
        }else{
            throw new WebApplicationException(RECURSO_RESERVA + idReserva + "/Estacion/" + llegada + " no existe", 404);
        }  
    }
    
    /**
     * Asignar Estacion 
     * @param idReserva
     * @param estacion
     * @throws BusinessLogicException 
     */
    @PUT
    public void asignarEstacion (@PathParam("idReserva") Long idReserva, EstacionDTO estacion) throws BusinessLogicException{
        EstacionDTO estacion1 = new EstacionDTO(estacionlogic.getEstacion(estacion.getId()));
        ReservaEntity actua = logic.getReserva(idReserva);
        EstacionEntity entity = estacion1.toEntity();
        if(entity!=null&&actua!=null)
        {
            actua.setEstacionLlegada(entity.getId()); //CAMBIADO POR ESTACION SALIDA
            logic.actualizarReserva(actua);
                    }
        else
           throw new WebApplicationException(RECURSO_RESERVA + idReserva, 404);    
    }
    
    
}

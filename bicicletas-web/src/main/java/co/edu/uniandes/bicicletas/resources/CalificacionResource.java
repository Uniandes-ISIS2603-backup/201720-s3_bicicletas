/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.CalificacionDTO;
import co.edu.uniandes.bicicletas.ejb.CalificacionLogic;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "calificaciones"
 * URI: /api/calificaciones
 * @author Gabriel Pinto
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource 
{
    @Inject
    CalificacionLogic calLogic;
    
   @GET
   public List<CalificacionDTO> getCalificaciones() throws BusinessLogicException
   {
       return listCalificacionEntity2DTO(calLogic.getCalificaciones());
   }
    
   private List<CalificacionDTO> listCalificacionEntity2DTO(List<CalificacionEntity> entityList) {
       List<CalificacionDTO> list = new ArrayList<>();
       for (CalificacionEntity entity : entityList) {
           list.add(new CalificacionDTO(entity));
       }
       return list;
   }
   
   @GET
   @Path("{id: \\d+}")
   public CalificacionDTO getCalificacion(@PathParam("id") Long idCali) throws BusinessLogicException {
       CalificacionEntity entity = calLogic.getCalificacion(idCali);
       if (entity == null) {
           throw new WebApplicationException("El recurso /calificaciones/" + idCali + " no existe.", 404);
       }
       return new CalificacionDTO(entity);
   }
}

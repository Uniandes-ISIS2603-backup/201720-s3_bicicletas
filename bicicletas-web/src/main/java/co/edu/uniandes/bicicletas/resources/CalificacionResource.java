/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
import javax.ws.rs.POST;
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
   
    /**
     * Obtiene todas las calificaciones existentes en la base de datos
     * @return Una colección de CalificacionDTO
     */
   @GET
   public List<CalificacionDTO> getCalificaciones()
   {
       return listCalificacionEntity2DTO(calLogic.getCalificaciones());
   }
    
   /**
    * Convierte una lista de CalificacionEntity a una lista de CalificacionDTO.
    * @param entityList Lista de CalificacionEntity
    * @return Lista de CalificacionDTO
    */
   private List<CalificacionDTO> listCalificacionEntity2DTO(List<CalificacionEntity> entityList) {
       List<CalificacionDTO> list = new ArrayList<>();
       for (CalificacionEntity entity : entityList) {
           list.add(new CalificacionDTO(entity));
       }
       return list;
   }
   
   /**
    * Obtiene una calificacion 
    * @param idCali Id de la calificación que se quiere obtener
    * @return Objeto CalificacionDTO
    * @throws BusinessLogicException 
    */
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

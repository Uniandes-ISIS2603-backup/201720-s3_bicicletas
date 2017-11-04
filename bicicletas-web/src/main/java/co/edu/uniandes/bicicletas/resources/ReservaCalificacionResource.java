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
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "calificaciones"
 * URI: usuarios/{usuariosId: \\d+}/reservas/{reservasId: \\d+}/calificaciones
 * @author Gabriel Pinto
 */
@Produces("application/json")
@Consumes("application/json")
public class ReservaCalificacionResource 
{
    @Inject
    private CalificacionLogic calificacionLogic;
    
    /**
     * Crea una nueva calificación en el sistema
     * @param idReserva Id de la reserva a la cual pertenece la calificación
     * @param idEstacion 0 equivale a la calificación de la estación de origen, y 1 equivale a la calificación de la estación de llegada
     * @param dto Los datos de la calificación que va a ser creada  
     * @return La calificación creada
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{id: \\d+}")
    public CalificacionDTO createCalificacion(@PathParam("idReserva") Long idReserva, @PathParam("id") Long idEstacion, CalificacionDTO dto) throws BusinessLogicException
    {
        if(dto.getNota() == null)
        {
            throw new BusinessLogicException("Debe ingresar una nota para calificar la estación");
        }
        else if( 0 > dto.getNota() || 6 <= dto.getNota() )
        {
            throw new BusinessLogicException("La nota seleccionada no es valida");
        }
        
        if(!(idEstacion == 0 || idEstacion == 1))
        {
            throw new BusinessLogicException("No se escoge correctamente la estación a calificar");
        }
        
        CalificacionEntity caliEn = calificacionLogic.createCalificacion(idEstacion, idReserva, dto.toEntity());
        if(caliEn == null )
        {
            throw new BusinessLogicException("Ya existe una calificación para la estación indicada dentro de la reserva");
        }
        
        return new CalificacionDTO(caliEn);
    }
    
    /**
     * Obtiene una de las 2 calificaciones que hay en una reserva
     * @param idReserva Id de la reserva de la cual se va a obtener la calificación
     * @param cali 0 equivale a la calificación de la estación de origen, y 1 equivale a la calificación de la estación de llegada
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacionReserva(@PathParam("idReserva") Long idReserva, @PathParam("id") Long cali) throws BusinessLogicException
    {
        //Boolean que representa si la estación es de origen (true) o llegada (false)
        boolean origen = false;
        
        if(!(cali == 0 || cali == 1))
        {
             throw new BusinessLogicException("No se escoge bien a que estación pertenece la calificación");
        }
        else if(cali == 0)
        {
            origen = true;
        }
        
        CalificacionEntity entity = calificacionLogic.getCalificionReserva(idReserva, origen);
        
        if (entity == null) 
        {   
            String esta = darNombreEstacion(origen);
            throw new WebApplicationException("Aún no hay una calificación para la " + esta, 404);
        }
       
        return new CalificacionDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("idReserva") Long idReserva, @PathParam("id") Long idCali, CalificacionDTO dto) throws BusinessLogicException 
    {
        if(dto.getIdCali() != null)
        {
            throw new WebApplicationException("No es posible actualizar el id de una calificación", 404);
        }
        
        boolean origen = false;
        if(!(idCali == 0 || idCali == 1))
        {
             throw new BusinessLogicException("No se escoge bien a que estación pertenece la calificación");
        }
        else if(idCali == 0)
        {
            origen = true;
        }
        
        CalificacionEntity entity = calificacionLogic.getCalificionReserva(idReserva, origen);
        
        if (entity == null) 
        {
            String esta = darNombreEstacion(origen);
            
            throw new WebApplicationException("No puede actualizar la calificación para la " + esta + " porque no existe", 404);
        
        }
       
        entity.setNota(dto.getNota());
        
        if(dto.getDescripcion() != null)
        {
            entity.setDescripcion(dto.getDescripcion());
        }
        
        CalificacionEntity actualizada = calificacionLogic.updateCalificacion(entity);
        
        return new CalificacionDTO(actualizada);

    }
    
    private String darNombreEstacion(boolean cali)
    {
        String estacion = "estación de ";
        if(cali)
        {
            estacion = estacion + "salida";
        }
        else
        {
           estacion = estacion + "llegada"; 
        }        
        return estacion;
        
    }
    
}

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
    
    @POST
    public CalificacionDTO createCalificacion(@PathParam("idReserva") Long idReserva, CalificacionDTO dto) throws BusinessLogicException
    {
         return new CalificacionDTO(calificacionLogic.createCalificacion(idReserva, dto.toEntity()));
    }
    
    @GET
    public CalificacionDTO getCalificacionReserva(@PathParam("idReserva") Long idReserva, Integer cali) throws BusinessLogicException
    {
        CalificacionEntity entity = calificacionLogic.getCalificionReserva(idReserva, cali);
        if (entity == null) 
        {   
            String esta = darNombreEstacion(cali);
            throw new WebApplicationException("El recurso /reservas/" + idReserva + "/calificaciones/" + esta + " no existe", 404);
        }
       
        return new CalificacionDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("idReserva") Long idReserva, @PathParam("id") Integer idCali, CalificacionDTO dto) throws BusinessLogicException 
    {
        CalificacionEntity entity = calificacionLogic.getCalificionReserva(idReserva, idCali);
        
        if (entity == null ) 
        {
            String esta = darNombreEstacion(idCali);
            
            throw new WebApplicationException("El recurso /reservas/" + idReserva + "/calificaciones/" + esta + " no existe", 404);
        
        }
       
        dto.setIdCali(entity.getId());
        
        CalificacionEntity actualizada = calificacionLogic.updateCalificacion(idReserva, idCali, dto.toEntity());
        
        return new CalificacionDTO(actualizada);

    }
    
    private String darNombreEstacion(Integer idCali)
    {
        String estacion = "estacionDe";
        if(idCali == 1 || idCali == 0)
            {
                if(idCali == 0)
                {
                   estacion = estacion + "Origen";
                }
                else if(idCali == 1)
                {
                    estacion = estacion + "Llegada";
                }   
            }
        else
        {
            throw new WebApplicationException("Está consultando mal las calificaciones asociadas a una reserva", 404);
        }
       
        
        return estacion;
        
    }
    
}

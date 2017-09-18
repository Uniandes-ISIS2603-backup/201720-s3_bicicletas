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
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "calificaciones"
 * URI: estaciones/{idEstacion: \\d+}/calificaciones
 * @author Gabriel Pinto
 */
@Produces("application/json")
@Consumes("application/json")
public class CalificacionEstacionResource 
{
    @Inject
    private CalificacionLogic calificacionLogic;
    
    @GET
    public List<CalificacionDTO> getCalificaciones(@PathParam("idEstacion") Long idEstacion) throws BusinessLogicException
    {
        return listEntity2DTO(calificacionLogic.getCalificacionesEstacion(idEstacion));
    }
    
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("idEstacion") Long idEstacion, @PathParam("id") Long idCali) throws BusinessLogicException
    {
        CalificacionEntity caliEntity = calificacionLogic.getCalificacionEstacion(idEstacion, idCali);
        if(caliEntity == null)
        {
            throw new WebApplicationException("El recurso /estaciones/" + idEstacion + "/calificaciones/" + idCali + " no existe", 404);
        }
        return new CalificacionDTO(caliEntity);
    }
    
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> listaEntiCali)
    {
        List<CalificacionDTO> lista = new ArrayList<>();
        for(CalificacionEntity caliEntity : listaEntiCali)
        {
            lista.add(new CalificacionDTO(caliEntity));
        }
        return lista;
    }
}

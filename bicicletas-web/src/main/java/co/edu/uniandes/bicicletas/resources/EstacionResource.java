/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.EstacionDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ka.babativa
 */
@Path("/estaciones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EstacionResource {
    @Inject
    EstacionLogic logica;
    
    private List<EstacionDTO> listEntity2DTO(List<EstacionEntity> entityList) {
        List<EstacionDTO> lista = new ArrayList<>();
        for(EstacionEntity entidad : entityList){
            lista.add(new EstacionDTO(entidad));
    }
        return lista;
        
}
    
    @GET
    public List<EstacionDTO> obtenerEstaciones(){
        return listEntity2DTO(logica.getEstaciones());
    }
    
    @GET
    @Path("{id: \\d+}")
    public EstacionDTO getEstacion(@PathParam("id") Long id) {
        return new EstacionDTO(logica.getEstacion(id));
    }
    
    @POST
    public EstacionDTO crearEstacion(EstacionDTO dto) throws BusinessLogicException {
        return new EstacionDTO(logica.crearEstacion(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EstacionDTO actualizarEstacion(@PathParam("id") Long id, EstacionDTO dto) {
        EstacionEntity entity = dto.toEntity();
        entity.setId(id);
        return new EstacionDTO(logica.actualizarEstacion(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void borrarEstacion(@PathParam("id") Long id) {
        logica.deleteEstacion(id);
    }
    
    @Path("{idEstacion: \\d+}/calificaciones")
    public Class<EstacionCalificacionResource> getCalificacionEstacionResource(@PathParam("idEstacion") Long idEstacion) {
        EstacionEntity entity = logica.getEstacion(idEstacion);
        if (entity == null) {
            throw new WebApplicationException("El recurso /estaciones/" + idEstacion + "/calificaciones no existe.", 404);
        }
        return EstacionCalificacionResource.class;
    }
    
    //CODIGO AÑADIDO POR CM.ALBA10 PARA RELACIONAR CON DIRECCION
    @Path("{estacionesId: \\d+}/direcciones")
	    public Class<EstacionDireccionResource> getEstacionDireccionResource(@PathParam("estacionesID") Long estacionesId) {
	        EstacionEntity entity = logica.getEstacion(estacionesId);
	        if (entity == null) {
	            throw new WebApplicationException("La estacion no existe", 404);
	        }
	        return EstacionDireccionResource.class;
	    }
    

}

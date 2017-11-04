
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */
	package co.edu.uniandes.bicicletas.resources;

	import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
	import co.edu.uniandes.bicicletas.dtos.EstacionDTO;
	import co.edu.uniandes.bicicletas.dtos.EstacionDTO;
import co.edu.uniandes.bicicletas.dtos.EstacionDetailDTO;
	import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
	import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
	import java.util.ArrayList;
	import java.util.List;
	import javax.ejb.Stateless;
	import javax.enterprise.context.RequestScoped;
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
	@RequestScoped
	public class EstacionResource {

	    @Inject
	    private EstacionLogic estacionLogic;

	    /**
	     * Convierte una lista de EstacionEntity a una lista de EstacionDTO.
	     *
	     * @param entityList Lista de EstacionEntity a convertir.
	     * @return Lista de EstacionDTO convertida.
	     * 
	     */
	    private List<EstacionDTO> listEntity2DTO(List<EstacionEntity> entityList) {
	        List<EstacionDTO> list = new ArrayList<>();
	        for (EstacionEntity entity : entityList) {
	            list.add(new EstacionDTO(entity));
	        }
	        return list;
	    }
            
            private List<EstacionDetailDTO> listEntityDetail2DTO(List<EstacionEntity> entityList) {
	        List<EstacionDetailDTO> list = new ArrayList<>();
	        for (EstacionEntity entity : entityList) {
	            list.add(new EstacionDetailDTO(entity));
	        }
	        return list;
	    }

	    /**
	     * Obtiene la lista de los registros de Estacion
	     *
	     * @return Colección de objetos de EstacionDTO
	     * 
	     */
	    @GET
	    public List<EstacionDetailDTO> getEstaciones() {
	        return listEntityDetail2DTO(estacionLogic.getEstaciones());
	    }

	    /**
	     * Obtiene los datos de una instancia de Estacion a partir de su ID
	     *
	     * @param id Identificador de la instancia a consultar
	     * @return Instancia de EstacionDTO con los datos del Estacion consultado
	     * 
	     */
	    @GET
	    @Path("{id: \\d+}")
	    public EstacionDetailDTO getEstacion(@PathParam("id") Long id) {
	        EstacionEntity entity = estacionLogic.getEstacion(id);
	        if (entity == null) {
	            throw new WebApplicationException("El estacion no existe", 404);
	        }
	        return new EstacionDetailDTO(entity);
	    }

	    /**
	     * Se encarga de crear un Estacion en la base de datos
	     *
	     * @param dto Objeto de EstacionDTO con los datos nuevos
	     * @return Objeto de EstacionDTOcon los datos nuevos y su ID
	     * 
	     */
	    @POST
	    public EstacionDTO createEstacion(EstacionDTO dto) {
                if(dto.getNombre()!=null){
                   return new EstacionDTO(estacionLogic.crearEstacion(dto.toEntity())); 
                }
	        else
                    throw new WebApplicationException("Debe ser indicado un nombre para la estación", 300);
	    }

	    /**
	     * Actualiza la información de una instancia de Estacion
	     *
	     * @param id Identificador de la instancia de Estacion a modificar
	     * @param dto Instancia de EstacionDTO con los nuevos datos
	     * @return Instancia de EstacionDTO con los datos actualizados
	     * 
	     */
	    @PUT
	    @Path("{id: \\d+}")
	    public EstacionDTO updateEstacion(@PathParam("id") Long id, EstacionDTO dto) {
	        EstacionEntity entity = dto.toEntity();
	        entity.setId(id);
	        EstacionEntity oldEntity = estacionLogic.getEstacion(id);
	        if (oldEntity == null) {
	            throw new WebApplicationException("El estacion no existe", 404);
	        }
	        entity.setDirecciones(oldEntity.getDirecciones());
	        return new EstacionDTO(estacionLogic.actualizarEstacion(entity));
	    }

	    /**
	     * Elimina una instancia de Estacion de la base de datos
	     *
	     * @param id Identificador de la instancia a eliminar
	     * 
	     */
	    @DELETE
            @Path("{id: \\d+}")
            public void borrarEstacion(@PathParam("id") Long id) {
                estacionLogic.deleteEstacion(id);
            }

            
	    @Path("{estacionesId: \\d+}/direcciones")
	    public Class<EstacionDireccionResource> getEstacionDireccionesResource(@PathParam("estacionesId") Long estacionesId) {
	        EstacionEntity entity = estacionLogic.getEstacion(estacionesId);
	        if (entity == null) {
	            throw new WebApplicationException("El estacion no existe", 404);
	        }
	        return EstacionDireccionResource.class;
	    }
	    
	    @Path("{idEstacion: \\d+}/calificaciones")
	    public Class<EstacionCalificacionResource> getCalificacionEstacionResource(@PathParam("idEstacion") Long idEstacion) {
	        EstacionEntity entity = estacionLogic.getEstacion(idEstacion);
	        if (entity == null) {
	            throw new WebApplicationException("El recurso /estaciones/" + idEstacion + "/calificaciones no existe.", 404);
	        }
	        return EstacionCalificacionResource.class;
	    }
	    
	    //CODIGO AÑADIDO POR CM.ALBA10 PARA RELACIONAR CON DIRECCION
//	    @Path("{estacionesId: \\d+}/direcciones")
//		    public Class<EstacionDireccionResource> getEstacionDireccionResource(@PathParam("estacionesID") Long estacionesId) {
//		        EstacionEntity entity = estacionLogic.getEstacion(estacionesId);
//		        if (entity == null) {
//		            throw new WebApplicationException("La estacion no existe", 404);
//		        }
//		        return EstacionDireccionResource.class;
//		    }
	    

            
            
         /**
     *
     * @param idUsuario
     * @return
     */
    @Path("{idEstacion: \\d+}/bicicletas")
            public Class<EstacionBicicletaResource> getUsuarioReservaResource(@PathParam("idEstacion") Long idEstacion) {
            EstacionEntity entity = estacionLogic.getEstacion(idEstacion);
            if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idEstacion + "/reservas/ no existe.", 404);
            }
            return EstacionBicicletaResource.class;
            }
            
    @Path("{idEstacion: \\d+}/accesorios")
    public Class<EstacionAccesorioResource> getAccesorio(@PathParam("idEstacion") Long idEstacion) {
        EstacionEntity entity = estacionLogic.getEstacion(idEstacion);
        if (entity == null) {
            throw new WebApplicationException("El recurso /estaciones/" + idEstacion + "/accesorios no existe.", 404);
        }
        return EstacionAccesorioResource.class;
    }
    
    @Path("{idEstacion: \\d+}/reservas")
	    public Class<EstacionReservaResource> getReservasEstacionResource(@PathParam("idEstacion") Long idEstacion) {
	        EstacionEntity entity = estacionLogic.getEstacion(idEstacion);
	        if (entity == null) {
	            throw new WebApplicationException("El recurso /estaciones/" + idEstacion + "/calificaciones no existe.", 404);
	        }
	        return EstacionReservaResource.class;
	    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.bicicletas.ejb.UsuarioLogic;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
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
import javax.ws.rs.WebApplicationException;

	/**
	 * Clase que implementa el recurso REST correspondiente a "usuarios".
	 *
	 * @author cm.alba10
	 */
	@Path("/usuarios")
	@Produces("application/json")
        @Consumes("application/json")
        @Stateless
	public class UsuarioResource {
            
            //----------------------------------------------------------
            //Atributos
            //----------------------------------------------------------
            /**
             * inyeccion de la logica de usuario
             */
	    private UsuarioLogic usuarioLogic;

 
            public UsuarioResource() {
                //constructor para la parte web
            }

            @Inject public UsuarioResource(UsuarioLogic usuarioLogic) {
                this.usuarioLogic = usuarioLogic;
            }
            
            private final String noExiste = "El usuario no existe";
            
            //----------------------------------------------------------
            //Metodos
            //----------------------------------------------------------
	    /**
             * Esta es la documentación 
             * del metodo listentitytodto.
             * Este metodo ha sido creado por
             * Carlos Alba
             * Este metodo se encarga de
	     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
	     *
	     * @param entityList Lista de UsuarioEntity a convertir.
	     * @return Lista de UsuarioDetailDTO convertida.
	     * 
	     */
	    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
	        List<UsuarioDetailDTO> list = new ArrayList<>();
	        for (UsuarioEntity entity : entityList) {
	            list.add(new UsuarioDetailDTO(entity));
	        }
	        return list;
	    }

	    /**
             * Esta es la documentación 
             * del metodo getusuarios.
             * Este metodo ha sido creado por
             * Carlos Alba
             * Este metodo se encarga de
	     * Obtiene la lista de los registros de Usuario
	     *
	     * @return Colección de objetos de UsuarioDetailDTO
	     * 
	     */
	    @GET
	    public List<UsuarioDetailDTO> getUsuarios() {
	        return listEntity2DTO(usuarioLogic.getUsuarios());
	    }

	    /**
             * Esta es la documentación 
             * del metodo getusuario.
             * Este metodo ha sido creado por
             * Carlos Alba
             * Este metodo se encarga de
	     * Obtiene los datos de una instancia de Usuario a partir de su ID
	     *
	     * @param id Identificador de la instancia a consultar
	     * @return Instancia de UsuarioDetailDTO con los datos del Usuario consultado
	     * 
	     */
	    @GET
	    @Path("{id: \\d+}")
	    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) {
	        UsuarioEntity entity = usuarioLogic.getUsuario(id);
	        if (entity == null) {
	            throw new WebApplicationException(noExiste, 404);
	        }
	        return new UsuarioDetailDTO(entity);
	    }

	    /**
	     * Se encarga de crear un Usuario en la base de datos
	     *
	     * @param dto Objeto de UsuarioDetailDTO con los datos nuevos
	     * @return Objeto de UsuarioDetailDTOcon los datos nuevos y su ID
             * @throws co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException
	     * 
	     */
	    @POST
	    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO dto) throws BusinessLogicException {
	        return new UsuarioDetailDTO(usuarioLogic.createUsuario(dto.toEntity()));
	    }

	    /**
             * Esta es la documentación 
             * del metodo updateusuario.
             * Este metodo ha sido creado por
             * Carlos Alba
             * Este metodo se encarga de
	     * Actualiza la información de una instancia de Usuario
	     *
	     * @param id Identificador de la instancia de Usuario a modificar
	     * @param dto Instancia de UsuarioDetailDTO con los nuevos datos
	     * @return Instancia de UsuarioDetailDTO con los datos actualizados
	     * 
	     */
	    @PUT
	    @Path("{id: \\d+}")
	    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO dto) {
	        UsuarioEntity entity = dto.toEntity();
	        entity.setDocumentoUsuario(id);
	        UsuarioEntity oldEntity = usuarioLogic.getUsuario(id);
	        if (oldEntity == null) {
	            throw new WebApplicationException(noExiste, 404);
	        }
	        entity.setDirecciones(oldEntity.getDirecciones());
	        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(id, entity));
	    }

	    /**
             * * Esta es la documentación 
             * del metodo deleteusuario.
             * Este metodo ha sido creado por
             * Carlos Alba
             * Este metodo se encarga de
	     * Elimina una instancia de Usuario de la base de datos
	     *
	     * @param id Identificador de la instancia a eliminar
	     * 
	     */
	    @DELETE
	    @Path("{id: \\d+}")
	    public void deleteUsuario(@PathParam("id") Long id) {
	        UsuarioEntity entity = usuarioLogic.getUsuario(id);
	        if (entity == null) {
	            throw new WebApplicationException(noExiste, 404);
	        }
	        usuarioLogic.deleteUsuario(id);
	    }

    /**
     * Esta es la documentación 
     * del metodo getdireccionesusuario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @param usuariosId
     * @return
     */
    @Path("{usuariosId: \\d+}/direcciones")
	    public Class<UsuarioDireccionResource> getUsuarioDireccionesResource(@PathParam("usuariosId") Long usuariosId) {
	        UsuarioEntity entity = usuarioLogic.getUsuario(usuariosId);
	        if (entity == null) {
	            throw new WebApplicationException(noExiste, 404);
	        }
	        return UsuarioDireccionResource.class;
	    }

    /**
     ** Esta es la documentación 
     * del metodo getpuntosusuario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @param idUsuario
     * @return
     */
    @Path("{idUsuario: \\d+}/puntos/")
            public Class<UsuarioPuntoResource> getUsuarioPuntoResource(@PathParam("idUsuario") Long idUsuario) {
            UsuarioEntity entity = usuarioLogic.getUsuario(idUsuario);
            if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/puntos/ no existe.", 404);
            }
            return UsuarioPuntoResource.class;
            }
            
    /**
     * Esta es la documentación 
     * del metodo getreservasusuario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @param idUsuario
     * @return
     */
    @Path("{idUsuario: \\d+}/reservas/")
            public Class<UsuarioReservaResource> getUsuarioReservaResource(@PathParam("idUsuario") Long idUsuario) {
            UsuarioEntity entity = usuarioLogic.getUsuario(idUsuario);
            if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/reservas/ no existe.", 404);
            }
            return UsuarioReservaResource.class;
            }
	}

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
import co.edu.uniandes.bicicletas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.WebApplicationException;

/**
 ** Clase que implementa el recurso REST correspondiente a "usuarios".
 *
 * @author cm.alba10
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    
	 @Inject
	    UsuarioLogic usuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

	    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

	    /**
	     * POST http://localhost:8080/bicicletas-web/api/usuarios Ejemplo
	     * json: { "name":"Norma" }
	     *
	     * @param usuario correponde a la representación java del objeto json
	     * enviado en el llamado.
	     * @return Devuelve el objeto json de entrada que contiene el id creado por
	     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
	     * "usuarioDetailDTO", "id": 1, "name": "Norma" }
	     * @throws BusinessLogicException
	     */
	    @POST
	    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws BusinessLogicException {
	        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
	        UsuarioEntity usuarioEntity = usuario.toEntity();
	        // Invoca la lógica para crear la usuario nueva
	        UsuarioEntity nuevoUsuario = usuarioLogic.createUsuario(usuarioEntity);
	        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
	        return new UsuarioDetailDTO(nuevoUsuario);
	    }

	    /**
	     * GET para todas las usuarios.
	     * http://localhost:8080/bicicletas-web/api/usuarios
	     *
	     * @return la lista de todas las usuarios en objetos json DTO.
	     * @throws BusinessLogicException
	     */
	    @GET
	    public List<UsuarioDetailDTO> getUsuarios() throws BusinessLogicException {
	        return listEntity2DetailDTO(usuarioLogic.getUsuarios());
	    }

	    /**
	     * GET para una usuario
	     * http://localhost:8080/bicicletas-web/api/usuarios/1
	     *
	     * @param id corresponde al id de la usuario buscada.
	     * @return La usuario encontrada. Ejemplo: { "type": "usuarioDetailDTO",
	     * "id": 1, "name": "Norma" }
	     * @throws BusinessLogicException
	     *
	     * En caso de no existir el id de la usuario buscada se retorna un 404 con
	     * el mensaje.
	     */
	    @GET
	    @Path("{id: \\d+}")
	    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
	        UsuarioEntity entity = usuarioLogic.getUsuario(id);
	        if (entity == null) {
	            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
	        }
	        return new UsuarioDetailDTO(usuarioLogic.getUsuario(id));
	    }

	    /**
	     * PUT http://localhost:8080/bicicletas-web/api/usuarios/1 Ejemplo
	     * json { "id": 1, "name": "cambio de nombre" }
	     *
	     * @param id corresponde a la usuario a actualizar.
	     * @param usuario corresponde a al objeto con los cambios que se van a
	     * realizar.
	     * @return La usuario actualizada.
	     * @throws BusinessLogicException
	     *
	     * En caso de no existir el id de la usuario a actualizar se retorna un
	     * 404 con el mensaje.
	     */
	    @PUT
	    @Path("{id: \\d+}")
	    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario) throws BusinessLogicException {
	        usuario.setId(id);
	        UsuarioEntity entity = usuarioLogic.getUsuario(id);
	        if (entity == null) {
	            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
	        }
	        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(id, usuario.toEntity()));
	    }

	    /**
	     * DELETE http://localhost:8080/bicicletas-web/api/usuarios/1
	     *
	     * @param id corresponde a la usuario a borrar.
	     * @throws BusinessLogicException
	     *
	     * En caso de no existir el id de la usuario a actualizar se retorna un
	     * 404 con el mensaje.
	     *
	     */
	    @DELETE
	    @Path("{id: \\d+}")
	    public void deleteUsuario(@PathParam("id") Long id) throws BusinessLogicException {
	        LOGGER.log(Level.INFO, "Inicia proceso de borrar una usuario con id {0}", id);
	        UsuarioEntity entity = usuarioLogic.getUsuario(id);
	        if (entity == null) {
	            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
	        }
	        usuarioLogic.deleteUsuario(id);
	    }

	    /**
	     *
	     * lista de entidades a DTO.
	     *
	     * Este método convierte una lista de objetos UsuarioEntity a una lista de
	     * objetos UsuarioDetailDTO (json)
	     *
	     * @param entityList corresponde a la lista de usuarios de tipo Entity
	     * que vamos a convertir a DTO.
	     * @return la lista de usuarios en forma DTO (json)
	     */
	    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
	        List<UsuarioDetailDTO> list = new ArrayList<>();
	        for (UsuarioEntity entity : entityList) {
	            list.add(new UsuarioDetailDTO(entity));
	        }
	        return list;
	    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.DireccionDetailDTO;
import co.edu.uniandes.bicicletas.ejb.DireccionLogic;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.persistence.DireccionPersistence;
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
 * Clase que implementa el recurso REST correspondiente a "direcciones".
 *
 * @author cm.alba10
 */
@Path("direcciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DireccionResource {

    @Inject
    DireccionLogic direccionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(DireccionPersistence.class.getName());

    /**
     * POST http://localhost:8080/bicicletas-web/api/direcciones Ejemplo
     * json: { "name":"Avenida caracas" }
     *
     * @param direccion correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "direccionDetailDTO", "id": 1, "name": "Avenida caracas" }
     * @throws BusinessLogicException
     */
    @POST
    public DireccionDetailDTO createDireccion(DireccionDetailDTO direccion) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        DireccionEntity direccionEntity = direccion.toEntity();
        // Invoca la lógica para crear la direccion nueva
       DireccionEntity nuevaDireccion = direccionLogic.createDireccion(direccionEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new DireccionDetailDTO(nuevaDireccion);
    }

    /**
     * GET para todas las direcciones.
     * http://localhost:8080/bicicletas-web/api/direcciones
     *
     * @return la lista de todas las direcciones en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<DireccionDetailDTO> getDirecciones() throws BusinessLogicException {
        return listEntity2DetailDTO(direccionLogic.getDirecciones());
    }

    /**
     * GET para una direccion
     * http://localhost:8080/bicicletas-web/api/direcciones/1
     *
     * @param id corresponde al id de la direccion buscada.
     * @return La direccion encontrada. Ejemplo: { "type": "direccionDetailDTO",
     * "id": 1, "name": "Avenida caracas" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la direccion buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public DireccionDetailDTO getDireccion(@PathParam("id") Long id) throws BusinessLogicException {
        DireccionEntity entity = direccionLogic.getDireccion(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /direciones/" + id + " no existe.", 404);
        }
        return new DireccionDetailDTO(direccionLogic.getDireccion(id));
    }

    /**
     * PUT http://localhost:8080/bicicletas-web/api/direccioness/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la direccion a actualizar.
     * @param direccion corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La direccion actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la direccion a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public DireccionDetailDTO updateDireccion(@PathParam("id") Long id, DireccionDetailDTO direccion) throws BusinessLogicException {
        direccion.setId(id);
        DireccionEntity entity = direccionLogic.getDireccion(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /direcciones/" + id + " no existe.", 404);
        }
        return new DireccionDetailDTO(direccionLogic.updateDireccion(id, direccion.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/bicicletas-web/api/direcciones/1
     *
     * @param id corresponde a la direccion a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la direccion a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDireccion(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una direccion con id {0}", id);
        DireccionEntity entity = direccionLogic.getDireccion(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /direcciones/" + id + " no existe.", 404);
        }
        direccionLogic.deleteDireccion(id);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos DireccionEntity a una lista de
     * objetos DireccionDetailDTO (json)
     *
     * @param entityList corresponde a la lista de direcciones de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de direcciones en forma DTO (json)
     */
    private List<DireccionDetailDTO> listEntity2DetailDTO(List<DireccionEntity> entityList) {
        List<DireccionDetailDTO> list = new ArrayList<>();
        for (DireccionEntity entity : entityList) {
            list.add(new DireccionDetailDTO(entity));
        }
        return list;
    }
}

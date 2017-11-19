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
import java.util.ArrayList;
import java.util.List;
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
    DireccionLogic direccionLogic;
    private String aviso = "El recurso /direcciones/";
    /**
     *
     * @return
     * @throws BusinessLogicException
     */
    @GET
    public List<DireccionDetailDTO> getDirecciones() throws BusinessLogicException {
        return listDireccionEntity2DetailDTO(direccionLogic.getDirecciones());
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public DireccionDetailDTO getDireccion(@PathParam("id") Long id) throws BusinessLogicException {
        DireccionEntity entity = direccionLogic.getDireccion(id);
        if (entity == null) {
            throw new WebApplicationException(aviso + id + " no existe.", 404);
        }
        return new DireccionDetailDTO(entity);
    }

    /**
     * @param direccion
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public DireccionDetailDTO createDireccion(DireccionDetailDTO direccion) throws BusinessLogicException {        
         return new DireccionDetailDTO(direccionLogic.createDireccion(direccion.toEntity()));
    }

    /**
     *
     * Ejemplo: { "description": "Las habilidades gerenciales en arquitectos de
     * software.", "editorial": { "id": 200, "name": "Oveja Negra 2" }, "image":
     * "https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg",
     * "isbn": "930330149-8", "name": "La comunicación en el software",
     * "publishingdate": "2017-08-20T00:00:00-05:00" }
     *
     * @param id
     * @param direccion
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public DireccionDetailDTO updateDireccion(@PathParam("id") Long id, DireccionDetailDTO direccion) throws BusinessLogicException {
        direccion.setId(id);
        DireccionEntity entity = direccionLogic.getDireccion(id);
        if (entity == null) {
            throw new WebApplicationException(aviso + id + " no existe.", 404);
        }
        return new DireccionDetailDTO(direccionLogic.updateDireccion(id, direccion.toEntity()));
    }

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{direccionesId: \\d+}")
    public void deleteDireccion(@PathParam("direccionesId") Long id) throws BusinessLogicException {
        DireccionEntity entity = direccionLogic.getDireccion(id);
        if (entity == null) {
            throw new WebApplicationException(aviso + id + " no existe.", 404);
        }
        direccionLogic.deleteDireccion(id);
    }

    private List<DireccionDetailDTO> listDireccionEntity2DetailDTO(List<DireccionEntity> entityList) {
        List<DireccionDetailDTO> list = new ArrayList<>();
        for (DireccionEntity entity : entityList) {
            list.add(new DireccionDetailDTO(entity));
        }
        return list;
    }
    
    
}

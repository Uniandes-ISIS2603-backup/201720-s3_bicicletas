/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.AccesorioBicicletaDTO;
import co.edu.uniandes.bicicletas.ejb.AccesorioBicicletaLogic;
import co.edu.uniandes.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.bicicletas.entities.AccesorioBicicletaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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

/**
 *
 * @author js.torres1
 */
@Path("bicicletas/AccesorioBicicleta")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class AccesorioBicicletaBicicletaResource {
    @Inject
    AccesorioBicicletaLogic bicicletasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    @Inject
    BicicletaLogic superBiciLogic;
    private static final Logger LOGGER = Logger.getLogger(AccesorioBicicletaBicicletaResource.class.getName());

    /**
     * POST http://localhost:8080/bicicletas-web/api/bicicletas/AccesorioBicicleta
     *
     * @param AccesorioBicicleta correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BicicletaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public AccesorioBicicletaDTO createAccesorioBici(AccesorioBicicletaDTO Bicicleta) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AccesorioBicicletaEntity BicicletaEntity = Bicicleta.toEntity();
        // Invoca la lógica para crear la Bicicleta nueva
        AccesorioBicicletaEntity nuevoBicicleta = bicicletasLogic.createAccesorioBici(BicicletaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new AccesorioBicicletaDTO(nuevoBicicleta);
    }

    /**
     * GET para todas las AccesorioBicicleta.
     * http://localhost:8080/bicicletas-web/api/bicicletas/AccesorioBicicleta
     *
     * @return la lista de todas las Bicicletaes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<AccesorioBicicletaDTO> getAccesoriosBici() throws BusinessLogicException {
        return listEntity2DetailDTO(bicicletasLogic.getAccesorioBici());
    }

   
    /**
     * PUT http://localhost:8080/bicicletas-web/api/bicicletas/1/AccesorioBicicleta/2 Ejemplo
     * json { "id": 2, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Bicicleta a actualizar.
     * @param bicicletas corresponde  al objeto con los cambios que se van a
     * realizar.
     * @return La Bicicleta actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Bicicleta a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public AccesorioBicicletaDTO updateBicicleta(@PathParam("id") Long id, AccesorioBicicletaDTO bicicletas) throws BusinessLogicException, UnsupportedOperationException {
          
          AccesorioBicicletaEntity entity =bicicletas.toEntity();
          bicicletasLogic.actualizarAccesorioBici(entity);
          return new AccesorioBicicletaDTO(entity);
      
    }

    /**
     * DELETE http://localhost:8080/bicicletas-web/api/bicicletass/{id}/AccesorioBicicleta/{idAccesorioBici}
     *
     * @param id corresponde a la Bicicleta a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Bicicleta a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAccesorioBici(@PathParam("id") Long id) throws BusinessLogicException {
         bicicletasLogic.deleteAccesorioBicicleta(id);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BicicletaEntity a una lista de
     * objetos BicicletaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Bicicletaes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Bicicletaes en forma DTO (json)
     */
    private List<AccesorioBicicletaDTO> listEntity2DetailDTO(List<AccesorioBicicletaEntity> entityList) {
        List<AccesorioBicicletaDTO> list = new ArrayList<>();
        for (AccesorioBicicletaEntity entity : entityList) {
            list.add(new AccesorioBicicletaDTO(entity));
        }
        return list;
    }
}

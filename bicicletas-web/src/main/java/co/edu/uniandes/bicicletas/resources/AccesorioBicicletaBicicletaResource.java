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
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author js.torres1
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class AccesorioBicicletaBicicletaResource {
    @Inject
    AccesorioBicicletaLogic bicicletasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    @Inject
    BicicletaLogic superBiciLogic;
    private static final Logger LOGGER = Logger.getLogger(AccesorioBicicletaBicicletaResource.class.getName());


   /**
     * POST http://localhost:8080/bicicletas-web/api/bicicletas/{id}/accesorioBicicleta
     *
     * @param id Long del id de la bicicleta.
     * @param accesorio DTO del accesorioBicicleta.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BicicletaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public AccesorioBicicletaDTO createAccesorioBici(@PathParam("idBicicleta")Long id,AccesorioBicicletaDTO accesorio) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AccesorioBicicletaEntity accBici = accesorio.toEntity();
        // Invoca la lógica para crear la Bicicleta nueva
        AccesorioBicicletaEntity nuevoBicicleta = superBiciLogic.createAccesorioBici(id, accBici);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new AccesorioBicicletaDTO(nuevoBicicleta);
    }
    /**
     * PUT http://localhost:8080/bicicletas-web/api/bicicletas/1/AccesorioBicicleta/2 Ejemplo
     * json { "id": 2, "atirbuto1": "Valor nuevo" }
     *
     * @param idBici corresponde a la Bicicleta a actualizar.
     * @param bicicletas corresponde  al accesorioBicicleta con los cambios que se van a
     * realizar.
     * @return La Bicicleta actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Bicicleta a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    public AccesorioBicicletaDTO updateAccesorioBicicleta(@PathParam("idBicicleta") Long idBici, AccesorioBicicletaDTO bicicletas) throws BusinessLogicException {
          
          AccesorioBicicletaEntity entity =bicicletas.toEntity();
          superBiciLogic.updateAcc(idBici, entity);
          return new AccesorioBicicletaDTO(entity);
      
    }

    /**
     * DELETE http://localhost:8080/bicicletas-web/api/bicicletas/{idBicicleta}/AccesorioBicicleta/{idAccesorioBici}
     *
     * @param id corresponde a la Bicicleta a borrar el accesorio.
     * @param idAcc corresponde al accesorio de la bicicleta a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Bicicleta a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{idAccesorioBici: \\d+}")
    public void deleteAccesorioBici(@PathParam("idBicicleta") Long id,@PathParam("idAccesorioBici") Long idAcc) throws BusinessLogicException {
         superBiciLogic.deleteAccesorioBicicleta(id, idAcc);
    }
    
    /**
<<<<<<< HEAD
     * Metodo que retorna un accesorio de una bicicleta dada una bicicleta a traves de HTTP GET
     * @param idBici ID de la bicicleta a consultar
     * @param idAcc ID Del accesorio a consultar
     * @return AccesorioBicicletaDTO dado por los parametros.
=======
     * GET http://localhost:8080/bicicletas-web/api/bicicletas/{idBicicleta}/AccesorioBicicleta/{idAccesorioBici}
     * @param idBici id de la bicicleta.
     * @param idAcc id del accesorioBicicleta.
     * @return 
>>>>>>> 41e5a095fa775f3151e071f6764d021ca18b26fd
     */
    @GET
    @Path("{idAccesorioBici: \\d+}")
    public AccesorioBicicletaDTO getAccesorioBici(@PathParam("idBicicleta") Long idBici,@PathParam("idAccesorioBici") Long idAcc){
        AccesorioBicicletaEntity entity = superBiciLogic.getAccesorioBici(idBici, idAcc);
        return new AccesorioBicicletaDTO(entity);
    }

    /**
     * GET http://localhost:8080/bicicletas-web/api/bicicletas/{idBicicleta}/AccesorioBicicleta
     * @param idBici bicicleta de los accesoriosBuscados
     * @return lista accesoriosBicicleta.
     */
    @GET
    public List<AccesorioBicicletaDTO> getAccesorioBicis(@PathParam("idBicicleta") Long idBici){
        List<AccesorioBicicletaEntity> entity = superBiciLogic.getAccesoriosBici(idBici);
        return listEntity2DetailDTO(entity);
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

package co.edu.uniandes.bicicletas.resources;

/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import co.edu.uniandes.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.BicicletaDTO;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;

import co.edu.uniandes.bicicletas.dtos.BicicletaDetailDTO;
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
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "Bicicletas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Bicicletas". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Bicicletas"
 *
 * @author ISIS2603
 *
 */
@Path("/bicicletas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class BicicletaResource {

    @Inject
    BicicletaLogic bicicletasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(BicicletaResource.class.getName());

    /**
     * POST http://localhost:8080/bicicletas-web/api/bicicletas
     *
     * @param bicicleta correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BicicletaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public BicicletaDetailDTO createBicicleta(BicicletaDTO bicicleta) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        
        
        // Invoca la lógica para crear la Bicicleta nueva
        BicicletaEntity nuevaBicicleta = bicicletasLogic.createBicicleta(bicicleta.toEntity());
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new BicicletaDetailDTO(nuevaBicicleta);
    }

    /**
     * GET para todas las Bicicletaes.
     * http://localhost:8080/bicicletas-web/api/bicicletas
     *
     * @return la lista de todas las Bicicletaes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<BicicletaDetailDTO> getBicicletas() throws BusinessLogicException {
        return listEntity2DetailDTO(bicicletasLogic.getBicicletas());
    }
    /**
     * GET Este metodo obtiene una biciclea especifica de las bicicletas del sistema.
     * @param id Long con el id de la bicicleta.
     * @return 
     */
    @GET
    @Path("{id:\\d+}")
    public BicicletaDetailDTO getBicicletaId(@PathParam("id")Long id){
        return new BicicletaDetailDTO(bicicletasLogic.getBIcicleta(id));
    }

   
    /**
     * PUT http://localhost:8080/bicicletas-web/api/bicicletas/1 Ejemplo
     * json { "id": 1, "atirbuto1": "Valor nuevo" }
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
    public BicicletaDetailDTO updateBicicleta(@PathParam("id") Long id, BicicletaDetailDTO bicicletas) throws BusinessLogicException {
          BicicletaEntity entity = bicicletas.toEntity();
          return new BicicletaDetailDTO(bicicletasLogic.actualizarBicicleta(entity));
      
    }

    /**
     * DELETE http://localhost:8080/bicicletas-web/api/bicicletass/{id}
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
    public void deleteBicicleta(@PathParam("id") Long id) throws BusinessLogicException {
        bicicletasLogic.deleteBicicleta(id);
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
    private List<BicicletaDetailDTO> listEntity2DetailDTO(List<BicicletaEntity> entityList) {
        List<BicicletaDetailDTO> list = new ArrayList<>();
        for (BicicletaEntity entity : entityList) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
/**
     *Este metodo llama a la clase de accesesorios bicicleta.
     * @param idBicicleta
     * @return el CLASS AccesroioBicicletaResource.
     */
    @Path("{idBicicleta: \\d+}/accesorioBicicleta")
    public Class<AccesorioBicicletaBicicletaResource> getClassAcc(@PathParam("idBicicleta")Long idBicicleta) {
        BicicletaEntity entity = bicicletasLogic.getBIcicleta(idBicicleta);
            if (entity == null) {
            throw new WebApplicationException("El recurso /bici/" + idBicicleta + "/accBici/ no existe.", 404);
            }
       return AccesorioBicicletaBicicletaResource.class;
    }
	
}

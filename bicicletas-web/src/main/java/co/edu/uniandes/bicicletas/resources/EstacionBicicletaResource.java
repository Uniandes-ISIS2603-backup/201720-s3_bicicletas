/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author js.torres1
 */
@Path("/estaciones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EstacionBicicletaResource {
    @Inject
    EstacionLogic estacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    private static final Logger LOGGER = Logger.getLogger(EstacionBicicletaResource.class.getName());
    /**
     * 
     */
    @GET
    @Path("{idBicicleta: \\d+AccesorioBicicleta/idAccesorioBici: \\d+}")
    public BicicletaDetailDTO getBici(@PathParam("idBicicleta") Long idEstacion,@PathParam("idAccesorioBici") Long idBici){
        BicicletaEntity entity = estacionLogic.getBiciEstacion(idEstacion, idBici);
        return new BicicletaDetailDTO(entity);
    }
}

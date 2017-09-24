/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author js.torres1
 */
@Path("bicicletas/AccesorioBicicleta")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EstacionBicicletaResource {
    @Inject
    EstacionLogic bicicletasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    @Inject
    BicicletaLogic superBiciLogic;
    private static final Logger LOGGER = Logger.getLogger(EstacionBicicletaResource.class.getName());
}

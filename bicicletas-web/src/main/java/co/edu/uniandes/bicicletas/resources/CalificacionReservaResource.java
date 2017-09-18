/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.ejb.CalificacionLogic;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "calificaciones"
 * URI: usuarios/{usuariosId: \\d+}/reservas/{reservasId: \\d+}/calificaciones
 * @author Gabriel Pinto
 */
@Produces("application/json")
@Consumes("application/json")
public class CalificacionReservaResource 
{
    @Inject
    private CalificacionLogic calificacionLogic;
}

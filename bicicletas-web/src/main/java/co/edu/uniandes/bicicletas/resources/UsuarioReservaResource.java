/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ds.chacon
 */
@Produces("application/json")
@Consumes("application/json")
public class UsuarioReservaResource 
{
    @Inject
    ReservaLogic reservaLogic;
    
    @Path("{idReserva: \\d+}/calificaciones/")
    public Class<ReservaCalificacionResource> getReservaCalificacionResource(@PathParam("idReserva") Long idReserva) 
    {
        ReservaEntity entity = reservaLogic.getReserva(idReserva);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /usuarios/" + idReserva + "/calificaciones no existe.", 404);
        }
        return ReservaCalificacionResource.class;
    }
    
}

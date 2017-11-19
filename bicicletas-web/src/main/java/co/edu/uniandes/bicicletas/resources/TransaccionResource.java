/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.TransaccionDTO;
import co.edu.uniandes.bicicletas.ejb.TransaccionLogic;
import co.edu.uniandes.bicicletas.entities.TransaccionEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Recurso de la entidad Transaccion
 * @author jd.trujillom
 */
@Path("transacciones")
@Produces("application/json")
@Consumes("application/json")
public class TransaccionResource {
    
    /**
     * Injecta la logica de persistencia. 
     */
    @Inject
    private TransaccionLogic transaccionLogic;
    
    /**
     * Retorna la transacción asociada a una reserva.
     * @param id de la transacción que se quiere retornar. 
     * @return la transacción con el id pasado por parametro. 
     */
    @GET
    @Path("{id: \\d+}")
    public TransaccionDTO darTransaccion(@PathParam("id")Long id){
        TransaccionEntity entity =  transaccionLogic.obtenerTransaccion(id);
        
        if(entity == null){
            throw new WebApplicationException("El recurso /transacciones/" + id + "/ no existe", 404);
        }
        
        return new TransaccionDTO(entity);
    }
}

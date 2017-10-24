/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.AccesorioDTO;
import co.edu.uniandes.bicicletas.ejb.AccesorioLogic;
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ka.babativa
 */
@Path("/accesorios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class AccesorioResource {
    
    @Inject
    AccesorioLogic logica;
    
    private List<AccesorioDTO> listEntity2DTO(List<AccesorioEntity> entityList) {
        List<AccesorioDTO> lista = new ArrayList<>();
        for(AccesorioEntity entidad : entityList){
            lista.add(new AccesorioDTO(entidad));
    }
        return lista;
        
}
    
   @GET
    public List<AccesorioDTO> obtenerAccesorios(){
        return listEntity2DTO(logica.getAccesorios());
    }
    
    @GET
    @Path("{id: \\d+}")
    public AccesorioDTO getEstacion(@PathParam("id") Long id) {
        return new AccesorioDTO(logica.getAccesorio(id));
    }
    
    @POST
    public AccesorioDTO crearEstacion(AccesorioDTO dto) throws BusinessLogicException {
        if(dto.getReservado()==0||dto.getReservado()==1)
        {
            if(dto.getTipo()==0||dto.getTipo()==1)
            {
               return new AccesorioDTO(logica.crearAccesorio(dto.toEntity())); 
            }
        }
        throw new WebApplicationException("No esta dentro del tipo o estado correspondiente", 301);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public AccesorioDTO actualizarAccesorio(@PathParam("id") Long id, AccesorioDTO dto) {
        AccesorioEntity entity = dto.toEntity();
        entity.setId(id);
        return new AccesorioDTO(logica.actualizarAccesorio(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void borrarAccesorio(@PathParam("id") Long id) {
        logica.deleteAccesorio(id);
    }
    
    
}

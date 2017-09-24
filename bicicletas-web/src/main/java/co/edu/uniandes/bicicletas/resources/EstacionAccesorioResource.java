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
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ka.babativa
 */
@Produces("application/json")
@Consumes("application/json")
public class EstacionAccesorioResource {
    
    @Inject
    private AccesorioLogic accesorioLogic;
    
    @GET
    public List<AccesorioDTO> getAccesorios(@PathParam("idEstacion") Long idEstacion) throws BusinessLogicException
    {
        return listEntity2DTO(accesorioLogic.getAccesoriosEstacion(idEstacion));
    }
    
    @GET
    @Path("{id: \\d+}")
    public AccesorioDTO getAccesorio(@PathParam("idEstacion") Long idEstacion, @PathParam("id") Long idAccesorio) throws BusinessLogicException
    {
        AccesorioEntity accesorioEntity = accesorioLogic.getAccesorioEstacion(idEstacion, idAccesorio);
        if(accesorioEntity == null) {
            throw new WebApplicationException("El recurso /estaciones/" + idEstacion + "/Accesorios/" + idAccesorio + " no existe", 404);
        }
        return new AccesorioDTO(accesorioEntity);
    }
    
    private List<AccesorioDTO> listEntity2DTO(List<AccesorioEntity> listaEntiCali)
    {
        List<AccesorioDTO> lista = new ArrayList<>();
        for(AccesorioEntity caliEntity : listaEntiCali){
            lista.add(new AccesorioDTO(caliEntity));
        }
        return lista;
    }
    
    
    
    
}

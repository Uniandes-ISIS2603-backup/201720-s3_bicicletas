/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.AccesorioDTO;
import co.edu.uniandes.bicicletas.ejb.AccesorioLogic;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
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
 * Clase que implementa el recurso REST correspondiente a "direcciones"
 * URI: estaciones/{estacionesId: \\d+}/accesorios
 * @author ka.babativa
 */
@Produces("application/json")
@Consumes("application/json")
public class EstacionAccesorioResource {
    
    @Inject
    private AccesorioLogic accesorioLogic;
    
    @Inject
    private EstacionLogic estacionLogic;
    
    private List<AccesorioDTO> listEntity2DTO(List<AccesorioEntity> listaEntiCali)
    {
        List<AccesorioDTO> lista = new ArrayList<>();
        for(AccesorioEntity caliEntity : listaEntiCali){
            lista.add(new AccesorioDTO(caliEntity));
        }
        return lista;
    }
    
    
     private List<AccesorioEntity> accesoriosListDTO2Entity(List<AccesorioDTO> dtos){
        List<AccesorioEntity> list = new ArrayList<>();
        for (AccesorioDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
     @GET
     public List<AccesorioDTO> listAccesorios(@PathParam("estacionesId") Long estacionesId){
         return listEntity2DTO(estacionLogic.listAccesorios(estacionesId));
     }
    
    
}

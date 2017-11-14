/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.AccesorioDTO;
import co.edu.uniandes.bicicletas.dtos.EstacionDetailDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 * Clase que implementa el recurso REST correspondiente a "direcciones"
 * URI: estaciones/{estacionesId: \\d+}/accesorios
 * @author ka.babativa
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class EstacionAccesorioResource {
    @Inject
    private EstacionLogic estacionLogic;
    
    @GET
    @Path("{idAccesorio: \\d+}")
    public AccesorioDTO getAccesorios(@PathParam("idEstacion") Long idEstacion,@PathParam("idAccesorio") Long idAccesorio) throws BusinessLogicException{
        AccesorioEntity entity = estacionLogic.getAccesorio(idEstacion, idAccesorio);
        return new AccesorioDTO(entity);
    }
    
    @PUT
    public EstacionDetailDTO asignarAccesorioEstacion(@PathParam("idEstacion") Long idEstacion, AccesorioDTO accesorio)throws BusinessLogicException{
      return new EstacionDetailDTO(estacionLogic.asignarAccesorio(idEstacion, accesorio.toEntity()));
    }
    
    @GET
    public List<AccesorioDTO> getEstacionAccesorios(@PathParam("idEstacion") Long idEstacion)throws BusinessLogicException {
        List<AccesorioEntity> listEntity = estacionLogic.getAccesorios1(idEstacion);
        return listEntity2DetailDTO(listEntity);
    }
    
    private List<AccesorioDTO> listEntity2DetailDTO(List<AccesorioEntity> entityList) {
        List<AccesorioDTO> list = new ArrayList<>();
        for (AccesorioEntity entity : entityList) {
            list.add(new AccesorioDTO(entity));
        }
        return list;
    }
    
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
    
    
    
}

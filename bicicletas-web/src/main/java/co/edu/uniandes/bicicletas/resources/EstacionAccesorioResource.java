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
    private EstacionLogic estacionLogic; //Variable que inyecta la logica de estacion
    
    /**
     * Metodo que retorna un accesorio a traves de HTTP GET
     * @param idEstacion ID De la estacion a consultar
     * @param idAccesorio ID Del accesorio a consultar
     * @return Un accesorio ddos los parametros.
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{idAccesorio: \\d+}")
    public AccesorioDTO getAccesorios(@PathParam("idEstacion") Long idEstacion,@PathParam("idAccesorio") Long idAccesorio) throws BusinessLogicException{
        AccesorioEntity entity = estacionLogic.getAccesorio(idEstacion, idAccesorio);
        return new AccesorioDTO(entity);
    }
     
    /**
     * Metodo que asigna un accesorio a una estación a traves de HTTP PUT
     * @param idEstacion ID De la estacion padre del accesorio.
     * @param accesorio DTO Con la informacion del accesorio a asignar
     * @return EstacionDetailDTO con el accesorio asignado
     * @throws BusinessLogicException 
     */
    @PUT
    public EstacionDetailDTO asignarAccesorioEstacion(@PathParam("idEstacion") Long idEstacion, AccesorioDTO accesorio)throws BusinessLogicException{
      return new EstacionDetailDTO(estacionLogic.asignarAccesorio(idEstacion, accesorio.toEntity()));
    }
    
    /**
     * Metodo que obtiene todos los accesorios dada una estación a traves de HTTP GET.
     * @param idEstacion ID De la estacion a consultar.
     * @return Una lista con todos los accesorios de la estación dada.
     * @throws BusinessLogicException 
     */
    @GET
    public List<AccesorioDTO> getEstacionAccesorios(@PathParam("idEstacion") Long idEstacion)throws BusinessLogicException {
        List<AccesorioEntity> listEntity = estacionLogic.getAccesorios1(idEstacion);
        return listEntity2DetailDTO(listEntity);
    }
    
    /**
     * Metodo encargada de pasar objetos de tipo AccesorioEntity a AccesorioDTO
     * @param entityList Lista con las entidades de accesorio
     * @return  Lista con los objetos de tipo accesorio.
     */
    private List<AccesorioDTO> listEntity2DetailDTO(List<AccesorioEntity> entityList) {
        List<AccesorioDTO> list = new ArrayList<>();
        for (AccesorioEntity entity : entityList) {
            list.add(new AccesorioDTO(entity));
        }
        return list;
    }
     
    
}

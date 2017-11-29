/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.AccesorioDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 * Clase que maneja el REST de ReservaAccesorio
 * @author ka.babativa
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class ReservaAccesorioResource {
    
    @Inject
    ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Metodo que retorna un accesorio de una reserva a traves de HTTP GET
     * @param idReserva ID De la reserva a consultar.
     * @param idAccesorio ID Del accesorio a consultar
     * @return Un accesorioDTO dados los parametros.
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{idAccesorio: \\d+}")
    public AccesorioDTO getAccesorios(@PathParam("idReserva") Long idReserva,@PathParam("idAccesorio") Long idAccesorio) throws BusinessLogicException{
        AccesorioEntity entity = reservaLogic.getAccesorio(idReserva, idAccesorio);
        return new AccesorioDTO(entity);
    }
    
    /**
     * Metodo que asigna un accesorio a una reserva a traves HTTP PUT.
     * @param idReserva ID De la reserva a consultar.
     * @param accesorio DTO Con la informacion del accesorio.
     * @return ReservaDTO Con la nueva informacion del accesorio.
     * @throws BusinessLogicException 
     */
    @PUT
    public ReservaDTO asignarAccesorioReserva(@PathParam("idReserva") Long idReserva, AccesorioDTO accesorio)throws BusinessLogicException{
      return new ReservaDTO(reservaLogic.asignarAccesorio(idReserva, accesorio.toEntity()));
    }
    
    /**
     * Metodo que desasigna un accesorio a una reserva a traves HTTP PUT.
     * @param idReserva ID De la reserva a consultar.
     * @param accesorio DTO Con la informacion(ID) del accesorio.
     * @return ReservaDTO Con la nueva informacion del accesorio.
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("/quitar")
    public ReservaDTO desasignarAccesorioReserva(@PathParam("idReserva") Long idReserva, AccesorioDTO accesorio)throws BusinessLogicException{
        return new ReservaDTO(reservaLogic.desasignarAccesorio(idReserva, accesorio.toEntity()));
    }
    
    /**
     * Metodo que retorna una lista con los accesorios de una reserva.
     * @param idEstacion ID De la estacion a consultar.
     * @return Una lista con todos los accesorios de una Reserva
     * @throws BusinessLogicException 
     */
    @GET
    public List<AccesorioDTO> getReservaAccesorios(@PathParam("idReserva") Long idEstacion)throws BusinessLogicException {
        List<AccesorioEntity> listEntity = reservaLogic.getAccesorios(idEstacion);
        return listEntity2DetailDTO(listEntity);
    }
    
    /**
     * Metodo que convierte de AccesorioEntity a ReservaDetailDTO
     * @param entityListLista con todos los objetos de tipo AccesorioEntity
     * @return Lista con todos los accesorios de tipo AccesorioDTO
     */
    private List<AccesorioDTO> listEntity2DetailDTO(List<AccesorioEntity> entityList) {
        List<AccesorioDTO> list = new ArrayList<>();
        for (AccesorioEntity entity : entityList) {
            list.add(new AccesorioDTO(entity));
        }
        return list;
    }
}

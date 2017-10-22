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
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ka.babativa
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class ReservaAccesorioResource {
    
    @Inject
    ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    private static final Logger LOGGER = Logger.getLogger(EstacionBicicletaResource.class.getName());
    
    @GET
    @Path("{idAccesorio: \\d+}")
    public AccesorioDTO getAccesorios(@PathParam("idReserva") Long idReserva,@PathParam("idAccesorio") Long idAccesorio) throws BusinessLogicException{
        AccesorioEntity entity = reservaLogic.getAccesorio(idReserva, idAccesorio);
        return new AccesorioDTO(entity);
    }
    
    @PUT
    public ReservaDTO asignarAccesorioReserva(@PathParam("idReserva") Long idReserva, AccesorioDTO accesorio)throws BusinessLogicException{
      return new ReservaDTO(reservaLogic.asignarAccesorio(idReserva, accesorio.toEntity()));
    }
    
    @GET
    public List<AccesorioDTO> getReservaAccesorios(@PathParam("idReserva") Long idEstacion)throws BusinessLogicException {
        List<AccesorioEntity> listEntity = reservaLogic.getAccesorios(idEstacion);
        return listEntity2DetailDTO(listEntity);
    }
    
    private List<AccesorioDTO> listEntity2DetailDTO(List<AccesorioEntity> entityList) {
        List<AccesorioDTO> list = new ArrayList<>();
        for (AccesorioEntity entity : entityList) {
            list.add(new AccesorioDTO(entity));
        }
        return list;
    }
}

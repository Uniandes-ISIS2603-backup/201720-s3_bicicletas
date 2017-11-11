/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.bicicletas.dtos.BicicletaDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
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
 *
 * @author js.torres1
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class ReservaBicicletaResource {
    @Inject
    ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    private static final Logger LOGGER = Logger.getLogger(EstacionBicicletaResource.class.getName());
    /**
     * 
     */
    @GET
    @Path("{idBicicleta: \\d+}")
    public BicicletaDetailDTO getBici(@PathParam("idReserva") Long idReserva,@PathParam("idBicicleta") Long idBici) throws BusinessLogicException{
        BicicletaEntity entity = reservaLogic.getBici(idReserva, idBici);
        return new BicicletaDetailDTO(entity);
    }
    
    @GET
    public List<BicicletaDetailDTO> getReservaBicicis(@PathParam("idReserva") Long idReserva)throws BusinessLogicException {
        List<BicicletaEntity> listEntity = reservaLogic.getBicis(idReserva);
        return listEntity2DetailDTO(listEntity);
    }
    @PUT
    public ReservaDTO updateBiciAso(@PathParam("idReserva") Long idReserva,BicicletaDTO bicicleta)throws BusinessLogicException{
      
      return new ReservaDTO(reservaLogic.asignarBicicleta(idReserva, bicicleta.toEntity()));
    }
    private List<BicicletaDetailDTO> listEntity2DetailDTO(List<BicicletaEntity> entityList) {
        List<BicicletaDetailDTO> list = new ArrayList<>();
        for (BicicletaEntity entity : entityList) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
}

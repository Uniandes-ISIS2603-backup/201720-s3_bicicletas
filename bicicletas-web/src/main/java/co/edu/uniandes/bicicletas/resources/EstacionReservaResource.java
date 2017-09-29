/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.CalificacionDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
public class EstacionReservaResource {
    
    @Inject
    private EstacionLogic estacionLogic;
    
     private List<ReservaDTO> reservaListEntity2DTO(List<ReservaEntity> entityList){
         List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }
     
     private List<ReservaEntity> reservaListDTO2Entity(List<ReservaDTO> dtos){
        List<ReservaEntity> list = new ArrayList<>();
        for (ReservaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
     
     @GET
    public List<ReservaDTO> listReserva(@PathParam("estacionesId") Long estacionesId) {
        return reservaListEntity2DTO(estacionLogic.listReservas(estacionesId));
    }
    
    @GET
    @Path("{reservaId: \\d+}")
    public ReservaDTO getReservas(@PathParam("estacionesId") Long estacionesId, @PathParam("reservaId") Long reservaId) {
        return new ReservaDTO(estacionLogic.getReserva(estacionesId, reservaId));
    }
    
}

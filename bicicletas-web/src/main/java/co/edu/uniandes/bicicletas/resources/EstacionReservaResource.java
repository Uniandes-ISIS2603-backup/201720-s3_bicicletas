/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase encargada de manejar el REST de EstacionReserva
 * @author ka.babativa
 */
@Produces("application/json")
@Consumes("application/json")
public class EstacionReservaResource {
    
    @Inject
    private EstacionLogic estacionLogic; //Atributo que inyecta la logica de Estacion
    
    /**
     * Metodo que transforma objetos de tipo ReservaEntity a ReservaDTO
     * @param entityList Lista con los objetos de tipo ReservaEntity
     * @return Lista con objetos de tipo ReservaDTO convertidos.
     */
     private List<ReservaDTO> reservaListEntity2DTO(List<ReservaEntity> entityList){
         List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }
     
     /**
      * Metodo que retorna todas las reservas de una estación a traves de HTTP GET.
      * @param estacionesId ID de la estacion a consultar.
      * @return Lista de reservaDTO de una estación dada. 
      */
     @GET
    public List<ReservaDTO> listReserva(@PathParam("idEstacion") Long estacionesId) {
        return reservaListEntity2DTO(estacionLogic.listReservas(estacionesId));
    }
    
    /**
     * Metodo que retorna una reserva de una estación a traves de HTTP GET.
     * @param estacionesId ID De la estacion a consultar.
     * @param reservaId ID De la reserva a consultar.
     * @return Un objeto de tipo ReservaDTO.
     */
    @GET
    @Path("{reservaId: \\d+}")
    public ReservaDTO getReservas(@PathParam("idEstacion") Long estacionesId, @PathParam("reservaId") Long reservaId) {
        return new ReservaDTO(estacionLogic.getReserva(estacionesId, reservaId));
    }
    
}

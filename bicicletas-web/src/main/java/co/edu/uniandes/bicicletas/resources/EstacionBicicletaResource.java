/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.bicicletas.dtos.EstacionDetailDTO;
import co.edu.uniandes.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
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

/**
 *
 * @author js.torres1
 */
@Path("/estaciones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EstacionBicicletaResource {
    @Inject
    EstacionLogic estacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    private static final Logger LOGGER = Logger.getLogger(EstacionBicicletaResource.class.getName());
    /**
     * 
     */
    @GET
    @Path("{idestacion: \\d+/bicicletas/idBicicleta: \\d+}")
    public BicicletaDetailDTO getBici(@PathParam("idestacion") Long idEstacion,@PathParam("idBicicleta") Long idBici){
        BicicletaEntity entity = estacionLogic.getBiciEstacion(idEstacion, idBici);
        return new BicicletaDetailDTO(entity);
    }
    
    @GET
    @Path("{idEstacion: \\d+/bicicletas}")
    public List<BicicletaDetailDTO> getBicicsEstacion(@PathParam("idEstacion") Long idEstacion) {
        List<BicicletaEntity> listEntity = estacionLogic.getBicisEstacion(idEstacion);
        return listEntity2DetailDTO(listEntity);
    }
    @PUT
    @Path("{idEstacion: \\d+/bicicletas}")
    public void updateBiciAso(@PathParam("idEstacion") Long idEstacion, BicicletaDetailDTO biciDTO){
       estacionLogic.upDateBici(estacionLogic.getEstacion(idEstacion), biciDTO.toEntity());
    }
    private List<BicicletaDetailDTO> listEntity2DetailDTO(List<BicicletaEntity> entityList) {
        List<BicicletaDetailDTO> list = new ArrayList<>();
        for (BicicletaEntity entity : entityList) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
}

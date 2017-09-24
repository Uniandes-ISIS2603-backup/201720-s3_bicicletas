/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.DireccionDetailDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "direcciones"
 * URI: estaciones/{estacionesId: \\d+}/direcciones
 * @author cm.alba10
 */
@Produces("application/json")
@Consumes("application/json")
public class DireccionEstacionResource {
    
     @Inject private EstacionLogic estacionLogic;
     
     /**
     * Convierte una lista de DireccionEntity a una lista de DireccionDetailDTO.
     *
     * @param entityList Lista de DireccionEntity a convertir.
     * @return Lista de DireccionDetailDTO convertida.
     * 
     */
    private List<DireccionDetailDTO> direccionesListEntity2DTO(List<DireccionEntity> entityList){
        List<DireccionDetailDTO> list = new ArrayList<>();
        for (DireccionEntity entity : entityList) {
            list.add(new DireccionDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de DireccionDetailDTO a una lista de DireccionEntity.
     *
     * @param dtos Lista de DireccionDetailDTO a convertir.
     * @return Lista de DireccionEntity convertida.
     * 
     */
    private List<DireccionEntity> direccionesListDTO2Entity(List<DireccionDetailDTO> dtos){
        List<DireccionEntity> list = new ArrayList<>();
        for (DireccionDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de DireccionDetailDTO asociadas a una
     * instancia de Estacion
     *
     * @param estacionesId Identificador de la instancia de Estacion
     * @return Colección de instancias de DireccionDetailDTO asociadas a la instancia de Estacion
     * 
     */
    @GET
    public List<DireccionDetailDTO> listDirecciones(@PathParam("estacionesId") Long estacionesId) {
        return direccionesListEntity2DTO(estacionLogic.listDirecciones(estacionesId));
    }

    /**
     * Obtiene una instancia de Direccion asociada a una instancia de Estacion
     *
     * @param estacionesId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * 
     */
    @GET
    @Path("{direccionesId: \\d+}")
    public DireccionDetailDTO getDirecciones(@PathParam("estacionesId") Long estacionesId, @PathParam("direccionesId") Long direccionesId) {
        return new DireccionDetailDTO(estacionLogic.getDireccion(estacionesId, direccionesId));
    }

    /**
     * Asocia un Direccion existente a un Estacion
     *
     * @param estacionesId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * @return Instancia de DireccionDetailDTO que fue asociada a Estacion
     * 
     */
    @POST
    @Path("{direccionesId: \\d+}")
    public DireccionDetailDTO addDirecciones(@PathParam("estacionesId") Long estacionesId, @PathParam("direccionesId") Long direccionesId) {
        return new DireccionDetailDTO(estacionLogic.addDireccion(estacionesId, direccionesId));
    }

    /**
     * Remplaza las instancias de Direccion asociadas a una instancia de Estacion
     *
     * @param estacionesId Identificador de la instancia de Estacion
     * @param direcciones Colección de instancias de DireccionDTO a asociar a instancia de Estacion
     * @return Nueva colección de DireccionDTO asociada a la instancia de Estacion
     * 
     */
    @PUT
    public List<DireccionDetailDTO> replaceDirecciones(@PathParam("estacionesId") Long estacionesId, List<DireccionDetailDTO> direcciones) {
        return direccionesListEntity2DTO(estacionLogic.replaceDirecciones(estacionesId, direccionesListDTO2Entity(direcciones)));
    }

    /**
     * Desasocia un Direccion existente de un Estacion existente
     *
     * @param estacionesId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * 
     */
    @DELETE
    @Path("{direccionesId: \\d+}")
    public void removeDirecciones(@PathParam("estacionesId") Long estacionesId, @PathParam("direccionesId") Long direccionesId) {
        estacionLogic.removeDireccion(estacionesId, direccionesId);
    }
}

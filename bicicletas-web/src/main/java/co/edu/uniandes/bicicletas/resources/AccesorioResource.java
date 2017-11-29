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
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase REST de Accesorio
 * @author ka.babativa
 */
@Path("/accesorios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class AccesorioResource {
    
    @Inject
    AccesorioLogic logica; //Atributo que inyecta la logica de un accesorio
    
    @Inject
    EstacionLogic estacionLogica; //Atributo que modela la logica de una estación
    
    /**
     * Metodo que convierte una lista de entidades a una lista de DTO
     * @param entityList Lista con las entidades
     * @return Una lista con los DTOS de las entidades que entran por parametro
     */
    private List<AccesorioDTO> listEntity2DTO(List<AccesorioEntity> entityList) {
        List<AccesorioDTO> lista = new ArrayList<>();
        for(AccesorioEntity entidad : entityList){
            lista.add(new AccesorioDTO(entidad));
    }
        return lista;
        
}
    /**
     * Metodo que obtiene todos los accesorios a travs de HTTP GET
     * @return Una lista de accesorios DTO.
     */
   @GET
    public List<AccesorioDTO> obtenerAccesorios(){
        return listEntity2DTO(logica.getAccesorios());
    }
    
    /**
     * Metodo oque obtiene un accesorio con un ID Dado a traves de HTTP GET
     * @param id ID del accesorio a consultar
     * @return Un accesorio de tipo DTO.
     */
    @GET
    @Path("{id: \\d+}")
    public AccesorioDTO getAccesorio(@PathParam("id") Long id) {
        return new AccesorioDTO(logica.getAccesorio(id));
    }
    
    /**
     * Metodo que crea un accesorio a traves HTTP POST
     * @param dto con la informacion del acccesorio a crear
     * @return El accesorio creado
     * @throws BusinessLogicException 
     */
    @POST
    public AccesorioDTO crearAccesorio(AccesorioDTO dto) throws BusinessLogicException {
            if((dto.getTipo()==2||dto.getTipo()==1) && (estacionLogica.getEstacion(dto.getEstacion().getId())!=null))
            {
                   dto.setReservado(0);
                   return new AccesorioDTO(logica.crearAccesorio(dto.toEntity())); 
        }
        throw new WebApplicationException("No esta dentro del tipo o estado correspondiente", 301);
    }
    
    /**
     * Metodo que actualiza un accesorio a traves de HTTP PUT
     * @param id ID del accesorio a actualizar
     * @param dto con la nueva información del accesorio.
     * @return El accesorio actualizado
     */
    @PUT
    @Path("{id: \\d+}")
    public AccesorioDTO actualizarAccesorio(@PathParam("id") Long id, AccesorioDTO dto) {
        AccesorioEntity entity = dto.toEntity();
        entity.setId(id);
        return new AccesorioDTO(logica.actualizarAccesorio(entity));
    }
    
    @PUT
    @Path("{id: \\d+}/mover")
    public AccesorioDTO actualizarEstacionAccesorio(@PathParam("id") Long id, AccesorioDTO dto) {
        AccesorioEntity entity = dto.toEntity();
        EstacionEntity estacion = dto.getEstacion();
        EstacionEntity finalEstacion = estacionLogica.getEstacion(estacion.getId());
        AccesorioEntity buscada = logica.getAccesorio(id);
        entity.setId(buscada.getId());
        entity.setName(buscada.getName());
        entity.setReserva(buscada.getReserva());
        entity.setReservado(buscada.getReservado());
        entity.setTipo(buscada.getTipo());
        entity.setEstacion(finalEstacion);
        entity.setId(id);
        return new AccesorioDTO(logica.actualizarAccesorio(entity));
    }
    
    
    /**
     * Metodo que elimina un accesorio a traves de HTTP DELETE
     * @param id del accesorio a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void borrarAccesorio(@PathParam("id") Long id) {
        logica.deleteAccesorio(id);
    }
    
    
}

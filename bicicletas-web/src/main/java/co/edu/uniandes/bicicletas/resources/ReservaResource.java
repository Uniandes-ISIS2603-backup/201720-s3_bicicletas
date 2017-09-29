/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.EstacionLogic;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.ejb.UsuarioLogic;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import org.slf4j.Logger;

/**
 *
 * @author ds.chacon
 */
@Path("/reservas")
@Produces("application/json")
@Consumes("application/json")
public class ReservaResource {
    @Inject
    private ReservaLogic logica;
    @Inject
    private UsuarioLogic logicaUsuario ;

    private List<ReservaDTO> listEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> lista = new ArrayList<>();
        for(ReservaEntity entidad : entityList){
            lista.add(new ReservaDTO(entidad));
    }
        return lista;
        
}
    
    @GET
    public List<ReservaDTO> obtenerReservas(){
        return listEntity2DTO(logica.getReservas());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("id") Long id) {
        ReservaEntity reservas = logica.getReserva(id);
        if(reservas==null){
          throw new WebApplicationException("La reserva con id "+ id +" no existe", 404);
        }
        return new ReservaDTO(reservas);
    }
    
    @POST
    @Path("{id: \\d+}")
    public ReservaDTO crearReserva (@PathParam("id") Long id , ReservaDTO dto) throws BusinessLogicException {
        if(logicaUsuario.getUsuario(id)==null){
          throw new WebApplicationException("El usuario con id "+ id +" no existe", 404);
        }
        return new ReservaDTO(logica.crearReserva( id ,dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ReservaDTO actualizarReserva (@PathParam("id") Long id, ReservaDTO dto) {
        ReservaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ReservaDTO(logica.actualizarReserva(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void borrarReserva(@PathParam("id") Long id) {
        logica.deleteReserva(id);
    }
    
    
    @Path("{idReserva: \\d+}/Estacion / { llegada: \\d+}")
    public Class<ReservaEstacionResource> getReservaEstacionResource(@PathParam("idReserva") Long idReserva,@PathParam("llegada") int llegada){
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Reserva/" + idReserva + "/Estacion/"+ llegada +"no existe.", 404);
        }
        return ReservaEstacionResource.class;
    }
    
    @Path("{idReserva: \\d+}/Pago")
    public Class<PagoReservaResource> getResource(@PathParam("idReserva") Long idReserva){
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Reserva/" + idReserva + "/Pago no existe.", 404);
        }
        return PagoReservaResource.class;
    }
    
    @Path("{idReserva: \\d+}/usuario")
    public Class<UsuarioReservaResource> getUsuario(@PathParam("idReserva") Long idReserva) {
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Reserva/" + idReserva + "/Pago no existe.", 404);
        }
        return   UsuarioReservaResource.class;
    } 
}

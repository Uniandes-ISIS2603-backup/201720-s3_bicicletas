/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.dtos.UsuarioDTO;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.ejb.UsuarioLogic;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

/**
 *Clase que modela la relacion Reserva Usuario
 * @author ds.chacon
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class UsuarioReservaResource 
{
    @Inject
    ReservaLogic reservaLogic;
    
    @Inject
    UsuarioLogic usuarioLogic;
    
    @GET
    public List<ReservaDTO> getReservas(@PathParam("idUsuario") Long idUsuario)
    {
        List<ReservaEntity> reservas = reservaLogic.getReservas();
        if(reservas == null || reservas.isEmpty())
        {
            throw new WebApplicationException("El usuario no tiene reservas en el sistema", 404); 
        }
        return listEntity2DTO(reservas);
    }
    
    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("id") Long id, @PathParam("idUsuario") Long idUsuario)
    {
        UsuarioEntity usuario = usuarioLogic.getUsuario(id);
        List<ReservaEntity> reservas = usuario.getReservas();
        if(reservas==null || reservas.isEmpty()){
            throw new WebApplicationException("El usuario no tiene reservas en el sistema", 404); 
        }
        return new ReservaDTO(reservaLogic.getReserva(id));
    }
    
    @DELETE 
    public void deleteReserva(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException 
    {
        reservaLogic.deleteReserva(idUsuario);
    }
    
    @POST
    public ReservaDTO createReserva(@PathParam("idUsuario") Long idUsuario , ReservaDTO nuevareserva) throws BusinessLogicException {
        return new ReservaDTO(reservaLogic.crearReserva(idUsuario, nuevareserva.toEntity()));
    } 
    
    private List<ReservaDTO> listEntity2DTO(List<ReservaEntity> listaEntiReserva)
    {
        List<ReservaDTO> lista = new ArrayList<ReservaDTO>();
        for(ReservaEntity puntoEntity : listaEntiReserva)
        {
            lista.add(new ReservaDTO(puntoEntity));
        }
        return lista;
    }
    
    @Path("{idReserva: \\d+}/calificaciones")
    public Class<ReservaCalificacionResource> getCalificacionReservaResource(@PathParam("idReserva") Long idReserva) {
        ReservaEntity entity = reservaLogic.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException("El recurso /reservas/" + idReserva + "/calificaciones no existe.", 404);
        }
        return ReservaCalificacionResource.class;
    }
}

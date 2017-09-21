/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.PuntoDTO;
import co.edu.uniandes.bicicletas.ejb.PuntoLogic;
import co.edu.uniandes.bicicletas.entities.PuntoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "puntos"
 * URI: usuarios/{idUsuario: \\d+}/puntos
 * @author Gabriel Pinto
 */
@Produces("application/json")
public class UsuarioPunto 
{
    @Inject
    private PuntoLogic puntoLogic;
    
    @GET
    public List<PuntoDTO> getCalificaciones(@PathParam("idUsuario") Long idUsuario)
    {
        List<PuntoEntity> puntos = puntoLogic.getPuntos(idUsuario);
        if(puntos == null)
        {
            throw new WebApplicationException("El usuario no tiene puntos en el sistema", 404); 
        }
        return listEntity2DTO(puntos);
    }
    
    @DELETE 
    public void deleteReview(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException 
    {
        puntoLogic.deletePuntos(idUsuario);
    }
    
    @POST
    public List<PuntoDTO> createPuntos(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return listEntity2DTO(puntoLogic.createPuntos(idUsuario));
    } 
    
    private List<PuntoDTO> listEntity2DTO(List<PuntoEntity> listaEntiPunto)
    {
        List<PuntoDTO> lista = new ArrayList<PuntoDTO>();
        for(PuntoEntity puntoEntity : listaEntiPunto)
        {
            lista.add(new PuntoDTO(puntoEntity));
        }
        return lista;
    }
}

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
import javax.ws.rs.ext.Provider;

/**
 * Clase que implementa el recurso REST correspondiente a "puntos"
 * URI: usuarios/{idUsuario: \\d+}/puntos
 * @author Gabriel Pinto
 */
@Provider
@Produces("application/json")
public class UsuarioPuntoResource 
{
    @Inject
    private PuntoLogic puntoLogic;
    
    /**
     * Obtiene todos los puntos que posee un usuario en el sistema
     * @param idUsuario Id del usuario del cual quieren ser consultados los puntos
     * @return Colección de PuntoDTO
     */
    @GET
    public List<PuntoDTO> getPuntos(@PathParam("idUsuario") Long idUsuario)
    {
        List<PuntoEntity> puntos = puntoLogic.getPuntos(idUsuario);
        if(puntos == null || puntos.isEmpty())
        {
            throw new WebApplicationException("El usuario no tiene puntos en el sistema", 404); 
        }
        return listEntity2DTO(puntos);
    }
    
    /**
     * Borra/redime 10 de los puntos totales que posee el usuario
     * @param idUsuario Id del usuario del cual quieren elinarse los puntos
     * @throws BusinessLogicException Se lanza si el usuario no tiene al menos 10 puntos  
     */
    @DELETE 
    public void deletePuntos(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException 
    {
        puntoLogic.deletePuntos(idUsuario);
    }
    
    /**
     * Se crean 10 puntos para el usuario 
     * @param idUsuario Id del usuario al cual se le crearán 10 puntos nuevos en el sistema
     * @return Colección de puntos creados
     * @throws BusinessLogicException Se lanza si 
     */
    @POST
    public PuntoDTO createPuntos(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return new PuntoDTO(puntoLogic.createPunto(idUsuario));
    } 
    
    private List<PuntoDTO> listEntity2DTO(List<PuntoEntity> listaEntiPunto)
    {
        List<PuntoDTO> lista = new ArrayList<>();
        for(PuntoEntity puntoEntity : listaEntiPunto)
        {
            lista.add(new PuntoDTO(puntoEntity));
        }
        return lista;
    }
}

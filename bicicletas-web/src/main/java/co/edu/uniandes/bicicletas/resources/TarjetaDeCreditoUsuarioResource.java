/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.TarjetaDeCreditoDTO;
import co.edu.uniandes.bicicletas.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.bicicletas.ejb.UsuarioLogic;
import co.edu.uniandes.bicicletas.entities.TarjetaDeCreditoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author jd.trujillom
 */
@Provider
@Produces("application/json")
public class TarjetaDeCreditoUsuarioResource {

    @Inject
    private TarjetaDeCreditoLogic tarjetaDeCreditoLogic;

    @Inject
    private UsuarioLogic usuarioLogic;

    @GET
    public List<TarjetaDeCreditoDTO> getTarjetas(@PathParam("idUsuario") Long idUsuario) {
        List<TarjetaDeCreditoEntity> respuesta = usuarioLogic.getTarjetas(idUsuario);
        if (respuesta == null) {
            return null;
        }
        return listEntity2DTO(respuesta);
    }

    @GET
    @Path("{idTarjeta: \\d+}")
    public TarjetaDeCreditoDTO getTarjeta(@PathParam("idUsuario") Long idUsuario, @PathParam("idTarjeta") Long idTarjeta) {
        List<TarjetaDeCreditoDTO> tarjetasUsuario = getTarjetas(idUsuario);
        TarjetaDeCreditoDTO respuesta = null;
        for (TarjetaDeCreditoDTO tarjeta : tarjetasUsuario) {
            if (tarjeta.getId().equals(idTarjeta)) {
                respuesta = tarjeta;
                break;
            }
        }

        if (respuesta == null) {
            throw new WebApplicationException("El usuario no cuenta con esa tarjeta de credito", 404);
        }

        return respuesta;
    }

    /**
     *
     * @param idUsuario
     * @param dto
     * @return
     */
    @POST
    public TarjetaDeCreditoDTO crearTrajetaDeCredito(@PathParam("idUsuario") Long idUsuario, TarjetaDeCreditoDTO dto) {
        TarjetaDeCreditoEntity entity = tarjetaDeCreditoLogic.crearTarjeta(dto.toEntity(), idUsuario);

        return new TarjetaDeCreditoDTO(entity);
    }

    @PUT
    @Path("{idTarjeta: \\d+}")
    public TarjetaDeCreditoDTO actualizarTarjetaDeCredito(@PathParam("idUsuario") Long idUsuario, TarjetaDeCreditoDTO dto, @PathParam("idTarjeta") Long idTarjeta) {
        TarjetaDeCreditoDTO tarjeta = getTarjeta(idUsuario, idTarjeta);
        if (tarjeta == null) {
            throw new WebApplicationException("El usuario no cuenta con esa tarjeta de credito", 404);
        }

        dto.setId(tarjeta.getId());
        TarjetaDeCreditoEntity tarjetaActualizada = tarjetaDeCreditoLogic.updatePago(dto.toEntity());

        return new TarjetaDeCreditoDTO(tarjetaActualizada);
    }

    @DELETE
    @Path("{idTarjeta: \\d+}")
    public void eliminarTarjeta(@PathParam("idUsuario") Long idUsuario, @PathParam("idTarjeta") Long idTarjeta) {
         TarjetaDeCreditoDTO tarjeta = getTarjeta(idUsuario, idTarjeta);
        if (tarjeta == null) {
            throw new WebApplicationException("El usuario no cuenta con esa tarjeta de credito", 404);
        }
        
        tarjetaDeCreditoLogic.deletePago(idTarjeta);
    }

    /**
     *
     * @param listatarjetaEntity
     * @return
     */
    private List<TarjetaDeCreditoDTO> listEntity2DTO(List<TarjetaDeCreditoEntity> listatarjetaEntity) {
        List<TarjetaDeCreditoDTO> lista = new ArrayList<>();
        for (TarjetaDeCreditoEntity tarjetaEntity : listatarjetaEntity) {
            lista.add(new TarjetaDeCreditoDTO(tarjetaEntity));
        }
        return lista;
    }

}

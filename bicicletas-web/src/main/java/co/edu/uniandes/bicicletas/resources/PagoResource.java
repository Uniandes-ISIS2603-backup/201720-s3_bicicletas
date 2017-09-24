/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.bicicletas.dtos.PagoDetailDTO;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import co.edu.uniandes.bicicletas.ejb.PagoLogic;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jd.trujillom
 */
@Path("/pagos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class PagoResource {

    @Inject
    PagoLogic logic;

    private static final Logger LOGGER = Logger.getLogger(PagoResource.class.getName());

    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO findPago(@PathParam("id") Long id) {
        PagoEntity pago = logic.find(id);
        if (pago == null) {
            throw new WebApplicationException("El recurso /pagos/" + id + "no existe.", 404);
        }

        return new PagoDetailDTO(pago);
    }

    @GET
    public List<PagoDetailDTO> findAll() {
        List<PagoEntity> listEntity = logic.findAll();
        return listEntity2DetailDTO(listEntity);
    }

    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("id") Long id, PagoDetailDTO pago) {
        pago.setId(id);

        PagoEntity buscado = logic.find(id);
        if (buscado == null) {
            throw new WebApplicationException("El recurso /pagos/" + id + "no existe.", 404);
        }

        PagoEntity updated = logic.updateUsuario(pago.toEntity());
        return new PagoDetailDTO(updated);
    }

    @POST
    public PagoDetailDTO createPago(PagoDetailDTO pago) {
        PagoEntity pagoEntity = pago.toEntity();
        PagoEntity nuevoPago = logic.crearPago(pagoEntity);

        return new PagoDetailDTO(nuevoPago);
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id) {

        PagoEntity buscado = logic.find(id);
        if (buscado == null) {
            throw new WebApplicationException("El recurso /pagos/" + id + "no existe.", 404);
        }

        logic.deleteUsuario(id);

    }

    private List<PagoDetailDTO> listEntity2DetailDTO(List<PagoEntity> entityList) {
        List<PagoDetailDTO> lista = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            lista.add(new PagoDetailDTO(entity));
        }

        return lista;
    }
}

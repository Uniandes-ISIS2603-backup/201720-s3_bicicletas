/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.PagoDetailDTO;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import co.edu.uniandes.bicicletas.ejb.PagoLogic;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
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
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {

    @Inject
    PagoLogic logic;

    @Inject
    ReservaPersistence reservaPersistence;

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

        PagoEntity updated = logic.updatePago(pago.toEntity());
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

        logic.deletePago(id);

    }

    @Path("{idPago: \\d+}/reserva")
    public Class<ReservaPagoResource> getReservaPago(@PathParam("idPago") Long idPago) {
        PagoEntity pago = logic.find(idPago);
        if (pago == null) {
            throw new WebApplicationException("El recurso /pagos/" + idPago + "no existe.", 404);
        }
        return ReservaPagoResource.class;
    }

    private List<PagoDetailDTO> listEntity2DetailDTO(List<PagoEntity> entityList) {
        List<PagoDetailDTO> lista = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            lista.add(new PagoDetailDTO(entity));
        }

        return lista;
    }

    @PUT
    @Path("{idPago: \\d+}/verificarPago")
    public PagoDetailDTO verificarPago(@PathParam("idPago") Long idPago) throws BusinessLogicException {
        PagoEntity pago = logic.find(idPago);

        if (pago == null) {
            throw new BusinessLogicException("No existe una pago con ese id");
        }

        if (pago.getEstado() != PagoEntity.PROCESANDO_PAGO) {
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + " el estado que se encuentra el pago");
        }

        ReservaEntity reserva = pago.getReserva();

        reserva.setEstado(ReservaEntity.REEMBOLSADO);
        reservaPersistence.update(reserva);
        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.PAGADO);
        logic.updatePago(pago);

        return new PagoDetailDTO(pago);

    }

    @PUT
    @Path("{idPago: \\d+}/verificarReembolso")
    public PagoDetailDTO verificarReembolso(@PathParam("idPago") Long idPago) throws BusinessLogicException {
        PagoEntity pago = logic.find(idPago);

        if (pago == null) {
            throw new BusinessLogicException("No existe una pago con ese id");
        }

        if (pago.getEstado() != PagoEntity.PROCESANDO_REEMBOLSO) {
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + " el estado que se encuentra el pago");
        }

        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.REEMBOLSO_TOTAL);
        logic.updatePago(pago);

        return new PagoDetailDTO(pago);

    }
}

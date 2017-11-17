/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.PagoDetailDTO;
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

    /**
     * Logica de pago.
     */
    @Inject
    PagoLogic logic;

    /**
     * Logica de persistencia.
     */
    @Inject
    ReservaPersistence reservaPersistence;

    private static final Logger LOGGER = Logger.getLogger(PagoResource.class.getName());
    
    /**
     * Constante para indicar que no existe
    */
    private static final String NO_EXISTE = "no existe.";
    
    /**
     * Constante para representar el recurso pagos
    */
    private static final String RECURSO_PAGO = "El recurso /pagos/";

    /**
     * Invoca a la logica de pago y busca un pago por id
     * @param id del pago que se quiere recuperar
     * @return el pago con el id dato
     */
    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO findPago(@PathParam("id") Long id) {
        PagoEntity pago = logic.find(id);
        if (pago == null) {
            throw new WebApplicationException(RECURSO_PAGO + id + NO_EXISTE, 404);
        }

        return new PagoDetailDTO(pago);
    }

    /**
     * Invoca la logica de pago y retorna todos los pagos del sistemas.
     * @return una lista con todos los pagos dle sistema.
     */
    @GET
    public List<PagoDetailDTO> findAll() {
        List<PagoEntity> listEntity = logic.findAll();
        return listEntity2DetailDTO(listEntity);
    }

    /**
     * Invoca la logica de pago y actualiza un pago
     * @param id del pago que se quiere actualizar.
     * @param pago que contiene la información con la que se quiere actualizar
     * el pago.
     * @return 
     */
    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("id") Long id, PagoDetailDTO pago) {
        pago.setId(id);

        PagoEntity buscado = logic.find(id);
        if (buscado == null) {
            throw new WebApplicationException(RECURSO_PAGO + id + NO_EXISTE, 404);
        }

        PagoEntity updated = logic.updatePago(pago.toEntity());
        return new PagoDetailDTO(updated);
    }

    /**
     * Invoca la logica del pago y persiste un pago.
     * @param pago que se quiere persistir.
     * @return el pago persistido en la base de datos.
     */
    @POST
    public PagoDetailDTO createPago(PagoDetailDTO pago) {
        PagoEntity pagoEntity = pago.toEntity();
        PagoEntity nuevoPago = logic.crearPago(pagoEntity);

        return new PagoDetailDTO(nuevoPago);
    }

    
    /**
     * Invoca la logica del pago y elimina un pago.
     * @param id del pago que se quiere eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id) {

        PagoEntity buscado = logic.find(id);
        if (buscado == null) {
            throw new WebApplicationException(RECURSO_PAGO + id + NO_EXISTE, 404);
        }

        logic.deletePago(id);

    }

    /**
     * Redirige al recurso que contiene la relación entre pago y reserva. 
     * @param idPago
     * @return 
     */
    @Path("{idPago: \\d+}/reserva")
    public Class<ReservaPagoResource> getReservaPago(@PathParam("idPago") Long idPago) {
        PagoEntity pago = logic.find(idPago);
        if (pago == null) {
            throw new WebApplicationException(RECURSO_PAGO + idPago + NO_EXISTE, 404);
        }
        return ReservaPagoResource.class;
    }

    /**
     * Convierte una lista de entities de pago a DetailDTO
     * @param entityList que se quiere convertir.
     * @return la lista de pagos en formato DTO.
     */
    private List<PagoDetailDTO> listEntity2DetailDTO(List<PagoEntity> entityList) {
        List<PagoDetailDTO> lista = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            lista.add(new PagoDetailDTO(entity));
        }

        return lista;
    }

    /**
     * Invoca la logica de pago y permite verificar si un pago fue realizado.
     * @param idPago que se quiere verificar.
     * @return el pago con el estado cambiado
     * @throws BusinessLogicException si el pago no está en el estado requerido.
     */
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

        reserva.setEstado(ReservaEntity.PAGADA);
        reservaPersistence.update(reserva);
        //Actualizar estado del pago 
        pago.setEstado(PagoEntity.PAGADO);
        logic.updatePago(pago);

        return new PagoDetailDTO(pago);

    }

    /**
     * Invoca la logica de pago y permite aprobar la solicitud de reembolso.
     * @param idPago de la cual se quiere aprobar su reembolso.
     * @return el pago reembolsado.
     * @throws BusinessLogicException si no se encuentra en el estado deseado.
     */
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

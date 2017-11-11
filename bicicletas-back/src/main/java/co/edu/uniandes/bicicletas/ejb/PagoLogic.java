/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.PagoPersistence;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que modela el pago hecho por un usuario antes de hacer efectiva una
 * reserva.
 *
 * @author jd.trujillom
 */
@Stateless
public class PagoLogic {

    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());
    
    /**
     * Constante para indicar algo sobre una reserva
    */
    private static final String RESERVA_ID = "La reserva con id: ";
    
    /**
     * Constante para indicar que no se tiene un pago asociado
    */
    private static final String NO_PAGO = " no tiene un pago asociado";

    @Inject
    PagoPersistence persistence;

    @Inject
    ReservaPersistence reservaPersistence;

    @Inject
    UsuarioLogic usuarioLogic;

    @Inject
    PuntoLogic puntoLogic;

    public PagoEntity crearPago(PagoEntity entity) {

        LOGGER.info("Inicia proceso de creación de editorial");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        // Invoca la persistencia para crear la editorial
        persistence.createPago(entity);
        LOGGER.info("Termina proceso de creación de editorial");
        return entity;
    }

    public PagoEntity find(Long idPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con idPago={0}", idPago);
        PagoEntity estacion = persistence.find(idPago);
        if (estacion == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", idPago);
        }

        return estacion;
    }

    public PagoEntity updatePago(PagoEntity pago) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", pago.getId());
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PagoEntity newEntity = persistence.updatePago(pago);
        LOGGER.log(Level.INFO, "Termina proceso de actualizarpago con id={0}", pago.getId());
        return newEntity;
    }

    public void deletePago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delatePago(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id={0}", id);
    }

    public List<PagoEntity> findAll() {
        return persistence.findAll();
    }

    public ReservaEntity darReserva(Long idPago) {
        PagoEntity pago = find(idPago);
        if (pago == null) {
            throw new WebApplicationException("No se encontró un pago con el id"
                    + "dado");
        }

        return pago.getReserva();
    }

    public ReservaEntity actualizarEstadoReserva(Long idPago, Integer nuevoEstado) {
        ReservaEntity reserva = darReserva(idPago);
        Integer estadoReserva = reserva.getEstado();
        if (Objects.equals(nuevoEstado, PagoEntity.PAGADO) && estadoReserva == ReservaEntity.PAGO) //Cambiar por esperando pago
        {
            reserva.setEstado(ReservaEntity.PAGADA);
            
        } else if ((Objects.equals(nuevoEstado, PagoEntity.REEMBOLSO_TOTAL)
                || Objects.equals(nuevoEstado, PagoEntity.REEMBOLSO_PARCIAL)) && estadoReserva == 10) //Cambiar reembolso solicitado
        {
            reserva.setEstado(10);
        }

        return reserva;
    }

    public PagoEntity pagarReservaConPuntos(Long idReserva) throws BusinessLogicException {

        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        if (reserva.getPago() == null) {
            throw new BusinessLogicException(RESERVA_ID + idReserva
                    + NO_PAGO);
        }

        if (pago.getEstado() != PagoEntity.ESPERANDO_PAGO) {
            throw new BusinessLogicException("No se puede realizar el pago con puntos "
                    + " en el estado que se encuentra el pago");
        }

        if (pago.getBicicletasPendientes() == 0) {
            throw new BusinessLogicException("Ya no hay bicicletas por pagar");
        }

        //Verificar si es posible el pago con puntos
        UsuarioEntity usuario = reserva.getUsuarioReserva();
        try {
            puntoLogic.deletePuntos(usuario.getDocumentoUsuario());
        } catch (BusinessLogicException e) {
            LOGGER.log(Level.SEVERE, "Excepcion:", e);
            throw new BusinessLogicException("El usuario cuenta con "
                    + usuario.getPuntos().size() + " puntos  y son necesario "
                    + 10 + " puntos para pagar la reserva");
        
        }
        //Se desminuye en uno las bicicletas pendientes
        pago.setBicicletasPendientes(pago.getBicicletasPendientes() - 1);
        
        //Se recalcula el costo de una reserva
        reserva.setPrecioFinalNumBicicletas(pago.getBicicletasPendientes());
        double monto = reserva.getPrecioFinal();
        if (reserva.getDescuento()) {
            monto -= monto * 0.05;
        }

        //Actualizar nuevo costo y disminuir bicicletas pendientes. 
        pago.setMonto(monto);
        

        //Verificar pago completo
        if (pago.getBicicletasPendientes() == 0) {
           // sumarPunto(reserva.getUsuarioReserva().getDocumentoUsuario());
            pago.setEstado(PagoEntity.PAGADO);
            reserva.setEstado(ReservaEntity.PAGO);
        }

        //Actualizar pago y reserva
        PagoEntity updatePago = updatePago(pago);
        reservaPersistence.update(reserva);

        return updatePago;
    }

    public PagoEntity pagarReserva(Long idReserva, int metodoDePago, String contrasenia) throws BusinessLogicException {
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        if (reserva.getPago() == null) {
            throw new BusinessLogicException(RESERVA_ID + idReserva
                    + NO_PAGO);
        }

        if (pago.getEstado() != PagoEntity.ESPERANDO_PAGO) {
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + "el estado que se encuentra el pago");
        }

        if (metodoDePago == PagoEntity.TARJETA_DE_CREDITO) {
            pago = pagarConTarjeta(reserva, pago, contrasenia);
        } else if (metodoDePago == PagoEntity.PSE) {
            pagarConPSE(reserva, pago, contrasenia);
        }

        return pago;
    }

    private PagoEntity pagarConTarjeta(ReservaEntity reserva, PagoEntity pago, String contraseniaTarjeta) throws BusinessLogicException {
        UsuarioEntity usuario = reserva.getUsuarioReserva();
        int csvUsuario = usuario.getNumeroCsv();
        int csvActual = Integer.parseInt(contraseniaTarjeta);

        if (csvActual != csvUsuario) {
            throw new BusinessLogicException("La contraseña no coincide con la registrada");
        }

        //El pago es posible
        pago.setEstado(PagoEntity.PROCESANDO_PAGO);
        pago.setFecha(new Date(System.currentTimeMillis()));
        pago.setIdUsuario(usuario.getDocumentoUsuario());
        pago.setMetodoDePago(PagoEntity.TARJETA_DE_CREDITO);

        //El usuario acumula un nuevo punto
        sumarPunto(usuario.getDocumentoUsuario());

        //Actualiza el pago.
        PagoEntity pagoActualizado = updatePago(pago);

        return pagoActualizado;
    }

    private PagoEntity pagarConPSE(ReservaEntity reserva, PagoEntity pago, String contraseniaPSE) throws BusinessLogicException {
        UsuarioEntity usuario = reserva.getUsuarioReserva();
        String pseContraseniaReal = usuario.getContraseniaPSE();

        if (!contraseniaPSE.equals(pseContraseniaReal)) {
            throw new BusinessLogicException("La contraseña no coincide con la registrada");
        }

        //El pago es posible
        pago.setEstado(PagoEntity.PROCESANDO_PAGO);
        pago.setFecha(new Date(System.currentTimeMillis()));
        pago.setIdUsuario(usuario.getDocumentoUsuario());
        pago.setMetodoDePago(PagoEntity.PSE);

        //El usuario acumula un punto
        sumarPunto(usuario.getDocumentoUsuario());

        //Actualiza el pago.
        PagoEntity pagoActualizado = updatePago(pago);

        return pagoActualizado;
    }

    private void sumarPunto(Long idUsuario) throws BusinessLogicException {
        puntoLogic.createPunto(idUsuario);
    }

    public PagoEntity solicitarReembolso(Long idReserva) throws BusinessLogicException {
        ReservaEntity reserva = reservaPersistence.find(idReserva);
        PagoEntity pago = reserva.getPago();

        if (reserva.getPago() == null) {
            throw new BusinessLogicException(RESERVA_ID + idReserva
                    + NO_PAGO);
        }

        if (!pago.getEstado().equals(PagoEntity.PAGADO)) {
            throw new BusinessLogicException("No se puede realizar el pago en"
                    + "el estado que se encuentra el pago");
        }
        Date dateActual = new Date(System.currentTimeMillis());
        Date dateInicio = reserva.getFechaInicio();

        Long timestampActual = dateActual.getTime();
        Long timestampIncio = dateInicio.getTime();

        Long timestampRestante = timestampIncio - timestampActual;

        if (timestampRestante > 1) {
            pago.setEstado(PagoEntity.PROCESANDO_REEMBOLSO);
            reserva.setEstado(ReservaEntity.CANCELADA);
        } else {
            pago.setEstado(PagoEntity.REEMBOLSO_PARCIAL);
            pago.setMonto(pago.getMonto() * 0.8);
            reserva.setEstado(ReservaEntity.REEMBOLSADO);
            reservaPersistence.update(reserva);
        }

        //Actualizar estado del pago 
        updatePago(pago);

        return pago;

    }

}

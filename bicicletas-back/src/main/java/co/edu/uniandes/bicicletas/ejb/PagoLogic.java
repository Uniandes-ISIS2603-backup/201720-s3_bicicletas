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

    /**
     * Logger de la clase PagoLogic
     */
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

    /**
     * Persiste el pago que revise por parametro en la base de datoss
     * @param entity que se quiere persistir.
     * @return el pago ya persistido en la base de datos. 
     */
    public PagoEntity crearPago(PagoEntity entity) {

        LOGGER.info("Inicia proceso de creación de editorial");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        // Invoca la persistencia para crear la editorial
        persistence.createPago(entity);
        LOGGER.info("Termina proceso de creación de editorial");
        return entity;
    }

    /**
     * Retorna el pago con el id dato por parametro
     * @param idPago del pago que se quiere recuperar
     * @return el pago buscado, null de lo contrario
     */
    public PagoEntity find(Long idPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con idPago={0}", idPago);
        PagoEntity estacion = persistence.find(idPago);
        if (estacion == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", idPago);
        }

        return estacion;
    }

    /**
     * Actualiza el pago que es pasado por parametro
     * @param el pago que se quiere actualizar
     * @return el pago actualiazado. 
     */
    public PagoEntity updatePago(PagoEntity pago) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", pago.getId());
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PagoEntity newEntity = persistence.updatePago(pago);
        LOGGER.log(Level.INFO, "Termina proceso de actualizarpago con id={0}", pago.getId());
        return newEntity;
    }

    /**
     * Elimina un pago dado su id.
     * @param id del pago que se quiere eliminar.
     */
    public void deletePago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delatePago(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id={0}", id);
    }

    /**
     * Retorna todos los pagos del sistema
     * @return una lista con los pagos del sistema
     */
    public List<PagoEntity> findAll() {
        return persistence.findAll();
    }

    /**
     * Dado el id de un pago, retorna la reserva asociada.
     * @param idPago cuya reserva se quiere recuperar.
     * @return la reserva del pago cuyo id fue pasado por parametro
     */
    public ReservaEntity darReserva(Long idPago) {
        PagoEntity pago = find(idPago);
        if (pago == null) {
            throw new WebApplicationException("No se encontró un pago con el id"
                    + "dado");
        }

        return pago.getReserva();
    }

    /**
     * Actualiza el estado de una reserva.
     * @param idPago cuya reserva se quiere actualizar
     * @param nuevoEstado al que se quiere cambiar el estado de la reserva.
     * @return la reserva actualizada.
     */
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

    /**
     * Permite realizar el pago de una bicicleta con puntos.
     * @param idReserva que se quiere pagar con puntos.
     * @return el pago asociado a la reserva.
     * @throws BusinessLogicException en caso que el usuario no tenga puntos suficientes.
     */
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

    /**
     * Método con el que se paga una reserva, ya sea por PSE o tarjeta
     * @param idReserva que se quiere pagar.
     * @param metodoDePago que es 1 para tarjeta y 2 para PSE
     * @param contrasenia que puede ser el CSV o la contraseña de PSE.
     * @return el pago con el pago ya realiza.
     * @throws BusinessLogicException si el estado en el que se encuentra el pago no es valido.
     */
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

    /**
     * Método con el que se paga con la tarjeta de credito.
     * @param reserva que se quiere pagar con tarjeta de credito.
     * @param pago asociado a la reserva.
     * @param contraseniaTarjeta que está asociada al usuario de la reserva.
     * @return el pago asociado a la reserva.
     * @throws BusinessLogicException si la contraseña no es valida.
     */
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
        return updatePago(pago);
 
    }

     /**
     * Método con el que se paga con PSE.
     * @param reserva que se quiere pagar con PSE
     * @param pago asociado a la reserva.
     * @param contraseniaPSE que está asociada al usuario de la reserva.
     * @return el pago asociado a la reserva.
     * @throws BusinessLogicException si la contraseña no es valida.
     */
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
        return updatePago(pago);
    }

    /**
     * Invoca a la logica de punto.
     * @param idUsuario
     * @throws BusinessLogicException 
     */
    private void sumarPunto(Long idUsuario) throws BusinessLogicException {
        puntoLogic.createPunto(idUsuario);
    }

    /**
     * Solicitar un reembolso sobre una reserva.
     * @param idReserva de la reserva sobre la cual se quiere solicitar un reembolso. 
     * @return el pago asociado a la reserva.
     * @throws BusinessLogicException  si la reserva no se encuentra en el estado pagado. 
     */
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

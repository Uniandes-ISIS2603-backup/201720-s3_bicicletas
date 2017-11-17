/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.PagoEntity;
import java.util.Date;

/**
 *
 * @author jd.trujillom
 */
public class PagoDTO {
    /**
     * Id que identifica a un pago.
     */
    private Long id;
   
    /**
     *  Monto de un pago.
     */
    private Double monto;
    
    /**
     * Fecha en la que se hizo un pago.
     */
    private Date fecha;
    
    /**
     * Estado en el que se encuentra un pago.
     */
    private Integer estado;
    
    /**
     * Id de la transacción asociada a un pago.
     */
    private Long idTransaccion;
    
    /**
     * Usuario que realizó un pago.
     */
    private Long idUsuario;
    
    /**
     * Bicicletas que aún no han sido pagados.
     */
    private Integer bicicletasPendientes;
    
    /**
     * Método con el que se realizó un pago. 
     */
    private Integer metodoDePago;
    
    /**
     * Constructor por defecto
     */
    public PagoDTO(){
        //No necesita cuerpo
    }
    
    /**
     * Constructor que recibe un entity como parametro
     * @param pago en forma entity.
     */
    public PagoDTO(PagoEntity pago){
        this.id = pago.getId();
        this.monto = pago.getMonto();
        this.fecha = pago.getFecha();
        this.estado = pago.getEstado();
        this.idTransaccion = pago.getIdTransaccion();
        this.idUsuario = pago.getIdUsuario();
        this.bicicletasPendientes = pago.getBicicletasPendientes();
        this.metodoDePago = pago.getMetodoDePago();
    }

    /**
     * Retorna el id del pago
     * @return id del pago
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id de un pago
     * @param id por el que se quiere cambiar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el monto del pago.
     * @return el monto del pago.
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Cambia el monto del pago
     * @param monto por el que se quiere cambiar el monto del pago.
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Retorna la fecha en la que se efectuó el pago.
     * @return la feche del pago.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Cambia la fecha en la que se efectuó el pago.
     * @param fecha por la que se quiere cambiar la fecha del pago..
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Retorna el estado actual del pago.
     * @return el estado del pago.
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * Cambia el estado en el que se encuentra el pago.
     * @param estado por el cual se quiere cambiar el pago.
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * Retorna la transacción asociada al pago.
     * @return la transacción asociada al pago.
     */
    public Long getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Cambia la transacción asociada a un pago.
     * @param idTransaccion 
     */
    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * Retorna el usuario asociado a un pago.
     * @return el id del usuario.
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Cambia el usuario asociado a un pago
     * @param idUsuario del usuario que se quiere cambiar del pago.
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Retorna el número de bicicletas que no ha sido pagado.
     * @return el número de bicicletas pendientes.
     */
    public Integer getBicicletasPendientes() {
        return bicicletasPendientes;
    }

    /**
     * Cambia el numero de bicicletas pendientes
     * @param bicicletasPendientes por las cuales se quiere cambiar las bicicletas
     * pendientes.
     */
    public void setBicicletasPendientes(Integer bicicletasPendientes) {
        this.bicicletasPendientes = bicicletasPendientes;
    }

    /**
     * El método de pago empleado para realizar el pago.
     * @return el método de pago empleado.
     */
    public Integer getMetodoDePago() {
        return metodoDePago;
    }

    /**
     * Cambia el metodo de pago empleado para realziar el pago.
     * @param metodoDePago por el que se quiere cambiar.
     */
    public void setMetodoDePago(Integer metodoDePago) {
        this.metodoDePago = metodoDePago;
    }
    
    
    /**
     * Convierte el DTO en un entity
     * @return el pago en formato DTO.
     */
    public PagoEntity toEntity(){
        PagoEntity entity = new PagoEntity();
        entity.setId(this.id);
        entity.setMonto(this.monto);
        entity.setFecha(this.fecha);
        entity.setEstado(this.estado);
        entity.setIdTransaccion(this.idTransaccion);
        entity.setIdUsuario(this.idUsuario);
        entity.setBicicletasPendientes(bicicletasPendientes);
        entity.setMetodoDePago(metodoDePago);
        
        return entity;
    }   
    
    
}

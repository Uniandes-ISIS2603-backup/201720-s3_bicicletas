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
    
    private Long id;
    private Double monto;
    private Date fecha;
    private Integer estado;
    private Long idTransaccion;
    private Long idUsuario;
    
    public PagoDTO(PagoEntity pago){
        this.id = pago.getId();
        this.monto = pago.getMonto();
        this.fecha = pago.getFecha();
        this.estado = pago.getEstado();
        this.idTransaccion = pago.getIdTransaccion();
        this.idUsuario = pago.getIdUsuario();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public PagoEntity toEntity(){
        PagoEntity entity = new PagoEntity();
        entity.setId(this.id);
        entity.setMonto(this.monto);
        entity.setFecha(this.fecha);
        entity.setEstado(this.estado);
        entity.setIdTransaccion(this.idTransaccion);
        entity.setIdUsuario(this.idUsuario);
        
        return entity;
    }   
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import java.util.Date;

/**
 *
 * @author ds.chacon
 */
public class ReservaDetailDTO extends ReservaDTO{
    
   

    
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ReservaDetailDTO(ReservaEntity entidad) {
        super(entidad);
//        this.descuento=entidad.getDescuento();
//        this.idEstacionSalida=entidad.getEstacionSalida().getId();
//        this.estado=entidad.getEstado();
//        this.fechaReserva=entidad.getFechaReserva();
//        this.fechaInicio=entidad.getFechaInicio().toString();
//        this.fechaEntrega=entidad.getFechaEntrega().toString();
//        this.fechaFinal=entidad.getFechaFinal().toString();
//        this.idReserva=entidad.getId();
//        this.preciofinal=entidad.getPrecioFinal();
//        this.idUsuario=entidad.getUsuarioReserva().getDocumentoUsuario();  
    }
    
//    
//    public Boolean getDescuento() {
//        return descuento;
//    }
//
//    public void setDescuento(Boolean descuento) {
//        this.descuento = descuento;
//    }
//
//    public Long getIdEstacionSalida() {
//        return idEstacionSalida;
//    }
//
//    public void setIdEstacionSalida(Long idEstacionSalida) {
//        this.idEstacionSalida = idEstacionSalida;
//    }
//
//    public int getEstado() {
//        return estado;
//    }
//
//    public void setEstado(int estado) {
//        this.estado = estado;
//    }
//
//    public Date getFechaReserva() {
//        return fechaReserva;
//    }
//
//    public void setFechaReserva(Date fechaReserva) {
//        this.fechaReserva = fechaReserva;
//    }
//
//    public String getFechaEntrega() {
//        return fechaEntrega;
//    }
//
//    public void setFechaEntrega(String fechaEntrega) {
//        this.fechaEntrega = fechaEntrega;
//    }
//
//    public String getFechaInicio() {
//        return fechaInicio;
//    }
//
//    public void setFechaInicio(String fechaInicio) {
//        this.fechaInicio = fechaInicio;
//    }
//
//    public String getFechaFinal() {
//        return fechaFinal;
//    }
//
//    public void setFechaFinal(String fechaFinal) {
//        this.fechaFinal = fechaFinal;
//    }
//
//    public Long getIdReserva() {
//        return idReserva;
//    }
//
//    public void setIdReserva(Long idReserva) {
//        this.idReserva = idReserva;
//    }
//
//    public double getPreciofinal() {
//        return preciofinal;
//    }
//
//    public void setPreciofinal(double preciofinal) {
//        this.preciofinal = preciofinal;
//    }
//
//    public long getIdUsuario() {
//        return idUsuario;
//    }
//
//    public void setIdUsuario(long idUsuario) {
//        this.idUsuario = idUsuario;
//    }
    
    
     /**
     * Constructor por defecto
     */
    public ReservaDetailDTO() {
        //No necesita cuerpo
    }
   
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ReservaEntity toEntity() {
        return super.toEntity();
    }
    
}

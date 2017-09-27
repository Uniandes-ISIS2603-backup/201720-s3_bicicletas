/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import java.util.Date;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.util.List;

/**
 *
 * @author ds.chacon
 */
public class ReservaDTO {
    
    
    int estado;
    long id;
    Date FechaEntrega;
    Date FechaInicio;
    long idReserva;
    double PrecioFinal;
    CalificacionEntity calificacionEstacionOrigen;
    CalificacionEntity calificacionEstacionLlegada;
    UsuarioEntity usuarioReserva;
    EstacionEntity EstacionLlegada;
    EstacionEntity EstacionOrigen;
    List<EstacionEntity> estaciones;
    PagoEntity pago;

    public CalificacionEntity getCalificacionEstacionOrigen() {
        return calificacionEstacionOrigen;
    }

    public void setCalificacionEstacionOrigen(CalificacionEntity calificacionEstacionOrigen) {
        this.calificacionEstacionOrigen = calificacionEstacionOrigen;
    }

    public CalificacionEntity getCalificacionEstacionLlegada() {
        return calificacionEstacionLlegada;
    }

    public void setCalificacionEstacionLlegada(CalificacionEntity calificacionEstacionLlegada) {
        this.calificacionEstacionLlegada = calificacionEstacionLlegada;
    }

    public UsuarioEntity getUsuarioReserva() {
        return usuarioReserva;
    }

    public void setUsuarioReserva(UsuarioEntity usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    public EstacionEntity getEstacionLlegada() {
        return EstacionLlegada;
    }

    public void setEstacionLlegada(EstacionEntity EstacionLlegada) {
        this.EstacionLlegada = EstacionLlegada;
    }

    public List<EstacionEntity> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<EstacionEntity> estaciones) {
        this.estaciones = estaciones;
    }

    public PagoEntity getPago() {
        return pago;
    }

    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(Date FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public double getPrecioFinal() {
        return PrecioFinal;
    }

    public void setPrecioFinal(double PrecioFinal) {
        this.PrecioFinal = PrecioFinal;
    }

    public EstacionEntity getEstacionOrigen() {
        return EstacionOrigen;
    }

    public void setEstacionOrigen(EstacionEntity EstacionOrigen) {
        this.EstacionOrigen = EstacionOrigen;
    }
    
     
    public ReservaDTO(){
        
    }
    
    public ReservaDTO(ReservaEntity entidad) {
        if(entidad!=null){
            this.id = entidad.getId();
            this.estado = entidad.getEstado();
            this.FechaEntrega = entidad.getFechaEntrega();
            this.FechaInicio = entidad.getFechaInicio();
            this.idReserva = entidad.getIdReserva();
            this.PrecioFinal = entidad.getPrecioFinal();
            this.EstacionLlegada = entidad.getEstacionLlegada();
            this.FechaEntrega = entidad.getFechaEntrega();
            this.FechaInicio = entidad.getFechaInicio();
            this.PrecioFinal = entidad.getPrecioFinal();
            this.calificacionEstacionLlegada = entidad.getCalificacionEstacionLlegada();
            this.calificacionEstacionOrigen = entidad.getCalificacionEstacionOrigen();
            this.estaciones = entidad.getEstaciones();
            this.usuarioReserva = entidad.getUsuarioReserva();
            this.pago = entidad.getPago();
        }
    }
    
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.getId());
        entity.setEstado(this.getEstado());
        entity.setFechaEntrega(this.getFechaEntrega());
        entity.setFechaInicio(this.getFechaInicio());
        entity.setPrecioFinal(this.getPrecioFinal());
        entity.setIdReserva(this.getIdReserva());
        entity.setPrecioFinal(this.getPrecioFinal());
        entity.setEstacionLlegada(this.getEstacionLlegada());
        entity.setFechaEntrega(this.getFechaEntrega());
        entity.setFechaInicio(this.getFechaInicio());
        entity.setCalificacionEstacionLlegada(this.getCalificacionEstacionLlegada());
        entity.setCalificacionEstacionOrigen(this.getCalificacionEstacionOrigen());
        entity.setEstaciones(this.getEstacionLlegada(), this.getEstacionOrigen());
        entity.setUsuarioReserva(this.getUsuarioReserva());
        entity.setPago(this.getPago());
        
        return entity;
    }
    
}

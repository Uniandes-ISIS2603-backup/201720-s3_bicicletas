/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import java.util.Date;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;

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
        return entity;
    }
    
}

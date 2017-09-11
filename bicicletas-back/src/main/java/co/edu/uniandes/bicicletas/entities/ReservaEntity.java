/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *Clase que modela reserva
 * @author ds.chacon
 */
public class ReservaEntity extends BaseEntity implements Serializable{
    /*
    idreserva
    idususario
    diaresreva
    fechainiccio
    fechaentrega
    pago
    calificafion
    idestacionfin
    idesatacioninicio
    */
    private long idReserva;
    
    private long idUsuario;
    
    private Date fechaInicio;
    
    private Date fechaFin;
    
    double Pago;
    
    double calificafion;
    
    long idEstacionInicio;
    
    long idEstacionfin;

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPago() {
        return Pago;
    }

    public void setPago(double Pago) {
        this.Pago = Pago;
    }

    public double getCalificafion() {
        return calificafion;
    }

    public void setCalificafion(double calificafion) {
        this.calificafion = calificafion;
    }

    public long getIdEstacionInicio() {
        return idEstacionInicio;
    }

    public void setIdEstacionInicio(long idEstacionInicio) {
        this.idEstacionInicio = idEstacionInicio;
    }

    public long getIdEstacionfin() {
        return idEstacionfin;
    }

    public void setIdEstacionfin(long idEstacionfin) {
        this.idEstacionfin = idEstacionfin;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ds.chacon
 */
@Entity

public class ReservaEntity extends BaseEntity implements Serializable {
    public static int CANCELADO=2; 
    public static int PAGO=1;
    public static int PAGADO=0; 
    
    int estado;
    
    @Temporal(TemporalType.DATE)
    Date FechaEntrega;
    
    @Temporal(TemporalType.DATE)
    Date FechaInicio;
    
    long idReserva;
    double PrecioFinal;
    
    @PodamExclude
    private CalificacionEntity calificacionEstacionLlegada;
    
    @PodamExclude
    private CalificacionEntity calificacionEstacionOrigen;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
    

    /**
     * @return the calificacionEstacionLlegada
     */
    public CalificacionEntity getCalificacionEstacionLlegada() {
        return calificacionEstacionLlegada;
    }

    /**
     * @param calificacionEstacionLlegada the calificacionEstacionLlegada to set
     */
    public void setCalificacionEstacionLlegada(CalificacionEntity calificacionEstacionLlegada) {
        this.calificacionEstacionLlegada = calificacionEstacionLlegada;
    }

    /**
     * @return the calificacionEstacionOrigen
     */
    public CalificacionEntity getCalificacionEstacionOrigen() {
        return calificacionEstacionOrigen;
    }

    /**
     * @param calificacionEstacionOrigen the calificacionEstacionOrigen to set
     */
    public void setCalificacionEstacionOrigen(CalificacionEntity calificacionEstacionOrigen) {
        this.calificacionEstacionOrigen = calificacionEstacionOrigen;
    }
    
    
    
}

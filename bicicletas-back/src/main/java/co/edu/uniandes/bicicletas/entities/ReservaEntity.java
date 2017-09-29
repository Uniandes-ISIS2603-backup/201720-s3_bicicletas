/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ds.chacon
 */
@Entity

public class ReservaEntity extends BaseEntity implements Serializable {
    
  
  
    public final static int PAGADO=0;
    public final static int PAGO=1;
    public final static int CANCELADO=2;
    public final static int USO=3;
    public final static int REENBOLSADO=4;
    
    
    @Temporal(TemporalType.DATE)
    private Date FechaEntrega;
    
    @Temporal(TemporalType.DATE)
    private Date FechaInicio;
    
    private long idReserva;
    
    private int Estado;
    
    @OneToOne
    @PodamExclude
    private CalificacionEntity calificacionEstacionOrigen;
    
    @OneToOne
    @PodamExclude
    private CalificacionEntity calificacionEstacionLlegada;
    
    @ManyToOne
    @PodamExclude
    private UsuarioEntity usuarioReserva;
    
    @OneToOne
    @PodamExclude
    private EstacionEntity EstacionLlegada;
    
    @OneToOne
    @PodamExclude
    private EstacionEntity EstacionSalida;
    
    
    @ManyToMany
    @PodamExclude
    private List<EstacionEntity> estaciones;
    
    @OneToOne 
    @PodamExclude
    private PagoEntity pago;
    
    private double precioFinal;

    
    public List<EstacionEntity> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(EstacionEntity salida, EstacionEntity llegada) {
        this.estaciones.add(salida);
        this.estaciones.add(llegada);
    }
    
    
    /**
     * @return the FechaEntrega
     */
    public Date getFechaEntrega() {
        return FechaEntrega;
    }

    /**
     * @param FechaEntrega the FechaEntrega to set
     */
    public void setFechaEntrega(Date FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }

    /**
     * @return the FechaInicio
     */
    public Date getFechaInicio() {
        return FechaInicio;
    }

    /**
     * @param FechaInicio the FechaInicio to set
     */
    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    /**
     * @return the idReserva
     */
    public long getIdReserva() {
        return idReserva;
    }

    /**
     * @param idReserva the idReserva to set
     */
    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * @return the Estado
     */
    public int getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(int Estado) {
        this.Estado = Estado;
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
     * @return the usuarioReserva
     */
    public UsuarioEntity getUsuarioReserva() {
        return usuarioReserva;
    }

    /**
     * @param usuarioReserva the usuarioReserva to set
     */
    public void setUsuarioReserva(UsuarioEntity usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    /**
     * @return the EstacionLlegada
     */
    public EstacionEntity getEstacionLlegada() {
        return EstacionLlegada;
    }

    /**
     * @param EstacionLlegada the EstacionLlegada to set
     */
    public void setEstacionLlegada(EstacionEntity EstacionLlegada) {
        this.EstacionLlegada = EstacionLlegada;
    }

    /**
     * @return the EstacionSalida
     */
    public EstacionEntity getEstacionSalida() {
        return EstacionSalida;
    }

    /**
     * @param EstacionSalida the EstacionSalida to set
     */
    public void setEstacionSalida(EstacionEntity EstacionSalida) {
        this.EstacionSalida = EstacionSalida;
    }

    /**
     * @return the pago
     */
    public PagoEntity getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    /**
     * @return the PrecioFinal
     */
    public double getPrecioFinal() {
        return precioFinal;
    }

    /**
     * @param PrecioFinal the PrecioFinal to set
     */
    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    

  }




    
    
    

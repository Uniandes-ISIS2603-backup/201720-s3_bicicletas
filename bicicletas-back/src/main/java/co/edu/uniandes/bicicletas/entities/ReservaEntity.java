
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
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
    @PodamExclude
    private Date fechaEntrega;
    
    @Temporal(TemporalType.DATE)
    @PodamExclude
    private Date fechaInicio;
     
    private int estado;
    
    @OneToOne
    @PodamExclude
    private CalificacionEntity calificacionEstacionOrigen;
    
    @OneToOne
    @PodamExclude
    private CalificacionEntity calificacionEstacionLlegada;
    
    @ManyToOne
    @PodamExclude
    @XmlInverseReference(mappedBy="reservas")
    private UsuarioEntity usuarioReserva;
    
    @PodamExclude
    private EstacionEntity estacionLlegada;
    
    @ManyToOne
    @PodamExclude
    private EstacionEntity estacionSalida;
    
    @OneToOne 
    @PodamExclude
    private PagoEntity pago;
    
    @OneToMany
    @PodamExclude
    private List<BicicletaEntity> bicicletas = new ArrayList<>();
    
    private double precioFinal;

     
    public void setBicis(List<BicicletaEntity> temp){
        setBicicletas(temp);
     }
     public List<BicicletaEntity> getBicis(){
         return getBicicletas();
     }

    /**
     * @return the fechaEntrega
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
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
     * @return the estacionLlegada
     */
    public EstacionEntity getEstacionLlegada() {
        return estacionLlegada;
    }

    /**
     * @param estacionLlegada the estacionLlegada to set
     */
    public void setEstacionLlegada(EstacionEntity estacionLlegada) {
        this.estacionLlegada = estacionLlegada;
    }

    /**
     * @return the estacionSalida
     */
    public EstacionEntity getEstacionSalida() {
        return estacionSalida;
    }

    /**
     * @param estacionSalida the estacionSalida to set
     */
    public void setEstacionSalida(EstacionEntity estacionSalida) {
        this.estacionSalida = estacionSalida;
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
     * @return the bicicletas
     */
    public List<BicicletaEntity> getBicicletas() {
        return bicicletas;
    }

    /**
     * @param bicicletas the bicicletas to set
     */
    public void setBicicletas(List<BicicletaEntity> bicicletas) {
        this.bicicletas = bicicletas;
    }

    /**
     * @return the precioFinal
     */
    public double getPrecioFinal() {
        return precioFinal;
    }

    /**
     * @param precioFinal the precioFinal to set
     */
    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }
     
     

}

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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ds.chacon
 */
@Entity

public class ReservaEntity extends BaseEntity implements Serializable {
    
    public final static int PAGADA=0;
    public final static int PAGO=1;
    public final static int CANCELADA=2;
    public final static int USO=3;
    public final static int REEMBOLSADO=4;
    public final static int FINALIZADA=5;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
     
    private int estado;
    
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    @PodamExclude
    private List<CalificacionEntity> calificaciones = new ArrayList<>();
    
    @ManyToOne
    @PodamExclude
    @XmlInverseReference(mappedBy="reservas")
    private UsuarioEntity usuarioReserva;
        
    @ManyToOne
    @PodamExclude
    @XmlInverseReference(mappedBy="reservas")
    private EstacionEntity estacionSalida;
    
    @PodamExclude
    private Long estacionLlegada;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAGO_ID")
    @PodamExclude
    private PagoEntity pago;
    
    @OneToOne
    @JoinColumn(name="TRANSACCION_ID")
    @PodamExclude
    private TransaccionEntity transaccion;
                    
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.REFRESH)
    @PodamExclude
    private List<BicicletaEntity> bicicletas = new ArrayList<>();
    
    @OneToMany
    @PodamExclude
    private List<AccesorioEntity> accesorios = new ArrayList<>();
    
    private double precioFinal;
    
    @Temporal(TemporalType.TIMESTAMP)
    //@PodamExclude
    private Date fechaReserva;
    
    @Temporal(TemporalType.TIMESTAMP)
    //@PodamExclude
    private Date fechaFinal;

    public Long getEstacionLlegada() {
        return estacionLlegada;
    }

    
    public void setEstacionLlegada(Long estacionLlegada) {
        this.estacionLlegada = estacionLlegada;
    }

    
   
    public Date getFechaFinal() {
        if (fechaFinal==null){
         return new Date(0, 0, 0);
        }else{
          return fechaFinal; 
        }
     
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    private Boolean Descuento;

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Boolean getDescuento() {
        return Descuento;
    }

    public void setDescuento() {
        if(fechaReserva.getDay()== fechaInicio.getDay() && fechaReserva.getYear()== fechaInicio.getYear() && fechaReserva.getMonth()== fechaInicio.getMonth()){
           this.Descuento= new Boolean(false) ;
        }else{
           this.Descuento= new Boolean(true);
        }
    }
    
   
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
     * @param calificacionEstacionLlegada the calificacionEstacionLlegada to set
     */
    public void setCalificacionEstacionLlegada(CalificacionEntity calificacionEstacionLlegada) {
        this.calificaciones.add(1, calificacionEstacionLlegada);
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
        if(bicicletas.isEmpty()){
        this.precioFinal = bicicletas.size()*PagoEntity.PRECIO_BICICLETA_HORA;
        }else{
        this.precioFinal = 0;
        }
    }

    /**
     * @param calificacionEstacionSalida the calificacionEstacionSalida to set
     */
    public void setCalificacionEstacionSalida(CalificacionEntity calificacionEstacionSalida) {
        this.calificaciones.add(0, calificacionEstacionSalida);
    }

    public List<AccesorioEntity> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<AccesorioEntity> accesorios) {
        this.accesorios = accesorios;
    }

    public TransaccionEntity getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(TransaccionEntity transaccion) {
        this.transaccion = transaccion;
    }
    
    public List<CalificacionEntity> getCalificaciones()
    {
        return this.calificaciones;
    }
    

}

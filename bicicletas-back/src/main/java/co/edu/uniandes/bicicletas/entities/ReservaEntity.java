
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *  Reserva que hace un usuario al sistema 
 * @author ds.chacon
 */
@Entity

public class ReservaEntity extends BaseEntity implements Serializable {
    
    /*
     *Constante que repesenta el estado de una reserva.Que la reseserva fue pagada 
     */
    public static final int PAGADA = 0;
    /*
     * La reserva requiere ser pagada 
     */
    public static final int PAGO = 1;
    /*
     * la reserva fue cancelada
     */
    public static final int CANCELADA = 2;
    /*
     * la reserva esta en uso
     */
    public static final int USO = 3;
    /*
     * el pago de l reserva fue reembolsado
     */
    public static final int REEMBOLSADO = 4;
    /*
     * La reserva esta finalizada
     */
    public static final int FINALIZADA = 5;

    /*
     * Fecha que el usuario define al crear la reserva
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    /*
     * Fecha que el usuariodefine para comenzar la reserva
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    
    
    /*
     * estado de la reserva
     */
    private int estado;

    /*
     * Calificaciones de una reserva 
     */
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    @PodamExclude
    private List<CalificacionEntity> calificaciones = new ArrayList<>();

    /*
     * Usuario que hace la reserva
     */
    @ManyToOne
    @PodamExclude
    @XmlInverseReference(mappedBy = "reservas")
    private UsuarioEntity usuarioReserva;
    /*
     * Estaciondonde comienza la reserva
     */
    @ManyToOne
    @PodamExclude
    @XmlInverseReference(mappedBy = "reservas")
    private EstacionEntity estacionSalida;
    /*
     * Estacion donde el usuario termina la reserva
     */
    @PodamExclude
    private Long estacionLlegada;
    
    /*
     * pago la reserva
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGO_ID")
    @PodamExclude
    private PagoEntity pago;

    /*
     * Transaccion de la reserva
     */
    @OneToOne
    @JoinColumn(name = "TRANSACCION_ID")
    @PodamExclude
    private TransaccionEntity transaccion;

    /*
     * bicicletas asociadas a la reserva
     */
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.REFRESH)
    @PodamExclude
    private List<BicicletaEntity> bicicletas = new ArrayList<>();

    /*
     * accesorios de la reserva
     */
    @OneToMany
    @PodamExclude
    private List<AccesorioEntity> accesorios = new ArrayList<>();

    /*
     * precio final de la reserva
     */
    private Double precioFinal;

    /*
     * fecha en la que fue creadala reserva la reserva
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReserva;

    /*
     *Fecha en la cual se finaliza una reserva 
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;

    /*
     * Metodo que devuelve la estacion de llegada
     */
    public Long getEstacionLlegada() {
        return estacionLlegada;
    }

    /*
     *Metodo que mofifica la estacion de llegada
     */
    public void setEstacionLlegada(Long estacionLlegada) {
        this.estacionLlegada = estacionLlegada;
    }

    /*
     * Metodo que devuelve la fecha final
     */
    public Date getFechaFinal() {
        if (fechaFinal == null) {
            return new Date(0, 0, 0);
        } else {
            return fechaFinal;
        }

    }
    
    /*
     * metodo que modifica la fecha final
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /*
     * Atributo que representa si hay o no descuento
     */
    private Boolean Descuento;

    /*
     * metodo que devuelve la fecha de la reserva
     */
    public Date getFechaReserva() {
        return fechaReserva;
    }
    /*
     * metodo que modifica la fecha de la reserva
     */
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    /*
     * metodo que devuelve eldescuento
     */
    public Boolean getDescuento() {
        return Descuento;
    }

    /*
     * metodo que decide si hay descuento
     */
    public void setDescuento() {
        if (fechaReserva.getDay() == fechaInicio.getDay() && fechaReserva.getYear() == fechaInicio.getYear() && fechaReserva.getMonth() == fechaInicio.getMonth()) {
            this.Descuento = new Boolean(false);
        } else {
            this.Descuento = new Boolean(true);
        }
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
     *Calcula el prefio final 
     */
    public void setPrecioFinalNumBicicletas(int numBicicletgas) {
        precioFinal = calcularCostoFinal(fechaInicio, fechaEntrega, numBicicletgas);
    }

    /*
     * metodo que cambia el precio final
     */
    public void setPrecioFinal(Double precioFinal) {
        this.precioFinal = precioFinal;
    }

    /**
     * Calcula el costo de usar un conjunto de bicicletas en un intervalo de
     * tiempo.
     *
     * @param inicio fecha en la que inicia la cuenta.
     * @param fin fecha en la que termina la cuenta.
     * @return el precio en el intervalo de tiempo;
     */
    private double calcularCostoFinal(Date inicio, Date fin, int cantidadBicicletas) {
        double costo = 0;
        int horasTotales = fin.getHours() - inicio.getHours();

        double precioHoras = cantidadBicicletas * PagoEntity.PRECIO_BICICLETA_HORA * ((double)horasTotales);

        //Calcular el precio por minutos
        double precioPorMinutos = Math.ceil(PagoEntity.PRECIO_BICICLETA_HORA / 60.0);

        //Calcula los minutos adicionales
        int minutosInicio = inicio.getMinutes();
        int minutosFin = fin.getMinutes();
        int minutos = 0;

        if (minutosFin >= minutosInicio) {
            minutos = minutosFin - minutosInicio;
        } else {
            minutos = minutosInicio - minutosFin;
        }

        double precioMinutos = cantidadBicicletas * precioPorMinutos * minutos;

        costo = precioMinutos + precioHoras;

        return costo;
    }

    /**
     * @param calificacionEstacionSalida the calificacionEstacionSalida to set
     */
    public void setCalificacionEstacionSalida(CalificacionEntity calificacionEstacionSalida) {
        this.calificaciones.add(0, calificacionEstacionSalida);
    }
    /*
     * metodo que devuelve los accesorios
     */
    public List<AccesorioEntity> getAccesorios() {
        return accesorios;
    }
    /*
     * metodo que modifica los accesorios 
     */
    public void setAccesorios(List<AccesorioEntity> accesorios) {
        this.accesorios = accesorios;
    }

    /*
     * metodo que decuelve la transaccion
     */
    public TransaccionEntity getTransaccion() {
        return transaccion;
    }
    
    /*
     * metodo que modifica la transaccion
     */

    public void setTransaccion(TransaccionEntity transaccion) {
        this.transaccion = transaccion;
    }
    
    /*
     * metodo que devuelve las calificaciones
     */

    public List<CalificacionEntity> getCalificaciones() {
        return this.calificaciones;
    }

}

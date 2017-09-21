/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Nota dada por el usuario a cada reserva (estación llegada/Origen), también puede incluir un comentario
 * @author gl.pinto10
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    /**
     * La descripción/comentario que realiza el usuario sobre la estación en la que estuvo
     */
    private String descripcion;
    
    /**
     * La fecha en la que se realiza la calificación
     */
    @Temporal(TemporalType.DATE)
    private Date fechaCali;
    
    /**
     * El id del usuario que realiza la calificación
     */
    private Long idUsuario;
    
    /**
     * La reserva asociada a la calificación
     */
    @PodamExclude
    @OneToOne
    private ReservaEntity reserva;
    
    /**
     * El id de la estacion que es calificada
     */
    private Long idEstacion;
    
    /**
     * La nota que le asigna el usuario a la estación
     */
    private Integer nota;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaCali
     */
    public Date getFechaCali() {
        return fechaCali;
    }

    /**
     * @param fechaCali the fechaCali to set
     */
    public void setFechaCali(Date fechaCali) {
        this.fechaCali = fechaCali;
    }

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idEstacion
     */
    public Long getIdEstacion() {
        return idEstacion;
    }

    /**
     * @param idEstacion the idEstacion to set
     */
    public void setIdEstacion(Long idEstacion) {
        this.idEstacion = idEstacion;
    }

    /**
     * @return the nota
     */
    public Integer getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(Integer nota) {
        this.nota = nota;
    }

    /**
     * @return the reserva
     */
    public ReservaEntity getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }
    
}
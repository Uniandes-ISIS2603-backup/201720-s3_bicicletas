/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    
    @ManyToOne
    @PodamExclude
    private EstacionEntity estacion;
    
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
    
    public EstacionEntity getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionEntity estacion) {
        this.estacion = estacion;
    }
    
}
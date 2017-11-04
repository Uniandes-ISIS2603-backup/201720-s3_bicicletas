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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
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
    @XmlInverseReference(mappedBy="calificaciones")
    private EstacionEntity estacion;
    
    /**
     * La reserva asociada a la calificación
     */
    @PodamExclude
    @ManyToOne
    @XmlInverseReference(mappedBy="calificaciones")
    private ReservaEntity reserva;
    
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

    public EstacionEntity getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionEntity estacion) {
        this.estacion = estacion;
    }

    /**
     * @return the idReserva
     */
    public ReservaEntity getReserva() {
        return this.reserva;
    }

    /**
     * @param reservaCal
     */
    public void setReserva(ReservaEntity reservaCal) {
        this.reserva = reservaCal;
    }
    
}
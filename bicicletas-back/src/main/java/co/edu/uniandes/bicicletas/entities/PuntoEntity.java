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
 * El punto que gana un usuario por cada reserva realizada
 * @author gl.pinto10
 */
@Entity
public class PuntoEntity extends BaseEntity implements Serializable
{
    /**
     * La fecha de creacion del punto
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPunto;
    
    /**
     * La fecha en la que se vence
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    
    /**
     * Usuario al que pertenecen los puntos
     */
    @PodamExclude 
    @ManyToOne
    @XmlInverseReference(mappedBy="puntos")
    private UsuarioEntity usuarioPunto;
    
    

    /**
     * @return the fechaPunto
     */
    public Date getFechaPunto() {
        return fechaPunto;
    }

    /**
     * @param fechaPunto the fechaPunto to set
     */
    public void setFechaPunto(Date fechaPunto) {
        this.fechaPunto = fechaPunto;
    }   

    /**
     * @return the usuarioPunto
     */
    public UsuarioEntity getUsuarioPunto() {
        return usuarioPunto;
    }

    /**
     * @param usuarioPunto the usuarioPunto to set
     */
    public void setUsuarioPunto(UsuarioEntity usuarioPunto) {
        this.usuarioPunto = usuarioPunto;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    
}

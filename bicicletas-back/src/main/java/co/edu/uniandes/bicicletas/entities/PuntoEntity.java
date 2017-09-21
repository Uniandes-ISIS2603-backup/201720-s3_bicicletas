/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    //@GeneratedValue 
    @Temporal(TemporalType.DATE)
    private Date fechaPunto;
    
    @PodamExclude 
    @ManyToOne
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
    
}

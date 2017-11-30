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
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que modela las tarjetas de credito de un usuario
 *
 * @author jd.trujillom
 */
@Entity
public class TarjetaDeCreditoEntity extends BaseEntity implements Serializable {
    
    
    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;

    
    /**
     * 
     */
    @PodamExclude
    @ManyToOne
    @XmlInverseReference(mappedBy = "tarjetas")
    private UsuarioEntity usuarioTarjeta;
    /**
     *
     */
    private Integer cvv;

    /**
     *
     */
    private String titular;

    /**
     *
     */
    private Long numero;

    /**
     *
     * @return
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * 
     * @param fechaVencimiento 
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * 
     * @return 
     */
    public Integer getCvv() {
        return cvv;
    }
    
    /**
     * 
     * @param cvv 
     */
    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
    
    /**
     * 
     * @return 
     */
    public String getTitular() {
        return titular;
    }
    
    /**
     * 
     * @param titular 
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }
    
    /**
     * 
     * @return 
     */
    public Long getNumero() {
        return numero;
    }
    
    /**
     * 
     * @param numero 
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public UsuarioEntity getUsuarioTarjeta() {
        return usuarioTarjeta;
    }

    public void setUsuarioTarjeta(UsuarioEntity usuarioTarjeta) {
        this.usuarioTarjeta = usuarioTarjeta;
    }

    
}

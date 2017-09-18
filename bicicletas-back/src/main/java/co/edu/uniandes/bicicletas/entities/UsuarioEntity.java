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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author cm.alba10
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    
 /**
 *Constantes
 */
   public final static Integer CEDULA=1; 
   public final static Integer TARJETA_IDENTIDAD=2; 
   public final static Integer PASAPORTE=3; 
   
/**
 *Atributos
 */

   private Integer tipoId; 
   private Long documentoUsuario;
   @Temporal(TemporalType.DATE)
   private Date fechaNacimiento;
   private Integer puntos;
   
   
 /**
 *Metodos
 */
    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }
    public Long getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(Long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
    
 
}

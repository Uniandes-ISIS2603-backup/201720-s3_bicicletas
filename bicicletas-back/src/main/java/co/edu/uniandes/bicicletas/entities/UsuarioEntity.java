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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
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
   
   @OneToMany(mappedBy = "usuario")
   @PodamExclude
   public List<DireccionEntity> direcciones = new  ArrayList<DireccionEntity>();
   @OneToMany(mappedBy = "usuario")
   @PodamExclude
   public List<PuntoEntity> puntos = new  ArrayList<PuntoEntity>();
    
   
 /**
 *Metodos
 */
   public List<DireccionEntity> getDirecciones() {   
        return direcciones;
    }
    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }

    public List<PuntoEntity> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntoEntity> puntos) {
        this.puntos = puntos;
    }
   
    
    
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
 
}

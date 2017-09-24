/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author cm.alba10
 */
@Entity
public class DireccionEntity extends BaseEntity implements Serializable {
    
/**
 *Atributos
 */
   
   private Integer codigoPostal; 
   private String descripcion; 
   private String ciudad;
   
   
   @PodamExclude
   private List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();

   
   @PodamExclude
   private List<EstacionEntity> estaciones = new ArrayList<EstacionEntity>();

 /**
 *Metodos
 */
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////RELACION CON USUARIOS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    
    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////RELACION CON ESTACIONES////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public List<EstacionEntity> getEstaciones() {
    return estaciones;
    }

    public void setEstaciones(List<EstacionEntity> estaciones) {
    this.estaciones = estaciones;
    }
}

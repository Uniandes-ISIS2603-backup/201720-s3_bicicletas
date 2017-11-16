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
import javax.persistence.ManyToMany;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
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
   
   
   
   @ManyToMany
   @PodamExclude
   @XmlInverseReference(mappedBy = "direcciones")
   private List<UsuarioEntity> usuarios = new ArrayList<>();

 /**
 *Metodos
     * @return 
 */
    public String getCiudad() {
        return ciudad;
    }

    /**
     *
     * @param ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     *
     * @return
     */
    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    /**
     *
     * @param codigoPostal
     */
    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Esta es la documentación 
     * del metodo getdescripcion.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Esta es la documentación 
     * del metodo setdescripciop.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////RELACION CON USUARIOS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Esta es la documentación 
     * del metodo getusuario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @return
     */
    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    /**
     * Esta es la documentación 
     * del metodo setusuario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @param usuarios
     */
    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }
}

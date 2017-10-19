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
import javax.persistence.CascadeType;
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
public class UsuarioEntity implements Serializable {
    
 /**
 *Constantes
 */
   public final static Integer CEDULA=1; 
 
    /**
     *
     */
    public final static Integer TARJETA_IDENTIDAD=2; 
 
    /**
     *
     */
    public final static Integer PASAPORTE=3; 
   
/**
 *Atributos
 */

   private Integer tipoId;
   @Id
   private Long documentoUsuario;
   private String nombre;
   @Temporal(TemporalType.DATE)
   private Date fechaNacimiento;
   
   @OneToMany(mappedBy = "usuarioReserva")
   @PodamExclude
   private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();

    /**
     *
     */
   @ManyToMany(mappedBy = "usuarios")
   @PodamExclude
   private List<DireccionEntity> direcciones = new  ArrayList<DireccionEntity>();
 
    /**
     *
     */
   @PodamExclude
   @OneToMany(mappedBy = "usuarioPunto", cascade=CascadeType.ALL)
   private List<PuntoEntity> puntos = new  ArrayList<PuntoEntity>();
    
   
 /**
 *Metodos
     * @return 
 */
   public List<DireccionEntity> getDirecciones() {   
        return direcciones;
    }

    /**
     *
     * @param direcciones
     */
    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }

    /**
     *
     * @return
     */
    public List<PuntoEntity> getPuntos() {
        return puntos;
    }

    /**
     *
     * @param puntos
     */
    public void setPuntos(List<PuntoEntity> puntos) {
        this.puntos = puntos;
    }
   
    /**
     *
     * @return
     */
    public Integer getTipoId() {
        return tipoId;
    }

    /**
     *
     * @param tipoId
     */
    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    /**
     *
     * @return
     */
    public Long getDocumentoUsuario() {
        return documentoUsuario;
    }

    /**
     *
     * @param documentoUsuario
     */
    public void setDocumentoUsuario(Long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    /**
     *
     * @return
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
 
}

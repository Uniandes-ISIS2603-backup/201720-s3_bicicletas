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
 *Atributos
 */

   private Integer tipoId;
   @Id
   private Long documentoUsuario;
   private String nombre;
   @Temporal(TemporalType.DATE)
   private Date fechaNacimiento;
   
   private Long tarjetaCredito;
   private int numeroCsv;
   private String contraseniaPSE;
   
   @OneToMany(mappedBy = "usuarioReserva")
   @PodamExclude
   private List<ReservaEntity> reservas = new ArrayList<>();

    /**
     *
     */
   @ManyToMany(mappedBy = "usuarios")
   @PodamExclude
   private List<DireccionEntity> direcciones = new  ArrayList<>();
 
    /**
     *
     */
   @PodamExclude
   @OneToMany(mappedBy = "usuarioPunto", cascade=CascadeType.ALL)
   private List<PuntoEntity> puntos = new  ArrayList<>();
    
   
   
   
 /**
 *Metodos
 */
   
    /**
     *  Esta es la documentación 
     * del metodo getdirecciones.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * get direcciones
     * @return
     */
   public List<DireccionEntity> getDirecciones() {   
        return direcciones;
    }

    /**
     *  Esta es la documentación 
     * del metodo setdirecciones.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * set direcciones
     * @param direcciones
     */
    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }

    /**
     * Esta es la documentación 
     * del metodo getpuntos.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * get puntos
     * @return
     */
    public List<PuntoEntity> getPuntos() {
        return puntos;
    }

    /**
     ** Esta es la documentación 
     * del metodo set puntos.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * set puntos
     * @param puntos
     */
    public void setPuntos(List<PuntoEntity> puntos) {
        this.puntos = puntos;
    }
   
    /**
     * Esta es la documentación 
     * del metodo gettipoid.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * get tipo id
     * @return
     */
    public Integer getTipoId() {
        return tipoId;
    }

    /**
     * Esta es la documentación 
     * del metodo settipoid.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * set tipoid
     * @param tipoId
     */
    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    /**
     * Esta es la documentación 
     * del metodo getdocumentousario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * get documento usuario
     * @return
     */
    public Long getDocumentoUsuario() {
        return documentoUsuario;
    }

    /**
      * Esta es la documentación 
     * del metodo setdocumentousario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * set documento usuario
     * @param documentoUsuario
     */
    public void setDocumentoUsuario(Long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    /**
     * Esta es la documentación 
     * del metodo getnombre.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * get nombre
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
     *
     * @return
     */
    public Long getTarjetaCredito() {
        return tarjetaCredito;
    }

    /**
     *
     * @param tarjetaCredito
     */
    public void setTarjetaCredito(Long tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    /**
     *
     * @return
     */
    public int getNumeroCsv() {
        return numeroCsv;
    }

    /**
     *
     * @param numeroCsv
     */
    public void setNumeroCsv(int numeroCsv) {
        this.numeroCsv = numeroCsv;
    }

    /**
     *
     * @return
     */
    public String getContraseniaPSE() {
        return contraseniaPSE;
    }

    /**
     *
     * @param contraseñaPSE
     */
    public void setContraseniaPSE(String contraseniaPSE) {
        this.contraseniaPSE = contraseniaPSE;
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

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
     *
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

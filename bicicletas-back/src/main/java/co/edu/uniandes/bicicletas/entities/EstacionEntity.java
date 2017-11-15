/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que modela una estación
 * @author ka.babativa
 */
@Entity
public class EstacionEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL)
    private List<AccesorioEntity> accesorios = new ArrayList<AccesorioEntity>(); //Atributo que contiene una lista de accesorios.
    
    @PodamExclude
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.REFRESH)
    @JoinColumn(name="ESTACION_ID")
    private List<CalificacionEntity> calificaciones = new ArrayList<>(); //Atributo que contiene una lista de calificaciones.
    
    @PodamExclude
    @OneToMany(mappedBy="estacionSalida", cascade = CascadeType.ALL)
    private List<ReservaEntity> reservas = new ArrayList<>();
    /**
     * Atributo que moldea las bicicletas de la estacion.
     */
    @PodamExclude
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.REFRESH)
    private List<BicicletaEntity> bicicletas = new ArrayList<>(); //Atributo que contiene una lista de bicicletas.
    
    private String direccion; //Atributo que modela la dirección.
    
    private String ciudad; //Atributo que modela la ciudad.

    
    /**
     * Metodo que retorna una dirección.
     * @return Un string con la direccion de la estación.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
    * Metodo que asigna la dirección a una estación.
    * @param direccion String con la direccion de la estación.
    */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

     /**
     * Metodo que retorna una ciudad.
     * @return Un string con la ciudad de la estación.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
    * Metodo que asigna la ciudad a una estación.
    * @param ciudad String con la ciudad de la estación.
    */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    /**
    * Metodo que retorna las reservas de una estación.
    * @return Una lista con las reservas de una estación.
    */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
    * Metodo que asigna las reservas a una estación.
    * @param reservas Lista con las reservas de la estación.
    */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * Metodo que retorna los accesorios de una estación.
     * @return Una lista con los accesorios de una estación.
     */
    public List<AccesorioEntity> getAccesorios() {
        return accesorios;
    }

    /**
    * Metodo que asigna los accesorios a una estación.
    * @param accesorios Lista con los accesorios de la estación.
    */
    public void setAccesorios(List<AccesorioEntity> accesorios) {
        this.accesorios = accesorios;
    }
    /**
     * Obtiene las Bicicletas de una Estacion.
     * @return bicicletas que pertenecen a la estacion.
     */

    /**
     * Metodo que retorna las bicicletas de una estación.
     * @return Una lista con las bicicletas de una estación.
     */
    public List<BicicletaEntity> getBicicletas() {
        return bicicletas;
    }
    /**
     * Set de las bicicletas de una Estacion
     * @param bicicletas bicicletas de una estacion.
     */
    public void setBicicletas(List<BicicletaEntity> bicicletas) {
        this.bicicletas = bicicletas;
    }

    /**
     * Metodo que retorna las calificaciones de una estación.
     * @return Una lista con las calificaciones de una estación.
     */
   public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

   /**
    * Metodo que asigna las calificaciones a una estación.
    * @param calificaciones Lista con las calificaciones de la estación.
    */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
     
}

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
    private List<AccesorioEntity> accesorios = new ArrayList<AccesorioEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.REFRESH)
    @JoinColumn(name="ESTACION_ID")
    private List<CalificacionEntity> calificaciones = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy="estacionSalida", cascade = CascadeType.ALL)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.REFRESH)
    private List<BicicletaEntity> bicicletas = new ArrayList<>();
    
    private String direccion;
    
    private String ciudad;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
    

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    public List<AccesorioEntity> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<AccesorioEntity> accesorios) {
        this.accesorios = accesorios;
    }

    public List<BicicletaEntity> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(List<BicicletaEntity> bicicletas) {
        this.bicicletas = bicicletas;
    }

   public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    
    public void setBicics(List<BicicletaEntity> bici){
        bicicletas = bici;
    }
    
    public List<BicicletaEntity> getBicis(){
        return bicicletas;
    }
}

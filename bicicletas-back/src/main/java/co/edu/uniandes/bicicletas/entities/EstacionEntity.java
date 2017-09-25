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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que modela una estación
 * @author ka.babativa
 */
@Entity
public class EstacionEntity extends BaseEntity implements Serializable {
    
    @ManyToMany
    @PodamExclude
    private List<DireccionEntity> direcciones = new ArrayList<DireccionEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "")
    private ArrayList<CalificacionEntity> calificaciones;
    
    @PodamExclude
    @OneToMany(mappedBy = "estacion")
    private List<AccesorioEntity> accesorios;
    
    @PodamExclude
    @ManyToMany
    private List<ReservaEntity> reservas;

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
    
   

    public List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }
    
    
    

    public ArrayList<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }  
}

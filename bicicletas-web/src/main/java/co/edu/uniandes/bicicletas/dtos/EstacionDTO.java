/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ka.babativa
 */
public class EstacionDTO {
    private String nombre;

    
    private List<DireccionEntity> direccion;
    private ArrayList<CalificacionEntity> calificaciones;
    private List<AccesorioEntity> accesorios;
    private Long id;

    
    
    public EstacionDTO(){
        
    }
    
    public EstacionDTO(EstacionEntity entidad) {
        if(entidad!=null){
            this.nombre = entidad.getNombre();
            this.direccion = entidad.getDirecciones();
            this.id = entidad.getId();
            this.accesorios = entidad.getAccesorios();
            this.calificaciones = entidad.getCalificaciones();
        }
    }
    
    public ArrayList<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public List<AccesorioEntity> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<AccesorioEntity> accesorios) {
        this.accesorios = accesorios;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<DireccionEntity> getDireccion() {
        return direccion;
    }
    
    public void setDireccion(List<DireccionEntity> direccion) {
        this.direccion = direccion;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public EstacionEntity toEntity() {
        EstacionEntity entity = new EstacionEntity();
        entity.setNombre(this.getNombre());
        entity.setId(this.getId());
        entity.setDirecciones(this.getDireccion());
        entity.setAccesorios(this.getAccesorios());
        entity.setCalificaciones(this.getCalificaciones());
        return entity;
    }
}

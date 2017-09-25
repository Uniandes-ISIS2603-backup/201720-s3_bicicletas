/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ka.babativa
 */
public class EstacionDetailDTO {
    
    private String nombre;

    
    private List<DireccionEntity> direccion;
    private List<CalificacionEntity> calificaciones;
    private List<AccesorioEntity> accesorios;
    private List<ReservaEntity> reservas;
    private Long id;
    private List<BicicletaDetailDTO> bicicletas = new ArrayList<>();

    
    
    public EstacionDetailDTO(){
        
    }
    
    public EstacionDetailDTO(EstacionEntity entidad) {
        if(entidad!=null){
            this.nombre = entidad.getName();
            this.direccion = entidad.getDirecciones();
            this.id = entidad.getId();
            this.accesorios = entidad.getAccesorios();
            this.calificaciones = entidad.getCalificacion();
            this.reservas = entidad.getReservas();
            bicicletas = listEntity2DetailDTO(entidad.getBicis());
        }
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
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
        entity.setName(this.getNombre());
        entity.setId(this.getId());
        entity.setDirecciones(this.getDireccion());
        entity.setAccesorios(this.getAccesorios());
        entity.setCalificacion(this.getCalificaciones());
        entity.setReservas(this.getReservas());
        entity.setBicics(listDTO2Entity(bicicletas));
        return entity;
    }
    private List<BicicletaDetailDTO> listEntity2DetailDTO(List<BicicletaEntity> entityList) {
        List<BicicletaDetailDTO> list = new ArrayList<>();
        for (BicicletaEntity entity : entityList) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
    private List<BicicletaEntity> listDTO2Entity(List<BicicletaDetailDTO> entityList) {
        List<BicicletaEntity> list = new ArrayList<>();
        for (BicicletaDetailDTO x : entityList) {
            list.add(x.toEntity());
        }
        return list;
    }
    public void setBicis(List<BicicletaDetailDTO> temp){
        bicicletas = temp;
    }
    public List<BicicletaDetailDTO> getBicis(){
        return bicicletas;
    }
}
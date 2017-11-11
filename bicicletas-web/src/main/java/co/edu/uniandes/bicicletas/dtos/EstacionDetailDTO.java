/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ka.babativa
 */
public class EstacionDetailDTO extends EstacionDTO {
    
    private List<CalificacionEntity> calificaciones;
    private List<AccesorioEntity> accesorios;
    private List<ReservaEntity> reservas;
    private List<BicicletaDetailDTO> bicicletas = new ArrayList<>();
    private String direccion;
    private String ciudad;
    
    
    public EstacionDetailDTO(){
        
    }
    
    public EstacionDetailDTO(EstacionEntity entidad) {
        super(entidad);
        if(entidad!=null){
            this.direccion=entidad.getDireccion();
            this.ciudad=entidad.getCiudad();
            this.accesorios = entidad.getAccesorios();
            this.calificaciones = entidad.getCalificaciones();
            this.reservas = entidad.getReservas();
            this.bicicletas = listEntity2DetailDTO(entidad.getBicicletas());
        }
    }

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


    public List<BicicletaDetailDTO> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(List<BicicletaDetailDTO> bicicletas) {
        this.bicicletas = bicicletas;
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
    
    
    public List<AccesorioEntity> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<AccesorioEntity> accesorios) {
        this.accesorios = accesorios;
    }

    public EstacionEntity toEntity() {
        EstacionEntity entity = new EstacionEntity();
        entity.setName(this.getNombre());
        entity.setId(this.getId());
        entity.setDireccion(this.getDireccion());
        entity.setCiudad(this.getCiudad());
        entity.setAccesorios(this.getAccesorios());
        entity.setCalificaciones(this.getCalificaciones());
        entity.setReservas(this.getReservas());
        entity.setBicicletas(listDTO2Entity(bicicletas));
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

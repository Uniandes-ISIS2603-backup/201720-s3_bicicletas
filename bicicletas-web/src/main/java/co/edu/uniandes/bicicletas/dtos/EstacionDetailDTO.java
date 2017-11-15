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
 * Clase que modela una estación detail
 * @author ka.babativa
 */
public class EstacionDetailDTO extends EstacionDTO {
    
    private List<CalificacionEntity> calificaciones; //Atributo que contiene una lista de calificaciones.
    private List<AccesorioEntity> accesorios; //Atributo que contiene una lista de accesorios.
    private List<ReservaEntity> reservas; //Atributo que contiene una lista de reservas
    private List<BicicletaDetailDTO> bicicletas = new ArrayList<>(); //Atributo que contiene una lista de bicicletas.
    private String direccion; //Atributo que modela la direccion de una estacion.
    private String ciudad; //Atributo que modela la ciudad de una estacion.
    
    /**
     * Constructor de la clase. No necesita cuerpo.
     */
    public EstacionDetailDTO(){
        //No necesita cuerpo
    }
    
    /**
     * Constructor en caso de que haya una entidad en el parametro.
     * @param entidad con las caracteristicas de la estación.
     */
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

    /**
     * Metodo que retorna una dirección.
     * @return Un string con la direccion de la estación.
     */
    @Override
    public String getDireccion() {
        return direccion;
    }

    /**
    * Metodo que asigna la dirección a una estación.
    * @param direccion String con la direccion de la estación.
    */
    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
    * Metodo que retorna una ciudad.
    * @return Un string con la ciudad de la estación.
    */
    @Override
    public String getCiudad() {
        return ciudad;
    }

    /**
    * Metodo que asigna la ciudad a una estación.
    * @param ciudad String con la ciudad de la estación.
    */
    @Override
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Metodo que retorna las bicicletas de una estación.
     * @return Una lista con las bicicletas de una estación.
     */
    public List<BicicletaDetailDTO> getBicicletas() {
        return bicicletas;
    }

    /**
     * Set de las bicicletas de una Estacion
     * @param bicicletas bicicletas de una estacion.
     */
    public void setBicicletas(List<BicicletaDetailDTO> bicicletas) {
        this.bicicletas = bicicletas;
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
     * Metodo que pasa de DTO A entidad
     * @return Una entidad con las caracteristicas de la estación.
     */
    @Override
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
    
    /**
     * Metodo que convierte de Entidades a DETAILDTO
     * @param entityList Lista con las entidades
     * @return Lista con los DetailDTO
     */
    private List<BicicletaDetailDTO> listEntity2DetailDTO(List<BicicletaEntity> entityList) {
        List<BicicletaDetailDTO> list = new ArrayList<>();
        for (BicicletaEntity entity : entityList) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Metodo que convierte de DTO A entidad
     * @param entityList Lista con DetailsDTO a Entidad
     * @return Una lista con entidades.
     */
    private List<BicicletaEntity> listDTO2Entity(List<BicicletaDetailDTO> entityList) {
        List<BicicletaEntity> list = new ArrayList<>();
        for (BicicletaDetailDTO x : entityList) {
            list.add(x.toEntity());
        }
        return list;
    }
    
    /**
     * Metodo que asigna las bicicletas a una estación.
     * @param temp lista de bicicletas
     */
    public void setBicis(List<BicicletaDetailDTO> temp){
        bicicletas = temp;
    }
    
    /**
     * Metodo que obtiene las bicicletas de forma DETAIL
     * @return Una lista de BicicletaDetail
     */
    public List<BicicletaDetailDTO> getBicis(){
        return bicicletas;
    }
}

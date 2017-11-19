/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.AccesorioBicicletaEntity;

/**
 *
 * @author js.torres1
 */
public class AccesorioBicicletaDTO {
    /**
     * Id del DTO accBicicleta.
     */
    private Long id;
    /**
     * Descripcion del DTO accBicicleta.
     */
    private String descripcion;
    /**
     * Nombre del DTO accBicicleta.
     */
    private int nombre;
    
    public AccesorioBicicletaDTO(){
        //No necesita cuerpo
    }
    public AccesorioBicicletaDTO(AccesorioBicicletaEntity accesorio){
        this.id = accesorio.getId();
        this.descripcion = accesorio.darDescrip();
        this.nombre = accesorio.darNombre();
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * Este metodo obtiene el metodo del DTO
     * @return int con el nombre de la bicicleta.
     */
    public int getNombre(){
        return nombre;
    }
    /**
     * Este metodo obtiene el String de la descripcion
     * @return String de la descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }
    /**
     * Este metodo setea el id con el valor dado.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Este metodo setea el int con el Nombre dado
     * @param pNombre El nombre a setear.
     */
    public void setNombre(int pNombre){
        this.nombre = pNombre;
    }
    /**
     * Este metodo Setea la descripcion con un valor dado
     * @param pDescrip valor a setear la descripcion.
     */
    public void setDescripcion(String pDescrip){
        this.descripcion = pDescrip;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public AccesorioBicicletaEntity toEntity() {
        AccesorioBicicletaEntity entity = new AccesorioBicicletaEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setDescripcion(this.descripcion);
        return entity;
    }
}

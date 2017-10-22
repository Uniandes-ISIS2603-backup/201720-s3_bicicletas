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
    private Long id;
    private String descripcion;
    private int nombre;
    
    public AccesorioBicicletaDTO(){
        
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
    public int getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(int pNombre){
        this.nombre = pNombre;
    }
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

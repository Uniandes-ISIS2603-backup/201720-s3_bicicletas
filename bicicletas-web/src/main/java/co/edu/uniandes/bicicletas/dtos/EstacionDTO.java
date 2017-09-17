/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.EstacionEntity;
/**
 *
 * @author ka.babativa
 */
public class EstacionDTO {
    private String nombre;
    private String direccion;
    private Long id;
    
    public EstacionDTO(){
        
    }
    
    public EstacionDTO(EstacionEntity entidad) {
        if(entidad!=null){
            this.nombre = entidad.getNombre();
            this.direccion = entidad.getDireccion();
            this.id = entidad.getId();
        }
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
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
        entity.setDireccion(this.getDireccion());
        return entity;
    }
}

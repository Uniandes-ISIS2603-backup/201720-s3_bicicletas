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
    private Long id;

    
    
    public EstacionDTO(){
        
    }
    
    public EstacionDTO(EstacionEntity entidad) {
        if(entidad!=null){
            this.nombre = entidad.getName();
            this.id = entidad.getId();
        }
    }
    
    
    public String getNombre() {
        return nombre;
    }
    
   
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return entity;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;

/**
 *
 * @author ka.babativa
 */
public class AccesorioDTO {
    private int tipo;
    private Long id;

    public AccesorioDTO(){
        
    }
    
    public AccesorioDTO(AccesorioEntity entidad){
        if(entidad!=null){
            this.tipo = entidad.getTipo();
            this.id = entidad.getId();
        }
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public AccesorioEntity toEntity(){
        AccesorioEntity entity = new AccesorioEntity();
        entity.setTipo(this.tipo);
        entity.setId(this.id);
        return entity;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.DireccionEntity;

/**
 *
 * @author cm.alba10
 */
public class DireccionDetailDTO extends DireccionDTO{
    
    
       /**
     * Constructor por defecto
     */
    public DireccionDetailDTO() {
        //Do nothing because is not necessary
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public DireccionDetailDTO(DireccionEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public DireccionEntity toEntity() {
        return super.toEntity();
    }
    
    
}

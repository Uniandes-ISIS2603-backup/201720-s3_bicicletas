/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.UsuarioEntity;

/**
 *
 * @author cm.alba10
 */
public class UsuarioDetailDTO extends UsuarioDTO {
    
     /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioE = super.toEntity();
        return usuarioE;
    }
}

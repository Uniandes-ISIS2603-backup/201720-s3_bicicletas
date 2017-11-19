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
        //No necesita cuerpo
    }

    /**
     * Esta es la documentación 
     * del metodo detaildto.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);
    }

    /**
     * Esta es la documentación 
     * del metodo toentity.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public UsuarioEntity toEntity() {
        return super.toEntity();
    }
}

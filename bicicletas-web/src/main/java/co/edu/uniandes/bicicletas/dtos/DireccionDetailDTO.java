/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import static javax.ws.rs.client.Entity.entity;

/**
 *
 * @author cm.alba10
 */
public class DireccionDetailDTO extends DireccionDTO{
    
    
       /**
     * Constructor por defecto
     */
    public DireccionDetailDTO() {
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
        DireccionEntity direccionE = super.toEntity();
        return direccionE;
    }
    
    
}

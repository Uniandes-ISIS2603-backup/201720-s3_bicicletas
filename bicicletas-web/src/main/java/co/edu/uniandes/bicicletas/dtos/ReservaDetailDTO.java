/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.ReservaEntity;

/**
 *
 * @author ds.chacon
 */
public class ReservaDetailDTO extends ReservaDTO {

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ReservaDetailDTO(ReservaEntity entidad) {
        super(entidad);  
    }

    /**
     * Constructor por defecto
     */
    public ReservaDetailDTO() {
        //No necesita cuerpo
    }
    
}

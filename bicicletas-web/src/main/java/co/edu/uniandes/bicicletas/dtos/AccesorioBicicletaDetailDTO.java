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
public class AccesorioBicicletaDetailDTO extends AccesorioBicicletaDTO{
    /**
     * Constructor por defecto
     */
    public AccesorioBicicletaDetailDTO() {
        //No necesita cuerpo
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public AccesorioBicicletaDetailDTO(AccesorioBicicletaEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public AccesorioBicicletaEntity toEntity() {
        AccesorioBicicletaEntity bicicleta = super.toEntity();
        return bicicleta;
    }
}

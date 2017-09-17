/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.PuntoEntity;
import java.util.Date;

/**
 *
 * @author gl.pinto10
 */
public class PuntoDTO {
    
    private Long idPunto;
    private Date fecha;
    
    /**
     * Constructor por defecto
     */
    public PuntoDTO(){}
    
    /**
     * Constructor del DTO de Punto
     * @param punEntity La Entity de tipo Punto que posee los datos 
     * necesarios para la creación del DTO
     */
    public PuntoDTO(PuntoEntity punEntity)
    {
        this.idPunto = punEntity.getId();
        this.fecha = punEntity.getFechaPunto();
        
    }
    
     /**
     * Se encarga de crear una nueva Entity a partir de los datos del DTO 
     * @return Una Entity de tipo Punto
     */
    public PuntoEntity toEntity()
    {
        PuntoEntity punEntity = new PuntoEntity();
        punEntity.setId(this.getIdPunto());
        punEntity.setFechaPunto(this.getFecha());
        
        return punEntity;
    }

    /**
     * @return the idPunto
     */
    public Long getIdPunto() {
        return idPunto;
    }

    /**
     * @param idPunto the idPunto to set
     */
    public void setIdPunto(Long idPunto) {
        this.idPunto = idPunto;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}

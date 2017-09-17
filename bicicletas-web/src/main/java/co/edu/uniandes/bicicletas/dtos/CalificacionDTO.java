/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import java.util.Date;

/**
 *
 * @author gl.pinto10
 */
public class CalificacionDTO 
{
    private Long idCali;
    private Date fechaCali;
    private Integer nota;
    private String descripcion;
   
     /**
     * Constructor por defecto
     */
    public CalificacionDTO() {}
    
    /**
     * Constructor del DTO de Calificacion
     * @param caliEntity La Entity de tipo Calificacion que posee los datos 
     * necesarios para la creación del DTO
     */
    public CalificacionDTO(CalificacionEntity caliEntity)
    {
        this.idCali = caliEntity.getId();
        this.fechaCali = caliEntity.getFechaCali();
        this.nota = caliEntity.getNota();
        this.descripcion = caliEntity.getDescripcion();
    }
    
    /**
     * Se encarga de crear una nueva Entity a partir de los datos del DTO 
     * @return Una Entity de tipo Calificacion
     */
    public CalificacionEntity toEntity()
    {
        CalificacionEntity caliEntity = new CalificacionEntity();
        caliEntity.setId(this.getIdCali());
        caliEntity.setFechaCali(this.getFechaCali());
        caliEntity.setNota(this.getNota());
        caliEntity.setDescripcion(this.getDescripcion());
        
        return caliEntity;
    }

    /**
     * @return the idCali
     */
    public Long getIdCali() {
        return idCali;
    }

    /**
     * @param idCali the idCali to set
     */
    public void setIdCali(Long idCali) {
        this.idCali = idCali;
    }

    /**
     * @return the fechaCali
     */
    public Date getFechaCali() {
        return fechaCali;
    }

    /**
     * @param fechaCali the fechaCali to set
     */
    public void setFechaCali(Date fechaCali) {
        this.fechaCali = fechaCali;
    }

    /**
     * @return the nota
     */
    public Integer getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(Integer nota) {
        this.nota = nota;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

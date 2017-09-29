/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
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
    private EstacionEntity estacion;
    private Long idUsuario;
   
     /**
     * Constructor por defecto
     */
    public CalificacionDTO() {
    //No necesita cuerpo
    }
    
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
        this.estacion = caliEntity.getEstacion();
        this.idUsuario = caliEntity.getReserva().getUsuarioReserva().getId();
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
        caliEntity.setEstacion(this.getEstacion());
        caliEntity.setIdUsuario(this.getIdUsuario());
        
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

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the estacion
     */
    public EstacionEntity getEstacion() {
        return estacion;
    }

    /**
     * @param estacion the estacion to set
     */
    public void setEstacion(EstacionEntity estacion) {
        this.estacion = estacion;
    }
    
    
}

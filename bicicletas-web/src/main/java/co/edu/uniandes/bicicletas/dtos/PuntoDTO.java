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

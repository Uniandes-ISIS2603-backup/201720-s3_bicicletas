package co.edu.uniandes.bicicletas.dtos;

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

import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;

/**
 * BicicletaDTO Objeto de transferencia de datos de Bicicletaes. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * @author ISIS2603,js.torres1
 */
public class BicicletaDTO {

    private Long id;
    private int estado;
    private String modelo;
    private String marca;
    private EstacionEntity estacion;

    /**
     * Constructor por defecto
     */
    public BicicletaDTO() {
        //No necesita cuerpo
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param bicicleta
     * @param Bicicleta: Es la entidad que se va a convertir a DTO
     */
    public BicicletaDTO(BicicletaEntity bicicleta) {
        this.id = bicicleta.getId();
        this.estado=bicicleta.darEstado();
        this.marca=bicicleta.darMarca();
        this.modelo=bicicleta.darModelo();
        this.estacion=bicicleta.getEstacion();
        
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    public int getEstado(){
        return estado;
    }public String getMarca(){
        return marca;
    }
    public String getModelo(){
        return modelo;
    }
    public EstacionEntity getEstacion(){
        return estacion;
    }
    public void setEstacion(EstacionEntity estacionr){
        estacion=estacionr;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    public void setEstado(int pEstado){
        this.estado=pEstado;
    }
    public void setMarca(String pMarca){
        this.marca=pMarca;
    }
    public void setModelo(String pModelo){
        this.modelo=pModelo;
    }
    

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public BicicletaEntity toEntity() {
        BicicletaEntity entity = new BicicletaEntity();
        entity.setId(this.id);
        entity.setEstado(this.estado);
        entity.setMarca(this.marca);
        entity.setModelo(this.modelo);
        entity.setEstacion(estacion);
        return entity;
    }
    

}

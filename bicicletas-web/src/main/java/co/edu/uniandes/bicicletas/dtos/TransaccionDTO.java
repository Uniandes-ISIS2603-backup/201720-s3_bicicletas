/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.TransaccionEntity;

/**
 * DTO de la clase TransaccionEntity
 * @author jd.trujillom
 */
public class TransaccionDTO {

    /**
     * Atributo que modela el id de la transacción-
     */
    private Long id;

    /**
     * Atributo que modela el tipo de transaccion
     */
    private Integer tipo;

    /*
    * Atributo que modela el valor que contendrá la transaccion
     */
    private Double valor;

    /**
     * Constructor vacio
     */
    public TransaccionDTO() {
        //No necesita cuerpo
    }

    /**
     * Constructor que recibe como parametro un entity
     * @param entity que se converità a DTO
     */
    public TransaccionDTO(TransaccionEntity entity) {
        id = entity.getId();
        tipo = entity.getTipo();
        valor = entity.getValor();
    }
    
    /**
     * Convierte un DTO a un entity
     * @return el entity convertido.
     */
    public TransaccionEntity toEntity(){
        
        TransaccionEntity entity = new TransaccionEntity();
        entity.setId(id);
        entity.setValor(valor);
        entity.setTipo(tipo);
        
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    
}

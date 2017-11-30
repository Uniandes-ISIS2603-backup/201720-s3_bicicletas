/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import java.util.Date;
import co.edu.uniandes.bicicletas.entities.TarjetaDeCreditoEntity;

/**
 * 
 * @author jd.trujillom
 */
public class TarjetaDeCreditoDTO {
    /**
     * 
     */
    private Long id;
    
    /**
     * 
     */
    private Date fechaDeVencimiento;
    
    /**
     * 
     */
    private int cvv;
    
    /**
     * 
     */
    private String titular;
    
    /**
     * 
     */
    private Long numero;
    
    /**
     * 
     */
    public TarjetaDeCreditoDTO(){
        //Don't do nothing
    }
    
    /**
     * 
     * @param entity 
     */
    public TarjetaDeCreditoDTO (TarjetaDeCreditoEntity entity){
        this.id =entity.getId();
        this.fechaDeVencimiento = entity.getFechaVencimiento();
        this.cvv = entity.getCvv();
        this.titular = entity.getTitular();
        this.numero = entity.getNumero();
        
    }

    /**
     * 
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    /**
     * 
     * @param fechaDeVencimiento 
     */
    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    /**
     * 
     * @return 
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * 
     * @param cvv 
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * 
     * @return 
     */
    public String getTitular() {
        return titular;
    }

    /**
     * 
     * @param titular 
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * 
     * @return 
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * 
     * @param numero 
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }
    
    
    public TarjetaDeCreditoEntity toEntity(){
        TarjetaDeCreditoEntity entity = new TarjetaDeCreditoEntity();
        entity.setId(id);
        entity.setNumero(numero);
        entity.setCvv(cvv);
        entity.setFechaVencimiento(fechaDeVencimiento);
        entity.setTitular(titular);
        
        return entity;
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Clase que modela la transacción que se realizará luego de ser finalizada una
 * reserva, en la cual se dirá si fue necesario realizar un reembolso, pagar un
 * excedente o no hacer nada dependiendo si el usuario entregó a tiempo sus 
 * bicicletas.
 * @author jd.trujillom
 */
@Entity
public class TransaccionEntity extends BaseEntity implements Serializable {
    
    /**
     * Constante que modela la transaccion posterior a la reserva en la cual se
     * le debe reembolsar dinero al usuario.
     */
    
    public static final Integer REEMBOLSO = 0;
    
    /**
     * Constante que modela el tipo de transaccion para la cual un usario debe
     * pagar cierto excedente.
     */
    
    public static final Integer PAGO_EXCEDENTE = 1;
    
    
    /**
     * Constante que modela la transaccion posterior a la reserva en la cual no
     * es necesario ni reembolsar ni pagar un excedente.
     */
    public static final Integer NO_NECESARIO = 2;
    
    /**
     * Atributo que modela el tipo de transaccion
     */
    private Integer tipo;
    
    /*
    * Atributo que modela el valor que contendrá la transaccion
    */
    private Double valor;

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

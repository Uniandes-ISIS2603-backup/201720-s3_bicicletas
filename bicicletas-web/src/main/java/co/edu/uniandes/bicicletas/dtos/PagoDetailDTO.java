/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.PagoEntity;

/**
 *
 * @author jd.trujillom
 */
public class PagoDetailDTO extends PagoDTO{

    
    /**
     * Constructor vacio
     */
    public PagoDetailDTO(){
        //No necesita cuerpo
    }
    
    /**
     * Constructor que recibe un entity. Usa el del super
     * @param entity que se quiere convertur en DTO,
     */
    public PagoDetailDTO (PagoEntity entity){
        super(entity);
    }
    
    /**
     * Método con el que se converte un DTO en entity.
     * @return el pago en entity.
     */
    @Override
    public PagoEntity toEntity() {
        return super.toEntity(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

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

    public PagoDetailDTO (PagoEntity entity){
        super(entity);
    }
    
    @Override
    public PagoEntity toEntity() {
        return super.toEntity(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

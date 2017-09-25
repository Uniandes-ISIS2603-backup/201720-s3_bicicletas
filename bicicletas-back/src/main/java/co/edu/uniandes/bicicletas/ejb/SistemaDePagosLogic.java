/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import java.util.Random;

/**
 *
 * @author jd.trujillom
 */
public class SistemaDePagosLogic {
    
    Random r;

    public SistemaDePagosLogic() {
        this.r = new Random();
    }
    
    
    public Long realizarPago(){
        return new Long(r.nextInt(1000));
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.torres1
 */
@Entity
public class AccesorioBicicletaEntity extends BaseEntity implements Serializable{
    private int nombre;
    private String descripcion;
    
    private final static int LUCES = 0;
    private final static int CANDADO = 1;
    private final static int CANASTILLA = 2;
    
    @ManyToOne 
    @PodamExclude
    private BicicletaEntity bici;
    
    
    public void setNombre(int pNombre){
        nombre = pNombre;
    }
    public void setDescripcion(String pdecrip){
        descripcion = pdecrip;
    }
    public int darNombre(){
        return nombre;
    }
    public String darDescrip(){
        return descripcion;
    }
    public void setBici(BicicletaEntity temp){
        bici= temp;
        
    }
}

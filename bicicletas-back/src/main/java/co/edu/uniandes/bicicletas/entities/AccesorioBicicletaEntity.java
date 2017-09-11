/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author js.torres1
 */
@Entity
public class AccesorioBicicletaEntity extends BaseEntity implements Serializable{
    public int nombre;
    public String descripcion;
    
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
}

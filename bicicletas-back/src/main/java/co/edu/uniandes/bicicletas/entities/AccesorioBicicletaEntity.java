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
    /**
     * Atributo que modela el nombre con las constantes.
     */
    private int nombre;
    /**
     * Atributo que describe cosas extra de los accesorios Bicicleta.
     */
    
    private String descripcion;
    
    //CONSTANTES DE LOS ACCESORIOS/////
    
    public static final int LUCES = 0;
    public static final int CANDADO = 1;
    public static final int CANASTILLA = 2;
    /**
     * Atributo que modela la bicicleta a la que esta ligado el accesorioBicicleta
     */
    @ManyToOne 
    @PodamExclude
    private BicicletaEntity bici;
    
    /**
     * Metodo que cambia el nombre del accesorio Bicicleta
     * @param pNombre int nuevo nombre de la bicicleta.
     */
    public void setNombre(int pNombre){
        nombre = pNombre;
    }
    /**
     * Nueva descripcion del accesorioBicicleta
     * @param pdecrip String nueva descripcion accesorio Bicicleta
     */
    public void setDescripcion(String pdecrip){
        descripcion = pdecrip;
    }
    /**
     * Metodo que da el nombre del accesorioBicicleta
     * @return 
     */
    public int darNombre(){
        return nombre;
    }
    /**
     * Metodo que retorna la descripcion del accesorioBicicleta.
     * @return 
     */
    public String darDescrip(){
        return descripcion;
    }
    /**
     * Metodo que asigna la bicicleta del accesorioBicicleta.
     * @param temp bicicleta nueva del accesorioBicicleta.
     */
    public void setBicicleta(BicicletaEntity temp){
        bici= temp;
        
    }
    /**
     * Metodo que devuelve la bicicleta que tiene el AccesorioBicicleta
     * @return bicicleta a la que esta asociada el accesorioBicicleta
     */
    public BicicletaEntity getBicicleta(){
        return bici;
    }
}

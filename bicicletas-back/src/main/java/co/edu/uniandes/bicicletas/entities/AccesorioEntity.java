/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Clase que modela los objetos de accesorio
 * @author ka.babativa
 */
@Entity
public class AccesorioEntity extends BaseEntity implements Serializable {
    /** Constante que indica si es casco */
    private final int CASCO = 1;
    /** Constante que indica si es chaleco */
    private final int CHALECO = 2;
    /** Variable que modela indica el tipo del accesorio*/
    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}

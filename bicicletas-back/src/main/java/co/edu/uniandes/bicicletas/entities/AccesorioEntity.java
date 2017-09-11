/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;

/**
 *
 * @author ka.babativa
 */
public class AccesorioEntity extends BaseEntity implements Serializable {
    private final int CASCO = 1;
    private final int CHALECO = 2;
    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}

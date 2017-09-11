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
 * @author ds.chacon
 */
@Entity

public class InfoEstacionEntity extends BaseEntity implements Serializable{
    private long idEstacion;
}

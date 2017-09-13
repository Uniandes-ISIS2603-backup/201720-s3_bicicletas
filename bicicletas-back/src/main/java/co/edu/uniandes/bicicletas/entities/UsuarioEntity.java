/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */

package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author cm.alba10
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {

   private Integer tipoId; 
   private Long documentoUsuario;
   public final static Integer CEDULA=1; 
   public final static Integer TARJETA_IDENTIDAD=2; 
   public final static Integer PASAPORTE=3; 
   private Integer puntos;

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
    
   
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.bicicletas.entities;
import co.edu.uniandes.baco.bicicletas.entities.BaseEntity;
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
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long idUsuario; 
   
   private Integer tipoId; 
   public final static Integer CEDULA=1; 
   public final static Integer TARJETA_IDENTIDAD=2; 
   public final static Integer PASAPORTE=3; 
   private Integer puntos;
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

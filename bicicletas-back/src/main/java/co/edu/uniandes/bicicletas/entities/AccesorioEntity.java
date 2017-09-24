/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

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
    
    private final int EN_RESERVA = 1;
    
    private final int EN_ESTACION = 2;
    /** Variable que modela indica el tipo del accesorio*/
    private int tipo;
    
    private int reservado;
    
    @PodamExclude
    @ManyToOne
    private EstacionEntity estacion;
    
    @PodamExclude
    @OneToOne
    private ReservaEntity reserva;

    public EstacionEntity getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionEntity estacion) {
        this.estacion = estacion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getReservado() {
        return reservado;
    }

    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que modela los objetos de accesorio
 * @author ka.babativa
 */
@Entity
public class AccesorioEntity extends BaseEntity implements Serializable {
    /** Constante que indica si es casco */
    public static final int CASCO = 1;
    /** Constante que indica si es chaleco */
    public static final int CHALECO = 2;
    /** Constante que indica si el accesorio esta en reserva */
    public static final int EN_RESERVA = 1;
    /** Constante que indica si el accesorio esta en estación */
    public static final int EN_ESTACION = 0;
    /** Variable que modela indica el tipo del accesorio*/
    private int tipo;
    /** Variable que modela indica el estado del accesorio*/
    private int reservado;
    
    @PodamExclude
    @ManyToOne
    @XmlInverseReference(mappedBy="accesorios")
    private EstacionEntity estacion; //Variable que indica la estación donde se encuentra.
    
    @PodamExclude
    @ManyToOne
    private ReservaEntity reserva; //Variable que indica la reserva en que esta.

    /**
     * Metodo que devuelve una estación.
     * @return la estación dueña del accesorio.
     */
    public EstacionEntity getEstacion() {
        return estacion;
    }

    /**
     * Metodo que asigna la estación.
     * @param estacion que será la dueña del accesorio.
     */
    public void setEstacion(EstacionEntity estacion) {
        this.estacion = estacion;
    }

    /**
     * Metodo que devuelve un integer de tipo de accesorio.
     * @return El tipo del accesorio.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Metodo que asigna el tipo.
     * @param tipo que será el tipo del accesorio.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que devuelve un integer de estado de accesorio.
     * @return El estado del accesorio.
     */
    public int getReservado() {
        return reservado;
    }

    /**
     * Metodo que asigna el estado.
     * @param reservado que será el estado del accesorio.
     */
    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    /**
     * Metodo que devuelve una reserva.
     * @return La reserva dueña del accesorio.
     */
    public ReservaEntity getReserva() {
        return reserva;
    }

    /**
     * Metodo que asigna la reserva.
     * @param reserva que será la reserva padre del accesorio.
     */
    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }
}

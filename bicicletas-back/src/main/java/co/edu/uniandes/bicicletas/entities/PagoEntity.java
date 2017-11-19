/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package co.edu.uniandes.bicicletas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Información de un pago asociado a una reserva
 * @author jd.trujillom
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    
    /**
     * Constante que modela el estado de una reserva en pagado. 
     */
    public static final Integer PAGADO = 0;
    
    /**
     * Constante que modela el estado de una reserva en espera del pago.
     */
    public static final Integer ESPERANDO_PAGO = 1;
    
    /**
     * Constante que modela el estado de un pago que no fue efectuado. Por ejemplo,
     * si la persona está dentro de la central de pagos y este es abortado o el
     * tiempo limite se alcanza. 
     */
    public static final Integer NO_EFECTUADO = 2;
    
    /**
     * Constante que modela el estado de un pago que fue reembolsado de forma
     * total. 
     */
    public static final Integer REEMBOLSO_TOTAL = 4;
    
    
    /**
     * Constante que modela el estado de un pago que se reembolso parcialmente
     * porque la reserva no se hizo efectiva. 
     */
    public static final Integer REEMBOLSO_PARCIAL = 5;
    
      /**
     * Constante que modela el estado de un pago el cual se está procesando.
     */
    public static final Integer PROCESANDO_PAGO = 7;
    
      /**
     * Constante que modela el estado de un pago el cual se está reembolsando.
     */
    public static final Integer PROCESANDO_REEMBOLSO = 8;
    
    /**
     * Constante que modela el método de pago con tarjeta de credito
     */
    public static final Integer TARJETA_DE_CREDITO = 1;
    
    /**
     * Constante que modela el método de pago con PSE.
     */
    public static final Integer PSE = 2;
    
    /**
     * Constante que modela el precio de alquilar una bicicleta por una hora
     */
    public static final Integer PRECIO_BICICLETA_HORA = 1000;
    
    /**
     * Relación que modela una reserva asociada a un pago 
     */
    @PodamExclude
    @OneToOne
    @JoinColumn(name="RESERVA_ID")
    @XmlInverseReference(mappedBy="pago")
    ReservaEntity reserva;
    
    /**
     * Atributo que modela el estado de un pago
     */
    
    private Integer estado;
    
    /**
     * Atributo que modela la fecha en que fue realizado el pago
     */
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    
    /**
     * Atributo que indica el id de la transacción con la que está ascociado un 
     * pago
     */
    
    private Long idTransaccion;
    
    
    /**
     * Atributo que indica el id del usuario que realizó el pago
     */
    
    private Long idUsuario;
    
    /**
     * Atributo que modela el pago hecho por un usuario al efectuar una reserva.
     */
    
    private Double monto;

    /**
     * Atributo que modela el método de pago que se usará
     */
    
    private Integer metodoDePago;
    
    /**
     * Atributo que modela la cantidad de bicicletas que aun no han sido
     * pagadas
     */
    private Integer bicicletasPendientes;
    
    /**
     * Retorna el estado actual del pago.
     * @return el estado del pago.
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * Cambia el estado en el que se encuentra el pago.
     * @param estado por el cual se quiere cambiar el pago.
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * Retorna la fecha en la que se efectuó el pago.
     * @return la feche del pago.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Cambia la fecha en la que se efectuó el pago.
     * @param fecha por la que se quiere cambiar la fecha del pago..
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

     /**
     * Retorna la transacción asociada al pago.
     * @return la transacción asociada al pago.
     */
    public Long getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Cambia la transacción asociada a un pago.
     * @param idTransaccion 
     */
    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * Retorna el usuario asociado a un pago.
     * @return el id del usuario.
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Cambia el usuario asociado a un pago
     * @param idUsuario del usuario que se quiere cambiar del pago.
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

     /**
     * Retorna el monto del pago.
     * @return el monto del pago.
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Cambia el monto del pago
     * @param monto por el que se quiere cambiar el monto del pago.
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    /**
     * Retorna la reserva asociado al pago.
     * @return la reserva del pago.
     */
    public ReservaEntity getReserva(){
        return reserva;
    }
    
    /**
     * Cambia la reserva asociada a un pago.
     * @param reserva por la que se quiere cambiar.
     */
    public void setReserva(ReservaEntity reserva){
        this.reserva = reserva;
    }

    /**
     * El método de pago empleado para realizar el pago.
     * @return el método de pago empleado.
     */
    public Integer getMetodoDePago() {
        return metodoDePago;
    }

    /**
     * Cambia el metodo de pago empleado para realziar el pago.
     * @param metodoDePago por el que se quiere cambiar.
     */
    public void setMetodoDePago(Integer metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

     /**
     * Retorna el número de bicicletas que no ha sido pagado.
     * @return el número de bicicletas pendientes.
     */
    public Integer getBicicletasPendientes() {
        return bicicletasPendientes;
    }

    /**
     * Cambia el numero de bicicletas pendientes
     * @param bicicletasPendientes por las cuales se quiere cambiar las bicicletas
     * pendientes.
     */
    public void setBicicletasPendientes(Integer bicicletasPendientes) {
        this.bicicletasPendientes = bicicletasPendientes;
    }
    
    
    
    
   
    
    
}

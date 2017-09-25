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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
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
    public final static Integer PAGADO = 0;
    
    /**
     * Constante que modela el estado de una reserva en espera del pago.
     */
    public final static Integer ESPERANDO_PAGO = 1;
    
    /**
     * Constante que modela el estado de un pago que no fue efectuado. Por ejemplo,
     * si la persona está dentro de la central de pagos y este es abortado o el
     * tiempo limite se alcanza. 
     */
    public final static Integer NO_EFECTUADO = 2;
    
    /**
     * Constante que modela el estado de un pago que fue reembolsado de forma
     * total. 
     */
    public final static Integer REEMBOLSO_TOTAL = 4;
    
    
    /**
     * Constante que modela el estado de un pago que se reembolso parcialmente
     * porque la reserva no se hizo efectiva. 
     */
    public final static Integer REEMBOLSO_PARCIAL = 5;
    
      /**
     * Constante que modela el estado de un pago el cual se está procesando.
     */
    public final static Integer PROCESANDO_PAGO = 7;
    
      /**
     * Constante que modela el estado de un pago el cual se está reembolsando.
     */
    public final static Integer PROCESANDO_REEMBOLSO = 8;
    
    /**
     * Relación que modela una reserva asociada a un pago 
     */
    @OneToOne
    @PodamExclude
    ReservaEntity reserva;
    
    /**
     * Atributo que modela el estado de un pago
     */
    
    private Integer estado;
    
    /**
     * Atributo que modela la fecha en que fue realizado el pago
     */
    
    @Temporal(javax.persistence.TemporalType.DATE)
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    
    public ReservaEntity getReserva(){
        return reserva;
    }
    
    
    public void setReserva(ReservaEntity reserva){
        this.reserva = reserva;
    }
    
    

   
    
    
}

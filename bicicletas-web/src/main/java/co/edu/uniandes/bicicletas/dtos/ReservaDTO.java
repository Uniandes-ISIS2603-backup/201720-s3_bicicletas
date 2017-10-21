
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import java.util.Date;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author ds.chacon
 */
public class ReservaDTO {
    
    private Long idReserva;
    private int estado;
    private UsuarioEntity usuarioReserva;
    private String fechaReserva;
    private String fechaInicio;
    private String fechaFin;
    private EstacionEntity estacionSalida;
    private EstacionEntity estacionLlegada;
    private double precioFinal;
    private Boolean descuento;
    
    
    public ReservaDTO(){
    }

    public ReservaDTO(ReservaEntity entidad) {
        if(entidad!=null){
            this.idReserva = entidad.getId();
            this.usuarioReserva = entidad.getUsuarioReserva();
            this.estado = entidad.getEstado();
            this.fechaInicio = entidad.getFechaInicio().toString();
            this.estacionSalida = entidad.getEstacionSalida();
            this.fechaFin = entidad.getFechaEntrega().toString();
            this.estacionLlegada = entidad.getEstacionLlegada();
            this.precioFinal = entidad.getPrecioFinal();
            this.fechaReserva = entidad.getFechaReserva().toString();
            this.descuento = entidad.getDescuento();
        }
    }
    
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.getIdReserva());
        entity.setUsuarioReserva(this.getUsuarioReserva());
        entity.setEstado(this.getEstado());
        entity.setFechaInicio(this.pasceFecha(fechaInicio));
        entity.setEstacionSalida(this.getEstacionSalida());
        entity.setFechaEntrega(this.pasceFecha(fechaFin));       
        entity.setEstacionLlegada(this.getEstacionLlegada());
        entity.setPrecioFinal(this.getPrecioFinal());
        entity.setFechaReserva(this.getFechaReserva());
        entity.setDescuento( );
        return entity;
    }

    
     public Date getFechaReserva() {
        return new GregorianCalendar().getTime();
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }
    /**
     * @return the idReserva
     */
    public Long getIdReserva() {
        return idReserva;
    }

    /**
     * @param idReserva the idReserva to set
     */
    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * @return the usuarioReserva
     */
    public UsuarioEntity getUsuarioReserva() {
        return usuarioReserva;
    }

    /**
     * @param usuarioReserva the usuarioReserva to set
     */
    public void setUsuarioReserva(UsuarioEntity usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaInicio
     */
    public String getFechaInicio() {
       return this.fechaInicio;
    }
    
    public Date pasceFecha ( String pfecha  ){
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            Date fecha = formato.parse(pfecha);
            return fecha;
        } catch (ParseException ex) {
           return new Date(2001, 01,01,01,00,00);
        }
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the estacionSalida
     */
    public EstacionEntity getEstacionSalida() {
        return estacionSalida;
    }

    /**
     * @param estacionSalida the estacionSalida to set
     */
    public void setEstacionSalida(EstacionEntity estacionSalida) {
        this.estacionSalida = estacionSalida;
    }

    /**
     * @return the fechaEntrega
     */
    public String getFechaEntrega() {
        return this.fechaFin;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(String fechaEntrega) {
        this.fechaFin = fechaEntrega;
    }

    /**
     * @return the estacionLlegada
     */
    public EstacionEntity getEstacionLlegada() {
        return estacionLlegada;
    }

    /**
     * @param estacionLlegada the estacionLlegada to set
     */
    public void setEstacionLlegada(EstacionEntity estacionLlegada) {
        this.estacionLlegada = estacionLlegada;
    }

    /**
     * @return the precioFinal
     */
    public double getPrecioFinal() {
        return precioFinal;
    }

    /**
     * @param precioFinal the precioFinal to set
     */
    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    
}
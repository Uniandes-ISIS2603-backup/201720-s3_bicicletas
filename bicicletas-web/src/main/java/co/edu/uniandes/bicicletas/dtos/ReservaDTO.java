
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

/**
 *
 * @author ds.chacon
 */
public class ReservaDTO {
    
    private Long idReserva;
    private String  fechaString;
    private UsuarioEntity usuarioReserva;
    private int estado;
    private Date fechaInicio;
    private EstacionEntity estacionSalida;
    private Date fechaEntrega;
    private EstacionEntity estacionLlegada;
    private double precioFinal;
        
    public ReservaDTO(){
    }

    public ReservaDTO(ReservaEntity entidad) {
        if(entidad!=null){
            this.idReserva = entidad.getId();
            this.usuarioReserva = entidad.getUsuarioReserva();
            this.estado = entidad.getEstado();
            this.fechaInicio = entidad.getFechaInicio();
            this.estacionSalida = entidad.getEstacionSalida();
            this.fechaEntrega = entidad.getFechaEntrega();
            this.estacionLlegada = entidad.getEstacionLlegada();
            this.precioFinal = entidad.getPrecioFinal();
            this.fechaString = entidad.getFechaString();
//            Date fecha = new Date(2000, 12, 1);
//            this.idReserva = Long.parseLong("133");
//            this.usuarioReserva =null ;
//            this.estado = 12;
//            this.fechaInicio = entidad.getFechaInicio();
//            this.estacionSalida = null;
//            this.fechaEntrega =null;
//            this.estacionLlegada = null;
//            this.precioFinal = 20;
        }
    }
    
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.getIdReserva());
        entity.setUsuarioReserva(this.getUsuarioReserva());
        entity.setEstado(this.getEstado());
        entity.setFechaInicio(this.getFechaInicio());
        entity.setEstacionSalida(this.getEstacionSalida());
        entity.setFechaEntrega(this.getFechaEntrega());       
        entity.setEstacionLlegada(this.getEstacionLlegada());
        entity.setPrecioFinal(this.getPrecioFinal());
        entity.setFechaString(this.getFechaString());

        return entity;
    }

     public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
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
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
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
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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
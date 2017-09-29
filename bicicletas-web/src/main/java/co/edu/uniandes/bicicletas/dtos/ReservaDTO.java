
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.PagoEntity;
import java.util.Date;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.util.List;
import javax.persistence.OneToOne;

/**
 *
 * @author ds.chacon
 */
public class ReservaDTO {
    
    
    int estado;
    long id;
    Date FechaEntrega;
    Date FechaInicio;
    double PrecioFinal;
    private Long idEstacionOrigen;
    private Long idEstacionLlegada;
    private Long idusuarioReserva;
    

    public long getUsuarioReserva() {
        return getIdusuarioReserva();
    }

    public void setUsuarioReserva(long pusuarioReserva) {
        this.setIdusuarioReserva((Long) pusuarioReserva);
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(Date FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }
    public double getPrecioFinal() {
        return PrecioFinal;
    }

    public void setPrecioFinal(double PrecioFinal) {
        this.PrecioFinal = PrecioFinal;
    }

    public Long getEstacionOrigen() {
        return getIdEstacionOrigen();
    }

    public void setEstacionOrigen(Long EstacionOrigen) {
        this.setIdEstacionOrigen(EstacionOrigen);
    }
    
     
    public ReservaDTO(){
        
    }
    
    public ReservaDTO(ReservaEntity entidad) {
        if(entidad!=null){
            this.id = entidad.getId();
            this.estado = entidad.getEstado();
            this.idEstacionOrigen = entidad.getEstacionSalida().getId();
            this.idEstacionLlegada = entidad.getEstacionLlegada().getId();
            this.FechaEntrega = entidad.getFechaEntrega();
            this.FechaInicio = entidad.getFechaInicio();
            this.PrecioFinal = entidad.getPrecioFinal();
            this.idusuarioReserva = entidad.getUsuarioReserva().getId();
        }
    }
    
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        
        entity.setId(this.getId());
        entity.setEstado(this.getEstado());
        entity.setFechaInicio(this.getFechaInicio());
        
        EstacionEntity lestacion2 = new EstacionEntity();
        lestacion2.setId(this.getIdEstacionesLlegada());
        entity.setEstacionLlegada(lestacion2);
        
        EstacionEntity lestacion = new EstacionEntity();
        lestacion.setId(this.getEstacionOrigen());
        entity.setEstacionSalida(lestacion);
        
        UsuarioEntity lusuario = new UsuarioEntity();
        lusuario.setId(this.getIdusuarioReserva());
        entity.setUsuarioReserva(lusuario);
        
        return entity;
    }

    /**
     * @return the idEstacionOrigen
     */
    public Long getIdEstacionOrigen() {
        return idEstacionOrigen;
    }

    /**
     * @param idEstacionOrigen the idEstacionOrigen to set
     */
    public void setIdEstacionOrigen(Long idEstacionOrigen) {
        this.idEstacionOrigen = idEstacionOrigen;
    }

    /**
     * @return the idEstacionesLlegada
     */
    public Long getIdEstacionesLlegada() {
        return idEstacionLlegada;
    }

    /**
     * @param idEstacionesLlegada the idEstacionesLlegada to set
     */
    public void setIdEstacionesLlegada(Long idEstacionesLlegada) {
        this.idEstacionLlegada = idEstacionesLlegada;
    }

    /**
     * @return the idusuarioReserva
     */
    public Long getIdusuarioReserva() {
        return idusuarioReserva;
    }

    /**
     * @param idusuarioReserva the idusuarioReserva to set
     */
    public void setIdusuarioReserva(Long idusuarioReserva) {
        this.idusuarioReserva = idusuarioReserva;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;

/**
 *
 * @author ka.babativa
 */
public class AccesorioDTO {
    private int tipo;
    private int reservado;
    private Long id; 
    private EstacionEntity estacion;
    private ReservaEntity reserva;

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

    public EstacionEntity getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionEntity estacion) {
        this.estacion = estacion;
    }

    public AccesorioDTO(){
        
    }
    
    public AccesorioDTO(AccesorioEntity entidad){
        if(entidad!=null){
            this.tipo = entidad.getTipo();
            this.id = entidad.getId();
            this.estacion = entidad.getEstacion();
            this.reserva = entidad.getReserva();
            this.reservado = entidad.getReservado();
        }
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public AccesorioEntity toEntity(){
        AccesorioEntity entity = new AccesorioEntity();
        entity.setTipo(this.tipo);
        entity.setId(this.id);
        entity.setEstacion(this.estacion);
        entity.setReserva(this.reserva);
        entity.setReservado(this.reservado);
        return entity;
    }
}

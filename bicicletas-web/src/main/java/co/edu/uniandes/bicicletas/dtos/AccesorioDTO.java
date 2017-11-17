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
 * Clase DTO de Acceosrio
 * @author ka.babativa
 */
public class AccesorioDTO {
    private int tipo; //Variable que modela el tipo 
    private int reservado; //Variable que modela el estado
    private Long id;  //Variable que modela el ID del accesorio
    private EstacionEntity estacion; //Estacion padre del accesorio
    private ReservaEntity reserva; //Reserva padre del accesorio
    
    /**
     * Constructor de la clase. No necesita cuerpo.
     */
    public AccesorioDTO(){
        //No necesita cuerpo
        
    }
    
    /**
     * Constructor en caso de que llegue una entidad.
     * @param entidad Entidad a convertir
     */
    public AccesorioDTO(AccesorioEntity entidad){
        if(entidad!=null){
            this.tipo = entidad.getTipo();
            this.id = entidad.getId();
            this.estacion = entidad.getEstacion();
            this.reserva = entidad.getReserva();
            this.reservado = entidad.getReservado();
        }
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
     * Metodo que devuelve un integer de ID.
     * @return El ID del accesorio.
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que asigna el ID.
     * @param id Id que se le dara el accesorio.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Metodo que pasa de DTO A entidad
     * @return Una entidad con las caracteristicas del accesorio.
     */
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

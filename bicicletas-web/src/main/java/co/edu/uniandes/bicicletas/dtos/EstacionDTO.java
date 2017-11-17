/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.EstacionEntity;
/**
 * Clase que modela objetos EstacionDTO
 * @author ka.babativa
 */
public class EstacionDTO {
    private String nombre; //Atributo que modela el nombre
    private Long id; //Atributo que modela el ID De la estacion
    private String direccion; //Atributo que modela la direccion de la estacion
    private String ciudad; //Atributo que modela la ciudad de la estacion
    
    /**
     * Constructor de la clase. No necesita cuerpo.
     */
    public EstacionDTO(){
        //No necesita cuerpo
    }

    /**
     * Constructor de la clase en que caso de que haya una entidad por parametro.
     * @param entidad Con las caracteristicas por parametro
     */
    public EstacionDTO(EstacionEntity entidad) {
        if(entidad!=null){
            this.nombre = entidad.getName();
            this.id = entidad.getId();
        }
    }
    
    /**
     * Metodo que retorna una dirección.
     * @return Un string con la direccion de la estación.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
    * Metodo que asigna la dirección a una estación.
    * @param direccion String con la direccion de la estación.
    */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
    * Metodo que retorna una ciudad.
    * @return Un string con la ciudad de la estación.
    */
    public String getCiudad() {
        return ciudad;
    }

    /**
    * Metodo que asigna la ciudad a una estación.
    * @param ciudad String con la ciudad de la estación.
    */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    /**
     * Metodo que asigna el nombre a una estación.
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
    * Metodo que asigna el nombre a una estación.
    * @param nombre String con el nombre de la estación.
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return Una entidad con las caracteristicas de la estación.
     */
    public EstacionEntity toEntity() {
        EstacionEntity entity = new EstacionEntity();
        entity.setName(this.getNombre());
        entity.setId(this.getId());
        entity.setDireccion(this.direccion);
        entity.setCiudad(this.ciudad);
        return entity;
    }
}

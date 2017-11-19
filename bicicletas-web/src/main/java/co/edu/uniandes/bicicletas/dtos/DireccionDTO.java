/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;

/**
 * DireccionDTO Objeto de transferencia de datos de Direcciones. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor.
 *
 * @author cm.alba10
 */
public class DireccionDTO {

   private Long id;
   private String name; 
   private Integer codigoPostal; 
   private String descripcion; 
   private String ciudad;
   
   /**
     * Constructor por defecto
     */
    public DireccionDTO() {
        //No necesita cuerpo
    }
    
    /**
     *  *Esta es la documentación 
     * del metodo direcciondto.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param direccion: Es la entidad que se va a convertir a DTO 
     */
    public DireccionDTO(DireccionEntity direccion) {
        this.id = direccion.getId();
        this.name = direccion.getName();
        this.codigoPostal= direccion.getCodigoPostal();
        this.descripcion= direccion.getDescripcion();
        this.ciudad= direccion.getCiudad();
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    /**
     *
     * @param codigoPostal
     */
    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * *Esta es la documentación 
     * del metodo descripcion.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * *Esta es la documentación 
     * del metodo setdescripocion.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     *
     * @param ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
     /**
      *  *Esta es la documentación 
     * del metodo toentity.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public DireccionEntity toEntity() {
        DireccionEntity entity = new DireccionEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCodigoPostal(this.codigoPostal);
        entity.setDescripcion(this.descripcion);
        entity.setCiudad(this.ciudad);
        return entity;
    }
}

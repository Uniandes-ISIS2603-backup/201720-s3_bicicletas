/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.util.Date;

/**
 * UsuarioDTO Objeto de transferencia de datos de Usuarios. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor.
 *
 * @author cm.alba10
 */
public class UsuarioDTO {
    
   private Long id;
   private String name; 
   private Integer tipoId; 
   private Long documentoUsuario;
   private Date fechaNacimiento;
   private Integer puntos;
   
    /**
     * Constructor por defecto
     */
    public UsuarioDTO() {
    }
    
    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param usuario: Es la entidad que se va a convertir a DTO 
     */
    public UsuarioDTO(UsuarioEntity usuario) {
        this.id = usuario.getId();
        this.name = usuario.getName();
        this.tipoId= usuario.getTipoId();
        this.documentoUsuario= usuario.getDocumentoUsuario();
        this.fechaNacimiento= usuario.getFechaNacimiento();
        this.puntos= usuario.getPuntos();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public Long getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(Long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
    
     /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setTipoId(this.tipoId);
        entity.setDocumentoUsuario(this.documentoUsuario);
        entity.setFechaNacimiento(this.fechaNacimiento);
        entity.setPuntos(this.puntos);
        return entity;
    }
}

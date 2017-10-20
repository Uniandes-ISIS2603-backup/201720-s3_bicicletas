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

   private String nombre; 
   private Integer tipoId; 
   private Long documentoUsuario;
   private Date fechaNacimiento;
   
   private Long tarjetaCredito;
   private int numeroCsv;
   private String contraseniaPSE;
    /**
     * Constructor por defecto
     */
    public UsuarioDTO() {
        //Do nothing because is not necessary
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param usuario: Es la entidad que se va a convertir a DTO 
     */
    public UsuarioDTO(UsuarioEntity usuario) {
        this.nombre = usuario.getNombre();
        this.tipoId= usuario.getTipoId();
        this.documentoUsuario= usuario.getDocumentoUsuario();
        this.fechaNacimiento= usuario.getFechaNacimiento();
        
        this.tarjetaCredito= usuario.getTarjetaCredito();
        this.numeroCsv= usuario.getNumeroCsv();
        this.contraseniaPSE=usuario.getContraseniaPSE();
    }



    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public Integer getTipoId() {
        return tipoId;
    }

    /**
     *
     * @param tipoId
     */
    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    /**
     *
     * @return
     */
    public Long getDocumentoUsuario() {
        return documentoUsuario;
    }

    /**
     *
     * @param documentoUsuario
     */
    public void setDocumentoUsuario(Long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    /**
     *
     * @return
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     *
     * @return
     */
    public Long getTarjetaCredito() {
        return tarjetaCredito;
    }

    /**
     *
     * @param tarjetaCredito
     */
    public void setTarjetaCredito(Long tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    /**
     *
     * @return
     */
    public int getNumeroCsv() {
        return numeroCsv;
    }

    /**
     *
     * @param numeroCsv
     */
    public void setNumeroCsv(int numeroCsv) {
        this.numeroCsv = numeroCsv;
    }

    /**
     *
     * @return
     */
    public String getContraseniaPSE() {
        return contraseniaPSE;
    }

    /**
     *
     * @param contraseniaPSE
     */
    public void setContraseniaPSE(String contraseniaPSE) {
        this.contraseniaPSE = contraseniaPSE;
    }

    
    
     /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(this.nombre);
        entity.setTipoId(this.tipoId);
        entity.setDocumentoUsuario(this.documentoUsuario);
        entity.setFechaNacimiento(this.fechaNacimiento);
        entity.setTarjetaCredito(this.tarjetaCredito);
        entity.setNumeroCsv(this.numeroCsv);
        entity.setContraseniaPSE(this.contraseniaPSE);
        return entity;
    }
}
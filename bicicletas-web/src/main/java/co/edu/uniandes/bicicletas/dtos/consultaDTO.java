/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ds.chacon
 */
public class consultaDTO {
    
    private UsuarioEntity usuario;
    
    private Date fechaInicio;
            
    private Date fechaFinal;
    
    private String stringInicio;
            
    private String stringFinal;
    
    public consultaDTO(){
        
    }
    

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio( String fechaInicio) {
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         try {
            Date fecha = formato.parse(fechaInicio);
            this.fechaInicio = fecha;
        } catch (ParseException ex) {
           this.fechaInicio = new Date(2001, 01,01,01,00,00);
        }
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         try {
            Date fecha = formato.parse(fechaFinal);
            this.fechaFinal = fecha;
        } catch (ParseException ex) {
           this.fechaFinal = new Date(2001, 01,01,01,00,00);
        }
    }

    public String getStringInicio() {
        return stringInicio;
    }

    public void setStringInicio(String stringInicio) {
        this.stringInicio = stringInicio;
    }

    public String getStringFinal() {
        return stringFinal;
    }

    public void setStringFinal(String stringFinal) {
        this.stringFinal = stringFinal;
    }
    
    
                    
    
}

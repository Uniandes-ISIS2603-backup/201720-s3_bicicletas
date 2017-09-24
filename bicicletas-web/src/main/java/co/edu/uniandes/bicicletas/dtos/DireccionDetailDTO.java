/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;

import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import static javax.ws.rs.client.Entity.entity;

/**
 *
 * @author cm.alba10
 */
public class DireccionDetailDTO extends DireccionDTO{
    
    
    // relación  cero o muchos usuarios
    private List<UsuarioDTO> usuario;
    
    // relación  estacion
    private List<EstacionDTO> estacion;
    /**
     * Constructor por defecto
     */
    public DireccionDetailDTO() {
        super();
    }
    
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public DireccionDetailDTO(DireccionEntity entity) {
        if (entity.getUsuarios()!= null) {
            usuario = new ArrayList<>();
            for (UsuarioEntity entityUsuario : entity.getUsuarios()) {
                usuario.add(new UsuarioDTO(entityUsuario));
            }

        }
        
        if (entity.getEstaciones()!= null) {
        estacion = new ArrayList<>();
        for (EstacionEntity entityEstacion : entity.getEstaciones()) {
            estacion.add(new EstacionDTO(entityEstacion));
        }

    }
        
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public DireccionEntity toEntity() {
        DireccionEntity direccionE = super.toEntity();
    
    if (usuario != null) {
        List<UsuarioEntity> usuariosEntity = new ArrayList<>();
        for (UsuarioDTO dtoUsuario : usuario) {
            usuariosEntity.add(dtoUsuario.toEntity());
        }
        direccionE.setUsuarios(usuariosEntity);
    }
    
    if (estacion != null) {
        List<EstacionEntity> estacionsEntity = new ArrayList<>();
        for (EstacionDTO dtoEstacion : estacion) {
            estacionsEntity.add(dtoEstacion.toEntity());
        }
        direccionE.setEstaciones(estacionsEntity);
    }
    
    return direccionE;
    }

    public List<UsuarioDTO> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<UsuarioDTO> usuario) {
        this.usuario = usuario;
    }

    public List<EstacionDTO> getEstacion() {
        return estacion;
    }

    public void setEstacion(List<EstacionDTO> estacion) {
        this.estacion = estacion;
    }
    
    
}

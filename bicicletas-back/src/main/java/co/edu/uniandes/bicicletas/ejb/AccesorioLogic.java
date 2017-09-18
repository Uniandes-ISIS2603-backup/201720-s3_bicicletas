/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.persistence.AccesorioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ka.babativa
 */
@Stateless
public class AccesorioLogic {
    @Inject
    private AccesorioPersistence persistence;
    
    public AccesorioEntity getAccesorio(Long id) throws WebApplicationException{
        AccesorioEntity accesorio = persistence.find(id);
        if(accesorio == null){
            throw new WebApplicationException("No hay un accesorio con dicho ID", 402);
        }
        return accesorio;
    }
    
    public void deleteAccesorio(Long id) throws WebApplicationException{
        AccesorioEntity accesorio = persistence.find(id);
         if(accesorio == null){
             throw new WebApplicationException("No hay un accesorio con dicho ID", 402);
         }
         persistence.delete(id);
    }
    
    public List<AccesorioEntity> getAccesorios(){
        return persistence.findAll();
    }
    
    public AccesorioEntity crearAccesorio(AccesorioEntity entidad){
        persistence.create(entidad);
        return entidad;
    }
    
    public AccesorioEntity actualizarEstacion(AccesorioEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una accesorio con dicho id", 402);
        }
        return persistence.update(entidad);
    }
}

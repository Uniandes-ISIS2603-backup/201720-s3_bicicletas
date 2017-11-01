/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.persistence.AccesorioPersistence;
import java.util.ArrayList;
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
    
    //private EstacionLogic estacionLogic;
    
    
    public AccesorioEntity getAccesorio(Long id) throws WebApplicationException{
        AccesorioEntity accesorio = persistence.find(id);
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
    
    public AccesorioEntity crearAccesorio(AccesorioEntity entidadA)throws BusinessLogicException{
        
        //Falta que se cree la relación entre Eeserva y Estacion
        //EstacionEntity estacion = estacionLogic.getEstacion(entidadA.getEstacion().getId());
       // if(estacion == null){
        //    throw new BusinessLogicException("No se encontró la estación que se desea agregar al sistema");
       // }
        
        AccesorioEntity accesorioEntity = persistence.create(entidadA);
        
        //List<AccesorioEntity> listaAccesorios = estacion.getAccesorios();
        
        //if(listaAccesorios == null){
        //    listaAccesorios = new ArrayList<>();
       //     listaAccesorios.add(accesorioEntity);
        //    estacion.setAccesorios(listaAccesorios);
       // }
       // 
       // listaAccesorios.add(accesorioEntity);
        
        return accesorioEntity;
    }
    
    public AccesorioEntity actualizarAccesorio(AccesorioEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una accesorio con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
    
    
    public EstacionEntity listEstaciones(Long direccionId) {
        return getAccesorio(direccionId).getEstacion();
    }
    
    public AccesorioEntity getDireccion(Long id) {
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        AccesorioEntity accesorio = persistence.find(id);
        return accesorio;
    }
    
    /**
     * Obtiene una instancia de EstacionEntity asociada a una instancia de Direccion
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param estacionesId Identificador de la instancia de Estacion
     * @return
     *
     */
    public EstacionEntity getEstacion(Long direccionId, Long estacionesId) {
        EstacionEntity list = getDireccion(direccionId).getEstacion();
        return list;
    }
    
    /**
     * Asocia un Estacion existente a un Direccion
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param estacionesId Identificador de la instancia de Estacion
     * @return Instancia de EstacionEntity que fue asociada a Direccion
     *
     */
    public EstacionEntity replaceEstacion(Long direccionId, EstacionEntity estacion) {
        AccesorioEntity accesorioEntity = getAccesorio(direccionId);
        accesorioEntity.setEstacion(estacion);
        return accesorioEntity.getEstacion();
    }
    
    /**
     * Desasocia un Estacion existente de un Direccion existente
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param estacionesId Identificador de la instancia de Estacion
     *
     */
   // public void removeEstacion(Long direccionId, Long estacionesId) {
     //   estacionLogic.removeEstacion(direccionId, estacionesId);
    //}
    
    public EstacionEntity addEstacion(Long direccionId, Long estacionesId) {
        AccesorioEntity direccionEntity = getAccesorio(direccionId);
        EstacionEntity estacionesEntity = new EstacionEntity();
        estacionesEntity.setId(estacionesId);
        direccionEntity.setEstacion(estacionesEntity);
        return getEstacion(direccionId, estacionesId);
    }
}

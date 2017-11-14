/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.persistence.AccesorioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa la logica de un 
 * @author ka.babativa
 */
@Stateless
public class AccesorioLogic {
    @Inject
    private AccesorioPersistence persistence; //Atributo para accewder a la persistencia del accesorio
    
    /**
     * Metodo que retorna un accesorio
     * @param id del accesorio.
     * @return accesorio con el id dado.
     * @throws WebApplicationException 
     */
    public AccesorioEntity getAccesorio(Long id) throws WebApplicationException{
        AccesorioEntity accesorio = persistence.find(id);
        return accesorio;
    }
    /**
     * Metodo que elimina un accesorio.
     * @param id del acccesorio
     * @throws WebApplicationException 
     */
    public void deleteAccesorio(Long id) throws WebApplicationException{
        AccesorioEntity accesorio = persistence.find(id);
        if(accesorio == null){
            throw new WebApplicationException("No hay un accesorio con dicho ID", 402);
        }
        persistence.delete(id);
    }
    
    /**
     * Metodo que retorna todos los accesorios.
     * @return una lista de accesorios.
     */
    public List<AccesorioEntity> getAccesorios(){
        return persistence.findAll();
    }
    
    /**
     * Metodo que crea un accesorio
     * @param entidadA Accesorio que sera creado.
     * @return El accesorio creado.
     * @throws BusinessLogicException 
     */
    public AccesorioEntity crearAccesorio(AccesorioEntity entidadA)throws BusinessLogicException{
        
       
        
        AccesorioEntity accesorioEntity = persistence.create(entidadA);
        
       
        
        return accesorioEntity;
    }
    /**
     * Metodo que actualiza un accesorio
     * @param entidad con el accesorio a actualizar
     * @return el accesorio actualizado.
     * @throws WebApplicationException 
     */
    public AccesorioEntity actualizarAccesorio(AccesorioEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una accesorio con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
    
    /**
     * Metodo que retorna una estacion.
     * @param direccionId
     * @return 
     */
    public EstacionEntity listEstaciones(Long direccionId) {
        return getAccesorio(direccionId).getEstacion();
    }
    
    /**
     * Metodo que retorna un accesorio dado un ID.
     * @param id a buscar.
     * @return Un accesorio.
     */
    public AccesorioEntity getDireccion(Long id) {
        
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
    
   
    
    public EstacionEntity addEstacion(Long direccionId, Long estacionesId) {
        AccesorioEntity direccionEntity = getAccesorio(direccionId);
        EstacionEntity estacionesEntity = new EstacionEntity();
        estacionesEntity.setId(estacionesId);
        direccionEntity.setEstacion(estacionesEntity);
        return getEstacion(direccionId, estacionesId);
    }
}

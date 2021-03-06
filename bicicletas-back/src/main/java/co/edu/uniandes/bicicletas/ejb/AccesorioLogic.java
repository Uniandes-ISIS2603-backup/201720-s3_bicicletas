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
    public AccesorioEntity getAccesorio(Long id){
        return persistence.find(id);
    }
    /**
     * Metodo que elimina un accesorio.
     * @param id del acccesorio
     * @throws WebApplicationException 
     */
    public void deleteAccesorio(Long id){
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
        return persistence.create(entidadA);
    }
    /**
     * Metodo que actualiza un accesorio
     * @param entidad con el accesorio a actualizar
     * @return el accesorio actualizado.
     * @throws WebApplicationException 
     */
    public AccesorioEntity actualizarAccesorio(AccesorioEntity entidad){
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
        
        return persistence.find(id);

    }
    
    /**
     * Obtiene una instancia de EstacionEntity asociada a una instancia de Direccion
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param estacionesId Identificador de la instancia de Estacion
     * @return
     *
     */
    public EstacionEntity getEstacion(Long direccionId) {
        return getDireccion(direccionId).getEstacion();
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
     * Metodo que retorna una estacion dada una direccion y una estacion.
     * @param direccionId ID de la direccion a Consultar
     * @param estacionesId ID De la estacion a consultar
     * @return Una estacion entity.
     */
    public EstacionEntity addEstacion(Long direccionId, Long estacionesId) {
        AccesorioEntity direccionEntity = getAccesorio(direccionId);
        EstacionEntity estacionesEntity = new EstacionEntity();
        estacionesEntity.setId(estacionesId);
        direccionEntity.setEstacion(estacionesEntity);
        return getEstacion(direccionId);
    }
}

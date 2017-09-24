/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.persistence.EstacionPersistence;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author 
 */
@Stateless
public class EstacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());
    
    @Inject
    private EstacionPersistence persistence;
    
    @Inject
    private DireccionLogic direccionLogic;
    
    public EstacionEntity getEstacion(Long id) throws WebApplicationException
    {
        //Toca agregarle más cosas, solo lo hice provisional
         EstacionEntity estacion = persistence.find(id);
         if(estacion == null){
             throw new WebApplicationException("No hay una estación con dicho ID", 402);
         }
         return estacion;
    }
    
    public void deleteEstacion(Long id) throws WebApplicationException
    {
         EstacionEntity estacion = persistence.find(id);
         if(estacion == null){
             throw new WebApplicationException("No hay una estación con dicho ID", 402);
         }
         persistence.delete(id);
    }
    
    public List<EstacionEntity> getEstaciones(){
        return persistence.findAll();
    }
    
    public EstacionEntity crearEstacion(EstacionEntity entidad){
        persistence.create(entidad);
        return entidad;
    }
    
    public EstacionEntity actualizarEstacion(EstacionEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    /**
     * Obtiene una colección de instancias de DireccionEntity asociadas a una
     * instancia de Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @return Colección de instancias de DireccionEntity asociadas a la instancia de
     * Estacion
     * @generated
     */
    public List<DireccionEntity> listDirecciones(Long estacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", estacionId);
        return getEstacion(estacionId).getDirecciones();
    }

    /**
     * Obtiene una instancia de DireccionEntity asociada a una instancia de Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * @return
     * @generated
     */
    public DireccionEntity getDireccion(Long estacionId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un libro con id = {0}", direccionesId);
        List<DireccionEntity> list = getEstacion(estacionId).getDirecciones();
        DireccionEntity direccionesEntity = new DireccionEntity();
        direccionesEntity.setId(direccionesId);
        int index = list.indexOf(direccionesEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Direccion existente a un Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * @return Instancia de DireccionEntity que fue asociada a Estacion
     * @generated
     */
    public DireccionEntity addDireccion(Long estacionId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar un libro al estacion con id = {0}", estacionId);
        direccionLogic.addEstacion(direccionesId, estacionId);
        return direccionLogic.getDireccion(direccionesId);
    }

    /**
     * Remplaza las instancias de Direccion asociadas a una instancia de Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param list Colección de instancias de DireccionEntity a asociar a instancia
     * de Estacion
     * @return Nueva colección de DireccionEntity asociada a la instancia de Estacion
     * @generated
     */
    public List<DireccionEntity> replaceDirecciones(Long estacionId, List<DireccionEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al estacion con id = {0}", estacionId);
        EstacionEntity estacionEntity = getEstacion(estacionId);
        List<DireccionEntity> direccionList = direccionLogic.getDirecciones();
        for (DireccionEntity direccion : direccionList) {
            if (list.contains(direccion)) {
                if (!direccion.getEstaciones().contains(estacionEntity)) {
                    direccionLogic.addEstacion(direccion.getId(), estacionId);
                }
            } else {
                direccionLogic.removeEstacion(direccion.getId(), estacionId);
            }
        }
        estacionEntity.setDirecciones(list);
        return estacionEntity.getDirecciones();
    }

    /**
     * Desasocia un Direccion existente de un Estacion existente
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * @generated
     */
    public void removeDireccion(Long estacionId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del estacion con id = {0}", estacionId);
        direccionLogic.removeEstacion(direccionesId, estacionId);
    }
    
}

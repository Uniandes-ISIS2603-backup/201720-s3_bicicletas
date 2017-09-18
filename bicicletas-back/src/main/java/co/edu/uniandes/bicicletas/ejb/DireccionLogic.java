/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.persistence.DireccionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author cm.alba10
 */
@Stateless
public class DireccionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(DireccionLogic.class.getName());

    @Inject
    private DireccionPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public DireccionEntity createDireccion(DireccionEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de direccion");
        // Verifica la regla de negocio que dice que no puede haber dos direcciones con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Direccion con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear la direccion
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de direccion");
        return entity;
    }
    
    /**
     * 
     * Obtener todas las direcciones existentes en la base de datos.
     *
     * @return una lista de direcciones.
     */
    public List<DireccionEntity> getDirecciones() {
        LOGGER.info("Inicia proceso de consultar todas las direcciones");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<DireccionEntity> direcciones = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las direcciones");
        return direcciones;
    }
    
    /**
     *
     * Obtener una direccion por medio de su id.
     * 
     * @param id: id de la direccion para ser buscada.
     * @return la direccion solicitada por medio de su id.
     */
    public DireccionEntity getDireccion(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar direccion con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        DireccionEntity direccion = persistence.find(id);
        if (direccion == null) {
            LOGGER.log(Level.SEVERE, "La direccion con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar direccion con id={0}", id);
        return direccion;
    }
    
    /**
     *
     * Actualizar una direccion.
     *
     * @param id: id de la direccion para buscarla en la base de datos.
     * @param entity: direccion con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return la direccion con los cambios actualizados en la base de datos.
     */
    public DireccionEntity updateDireccion(Long id, DireccionEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar direccion con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        DireccionEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar direccion con id={0}", entity.getId());
        return newEntity;
    }
    
    /**
     * Borrar un direccion
     *
     * @param id: id de la direccion a borrar
     */
    public void deleteDireccion(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar direccion con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar direccion con id={0}", id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
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
     * Esta es la documentación 
     * del metodo createdireccion.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
     * Esta es la documentación 
     * del metodo getdirecciones.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
     * Esta es la documentación 
     * del metodo setDocumentoUsuario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
     * Esta es la documentación 
     * del metodo updatedireccion.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
     * Esta es la documentación 
     * del metodo deletedireccion.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
    
    ///////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////RELACION CON USUARIOS////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Obtiene una colección de instancias de UsuarioEntity asociadas a una
     * instancia de Direccion
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @return Colección de instancias de UsuarioEntity asociadas a la instancia
     * de Direccion
     * 
     */
    public List<UsuarioEntity> listUsuarios(Long direccionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios del direccion con id = {0}", direccionId);
        return getDireccion(direccionId).getUsuarios();
    }

    /**
     * Esta es la documentación 
     * del metodo getusuario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Obtiene una instancia de UsuarioEntity asociada a una instancia de Direccion
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param usuariosId Identificador de la instancia de Usuario
     * @return 
     * 
     */
    public UsuarioEntity getUsuario(Long direccionId, Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un usuario del direccion con id = {0}", direccionId);
        List<UsuarioEntity> list = getDireccion(direccionId).getUsuarios();
        UsuarioEntity usuariosEntity = new UsuarioEntity();
        usuariosEntity.setDocumentoUsuario(usuariosId);
        int index = list.indexOf(usuariosEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Esta es la documentación 
     * del metodo aduusaruio.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Asocia un Usuario existente a un Direccion
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param usuariosId Identificador de la instancia de Usuario
     * @return Instancia de UsuarioEntity que fue asociada a Direccion
     * 
     */
    public UsuarioEntity addUsuario(Long direccionId, Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un usuario al direccion con id = {0}", direccionId);
        DireccionEntity direccionEntity = getDireccion(direccionId);
        UsuarioEntity usuariosEntity = new UsuarioEntity();
        usuariosEntity.setDocumentoUsuario(usuariosId);
        direccionEntity.getUsuarios().add(usuariosEntity);
        return getUsuario(direccionId, usuariosId);
    }

    /**
     * Esta es la documentación 
     * del metodo rplaceusaurio.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Remplaza las instancias de Usuario asociadas a una instancia de Direccion
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param list Colección de instancias de UsuarioEntity a asociar a instancia
     * de Direccion
     * @return Nueva colección de UsuarioEntity asociada a la instancia de Direccion
     * 
     */
    public List<UsuarioEntity> replaceUsuarios(Long direccionId, List<UsuarioEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un usuario del direccion con id = {0}", direccionId);
        DireccionEntity direccionEntity = getDireccion(direccionId);
        direccionEntity.setUsuarios(list);
        return direccionEntity.getUsuarios();
    }

    /**
     * Esta es la documentación 
     * del metodo removeusario.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Desasocia un Usuario existente de un Direccion existente
     *
     * @param direccionId Identificador de la instancia de Direccion
     * @param usuariosId Identificador de la instancia de Usuario
     * 
     */
    public void removeUsuario(Long direccionId, Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un usuario del direccion con id = {0}", direccionId);
        DireccionEntity entity = getDireccion(direccionId);
        UsuarioEntity usuariosEntity = new UsuarioEntity();
        usuariosEntity.setDocumentoUsuario(usuariosId);
        entity.getUsuarios().remove(usuariosEntity);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.UsuarioPersistence;
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
public class UsuarioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    
    @Inject
    private DireccionLogic direccionLogic;
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de usuario");
        // Verifica la regla de negocio que dice que no puede haber dos usuarios con el mismo nombre
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un Usuario con el nombre \"" + entity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear el usuario
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de usuario");
        return entity;
    }
    
    
    /**
     * 
     * Obtener todos los usuarios existentes en la base de datos.
     *
     * @return una lista de usuarios.
     */
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.info("Inicia proceso de consultar todos los usuarios");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<UsuarioEntity> usuarios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas los usuarios");
        return usuarios;
    }

    /**
     *
     * Obtener un usuario por medio de su id.
     * 
     * @param id: id del usuario para ser buscada.
     * @return el usuario solicitada por medio de su id.
     */
    public UsuarioEntity getUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar usuario con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        UsuarioEntity usuario = persistence.find(id);
        if (usuario == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar usuario con id={0}", id);
        return usuario;
    }

    /**
     *
     * Actualizar un usuario.
     *
     * @param id: id del usuario para buscarla en la base de datos.
     * @param entity: usuario con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return el usuario con los cambios actualizados en la base de datos.
     */
    /**
     *
     * Actualizar un usuario.
     *
     * @param id: id del usuario para buscarla en la base de datos.
     * @param entity: usuario con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return el usuario con los cambios actualizados en la base de datos.
     */
    public UsuarioEntity updateUsuario(Long id, UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar usuario con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        UsuarioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar usuario con id={0}", entity.getDocumentoUsuario());
        return newEntity;
    }
   

    /** public UsuarioEntity updateUsuario(Long id, UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar usuario con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        UsuarioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar usuario con id={0}", entity.getId());
        return newEntity;
    }
     * Borrar un usuario
     *
     * @param id: id del la usuario a borrar
     */
    public void deleteUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar usuario con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar usuario con id={0}", id);
    }
    
    //PARTE DE DIRECCION
    
   /**
     * Obtiene una colección de instancias de DireccionEntity asociadas a una
     * instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @return Colección de instancias de DireccionEntity asociadas a la instancia de
     * Usuario
     * @generated
     */
    public List<DireccionEntity> listDirecciones(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las direcciones del usuario con id = {0}", usuarioId);
        return getUsuario(usuarioId).getDirecciones();
    }
    
    /**
     * Obtiene una instancia de DireccionEntity asociada a una instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param direccionesId Identificador de la instancia de Direcciones
     * @return
     * @generated
     */
    public DireccionEntity getDirecciones(Long usuarioId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un direccion con id = {0}", direccionesId);
        List<DireccionEntity> list = getUsuario(usuarioId).getDirecciones();
        DireccionEntity direccionesEntity = new DireccionEntity();
        direccionesEntity.setId(direccionesId);
        int index = list.indexOf(direccionesEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia una direccion existente a un Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param direccionesId Identificador de la instancia de Direcciones
     * @return Instancia de DireccionEntity que fue asociada a Usuario
     * @generated
     */
    public DireccionEntity addDireccion(Long usuarioId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar un direccion al usuario con id = {0}", usuarioId);
        direccionLogic.addUsuario(direccionesId, usuarioId);
        return direccionLogic.getDireccion(direccionesId);
    }

    /**
     * Remplaza las instancias de Direcciones asociadas a una instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param list Colección de instancias de DireccionEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de DireccionEntity asociada a la instancia de Usuario
     * @generated
     */
    public List<DireccionEntity> replaceDirecciones(Long usuarioId, List<DireccionEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los direcciones asocidos al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = getUsuario(usuarioId);
        List<DireccionEntity> direccionList = direccionLogic.getDirecciones();
        for (DireccionEntity direccion : direccionList) {
            if (list.contains(direccion)) {
                if (!direccion.getUsuarios().contains(usuarioEntity)) {
                    direccionLogic.addUsuario(direccion.getId(), usuarioId);
                }
            } else {
                direccionLogic.removeUsuario(direccion.getId(), usuarioId);
            }
        }
        usuarioEntity.setDirecciones(list);
        return usuarioEntity.getDirecciones();
    }

    /**
     * Desasocia un Direccion existente de un Usuario existente
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param direccionesId Identificador de la instancia de Direccion
     * @generated
     */
    public void removeDireccion(Long usuarioId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un direccion del usuario con id = {0}", usuarioId);
        direccionLogic.removeUsuario(direccionesId, usuarioId);
    }
}

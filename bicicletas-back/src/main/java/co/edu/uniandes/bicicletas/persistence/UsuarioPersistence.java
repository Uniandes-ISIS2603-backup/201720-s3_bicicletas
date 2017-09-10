/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package co.edu.uniandes.bicicletas.persistence;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que se encarga de persistir los objetos de tipo UsuarioEntiy
 * @author cm.alba10
 */
@Stateless
public class UsuarioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(DireccionPersistence.class.getName());
    
    @PersistenceContext(unitName="bicicletasPU")
    protected EntityManager em;
    
   /**
     * Devuelve todas los usuarios de la base de datos
     * @return Una lista con todas los usuarios que se encuentran en el sistema
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.info("Consultando todas los usuarios");
       
        // Se crea un query para buscar todas los usuarios en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", UsuarioEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algunan usuario con el id que se envía como argumento
     * @param id: id correspondiente a la direccion buscadal usuario buscado
     * @return el usuario buscado
     */
    public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando usuario con id={0}", id);
        return em.find(UsuarioEntity.class, id);
    }
    
    //Dar las direcciones de un usuario
    public UsuarioEntity findAddress(Long id) {
      //Dar las direcciones de un usuario
      //Dar las direcciones de un usuario
      //Dar las direcciones de un usuario
        return null;
    }
    
    /**
     * Persiste un nuevo usuario
     * @param entity: Objeto usuario que se creará en la base de datos
     * @return devuelve la entidad creada con un id en la base de datos
     */
    public UsuarioEntity create(UsuarioEntity entity) {
      
        LOGGER.info("Crendo un usuario nuev");
        em.persist(entity);
        LOGGER.info("Se ha creado un usuario nuevo");
        return entity;
    }
    
    //Crear una direccion de un usuario
    public UsuarioEntity createAddress(UsuarioEntity entity) {
        //Crear una direccion de un usuario
        //Crear una direccion de un usuario
        //Crear una direccion de un usuario
        return null;
    }
    
    /**
     * Actualiza un usuario
     * @param entity: El usuario que viene con los nuevos datos
     * @return Un usuario que tiene los nuevo datos
     */
    public UsuarioEntity update(UsuarioEntity entity) {
       LOGGER.log(Level.INFO, "Actualizando usuario con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    //Actualizar la informacion de la direccion un usuario
    public UsuarioEntity updateAddress(UsuarioEntity entity) {
       //Actualiza la informacion de la direccion un usuario
       //Actualiza la informacion de la direccion un usuario
       //Actualiza la informacion de la direccion un usuario
        return null;
    }
    
    /**
     * Elimina un usuario
     * @param id: El id del usuario que se busca eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando usuario con id={0}", id);
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }
    
    //Eliminar una direccion de un usuario
    public void deleteAddress(Long id) {
        
        //Eliminar una direccion de un usuario
        //Eliminar una direccion de un usuario
        //Eliminar una direccion de un usuario
       
    }
}

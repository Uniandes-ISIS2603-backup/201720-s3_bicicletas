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
import javax.persistence.TypedQuery;

/**
 * Clase que se encarga de persistir los objetos de tipo UsuarioEntiy
 * @author cm.alba10
 */
@Stateless
public class UsuarioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    
    /**
     *
     */
    @PersistenceContext(unitName="bicicletasPU")
    protected EntityManager em;
    
   /**
     * Devuelve todas los usuarios de la base de datos
     * @return Una lista con todas los usuarios que se encuentran en el sistema
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.info("Consultando todas los usuarios");
       
        // Se crea un query para buscar todas los usuarios en la base de datos.
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algunan usuario con el id que se envía como argumento
     * @param id: id correspondiente al id del usuario buscado
     * @return el usuario buscado
     */
    public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando usuario con id={0}", id);
        return em.find(UsuarioEntity.class, id);
    }
    
    /**
     * Persiste un nuevo usuario
     * @param entity: Objeto usuario que se creará en la base de datos
     * @return devuelve la entidad creada con un id en la base de datos
     */
    public UsuarioEntity create(UsuarioEntity entity) {
      
        LOGGER.info("Crendo un usuario nuevo");
        em.persist(entity);
        LOGGER.info("Se ha creado un usuario nuevo");
        return entity;
    }
    
    /**
     * Actualiza un usuario
     * @param entity: El usuario que viene con los nuevos datos
     * @return Un usuario que tiene los nuevo datos
     */
    public UsuarioEntity update(UsuarioEntity entity) {
       LOGGER.log(Level.INFO, "Actualizando usuario con id={0}", entity.getDocumentoUsuario());
        return em.merge(entity);
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
    
    /**
     * Busca si hay algunan usuario con el nombre que se envía de argumento
     *
     * @param name: Nombre del usuario que se está buscando
     * @return null si no existe ningun usuario con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
     public UsuarioEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando usuario por nombre ", name);

        // Se crea un query para buscar usuarios con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.nombre = :name", UsuarioEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameName = query.getResultList();
        UsuarioEntity result; 
        if (sameName == null ) {
            result = null;
        } else if (sameName.isEmpty()) {
             result = null;
        } else {
            result =  sameName.get(0);
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package co.edu.uniandes.bicicletas.persistence;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que se encarga de persistir los objetos de tipo DireccionEntity
 * @author cm.alba10
 */
@Stateless
public class DireccionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(DireccionPersistence.class.getName());
    
    /**
     *
     */
    @PersistenceContext(unitName="bicicletasPU")
    protected EntityManager em;
    
     /**
     * Devuelve todas las direcciones de la base de datos
     * @return Una lista con todas las direcciones que se encuentran en el sistema
     */
    public List<DireccionEntity> findAll() {
       LOGGER.info("Consultando todas las Direcciones");
        
        // Se crea un query para buscar todas las direcciones en la base de datos.
        TypedQuery query = em.createQuery("select u from DireccionEntity u", DireccionEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna direccion con el id que se envía como argumento
     * @param id: id correspondiente a la direccion buscada
     * @return la direccion buscada
     */
    public DireccionEntity find(Long id) {
        
        LOGGER.log(Level.INFO, "Consultando Direccion con id={0}", id);
        return em.find(DireccionEntity.class, id);
    }

    /**
     * Persiste una nueva direccion
     * @param entity: Objeto direccion que se creará en la base de datos
     * @return devuelve la entidad creada con un id en la base de datos
     */
    public DireccionEntity create(DireccionEntity entity) {
        LOGGER.info("Crendo una direccion nueva");
        em.persist(entity);
        LOGGER.info("Se ha creado una direccion nueva");
        return entity;
    }
    
    /**
     * Actualiza una direccion
     * @param entity: La direccion que viene con los nuevos datos
     * @return Una direccion que tiene los nuevo datos
     */
    public DireccionEntity update(DireccionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando direccion con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Elimina una direccion
     * @param id: El id de la direccion que se busca eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando direccion con id={0}", id);
        DireccionEntity entity = em.find(DireccionEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Busca si hay alguna Direccion con el nombre que se envía de argumento
     *
     * @param name: Nombre de la Direccion que se está buscando
     * @return null si no existe ninguna Direccion con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
     public DireccionEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando direccion por nombre ", name);

        // Se crea un query para buscar direcciones con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From DireccionEntity e where e.name = :name", DireccionEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<DireccionEntity> sameName = query.getResultList();
        DireccionEntity result = null; 
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

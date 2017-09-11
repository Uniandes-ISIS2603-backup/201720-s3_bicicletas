/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ka.babativa
 */
@Stateless
public class EstacionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EstacionPersistence.class.getName());
    
    @PersistenceContext(unitName="bicicletasPU")
    protected EntityManager em;
    
     /**
     * Devuelve todas las estaciones
     * @return Una lista con todas las estaciones que se encuentran en el sistema
     */
    public List<EstacionEntity> findAll() {
       LOGGER.info("Consultando todas las Estaciones");
        // Query para buscar la estacion
        TypedQuery query = em.createQuery("select u from EstacionEntity u", EstacionEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca una estacion con un id por parametro
     * @param id: id a buscar
     * @return Una estacion con el id de parametro
     */
    public EstacionEntity find(Long id) {
        
        LOGGER.log(Level.INFO, "Consultando Estacion con id={0}", id);
        return em.find(EstacionEntity.class, id);
    }

    /**
     * Crear una nueva estacion
     * @param entity: Objeto de tipo estacion a persistir
     * @return La estacion creada
     */
    public EstacionEntity create(EstacionEntity entity) {
        LOGGER.info("Creando una estacion nueva");
        em.persist(entity);
        LOGGER.info("Se creo satisfactoriamente una estacion nueva");
        return entity;
    }
    
    /**
     * Actualiza una estacion
     * @param entity: Una entidad con los datos a actualizar
     * @return Una entidad tipo estacion actualizada
     */
    public EstacionEntity update(EstacionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando estacion con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Elimina una estacion
     * @param id: El id de la estacion que se desea eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Se borrara la estacion con id={0}", id);
        EstacionEntity entity = em.find(EstacionEntity.class, id);
        em.remove(entity);
    }
}

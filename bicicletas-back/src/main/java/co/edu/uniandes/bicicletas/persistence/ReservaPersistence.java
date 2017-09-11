/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ds.chacon
 */
@Stateless
public class ReservaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(AccesorioPersistence.class.getName());
    
    @PersistenceContext(unitName="bicicletasPU")
    protected EntityManager em;
    
     /**
     * Devuelve todos los accesorios
     * @return Una lista con todas los accesorios que se encuentran en el sistema
     */
    public List<ReservaEntity> findAll() {
       LOGGER.info("Consultando todos las Reservas");
        // Query para buscar el accesorio
        TypedQuery query = em.createQuery("select u from ReservaEntity u", ReservaEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca un accesorio con un id por parametro
     * @param id: id a buscar
     * @return Un accesorio con el id de parametro
     */
    public ReservaEntity find(Long id) {
        
        LOGGER.log(Level.INFO, "Consultando una Reserva con id={0}", id);
        return em.find(ReservaEntity.class, id);
    }

    /**
     * Crear un nuevo accesorio
     * @param entity: Objeto de tipo accesorio a persistir
     * @return El accesorio creado
     */
    public ReservaEntity create(ReservaEntity entity) {
        LOGGER.info("Creando una Reserva nueva");
        em.persist(entity);
        LOGGER.info("Se creo satisfactoriamente una Reserva nueva");
        return entity; 
    }
    
    /**
     * Actualiza un accesorio
     * @param entity: Una entidad con los datos a actualizar
     * @return Una entidad tipo accesorio actualizada
     */
    public ReservaEntity update(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Reserva con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Elimina un accesorio
     * @param id: El id del accesorio que se desea eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Se borrara la reserva con id={0}", id);
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        em.remove(entity);
    }
}

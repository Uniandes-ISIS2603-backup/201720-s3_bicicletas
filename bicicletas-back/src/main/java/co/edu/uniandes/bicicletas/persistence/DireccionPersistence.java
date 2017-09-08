/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author cm.alba10
 */
@Stateless
public class DireccionPersistence {
    
    @PersistenceContext(unitName="direccionPU")
    protected EntityManager em;
    
    //Dar todas las direcciones
    public List<DireccionEntity> findAll() {
       
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
    
    //Dar una direccion segun su id
    public DireccionEntity find(Long id) {
      
        return em.find(DireccionEntity.class, id);
    }

     //Crear una direccion
    public DireccionEntity create(DireccionEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }
    
    //Actualizar la informacion de una direccion
    public DireccionEntity update(DireccionEntity entity) {
       
        return em.merge(entity);
    }
    
     //Eliminar una direccion
    public void delete(Long id) {
        
        DireccionEntity entity = em.find(DireccionEntity.class, id);
        em.remove(entity);
    }
}

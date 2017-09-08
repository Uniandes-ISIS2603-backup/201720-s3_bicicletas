/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
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
public class UsuarioPersistence {
    
    @PersistenceContext(unitName="usuarioPU")
    protected EntityManager em;
    
    //Dar todos los usuarios
    public List<UsuarioEntity> findAll() {
       
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }

    //Dar un usuario segun su id
    public UsuarioEntity find(Long id) {
      
        return em.find(UsuarioEntity.class, id);
    }
    
    //Dar las direcciones de un usuario
    public UsuarioEntity findAddress(Long id) {
      //Dar las direcciones de un usuario
      //Dar las direcciones de un usuario
      //Dar las direcciones de un usuario
        return null;
    }
    
    //Crear un usuario
    public UsuarioEntity create(UsuarioEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }
    
    //Crear una direccion de un usuario
    public UsuarioEntity createAddress(UsuarioEntity entity) {
        //Crear una direccion de un usuario
        //Crear una direccion de un usuario
        //Crear una direccion de un usuario
        return null;
    }
    
    //Actualizar la informacion de un usuario
    public UsuarioEntity update(UsuarioEntity entity) {
       
        return em.merge(entity);
    }
    
    //Actualizar la informacion de la direccion un usuario
    public UsuarioEntity updateAddress(UsuarioEntity entity) {
       //Actualiza la informacion de la direccion un usuario
       //Actualiza la informacion de la direccion un usuario
       //Actualiza la informacion de la direccion un usuario
        return null;
    }
    
    //Eliminar un usuario
    public void delete(Long id) {
        
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

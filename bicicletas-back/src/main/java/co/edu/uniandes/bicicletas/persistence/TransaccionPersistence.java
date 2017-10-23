/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.TransaccionEntity;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;


/**
 * Clase que funciona como el manejador de persistencia de la clase
 * TransaccionPersistence
 * @author jd.trujillom
 */

@Stateless
public class TransaccionPersistence {
    
    private final static Logger LOGGER = Logger.getLogger(TransaccionPersistence.class.getName());
    
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;
    
    /**
     * Persiste una transaccion en la base de datos.
     * @param entity que se quiere persister.
     * @return la trasanccion persistida en la base de datos. 
     */
    public TransaccionEntity createTransaccion(TransaccionEntity entity){
        LOGGER.info("Creando una nueva transacción");
        
        em.persist(entity);
        
        LOGGER.info("Se ha creado una nueva transaccion");
        
        return entity;
    }
    
    /**
     * Busca una transaccion por el id pasado por parametro
     * @param idTransaccion de la trasanccion que se está buscadno
     * @return la transaccion en caso de que la encuentre, null de lo contrario.
     */
    public TransaccionEntity findTransaccion(Long idTransaccion){
       LOGGER.log(Level.INFO, "Consultando transaccion con id={0}", idTransaccion);
        
        return em.find(TransaccionEntity.class, idTransaccion);
    }
    
    /**
     * Actualiza una transacción.
     * @param entity que se quiere actualizar
     * @return transaccion actualizada. 
     */
    public TransaccionEntity updateTransaccion(TransaccionEntity entity){
        LOGGER.log(Level.INFO, "Se está actualizando la transacción con id={0}", entity.getId());
        
        return em.merge(entity);
    }
    
}
    

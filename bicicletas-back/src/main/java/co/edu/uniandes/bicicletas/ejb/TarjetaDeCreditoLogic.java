/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.bicicletas.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.TarjetaDeCreditoPersistence;
import co.edu.uniandes.bicicletas.persistence.UsuarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.trujillom
 */
@Stateless
public class TarjetaDeCreditoLogic {
    
     /**
      * 
      */
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());
    
    /**
     * 
     */
    @Inject
    TarjetaDeCreditoPersistence persistence;
    
    /**
     * 
     */
    @Inject
    UsuarioPersistence usuarioPersistence;
    
     /**
     * Persiste la tarjeta que revise por parametro en la base de datoss
     * @param entity que se quiere persistir.
     * @return la tarjeta ya persistido en la base de datos. 
     */
    public TarjetaDeCreditoEntity crearTarjeta (TarjetaDeCreditoEntity entity) {

        LOGGER.info("Inicia proceso de creación de tarjeta");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        // Invoca la persistencia para crear la editorial
        persistence.createTarjeta(entity);
        LOGGER.info("Termina proceso de creación de tarjeta");
        return entity;
    }
    
        /**
     * Persiste la tarjeta que revise por parametro en la base de datoss
     * @param entity que se quiere persistir.
     * @return la tarjeta ya persistido en la base de datos. 
     */
    public TarjetaDeCreditoEntity crearTarjeta (TarjetaDeCreditoEntity entity, Long idUsuario) {

        LOGGER.info("Inicia proceso de creación de tarjeta");
 
        
        UsuarioEntity usuario = usuarioPersistence.find(idUsuario);
        entity.setUsuarioTarjeta(usuario);
        persistence.createTarjeta(entity);
        LOGGER.info("Termina proceso de creación de tarjeta");
        return entity;
    }
    
    
    /**
     * Retorna el pago con el id dato por parametro
     * @param idtarjeta del pago que se quiere recuperar
     * @return el pago buscado, null de lo contrario
     */
    public TarjetaDeCreditoEntity find(Long idtarjeta) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar tarjeta con idPago={0}", idtarjeta);
        TarjetaDeCreditoEntity tarjeta = persistence.find(idtarjeta);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La tarjeta con el id {0} no existe", idtarjeta);
        }

        return tarjeta;
    }
    
    
    /**
     * Actualiza el pago que es pasado por parametro
     * @param el pago que se quiere actualizar
     * @return el pago actualiazado. 
     */
    public TarjetaDeCreditoEntity updatePago(TarjetaDeCreditoEntity tarjeta) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar tarjeta con id={0}", tarjeta.getId());
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        TarjetaDeCreditoEntity newEntity = persistence.updateTarjeta(tarjeta);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar tarjeta con id={0}", tarjeta.getId());
        return newEntity;
    }
    
      /**
     * Elimina una tarjeta dado su id.
     * @param id de la  que se quiere eliminar.
     */
    public void deletePago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar tarjeta con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.deletePago(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar tarjeta con id={0}", id);
    }

    
   
    
    
    
    
}

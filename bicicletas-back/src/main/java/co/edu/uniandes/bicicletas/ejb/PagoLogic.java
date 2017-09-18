/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.persistence.PagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.trujillom
 */
@Stateless
public class PagoLogic {

    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    PagoPersistence persistence;

    public PagoEntity crearPago(PagoEntity entity) {

        LOGGER.info("Inicia proceso de creación de editorial");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        // Invoca la persistencia para crear la editorial
        persistence.createPago(entity);
        LOGGER.info("Termina proceso de creación de editorial");
        return entity;
    }

    public PagoEntity find(Long idPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con idPago={0}", idPago);
        PagoEntity estacion = persistence.find(idPago);
        if (estacion == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", idPago);
        }

        return estacion;
    }

    public PagoEntity updateUsuario(PagoEntity pago) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", pago.getId());
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PagoEntity newEntity = persistence.updatePago(pago);
        LOGGER.log(Level.INFO, "Termina proceso de actualizarpago con id={0}", pago.getId());
        return newEntity;
    }

    public void deleteUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delatePago(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id={0}", id);
    }
    
    public List<PagoEntity> findAll(){
        return persistence.findAll();
    }

}

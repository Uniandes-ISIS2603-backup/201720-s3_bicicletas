/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.TarjetaDeCreditoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jd.trujillom
 */
@Stateless
public class TarjetaDeCreditoPersistence {
     /**
     * El logger de la clase TarjetDeCreditoPersistence.
     */
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoPersistence.class.getName());

    
    /**
     * El contexto de persistencia.
     */
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;
    
     /**
     * Persiste un pago en la base de datos
     * @param tarjeta (como objeto) el cual se quiere persistir
     * @return 
     */
    public TarjetaDeCreditoEntity createTarjeta(TarjetaDeCreditoEntity tarjeta) {
        LOGGER.info("Creando un nueva tarjeta de credito");
        em.persist(tarjeta);
        
        LOGGER.info("Se ha creado una tarjeta nueva");
        return tarjeta;
    }
    
     /**
     * Actualiza una tarjeta
     * @param tarjeta: El pago que viene con los cambios
     * @return  el pago con los cambios aplicados. 
     */
    public TarjetaDeCreditoEntity updateTarjeta(TarjetaDeCreditoEntity tarjeta){
        LOGGER.log(Level.INFO, "Actualizando tarjeta con id={0}", tarjeta.getId());
        
        return em.merge(tarjeta);
    }
    
       /**
     * Borra un pago de la base de datos recibiendo como parametro el id del 
     * pago.
     * @param idTarjeta : id correspondiente al Pago a borrar
     */
    public void deletePago(Long idTarjeta){
        LOGGER.log(Level.INFO, "Borrando tarjeta con id={0}", idTarjeta);
        
        TarjetaDeCreditoEntity tarjeta = find(idTarjeta);
        
        em.remove(tarjeta);
        
    
    }
    
     /**
     * Busca un pago recibiendo comi parametro el id.
     * @param idTarjeta: id del Pago que se quiere obtener
     * @return un Pago. 
     */
    public TarjetaDeCreditoEntity find(Long idTarjeta){
         LOGGER.log(Level.INFO, "Consultando tarjeta con id={0}", idTarjeta);
         return em.find(TarjetaDeCreditoEntity.class, idTarjeta);
    }
    
    
    
    
    
}

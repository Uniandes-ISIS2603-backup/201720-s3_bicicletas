/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.PagoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jd.trujillom
 */
public class PagoPersistence {

    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    /**
     * Persistence un pago en la base de datos
     * @param Pago (como objeto) el cual se quiere persistir
     * @return 
     */
    public PagoEntity createPago(PagoEntity pago) {
        LOGGER.info("Creando un nuevo pago");
        em.persist(pago);
        
        LOGGER.info("Se ha creado un pago nuevo");
        return pago;
    }
    
    /**
     * Actualiza un pago
     * @param pago: El pago que viene con los cambios
     * @return  el pago con los cambios aplicados. 
     */
    public PagoEntity updatePago(PagoEntity pago){
        LOGGER.log(Level.INFO, "Actualizando Pago con id={0}", pago.getId());
        
        return em.merge(pago);
    }
    
    /**
     * Borra un pago de la base de datos recibiendo como parametro el id del 
     * pago.
     * @param idPago : id correspondiente al Pago a borrar
     */
    public void delatePago(Long idPago){
        LOGGER.log(Level.INFO, "Borrando Pago con id={0}", idPago);
        
        PagoEntity pago = find(idPago);
        
        em.remove(pago);
        
    }
    
    /**
     * Busca un pago recibiendo comi parametro el id.
     * @param idPago: id del Pago que se quiere obtener
     * @return un Pago. 
     */
    public PagoEntity find(Long idPago){
         LOGGER.log(Level.INFO, "Consultando Pago con id={0}", idPago);
         return em.find(PagoEntity.class, idPago);
    }
    
}


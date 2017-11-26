/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.AccesorioBicicletaEntity;
import co.edu.uniandes.bicicletas.persistence.AccesorioBicicletaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author js.torres1
 */
@Stateless
public class AccesorioBicicletaLogic {
    private static final Logger LOGGER = Logger.getLogger(AccesorioBicicletaLogic.class.getName());

    @Inject
    private AccesorioBicicletaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
   
    /**
     * Crea un accesorio Bicicleta
     * @param entity
     * @return el accesorioBicicleta que fue creado.
     * @throws BusinessLogicException
     */
    public AccesorioBicicletaEntity createAccesorioBici(AccesorioBicicletaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Bicicleta");
        // Invoca la persistencia para crear la BicicletaLogic
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Bicicleta");
        return entity;
    }

    /**
     * 
     * Obtener todos los accesorios Bicicleta existentes en la base de datos.
     *
     * @return una lista de Bicicletaes.
     */
    public List<AccesorioBicicletaEntity> getAccesorioBici() {
        LOGGER.info("Inicia proceso de consultar todas las Bicicletaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<AccesorioBicicletaEntity> acc = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Bicicletaes");
        return acc;
    }
    /**
     * 
     * Obtener un accesorioBicicleta especifico.
     * @param id Long el id del accesorio Bicicleta
     * @return el accesorio Bicicleta de dicho id
     */
    public AccesorioBicicletaEntity getAccesorioBici(Long id){
        LOGGER.info("Inicica proceso de consulta a una bicicleta");
        AccesorioBicicletaEntity acc = persistence.find(id);
        LOGGER.info("Termina el proceso de consulta a una bicicleta");
        return acc;
    }
    /**
     * Borrar un accesorioBicicleta
     * @param id Long el id de la bicicleta que se necesita
     * @throws WebApplicationException 
     */
    
    public void deleteAccesorioBicicleta(Long id)
    {
         AccesorioBicicletaEntity bicicleta = persistence.find(id);
         if(bicicleta == null){
             throw new WebApplicationException("No hay una estación con dicho ID", 402);
         }
         persistence.delete(id);
    }
    /**
     * Actualiza un accesorioBicicleta
     * @param entidad
     * @return el accesorioBicicleta que se debe actualizar.
     * @throws WebApplicationException 
     */
    public AccesorioBicicletaEntity actualizarAccesorioBici(AccesorioBicicletaEntity entidad)
    {
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return persistence.update(entidad);
    }
}

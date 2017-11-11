package co.edu.uniandes.bicicletas.ejb;
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


import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.AccesorioBicicletaEntity;
import co.edu.uniandes.bicicletas.persistence.BicicletaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class BicicletaLogic {

    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());
    
    /**
     * Constante para indicar que no hay una estación con dicho id
     */
    private static final String NO_ESTACION_ID = "No hay una estación con dicho id";

    @Inject
    private BicicletaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    @Inject
    private AccesorioBicicletaLogic AccesorioBicicleta;
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public BicicletaEntity createBicicleta(BicicletaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Bicicleta");
        // Invoca la persistencia para crear la BicicletaLogic
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Bicicleta");
        return entity;
    }

    /**
     * 
     * Obtener todas las Bicicletaes existentes en la base de datos.
     *
     * @return una lista de Bicicletaes.
     */
    public List<BicicletaEntity> getBicicletas() {
        LOGGER.info("Inicia proceso de consultar todas las Bicicletaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<BicicletaEntity> Bicicletas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Bicicletaes");
        return Bicicletas;
    }
    public BicicletaEntity actualizarBicicleta(BicicletaEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    /**
     * 
     * Obtener una bicicleta Especifica
     */
    public BicicletaEntity getBIcicleta(Long id){
        LOGGER.info("Inicica proceso de consulta a una bicicleta");
        BicicletaEntity bicicleta = persistence.find(id);
        LOGGER.info("Termina el proceso de consulta a una bicicleta");
        return bicicleta;
    }
    public void deleteBicicleta(Long id) throws WebApplicationException
    {
         BicicletaEntity bicicleta = persistence.find(id);
         if(bicicleta == null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
         persistence.delete(id);
    }
    public AccesorioBicicletaEntity createAccesorioBici(Long idBici,AccesorioBicicletaEntity entity) throws BusinessLogicException {
        LOGGER.info(entity.darNombre()+""+entity.darDescrip());
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
        List<AccesorioBicicletaEntity> rta = bici.getAccesorioBicicletas();
        rta.add(entity);
        return AccesorioBicicleta.createAccesorioBici(entity);
    }

    /**
     * 
     * Obtener todas las Bicicletaes existentes en la base de datos.
     *
     * @return una lista de Bicicletaes.
     */
    public List<AccesorioBicicletaEntity> getAccesoriosBici(Long idBici) {
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
        return bici.getAccesorioBicicletas();
    }
    /**
     * 
     * Obtener una bicicleta Especifica
     */
    public AccesorioBicicletaEntity getAccesorioBici(Long idBici, Long idAcc){
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
        return AccesorioBicicleta.getAccesorioBici(idAcc);
    }
    
    public void deleteAccesorioBicicleta(Long idBici,Long id) throws WebApplicationException
    {
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
        bici.getAccesorioBicicletas().remove(AccesorioBicicleta.getAccesorioBici(id));
         AccesorioBicicleta.deleteAccesorioBicicleta(id);
    }
    public AccesorioBicicletaEntity updateAcc(Long idBici,AccesorioBicicletaEntity entity){
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
        }
        return AccesorioBicicleta.actualizarAccesorioBici(entity);
    }
    
    
    
    


}

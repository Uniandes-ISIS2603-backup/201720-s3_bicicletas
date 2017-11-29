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
    private AccesorioBicicletaLogic accesorioBicicleta;
    /**
     * Este metodo se usa para crear una bicicleta
     * @param entity
     * @return BicicletaEntity
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
     * @return una lista de Bicicletaes.
     */
    public List<BicicletaEntity> getBicicletas() {
        LOGGER.info("Inicia proceso de consultar todas las Bicicletaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<BicicletaEntity> bicicletas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Bicicletaes");
        return bicicletas;
    }
    /**
     * 
     * Obtener todas las Bicicletaes existentes en la base de datos.
     * @param entidad la informacion de la bicicleta que se desea actualizar
     * @return una bicicleta ya actualizada.
     */
    public BicicletaEntity actualizarBicicleta(BicicletaEntity entidad) 
    {
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    /**
     * 
     * Obtener una bicicleta Especifica
     * @param id Long del id de la bicicleta
     * @return la bicicleta que se obtiene de dicho id
     */
    public BicicletaEntity getBIcicleta(Long id){
        LOGGER.info("Inicica proceso de consulta a una bicicleta");
        BicicletaEntity bicicleta = persistence.find(id);
        if(bicicleta==null){
            throw new WebApplicationException("No existe la bicicleta con id "+id);
        }
        LOGGER.info("Termina el proceso de consulta a una bicicleta");
        return bicicleta;
    }
    /**
     * 
     * Borrar una bicicleta Especifica
     * @param id Long del id de la bicicleta
     */
    public void deleteBicicleta(Long id) 
    {
         BicicletaEntity bicicleta = persistence.find(id);
         if(bicicleta == null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
         persistence.delete(id);
    }
    /**
     * 
     * Crear un AccesiorioBicicleta especifico
     * @param idBici Long del id de la bicicleta
     * @param entity con la informacion del accesorio Bicicleta que se va a crear
     * @return el nuevo accesorioBicicleta
     * @throws co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException
     */
    public AccesorioBicicletaEntity createAccesorioBici(Long idBici,AccesorioBicicletaEntity entity) throws BusinessLogicException{
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
        List<AccesorioBicicletaEntity> rta = bici.getAccesorioBicicletas();
        rta.add(entity);
        return accesorioBicicleta.createAccesorioBici(entity);
    }

    /**
     * 
     * Obtener todas los accesoriosBicicleta existentes en la base de datos.
     *
     * @param idBici
     * @return una lista de accesoriosBicicleta.
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
     * Obtener un accesorioBicicleta especifico
     * @param idBici Long id de la bicicleta
     * @param idAcc Long id del AccesorioBicicleta
     * @return debuelve un accesorio bicicleta Buscado
     */
    public AccesorioBicicletaEntity getAccesorioBici(Long idBici, Long idAcc){
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
        return accesorioBicicleta.getAccesorioBici(idAcc);
    }
    /**
     * 
     * Borrar un accesorioBicicleta especifico
     * @param idBici Long del id de la bicicleta
     * @param id Long del id de la bicicleta
     */
    public void deleteAccesorioBicicleta(Long idBici,Long id)
    {
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
         }
        bici.getAccesorioBicicletas().remove(accesorioBicicleta.getAccesorioBici(id));
         accesorioBicicleta.deleteAccesorioBicicleta(id);
    }
    /**
     * 
     * Borrar un accesorioBicicleta especifico
     * @param idBici Long del id de la bicicleta
     * @param entity Long del id de la bicicleta
     * @return el accesorio bicicleta que fue actualizado
     */
    public AccesorioBicicletaEntity updateAcc(Long idBici,AccesorioBicicletaEntity entity){
        BicicletaEntity bici = persistence.find(idBici);
        if(bici== null){
             throw new WebApplicationException(NO_ESTACION_ID, 402);
        }
        return accesorioBicicleta.actualizarAccesorioBici(entity);
    }
    
    
    
    


}

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


import co.edu.uniandes.bicicletas.entities.PuntoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que se encarga de persistir los objetos de tipo PuntoEntity
 * @author gl.pinto10
 */
@Stateless 
public class PuntoPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(PuntoPersistence.class.getName());
    
    @PersistenceContext(unitName="bicicletasPU")
    protected EntityManager em;
    
    /**
     * Persiste un nuevo punto
     * @param entity: Objeto Punto que se creará en la base de datos
     * @return devuelve la entidad creada con un id en la base de datos
     */
    public PuntoEntity create (PuntoEntity entity)
    {
        LOGGER.info("Creando un nuevo Punto");
        em.persist(entity);
        LOGGER.info("Se ha creado un Punto nuevo");
        return entity;            
    }
    
    /**
     * Busca si hay alguna Punto con el id que se envía como argumento
     * @param id: id correspondiente a la Punto buscado
     * @return el Punto buscado
     */
    public PuntoEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Punto con id={0}", id);
        return em.find(PuntoEntity.class, id);
    }
    
    /**
     * Devuelve todos los Puntos de la base de datos
     * @return Una lista con todas las calificaciones que se encuentran en el sistema
     */
    public List<PuntoEntity> findAll()
    {
         LOGGER.info("Consultando todas los Puntos");
         
        // Se crea un query para buscar todas las calificaciones en la base de datos.
        TypedQuery query = em.createQuery("select u from PuntoEntity u", PuntoEntity.class);
        return query.getResultList();   
    }  
    
    /**
     * Elimina un punto
     * @param id: El id del punto que se desea eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Se borrara el punto con id={0}", id);
        PuntoEntity entity = em.find(PuntoEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Actualiza un Punto
     * @param entity: El punto que viene con los nuevos datos
     * @return Un Punto que tiene los nuevo datos
     */
    public PuntoEntity update (PuntoEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando Punto con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    
    
}

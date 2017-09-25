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

import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 * Clase que se encarga de persistir los objetos de tipo CalificacionEntity
 * @author gl.pinto10
 */
@Stateless
public class CalificacionPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
    
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;
    
    /**
     * Persiste una nueva calificación
     * @param entity: Objeto Calificacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id en la base de datos
     */
    public CalificacionEntity create(CalificacionEntity entity)
    {
        LOGGER.info("Crendo una Calificacion nueva");
        em.persist(entity);
        LOGGER.info("Se ha creado una Calificacion nueva");
        return entity;
    }
    
    /**
     * Actualiza una Calificacion
     * @param entity: La Clasificacion que viene con los nuevos datos
     * @return Una Calificacion que tiene los nuevo datos
     */
    public CalificacionEntity update (CalificacionEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando Calificacion con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Busca si hay alguna Calificacion con el id que se envía como argumento
     * @param id: id correspondiente a la Calificacion buscada
     * @return la Calificacion buscada
     */
    public CalificacionEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Calificacion con id={0}", id);
        return em.find(CalificacionEntity.class, id);
    }
    
    /**
     * Devuelve todas las calificaciones de la base de datos
     * @return Una lista con todas las calificaciones que se encuentran en el sistema
     */
    public List<CalificacionEntity> findAll()
    {
         LOGGER.info("Consultando todas las Calificaciones");
         
        // Se crea un query para buscar todas las calificaciones en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        
        return query.getResultList();
    }  
    
}

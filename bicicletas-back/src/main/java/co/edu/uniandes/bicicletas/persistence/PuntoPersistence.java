/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    
}

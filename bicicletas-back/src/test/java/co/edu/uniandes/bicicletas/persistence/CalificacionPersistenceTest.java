 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author gl.pinto10
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Calificacion, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     /** Inyección de la dependencia a la clase CalificacionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private CalificacionPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     *
     */
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    public CalificacionPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
     /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Borra los datos en la base de datos directamente utilizando el EntityManager y la UserTransaction
     */
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }

    /**
     * Crea nuevos datos utilizando Podam quien crea datos aleatoriamente
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
           CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);

           em.persist(entity);
           data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class CalificacionPersistence.
     */
    @Test
    public void testCreate() throws Exception 
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setReserva(null);
        newEntity.setEstacion(null);
        CalificacionEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getNota(), entity.getNota());
        Assert.assertEquals(newEntity.getFechaCali(), entity.getFechaCali());
        Assert.assertNull(entity.getReserva());
        Assert.assertNull(entity.getEstacion());
        Assert.assertTrue(newEntity.equals(entity));
        newEntity.setNota(null);
        Assert.assertFalse(newEntity.equals(entity));
    }

    @Test
    public void testEquals() throws Exception 
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity nuevaCalificacion = factory.manufacturePojo(CalificacionEntity.class);
        nuevaCalificacion.setEstacion(null);
        nuevaCalificacion.setReserva(null);
        
        CalificacionEntity calificacion = null;
        String caliSt = "String de calificacion";
        
        Assert.assertFalse(nuevaCalificacion.equals(calificacion));
        Assert.assertFalse(nuevaCalificacion.equals(caliSt));    
    }
    
    /**
     * Test of update method, of class CalificacionPersistence.
     */
    @Test
    public void testUpdate() throws Exception 
    {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of find method, of class CalificacionPersistence.
     */
    @Test
    public void testFind() throws Exception 
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAll method, of class CalificacionPersistence.
     */
    @Test
    public void testFindAll() throws Exception 
    {
        List<CalificacionEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity ent : list) {
            boolean found = false;
            for (CalificacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
     }
    
    /**
     * Test of delete method, of class CalificacionPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        CalificacionEntity entidad = data.get(0);
        persistence.delete(entidad.getId());
        CalificacionEntity eliminada = em.find(CalificacionEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
    
    
}

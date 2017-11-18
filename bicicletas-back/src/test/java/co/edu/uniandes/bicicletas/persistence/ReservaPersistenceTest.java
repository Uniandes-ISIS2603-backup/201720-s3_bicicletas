/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ds.chacon
 */

@RunWith(Arquillian.class)
public class ReservaPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Usuario, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ReservaPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     *
     */
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
  
    /**
     *
     */
    public ReservaPersistenceTest(){}
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
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
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll() throws Exception {
        List<ReservaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity ent : list) {
        boolean found = false;
        for (ReservaEntity entity : data) {
            if (entity.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }

    /**
     * Test of find method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testFind() throws Exception {
        ReservaEntity entity = data.get(0);
        ReservaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of create method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescuento(), entity.getDescuento());
        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
        Assert.assertEquals(newEntity.getFechaEntrega(), entity.getFechaEntrega());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getFechaFinal(), entity.getFechaFinal());
        Assert.assertEquals(newEntity.getFechaReserva(), entity.getFechaReserva());
        Assert.assertEquals(newEntity.getPrecioFinal(), entity.getPrecioFinal(), 0);
        
        Assert.assertNull(newEntity.getCalificaciones());
        
        newEntity.setUsuarioReserva(null);
        newEntity.setEstacionSalida(null);
        newEntity.setEstacionLlegada(null);
        newEntity.setPago(null);
        newEntity.setTransaccion(null);
        newEntity.setAccesorios(null);
        
        Assert.assertNull(newEntity.getBicicletas());
        Assert.assertNull(newEntity.getUsuarioReserva());
        Assert.assertNull(newEntity.getEstacionSalida());
        Assert.assertNull(newEntity.getEstacionLlegada());
        Assert.assertNull(newEntity.getPago());
        Assert.assertNull(newEntity.getTransaccion());
        Assert.assertNull(newEntity.getAccesorios());
        
        
        
        
    }
    
    /**
     * Test of update method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdate() throws Exception {
        ReservaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }


    /**
     * Test of delete method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testDelete() throws Exception {
        ReservaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

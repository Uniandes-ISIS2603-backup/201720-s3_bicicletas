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
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ds.chacon
 */
@RunWith(Arquillian.class)
public class ReservaPersistenceTest {
    
    
     @Inject
    private ReservaPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
            UserTransaction utx;
    
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(EstacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public ReservaPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
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
    
        private void clearData() {
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }
    
    
    private void insertData() {
        PodamFactory factory = new uk.co.jemos.podam.api.PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojoWithFullData(ReservaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class ReservaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<ReservaEntity> Reserva = persistence.findAll();
        Assert.assertEquals(Reserva.size(), data.size());
        for (ReservaEntity ent : Reserva) {
            boolean found = false;
            for (ReservaEntity entity : data) {
                if (ent.getId()== entity.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of find method, of class ReservaPersistence.
     */
    @Test
    public void testFind() throws Exception {
        ReservaEntity  buscar = data.get(0);
        ReservaEntity  encontrada = persistence.find(buscar.getId());
        Assert.assertNotNull(encontrada);
        Assert.assertEquals(buscar.getName(), encontrada.getName());
    }

    /**
     * Test of create method, of class ReservaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        
        ReservaEntity nuevaReserva = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity resultado = persistence.create(nuevaReserva);
        
        Assert.assertNotNull(resultado);
        
        ReservaEntity creada = em.find(ReservaEntity.class, resultado.getId());
        
        Assert.assertEquals(nuevaReserva.getName(), creada.getName());
    }

    /**
     * Test of update method, of class ReservaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        ReservaEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity nuevaEntidad = factory.manufacturePojo(ReservaEntity.class);
        nuevaEntidad.setId(entidad.getId());
        persistence.update(nuevaEntidad);
        ReservaEntity resp = em.find(ReservaEntity.class, entidad.getId());
        Assert.assertEquals(nuevaEntidad.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class ReservaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        ReservaEntity entidad = data.get(0);
        persistence.delete(entidad.getId());
        ReservaEntity eliminada = em.find(ReservaEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
    
}

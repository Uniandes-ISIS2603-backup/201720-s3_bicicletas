/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ka.babativa
 */
@RunWith(Arquillian.class)
public class EstacionPersistenceTest {
    
   @Inject
    private EstacionPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<EstacionEntity> data = new ArrayList<EstacionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstacionEntity.class.getPackage())
                .addPackage(EstacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from EstacionEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EstacionEntity entity = factory.manufacturePojo(EstacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class EstacionPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        fail("testFindAll");
    }

    /**
     * Test of find method, of class EstacionPersistence.
     */
    @Test
    public void testFind() throws Exception {
        fail("testFind");
    }

    /**
     * Test of create method, of class EstacionPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        fail("testCreate");
    }

    /**
     * Test of update method, of class EstacionPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        fail("testUpdate");
    }

    /**
     * Test of delete method, of class EstacionPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        fail("testDelete");
    }
    
}

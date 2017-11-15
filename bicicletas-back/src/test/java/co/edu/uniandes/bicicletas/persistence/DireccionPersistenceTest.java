/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.DireccionEntity;
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

/*
 * @author cm.alba10
 */

/**
 *
 * @author cm.alba10
 */

@RunWith(Arquillian.class)
public class DireccionPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Direccion
     *
     *, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DireccionEntity.class.getPackage())
                .addPackage(DireccionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    /**
     * Inyección de la dependencia a la clase DireccionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private DireccionPersistence persistence;

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
    private List<DireccionEntity> data = new ArrayList<DireccionEntity>();
    
    /**
     *
     */
    public DireccionPersistenceTest(){}
    
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
        em.createQuery("delete from DireccionEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DireccionEntity entity = factory.manufacturePojo(DireccionEntity.class);

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
     * Test of findAll method, of class DireccionPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll() throws Exception {
        List<DireccionEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DireccionEntity ent : list) {
        boolean found = false;
        for (DireccionEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }

    /**
     * Test of find method, of class DireccionPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testFind() throws Exception {
        DireccionEntity entity = data.get(0);
        DireccionEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of create method, of class DireccionPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        DireccionEntity newEntity = factory.manufacturePojo(DireccionEntity.class);
        newEntity.setUsuarios(null);
        DireccionEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        DireccionEntity entity = em.find(DireccionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getCodigoPostal(), entity.getCodigoPostal());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertNotNull(newEntity.getUsuarios());
    }

    /**
     * Test of update method, of class DireccionPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdate() throws Exception {
        DireccionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DireccionEntity newEntity = factory.manufacturePojo(DireccionEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        DireccionEntity resp = em.find(DireccionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class DireccionPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testDelete() throws Exception {
         DireccionEntity entity = data.get(0);
        persistence.delete(entity.getId());
        DireccionEntity deleted = em.find(DireccionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

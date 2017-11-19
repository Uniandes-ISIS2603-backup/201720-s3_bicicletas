/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.PagoEntity;
import co.edu.uniandes.bicicletas.entities.TransaccionEntity;
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
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Clase que contiene las pruebas unitarias para transaccionPersistence
 * @author jd.trujillom
 */
@RunWith(Arquillian.class)
public class TransaccionPersistenceTest {
    
    /* Inyección de la dependencia a la clase TransaccionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private TransaccionPersistence persistence;

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
    private List<TransaccionEntity> data = new ArrayList<TransaccionEntity>();

    /*
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     private void clearData() {
        em.createQuery("delete from TransaccionEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TransaccionEntity entity = factory.manufacturePojo(TransaccionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    public TransaccionPersistenceTest() {
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
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createTransaccion method, of class TransaccionPersistence.
     */
    @Test
    public void testCreateTransaccion() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionEntity newEntity = factory.manufacturePojo(TransaccionEntity.class);
        TransaccionEntity result = persistence.createTransaccion(newEntity);

        Assert.assertNotNull(result);
        TransaccionEntity entity = em.find(TransaccionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getValor(), entity.getValor());
    }

    /**
     * Test of findTransaccion method, of class TransaccionPersistence.
     */
    @Test
    public void testFindTransaccion() throws Exception {
        TransaccionEntity transaccion = data.get(0);
        TransaccionEntity transaccionBaseDatos = persistence.findTransaccion(transaccion.getId());

        assertNotNull(transaccion);
        assertEquals(transaccion.getName(), transaccionBaseDatos.getName());
    }

    /**
     * Test of updateTransaccion method, of class TransaccionPersistence.
     */
    @Test
    public void testUpdateTransaccion() throws Exception {
        TransaccionEntity transaccion = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionEntity updated = factory.manufacturePojo(TransaccionEntity.class);

        updated.setId(transaccion.getId());
        persistence.updateTransaccion(updated);

        TransaccionEntity resp = persistence.findTransaccion(updated.getId());
        assertEquals(updated.getName(), resp.getName());
    }
    
}

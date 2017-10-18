/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
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
 * @author cm.alba10
 */

@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {
    
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
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private UsuarioPersistence persistence;

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
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();
  
    /**
     *
     */
    public UsuarioPersistenceTest(){}
    
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

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
        List<UsuarioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity ent : list) {
        boolean found = false;
        for (UsuarioEntity entity : data) {
            if (entity.getDocumentoUsuario().equals(entity.getDocumentoUsuario())) {
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
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = persistence.find(entity.getDocumentoUsuario());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Test of create method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getDocumentoUsuario());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Test of update method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdate() throws Exception {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);

        newEntity.setDocumentoUsuario(entity.getDocumentoUsuario());

        persistence.update(newEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getDocumentoUsuario());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }


    /**
     * Test of delete method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testDelete() throws Exception {
        UsuarioEntity entity = data.get(0);
        persistence.delete(entity.getDocumentoUsuario());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getDocumentoUsuario());
        Assert.assertNull(deleted);
    }
    
}

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
     * Esta es la documentación 
     * del metodo cleardata.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Borra los datos en la base de datos directamente utilizando el EntityManager y la UserTransaction
     */
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }


     /**
     * Esta es la documentación 
     * del metodo insertdata.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Inserta los datos en la base de datos directamente utilizando el EntityManager y la UserTransaction
     */
       private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     *Esta es la documentación 
     * del metodo teardown.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * teardown
     */
    @After
    public void tearDown() {
    }

    /**
     * Esta es la documentación 
     * del metodo testfindall.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
     * Esta es la documentación 
     * del metodo testfind.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
     * Esta es la documentación 
     * del metodo testcreate.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Test of create method, of class UsuarioPersistence.
     * adicionalmente este 
     * metodo tambien prueba
     * cada uno de los
     * atributos de el
     * recurso usuario
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setDirecciones(null);
        newEntity.setPuntos(null);
        newEntity.setReservas(null);
        UsuarioEntity result = persistence.create(newEntity);
        
        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getDocumentoUsuario());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getTipoId(), entity.getTipoId());
        Assert.assertNotNull(newEntity.getFechaNacimiento());
        Assert.assertNotNull(entity.getFechaNacimiento());
        Assert.assertEquals(newEntity.getContraseniaPSE(), entity.getContraseniaPSE());
        Assert.assertEquals(newEntity.getTarjetaCredito(), entity.getTarjetaCredito());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
        Assert.assertEquals(newEntity.getNumeroCsv(), entity.getNumeroCsv());
        Assert.assertNotNull(newEntity.getReservas());
        Assert.assertNotNull(entity.getDirecciones());
        Assert.assertNotNull(entity.getPuntos());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Esta es la documentación 
     * del metodo testupdate.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
     * Esta es la documentación 
     * del metodo test delete.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
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
    
    /**
     * Esta es la documentación 
     * del metodo testfindbyname.
     * Este metodo ha sido creado por
     * Carlos Alba
     * Este metodo se encarga de
     * Test of findByName method, of class UsuarioPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByName() throws Exception {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = persistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.PuntoEntity;
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
public class PuntoPersistenceTest {
    
    public PuntoPersistenceTest() {}
    
     /** Inyección de la dependencia a la clase PuntoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PuntoPersistence persistence;

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
    private List<PuntoEntity> data = new ArrayList<PuntoEntity>();
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Punto, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoEntity.class.getPackage())
                .addPackage(PuntoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
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
        em.createQuery("delete from PuntoEntity").executeUpdate();
    }

    /**
     * Crea nuevos datos utilizando Podam quien crea datos aleatoriamente
     */
    private void insertData() {
       PodamFactory factory = new PodamFactoryImpl();
       for (int i = 0; i < 3; i++) 
       {
           PuntoEntity entity = factory.manufacturePojo(PuntoEntity.class);

           em.persist(entity);
           data.add(entity);
       }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class PuntoPersistence.
     */
    @Test
    public void testCreate() throws Exception 
    {
        PodamFactory factory = new PodamFactoryImpl();
        
        PuntoEntity nuevoPunto = factory.manufacturePojo(PuntoEntity.class);
        nuevoPunto.setUsuarioPunto(null);
        PuntoEntity resultado = persistence.create(nuevoPunto);

        Assert.assertNotNull(resultado);
        
        PuntoEntity pCreado = em.find(PuntoEntity.class, resultado.getId());
        
        Assert.assertNotNull(pCreado);
        
        Assert.assertEquals(nuevoPunto.getId(), pCreado.getId());
        Assert.assertEquals(nuevoPunto.getName(), pCreado.getName());
        Assert.assertEquals(nuevoPunto.getFechaPunto(), pCreado.getFechaPunto());
        Assert.assertEquals(nuevoPunto.getFechaVencimiento(), pCreado.getFechaVencimiento());
        Assert.assertNull(nuevoPunto.getUsuarioPunto());
        Assert.assertTrue(nuevoPunto.equals(pCreado));
        nuevoPunto.setFechaPunto(null);
        Assert.assertFalse(nuevoPunto.equals(pCreado));
    }
    
    @Test
    public void testEquals() throws Exception 
    {
        PodamFactory factory = new PodamFactoryImpl();
        PuntoEntity nuevoPunto = factory.manufacturePojo(PuntoEntity.class);
        nuevoPunto.setUsuarioPunto(null);
        
        PuntoEntity punto = null;
        String puntSt = "String de punto";
        
        Assert.assertFalse(nuevoPunto.equals(punto));
        Assert.assertFalse(nuevoPunto.equals(puntSt));    
    }
    

    /**
     * Test of find method, of class PuntoPersistence.
     */
    @Test
    public void testFind() throws Exception 
    {
        PuntoEntity entity = data.get(0);
        PuntoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAll method, of class PuntoPersistence.
     */
    @Test
    public void testFindAll() throws Exception 
    {
        List<PuntoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PuntoEntity ent : list) {
            boolean found = false;
            for (PuntoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test of delete method, of class PuntoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        PuntoEntity entidad = data.get(0);
        persistence.delete(entidad.getId());
        PuntoEntity eliminada = em.find(PuntoEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
    
     /**
     * Test of update method, of class PuntoPersistence.
     */
    @Test
    public void testUpdate() throws Exception 
    {
        PuntoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PuntoEntity newEntity = factory.manufacturePojo(PuntoEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        PuntoEntity resp = em.find(PuntoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
   
}
    


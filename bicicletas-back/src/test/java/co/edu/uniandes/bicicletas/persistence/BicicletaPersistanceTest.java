/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
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
 * @author js.torres1
 */
@RunWith(Arquillian.class)
public class BicicletaPersistanceTest {
     @Inject
    private BicicletaPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
            UserTransaction utx;
    
    
    
    private List<BicicletaEntity> data = new ArrayList<BicicletaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(AccesorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    public  BicicletaPersistanceTest(){}
    
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
        em.createQuery("delete from BicicletaEntity").executeUpdate();
    }
    
    
    private void insertData() {
        PodamFactory factory = (PodamFactory) new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BicicletaEntity entity = factory.manufacturePojo(BicicletaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of findAll method, of class BicicletaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<BicicletaEntity> lista = persistence.findAll();
        Assert.assertEquals(lista.size(), data.size());
        for (BicicletaEntity ent : lista) {
            boolean found = false;
            for (BicicletaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test of find method, of class AccesorioPersistence.
     */
    @Test
    public void testFind() throws Exception {
        BicicletaEntity buscar = data.get(0);
        BicicletaEntity encontrada = persistence.find(buscar.getId());
        Assert.assertNotNull(encontrada);
        Assert.assertEquals(buscar.getId(), encontrada.getId());
    }
    
    /**
     * Test of create method, of class BicicletaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = (PodamFactory) new PodamFactoryImpl();
        
        BicicletaEntity nuevoAccesorio = factory.manufacturePojo(BicicletaEntity.class);
        BicicletaEntity resultado = persistence.create(nuevoAccesorio);
        
        Assert.assertNotNull(resultado);
        
        BicicletaEntity creada = em.find(BicicletaEntity.class, resultado.getId());
        
        Assert.assertEquals(nuevoAccesorio.getId(), creada.getId());
    }
    
    /**
     * Test of update method, of class AccesorioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        BicicletaEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BicicletaEntity nuevaEntidad = factory.manufacturePojo(BicicletaEntity.class);
        nuevaEntidad.setId(entidad.getId());
        persistence.update(nuevaEntidad);
        BicicletaEntity resp = em.find(BicicletaEntity.class, entidad.getId());
        Assert.assertEquals(nuevaEntidad.getName(), resp.getName());
    }
    
    /**
     * Test of delete method, of class AccesorioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        BicicletaEntity entidad = data.get(0);
        persistence.delete(entidad.getId());
        BicicletaEntity eliminada = em.find(BicicletaEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.bicicletas.persistence;


import co.edu.uniandes.bicicletas.entities.EstacionEntity;
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
    
    
    public EstacionPersistenceTest(){}
    
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
        List<EstacionEntity> lista = persistence.findAll();
        Assert.assertEquals(lista.size(), data.size());
        for (EstacionEntity ent : lista) {
            boolean found = false;
            for (EstacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test of find method, of class EstacionPersistence.
     */
    @Test
    public void testFind() throws Exception {
        EstacionEntity  buscar = data.get(0);
        EstacionEntity  encontrada = persistence.find(buscar.getId());
        Assert.assertNotNull(encontrada);
        Assert.assertEquals(buscar.getName(), encontrada.getName());
    }
    
    /**
     * Test of create method, of class EstacionPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        
        EstacionEntity nuevaEstacion = factory.manufacturePojo(EstacionEntity.class);
        EstacionEntity resultado = persistence.create(nuevaEstacion);
        
        Assert.assertNotNull(resultado);
        
        EstacionEntity creada = em.find(EstacionEntity.class, resultado.getId());
        
        Assert.assertEquals(nuevaEstacion.getName(), creada.getName());
    }
    
    /**
     * Test of update method, of class EstacionPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        EstacionEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EstacionEntity nuevaEntidad = factory.manufacturePojo(EstacionEntity.class);
        nuevaEntidad.setId(entidad.getId());
        persistence.update(nuevaEntidad);
        EstacionEntity resp = em.find(EstacionEntity.class, entidad.getId());
        Assert.assertEquals(nuevaEntidad.getName(), resp.getName());
    }
    
    /**
     * Test of delete method, of class EstacionPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        EstacionEntity entidad = data.get(0);
        persistence.delete(entidad.getId());
        EstacionEntity eliminada = em.find(EstacionEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
    
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
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


/**
 *
 * @author ka.babativa
 */
@RunWith(Arquillian.class)
public class AccesorioPersistenceTest {
    
    @Inject
    private AccesorioPersistence persistence;
    
    @PersistenceContext(unitName="bicicletasPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    
    
    private List<AccesorioEntity> data = new ArrayList<AccesorioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AccesorioEntity.class.getPackage())
                .addPackage(AccesorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    public  AccesorioPersistenceTest(){}
    
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
        em.createQuery("delete from AccesorioEntity").executeUpdate();
    }
    
    
    private void insertData() {
        PodamFactory factory = (PodamFactory) new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AccesorioEntity entity = factory.manufacturePojo(AccesorioEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of findAll method, of class AccesorioPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<AccesorioEntity> lista = persistence.findAll();
        Assert.assertEquals(lista.size(), data.size());
        for (AccesorioEntity ent : lista) {
            boolean found = false;
            for (AccesorioEntity entity : data) {
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
        AccesorioEntity buscar = data.get(0);
        AccesorioEntity encontrada = persistence.find(buscar.getId());
        Assert.assertNotNull(encontrada);
        Assert.assertEquals(buscar.getName(), encontrada.getName());
    }
    
    /**
     * Test of create method, of class AccesorioPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = (PodamFactory) new PodamFactoryImpl();
        
        AccesorioEntity nuevoAccesorio = factory.manufacturePojo(AccesorioEntity.class);
        AccesorioEntity resultado = persistence.create(nuevoAccesorio);
        
        Assert.assertNotNull(resultado);
        
        AccesorioEntity creada = em.find(AccesorioEntity.class, resultado.getId());
        
        Assert.assertEquals(nuevoAccesorio.getTipo(), creada.getTipo());
    }
    
    /**
     * Test of update method, of class AccesorioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        AccesorioEntity entidad = data.get(0);
        PodamFactory factory = (PodamFactory) new PodamFactoryImpl();
        AccesorioEntity nuevaEntidad = factory.manufacturePojo(AccesorioEntity.class);
        nuevaEntidad.setId(entidad.getId());
        persistence.update(nuevaEntidad);
        AccesorioEntity resp = em.find(AccesorioEntity.class, entidad.getId());
        Assert.assertEquals(nuevaEntidad.getName(), resp.getName());
    }
    
    /**
     * Test of delete method, of class AccesorioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        AccesorioEntity entidad = data.get(0);
        persistence.delete(entidad.getId());
        AccesorioEntity eliminada = em.find(AccesorioEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
    
}

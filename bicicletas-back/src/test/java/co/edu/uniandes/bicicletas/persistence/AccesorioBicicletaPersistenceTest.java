/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.persistence;

import co.edu.uniandes.bicicletas.entities.AccesorioBicicletaEntity;
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
 * @author js.torres1
 */
@RunWith(Arquillian.class)
public class AccesorioBicicletaPersistenceTest {
    @Inject
    private AccesorioBicicletaPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
            UserTransaction utx;
    
    
    
    private List<AccesorioBicicletaEntity> data = new ArrayList<AccesorioBicicletaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AccesorioBicicletaEntity.class.getPackage())
                .addPackage(AccesorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    public  AccesorioBicicletaPersistenceTest(){}
    
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
            AccesorioBicicletaEntity entity = factory.manufacturePojo(AccesorioBicicletaEntity.class);
            
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
        List<AccesorioBicicletaEntity> lista = persistence.findAll();
        Assert.assertEquals(lista.size(), data.size());
        for (AccesorioBicicletaEntity ent : lista) {
            boolean found = false;
            for (AccesorioBicicletaEntity entity : data) {
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
        AccesorioBicicletaEntity buscar = data.get(0);
        AccesorioBicicletaEntity encontrada = persistence.find(buscar.getId());
        Assert.assertNotNull(encontrada);
        Assert.assertEquals(buscar.getId(), encontrada.getId());
    }
    
    /**
     * Test of create method, of class BicicletaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = (PodamFactory) new PodamFactoryImpl();
        
        AccesorioBicicletaEntity nuevoAccesorio = factory.manufacturePojo(AccesorioBicicletaEntity.class);
        nuevoAccesorio.setBicicleta(null);
        AccesorioBicicletaEntity resultado = persistence.create(nuevoAccesorio);
        
        Assert.assertNotNull(resultado);
        
        AccesorioBicicletaEntity creada = em.find(AccesorioBicicletaEntity.class, resultado.getId());
        
        Assert.assertEquals(nuevoAccesorio.getId(), creada.getId());
        Assert.assertEquals(nuevoAccesorio.darNombre(), creada.darNombre());
        Assert.assertEquals(nuevoAccesorio.darDescrip(), creada.darDescrip());
        Assert.assertNull(creada.getBicicleta());
    }
    
    /**
     * Test of update method, of class AccesorioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        AccesorioBicicletaEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AccesorioBicicletaEntity nuevaEntidad = factory.manufacturePojo(AccesorioBicicletaEntity.class);
        nuevaEntidad.setId(entidad.getId());
        persistence.update(nuevaEntidad);
        AccesorioBicicletaEntity resp = em.find(AccesorioBicicletaEntity.class, entidad.getId());
        Assert.assertEquals(nuevaEntidad.getName(), resp.getName());
    }
    
    /**
     * Test of delete method, of class AccesorioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        AccesorioBicicletaEntity entidad = data.get(0);
        persistence.delete(entidad.getId());
        AccesorioBicicletaEntity eliminada = em.find(AccesorioBicicletaEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
}

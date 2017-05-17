package org.hibernate.bugs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");

        // BaseCoreFunctionalTestCase automatically creates the SessionFactory and provides the Session.
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Map<Locale, LocalizedValue> p1Desc = new HashMap<>();
        p1Desc.put(Locale.ENGLISH, LocalizedValue.valueOf("test"));
        p1Desc.put(new Locale("bg"), LocalizedValue.valueOf("test"));

        Product p1 = new Product();
        p1.setId(1L);
        p1.setCode("p1Code");
        p1.setDescription(p1Desc);
        entityManager.merge(p1);

        assertEquals(2, p1.getDescription().size());

        // Do stuff...
        entityManager.getTransaction().commit();

        try {
            entityManager.getTransaction().begin();

            List result = entityManager.createQuery("from product as p where p.code='p1Code'").getResultList();

            Product p = ((Product) result.get(0));
            p.setCode("new-code");

            entityManager.merge(p);

            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MyCustomJpaListener.products.clear();
        }
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void testExceptionIsThrownWhenSavingCollectionOfEmbeddables() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List result = entityManager.createQuery("from product as p where p.code='new-code'").getResultList();

        Product p = ((Product) result.get(0));
        p.getDescription().put(Locale.ENGLISH, LocalizedValue.valueOf("new-value"));
        p.getDescription().put(Locale.GERMAN, LocalizedValue.valueOf("test-test"));
        entityManager.merge(p);
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();

        assertEquals(1, MyCustomJpaListener.products.size());
    }

    @Test
    public void testSaveSimpleAttribute() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List result = entityManager.createQuery("from product as p where p.code='new-code'").getResultList();

        assertNotNull(result);
        Product p = ((Product) result.get(0));
        p.setOther("new-code");
        entityManager.merge(p);
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();

        assertEquals(1, MyCustomJpaListener.products.size());
    }
}

/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hibernate.bugs;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using its built-in unit test framework.
 * Although ORMStandaloneTestCase is perfectly acceptable as a reproducer, usage of this class is much preferred.
 * Since we nearly always include a regression test with bug fixes, providing your reproducer using this method
 * simplifies the process.
 * What's even better?  Fork hibernate-orm itself, add your test case directly to a module's unit tests, then
 * submit it as a PR!
 */
public class ORMUnitTestCase extends BaseCoreFunctionalTestCase {

    // Add your entities here.
    @Override
    protected Class[] getAnnotatedClasses() {
        return new Class[] { Product.class, BaseProduct.class

                        //				Foo.class,
                        //				Bar.class
        };
    }

    // If you use *.hbm.xml mappings, instead of annotations, add the mappings here.
    @Override
    protected String[] getMappings() {
        return new String[] {
                        //				"Foo.hbm.xml",
                        //				"Bar.hbm.xml"
        };
    }

    // If those mappings reside somewhere other than resources/org/hibernate/test, change this.
    @Override
    protected String getBaseForMappings() {
        return "org/hibernate/test/";
    }

    // Add in any settings that are specific to your test.  See resources/hibernate.properties for the defaults.
    @Override
    protected void configure(Configuration configuration) {
        super.configure(configuration);

        configuration.setProperty(AvailableSettings.SHOW_SQL, Boolean.TRUE.toString());
        configuration.setProperty(AvailableSettings.FORMAT_SQL, Boolean.TRUE.toString());
        configuration.setProperty(AvailableSettings.GENERATE_STATISTICS, "true");
    }

    //    @Before
    //    public void setUp() {
    //        // BaseCoreFunctionalTestCase automatically creates the SessionFactory and provides the Session.
    //        Session s = openSession();
    //        Transaction tx = s.beginTransaction();
    //
    //        Map<Locale, LocalizedValue> p1Desc = new HashMap<>();
    //        p1Desc.put(Locale.ENGLISH, LocalizedValue.valueOf("test"));
    //        p1Desc.put(new Locale("bg"), LocalizedValue.valueOf("test"));
    //
    //        Product p1 = new Product();
    //        p1.setId(1L);
    //        p1.setCode("p1Code");
    //        p1.setDescription(p1Desc);
    //        s.merge(p1);
    //
    //        assertEquals(2, p1.getDescription().size());
    //
    //        // Do stuff...
    //        tx.commit();
    //
    //        try {
    //            // BaseCoreFunctionalTestCase automatically creates the SessionFactory and provides the Session.
    //            s = openSession();
    //            tx = s.beginTransaction();
    //
    //            List result = s.createQuery("from product as p where p.code='p1Code'").list();
    //
    //            Product p = ((Product) result.get(0));
    //            p.setCode("new-code");
    //
    //            s.merge(p);
    //
    //            tx.commit();
    //            s.close();
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        } finally {
    //            MyCustomJpaListener.products.clear();
    //        }
    //
    //    }

    // Add your tests, using standard JUnit.
    @Test
    public void testExceptionIsThrownWhenSavingAttribute() throws Exception {

    }

    //
    //    // Add your tests, using standard JUnit.
    @Test
    public void testExceptionIsThrownWhenSavingCollectionOfEmbeddables() throws Exception {

    }
}

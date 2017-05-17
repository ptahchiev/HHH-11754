package org.hibernate.bugs;

import org.hibernate.event.spi.PostCollectionUpdateEvent;
import org.hibernate.event.spi.PostCollectionUpdateEventListener;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public class MyCustomJpaListener implements PostCollectionUpdateEventListener {

    @PostUpdate
    @PostPersist
    public void test(BaseProduct product) {
        products.add(product);
    }

    public static List<BaseProduct> products = new ArrayList<>();

    @Override
    public void onPostUpdateCollection(PostCollectionUpdateEvent event) {
        products.add((BaseProduct) event.getAffectedOwnerOrNull());
    }
}

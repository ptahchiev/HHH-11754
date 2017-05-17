package org.hibernate.bugs;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import java.util.Locale;
import java.util.Map;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity(name = "product")
public class Product extends BaseProduct {

    @ElementCollection(targetClass = LocalizedValue.class)
    @CollectionTable(name = "product_description_lv", joinColumns = @JoinColumn(name = "product_id"), indexes = {
                    @Index(name = "idx_" + "product_description_lv", columnList = "product_id") }, foreignKey = @ForeignKey(name = "fk_"
                    + "product_description_lv"))
    @MapKeyColumn(name = "locale")
    private Map<Locale, LocalizedValue> description;

    public Map<Locale, LocalizedValue> getDescription() {
        return description;
    }

    public void setDescription(Map<Locale, LocalizedValue> description) {
        this.description = description;
    }
}

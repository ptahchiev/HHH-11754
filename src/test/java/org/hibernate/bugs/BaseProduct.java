package org.hibernate.bugs;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Locale;
import java.util.Map;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
@Entity(name = "base_product")
@EntityListeners({ MyCustomJpaListener.class })
public class BaseProduct {

    @Id
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "other")
    private String other;

    @ElementCollection(targetClass = LocalizedValue.class)
    @CollectionTable(name = "product_name_lv", joinColumns = @JoinColumn(name = "product_id"), indexes = {
                    @Index(name = "idx_" + "product_name_lv", columnList = "product_id") }, foreignKey = @ForeignKey(name = "fk_" + "product_name_lv"))
    @MapKeyColumn(name = "locale")
    private Map<Locale, LocalizedValue> name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<Locale, LocalizedValue> getName() {
        return name;
    }

    public void setName(Map<Locale, LocalizedValue> name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

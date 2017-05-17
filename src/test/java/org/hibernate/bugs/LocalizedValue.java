package org.hibernate.bugs;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Embeddable
@Access(AccessType.FIELD)
public class LocalizedValue {

    @Lob
    @Column(length = 50000, name = "val")
    private String value;

    /**
     * A getter method for the {@code value}.
     */
    public String getValue() {
        return value;
    }

    /**
     * A setter method for the {@code value}.
     *
     * @param value The value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) { //NOPMD
            return false;
        }
        LocalizedValue that = (LocalizedValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static LocalizedValue valueOf(String value) {
        LocalizedValue localized = new LocalizedValue();
        localized.setValue(value);
        return localized;
    }
}

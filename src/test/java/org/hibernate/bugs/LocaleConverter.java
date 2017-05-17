package org.hibernate.bugs;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Converter(autoApply = true)
public class LocaleConverter implements AttributeConverter<Locale, String> {

    @Override
    public String convertToDatabaseColumn(Locale locale) {
        return locale == null ? null : locale.toString();
    }

    @Override
    public Locale convertToEntityAttribute(String locale) {
        return StringUtils.isEmpty(locale) ? null : StringUtils.parseLocaleString(locale);
    }
}

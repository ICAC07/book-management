package com.baufest.book.management.configuration;

import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class MessageLocaleConfiguration {

    @Bean
    public Locale setDefaultLocale(@Value("${spring.messages.locale}") String locale) {
        Locale.setDefault(Objects.requireNonNull(StringUtils.parseLocale(locale)));
        return Locale.getDefault();
    }
    /*
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        FixedLocaleResolver localeResolver = new FixedLocaleResolver();
        localeResolver.setDefaultLocale(Locale.getDefault());
        return localeResolver;
    }
     */
}

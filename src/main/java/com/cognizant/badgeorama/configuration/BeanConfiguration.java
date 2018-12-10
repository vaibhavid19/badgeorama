package com.cognizant.badgeorama.configuration;

import org.springframework.beans.BeansException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class BeanConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public BeanConfiguration() {

    }

    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {

        builder.setConnectTimeout(Duration.of(10, ChronoUnit.SECONDS));
        RestTemplate template = builder.build();
        return template;
    }

}

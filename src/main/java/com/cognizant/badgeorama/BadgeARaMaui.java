package com.cognizant.badgeorama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

/**
 * The following tutorials were helpful in making this application:
 * <p>
 * https://www.baeldung.com/thymeleaf-in-spring-mvc
 * https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html
 * https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-thymeleaf/
 * https://spring.io/guides/gs/serving-web-content/
 * https://www.boraji.com/spring-mvc-5-hello-world-example-with-thymeleaf-template
 * https://o7planning.org/en/11545/spring-boot-and-thymeleaf-tutorial#a10849074
 * https://www.baeldung.com/spring-boot-custom-error-page
 */
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class BadgeARaMaui {
    public static void main(String[] args) {
        SpringApplication.run(BadgeARaMaui.class, args);
    }
}

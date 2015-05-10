package org.pti.poster.integration;


import com.mangofactory.swagger.plugin.EnableSwagger;
import org.pti.poster.integration.security.WebSecurityConfiguration;
import org.pti.poster.rest.RestSpringConfig;
import org.pti.poster.service.SpringServiceApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebSecurity
@ComponentScan
@EnableSwagger
@Import(value = {SpringServiceApplicationConfiguration.class, WebSecurityConfiguration.class, RestSpringConfig.class})
public class Application extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
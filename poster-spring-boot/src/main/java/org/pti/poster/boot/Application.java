package org.pti.poster.boot;


import org.pti.poster.SpringApplicationConfiguration;
import org.pti.poster.boot.security.WebSecurityConfiguration;
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
@Import(value = {SpringApplicationConfiguration.class, WebSecurityConfiguration.class})
public class Application extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
package org.pti.poster.integration;


import com.hazelcast.web.SessionListener;
import com.hazelcast.web.spring.SpringAwareWebFilter;
import com.mangofactory.swagger.plugin.EnableSwagger;
import org.pti.poster.integration.security.WebSecurityConfiguration;
import org.pti.poster.rest.RestSpringConfig;
import org.pti.poster.service.SpringServiceApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;

import javax.servlet.DispatcherType;

@SpringBootApplication
@EnableWebSecurity
@EnableSwagger
@Import(value = {SpringServiceApplicationConfiguration.class, WebSecurityConfiguration.class, RestSpringConfig.class})
public class Application extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        new SpringApplicationBuilder(ParentConfiguration.class)
//                .profiles("adminServer", "single")
//                .child(AdminServerApplication.class)
//                .run(args);
    }


    /*  hazelcast configuration */
    @Bean
    public FilterRegistrationBean hazelcastFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new SpringAwareWebFilter());

        registration.addUrlPatterns("/*");
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);

        // Configure init parameters as appropriate:
        registration.addInitParameter("map-name", "sessions-map");
        registration.addInitParameter("cookie-name", "hazelcast.sessionId");
//         registration.addInitParameter("debug", "true");
//         registration.addInitParameter("config-location", "/WEB-INF/hazelcast.xml");
//         registration.addInitParameter("instance-name", "default-hazelcast-instance");

        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<SessionListener> hazelcastSessionListener() {
        return new ServletListenerRegistrationBean<SessionListener>(new SessionListener());
    }

    @Bean
    /*in case of using hazelcast we have an exception like 
    NoSuchBeanDefinitionException: No qualifying bean of type 
    [org.springframework.security.core.session.SessionRegistry]
    works fine now
    */
    
    public SessionRegistry sessionRegistry() {
        return new org.springframework.security.core.session.SessionRegistryImpl();
    }
}
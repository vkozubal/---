package org.pti.poster.server.appserver.config;

import com.mangofactory.swagger.configuration.JacksonSwaggerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonSwaggerConfig {

	@Bean
	public JacksonSwaggerSupport jacksonSwaggerSupport() {
		return new JacksonSwaggerSupport();
	}

}

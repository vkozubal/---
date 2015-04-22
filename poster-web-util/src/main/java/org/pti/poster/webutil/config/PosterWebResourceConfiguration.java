package org.pti.poster.webutil.config;

import org.pti.poster.webutil.converter.PostCollectionConverter;
import org.pti.poster.webutil.converter.RegisteredPostConverter;
import org.pti.poster.webutil.converter.UnregisteredPostConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
public class PosterWebResourceConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	JacksonFilterConfig filterConfig;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
		httpMessageConverters.add(new RegisteredPostConverter(filterConfig, MediaType.APPLICATION_JSON));
		httpMessageConverters.add(new UnregisteredPostConverter(filterConfig, MediaType.APPLICATION_JSON));
		httpMessageConverters.add(new PostCollectionConverter(filterConfig, MediaType.APPLICATION_JSON));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/api/swagger/**")
				.addResourceLocations("classpath:/static/swagger/");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
		super.extendMessageConverters(converters);
	}
}

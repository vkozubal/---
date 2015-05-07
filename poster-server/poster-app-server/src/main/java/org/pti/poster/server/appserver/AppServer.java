package org.pti.poster.server.appserver;

import org.pti.poster.PosterAppServerApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableOAuth2Client
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Import(value = PosterAppServerApplicationConfiguration.class)

public class AppServer {

	@Value("${oauth.resource:http://localhost:8811}")
	private String baseUrl;

	@Value("${oauth.authorize:http://localhost:7711/oauth/authorize}")
	private String authorizeUrl;

	@Value("${oauth.token:http://localhost:7711/oauth/token}")
	private String tokenUrl;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AppServer.class, args);
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	public OAuth2RestOperations restTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext());
		AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
		provider.setClientTokenServices(clientTokenServices());
		return template;
	}

	@Bean
	public ClientTokenServices clientTokenServices() {
		return new JdbcClientTokenServices(dataSource);
	}

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		ImplicitResourceDetails resource = new ImplicitResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setUserAuthorizationUri(authorizeUrl);
		resource.setClientId("poster");
		resource.setId("auth-rest");
		resource.setUseCurrentUri(true);
		return resource;
	}
}
package org.pti.poster.auth.server;

import org.pti.poster.security.hibernate.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.InputStreamReader;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void setUpTokenDatasource() {
		Resource resource = new ClassPathResource("db_schema");

		try {
			ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection(), true, true);
			scriptRunner.runScript(new InputStreamReader(resource.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}
}
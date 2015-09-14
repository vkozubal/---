package org.pti.poster.dao;

import org.pti.poster.dao.repository.RepositoryPackage;
import org.pti.poster.model.SpringConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = RepositoryPackage.class)
@Configuration
@ComponentScan
@Import(value = SpringConfiguration.class)
public class DaoConfiguration {
}
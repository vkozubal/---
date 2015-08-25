package org.pti.poster.service;


import org.pti.poster.dao.MongoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(value = {MongoConfiguration.class})
public class SpringServiceApplicationConfiguration {
}
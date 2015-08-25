package org.pti.poster.service;

import org.pti.poster.dao.config.UnitTestMongoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.pti.poster.service.impl")
@Import(value = {UnitTestMongoConfiguration.class})
public class TestSprintServiceApplicationConfiguration {
}

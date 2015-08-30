package org.pti.poster.service;

import org.pti.poster.dao.TestMongoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {TestMongoConfiguration.class, SpringServiceApplicationConfiguration.class})
public class TestSprintServiceApplicationConfiguration {
}

package org.pti.poster.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class HazelcastConfiguration {

	private HazelcastInstance hazelcastInstance;

	@PostConstruct
	public void init() {
		Config hazelcastConfig = new Config();
		hazelcastInstance = Hazelcast.newHazelcastInstance(hazelcastConfig);
	}

	public HazelcastInstance getHazelcastInstance() {
		return hazelcastInstance;
	}
}
package org.pti.poster.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.pti.poster.rest.PostService;

public class RestResourceConfig extends ResourceConfig {

    public RestResourceConfig() {
	register(PostService.class);
    }
}

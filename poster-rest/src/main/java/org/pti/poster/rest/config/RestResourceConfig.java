package org.pti.poster.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.annotations.Service;
import org.pti.poster.rest.ContactsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class RestResourceConfig extends ResourceConfig {

    public RestResourceConfig() {
        register(ContactsService.class);
    }
}


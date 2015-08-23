package org.pti.poster.rest.hateoas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = false)
public class Greeting extends ResourceSupport {

    private final String content;

    public Greeting(String content) {
        this.content = content;
    }
}
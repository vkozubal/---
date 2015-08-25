package org.pti.poster.rest.resources;

import org.pti.poster.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class PostLinks {
    @Autowired
    private EntityLinks entityLinks;
    
    Link deletePostLink(Post post){
        return entityLinks.linkForSingleResource(post).withSelfRel();
    }
}
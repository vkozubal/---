package org.pti.poster.rest.resources;

import org.pti.poster.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class PostResourceProcessor implements ResourceProcessor<Resource<Post>> {

    private @Autowired PostLinks postLinks;
    
    /*
	 * (non-Javadoc)
	 * @see org.springframework.hateoas.ResourceProcessor#process(org.springframework.hateoas.ResourceSupport)
	 */
    @Override
    public Resource<Post> process(Resource<Post> resource) {
        Post content = resource.getContent();

        resource.add(postLinks.deletePostLink(content));
        return resource;
    }
}
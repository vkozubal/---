package org.pti.poster.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.pti.poster.dao.PostDAO;
import org.pti.poster.view.PostView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path(value = "/post")
public class PostService {

    @Autowired
    private PostDAO postDAO;
    
    @Path("/get/{id}")
    public PostView getPost(@PathParam("id") String id) {
	return new PostView(postDAO.getPostById(id));
    }
}

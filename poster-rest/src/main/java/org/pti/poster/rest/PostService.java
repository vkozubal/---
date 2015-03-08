package org.pti.poster.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.pti.poster.view.PostView;

@Path(value = "/post")
public class PostService{

	@Path("/get")
	public PostView getPost(@PathParam("id") String id) {
		return new PostView("Hello World!");
	}
}

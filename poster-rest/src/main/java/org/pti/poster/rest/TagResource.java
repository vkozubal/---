package org.pti.poster.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.pti.poster.model.Post;
import org.pti.poster.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

@Api(value = "Tag resource", position = 1)
@RequestMapping("/api/posts/{id}/tags")
@RestController
public class TagResource {

    @Autowired
    PostService postService;

    @ApiOperation(value = "Creates new tag")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void updatePost(@RequestBody String tag, @PathVariable Long id, HttpServletResponse response){
        Post initial = postService.getById(id);
        ArrayList<Post.Tag> tags = new ArrayList<>(initial.getPostTags());
        tags.add(new Post.Tag(tag));
        Post post = new Post(initial.getText(), tags);
        response.setHeader(HttpHeaders.LOCATION, "/api/posts/" + String.valueOf(postService.addPost(post)));
    }
    
    @ApiOperation(value = "Deletes tag from post")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deletePost(@RequestBody String tag, @PathVariable Long id, HttpServletResponse response){
        Post initial = postService.getById(id);
        ArrayList<Post.Tag> tags = new ArrayList<>(initial.getPostTags());
        tags.remove(new Post.Tag(tag));
        Post post = new Post(initial.getText(), tags);
        response.setHeader(HttpHeaders.LOCATION, "/api/posts/" + String.valueOf(postService.addPost(post)));
    }

    @ApiOperation(value = "Get All post tags.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Post.Tag> getTags(@PathVariable Long id){
        return postService.getById(id).getPostTags();
    }
}

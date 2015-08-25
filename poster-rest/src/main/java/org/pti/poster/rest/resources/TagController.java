package org.pti.poster.rest.resources;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.pti.poster.model.Post;
import org.pti.poster.model.Tag;
import org.pti.poster.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Api(value = "Tag resource", position = 1)
@RequestMapping("/rest/posts/{id}/tags")
@ExposesResourceFor(Tag.class)
@RestController
@Slf4j
public class TagController {
    public static final String POSTS_ROOT = "/rest/posts/";
    
    @Autowired
    PostService postService;

    @ApiOperation(value = "Adds new tag to existing post.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addPostTag(@RequestBody Tag tag, @PathVariable(value = "id") BigInteger id, HttpServletResponse response) {
        Post initial = getPostById(id);
        ArrayList<Tag> tags = new ArrayList<>(initial.getPostTags());
        tags.add(tag);
        String text = initial.getText();
        removeOldPostAndCreateNew(id, response, tags, text);
    }

    @ApiOperation(value = "Deletes tag from post")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@RequestBody Tag tag, @PathVariable BigInteger id, HttpServletResponse response) {
        Post initial = getPostById(id);
        ArrayList<Tag> tags = new ArrayList<>(initial.getPostTags());
        tags.remove(tag);
        String text = initial.getText();
        removeOldPostAndCreateNew(id, response, tags, text);
    }

    private Post getPostById(@PathVariable BigInteger id) {
        return postService.getById(getUser(), id);
    }

    @ApiOperation(value = "Get All post tags.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Tag> getTags(@PathVariable BigInteger id) {
        return getPostById(id).getPostTags();
    }

    private void removeOldPostAndCreateNew(BigInteger oldPostId, HttpServletResponse response, ArrayList<Tag> tags, String text) {
        Post post = new Post(text, tags);
        postService.remove(getUser(), oldPostId);// remove old post

        log.info("before adding post");
        BigInteger id = postService.addPost(getUser(), post);// add new post to existing user

        log.info("Set id {} in response header", String.valueOf(id));
        response.setHeader(HttpHeaders.LOCATION, POSTS_ROOT + String.valueOf(id));
    }

    private UserDetails getUser() {
        return Utils.getCurrentUserDetails();
    }
}

package org.pti.poster.rest.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.pti.poster.model.Constants;
import org.pti.poster.model.Post;
import org.pti.poster.rest.hateoas.Greeting;
import org.pti.poster.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Api(value = "Post resource", position = 1)
@RequestMapping("/rest/posts")
@ExposesResourceFor(Post.class)
@RestController
public class PostController {

    private static final String TEMPLATE = "Hello, %s!";
    private PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("Gets post by identifier")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPostById(@PathVariable BigInteger id) {
        return postService.getById(getUser(), id);
    }

    @ApiOperation(value = "Creates brand new resource", notes = "Use POST to create resources when you do not know the resource identifier. " +
            "Returns the location of created resource.")
    @ResponseStatus(HttpStatus.CREATED) // returns 201 status
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HttpEntity<Resources<Resource>> createPost(@RequestBody Post post) {

        BigInteger bigInteger = postService.addPost(getUser(), post);
        ControllerLinkBuilder link = linkTo(ControllerLinkBuilder.methodOn(PostController.class).getPostById(bigInteger));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(link.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Returns collection of posts")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Post> getPosts(@RequestParam(required = false) String filteredDate) {
        
        if (filteredDate == null) {
            return postService.getAllPostsForUser(getUser());
        }

        DateTime targetDate = new DateTime(getDateFromString(filteredDate));

        Collection<Post> userPostsByDate = new ArrayList<>();
        for (Post post : postService.getAllPostsForUser(getUser())) {
            if (new Interval(targetDate.withTimeAtStartOfDay(), targetDate.plusDays(1).withTimeAtStartOfDay())
                    .contains(new DateTime(post.getCreationDate()))) {
                userPostsByDate.add(post);
            }
        }
        return userPostsByDate;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deletes the specified post.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable BigInteger id) {
        postService.remove(getUser(), id);
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(PostController.class).greeting(name)).withSelfRel());
        return greeting;
    }
/*
    @ApiOperation(value = "Makes partial update", notes = "You can use POST to send either all available values or just a subset of available values. " +
            "Returns the location of updated resource.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMappi.ng(value = "/{id}", method = RequestMethod.POST)
    public void makePartialUpdate(@RequestBody PostView view, @PathVariable BigInteger id, HttpServletResponse response) {
        //TODO make partitial update
        response.setHeader(HttpHeaders.LOCATION, String.valueOf(view.getId()));
    }*/
    
    /*
     todo
    @ApiOperation(value = "Makes fully update of resource under identifier.", notes = "Since PUT is idempotent, you must send all possible values. Returns the location of updated resource.")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addNewPost(@RequestBody PostView view, @PathVariable BigInteger id) {

        postService.remove(getUser(), id);  // remove old post
        BigInteger newId = postService.addPost(getUser(), PostView.transformToPost(view));

        Link link = new Link();
        link.add(linkTo(ControllerLinkBuilder.methodOn(PostResource.class).getPostById()));
        return link;
    }
*/

    private Date getDateFromString(String dateString) {
        DateFormat format = new SimpleDateFormat(Constants.DATE_PATTERN, Locale.ENGLISH);
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private UserDetails getUser() {
        return Utils.getCurrentUserDetails();
    }
}
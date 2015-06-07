package org.pti.poster.rest.resources;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.pti.poster.model.Constants;
import org.pti.poster.model.Post;
import org.pti.poster.rest.view.PostView;
import org.pti.poster.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

@Api(value = "Post resource", position = 1)
@RequestMapping("/rest/posts")
@RestController
public class PostResource {
    private PostService postService;

    @Autowired
    PostResource(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("Gets post by identifier")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    PostView getPostById(@PathVariable BigInteger id) {
        return PostView.fromPost(postService.getById(getUser(), id));
    }

    @ApiOperation(value = "Creates brand new resource", notes = "Use POST to create resources when you do not know the resource identifier. " +
            "Returns the location of created resource.")
    @ResponseStatus(HttpStatus.CREATED) // returns 201 status
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createPost(@RequestBody PostView view, HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, TagResource.POSTS_ROOT + String.valueOf(postService.addPost(getUser(), PostView.transformToPost(view))));
    }

    @ApiOperation(value = "Makes partial update", notes = "You can use POST to send either all available values or just a subset of available values. " +
            "Returns the location of updated resource.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void makePartialUpdate(@RequestBody PostView view, @PathVariable BigInteger id, HttpServletResponse response) {
        //TODO make partitial update
        response.setHeader(HttpHeaders.LOCATION, String.valueOf(view.getId()));
    }

    @ApiOperation(value = "Makes fully update of resource under identifier.", notes = "Since PUT is idempotent, you must send all possible values. Returns the location of updated resource.")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addNewPost(@RequestBody PostView view, @PathVariable BigInteger id, HttpServletResponse response) {

        postService.remove(getUser(), id);  // remove old post 
        response.setHeader(HttpHeaders.LOCATION, TagResource.POSTS_ROOT +
                String.valueOf(postService.addPost(getUser(), PostView.transformToPost(view))));// add new post.
    }

    @ApiOperation(value = "Returns collection of posts")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Post> getPosts(@RequestParam(required = false) String filteredDate) throws ParseException {
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

    private Date getDateFromString(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat(Constants.DATE_PATTERN, Locale.ENGLISH);
        return format.parse(dateString);
    }

    private UserDetails getUser() {
        return Utils.getCurrentUserDetails();
    }
}

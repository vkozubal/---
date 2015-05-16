package org.pti.poster.rest.resources;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.pti.poster.model.Constants;
import org.pti.poster.model.Post;
import org.pti.poster.rest.view.PostView;
import org.pti.poster.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Api(value = "Post resource", position = 1)
@RequestMapping("/rest/posts")
@RestController
public class PostResource {
    PostService postService;

    @Autowired
    PostResource(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("Gets post by identifier")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PostView getPostById(@PathVariable BigInteger id) {
        return PostView.fromPost(postService.getById(id));
    }

    @ApiOperation(value = "Creates brand new resource", notes = "Use POST to create resources when you do not know the resource identifier. Returns the location of created resource.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createPost(@RequestBody PostView view, HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, TagResource.POSTS_ROOT + String.valueOf(postService.addPost(PostView.fromView(view))));
    }

    @ApiOperation(value = "Makes partial update", notes = "You can use POST to send either all available values or just a subset of available values. Returns the location of updated resource.")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void makePartialUpdate(@RequestBody PostView view, @PathVariable Long id, HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, String.valueOf(view.getId()));
    }

    @ApiOperation(value = "Makes fully update of resource under identifier.", notes = "Since PUT is idempotent, you must send all possible values. Returns the location of updated resource.")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void addNewPost(@RequestBody PostView view, @PathVariable Long id, HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, TagResource.POSTS_ROOT + String.valueOf(postService.update(PostView.fromView(view))));
    }

    @ApiOperation(value = "Returns collection of posts")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Post> getPosts(@RequestParam(required = false) String filteredDate) throws ParseException {
        if (filteredDate == null) {
            return postService.getAllPosts();
        }
        return postService.filterPostByDate(getDateFromString(filteredDate));
    }

    //Todo  write integration test for this method

    // TODO handle the response statuses! http://stackoverflow.com/questions/2342579/http-status-code-for-update-and-delete
    @ApiOperation(value = "Deletes the specified post.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable BigInteger id) {
        postService.remove(id);
    }

    private Date getDateFromString(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat(Constants.DATE_PATTERN, Locale.ENGLISH);
        return format.parse(dateString);
    }
}

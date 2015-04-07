package org.pti.poster.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.pti.poster.model.Post;
import org.pti.poster.service.PostService;
import org.pti.poster.view.PostView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

@Api(value = "Post resource", position = 1)
@RequestMapping("/rest/posts")
@RestController
public class PostResource {
    private final static String DATE_PATTERN = "yyyy-MM-dd";

    @Autowired
    PostResource(PostService postService){
        this.postService = postService;
    }
    
    PostService postService;

    @ApiOperation("Gets post by identifier")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    PostView getPostById(@PathVariable Long id) {
        return PostView.fromPost(postService.getById(id));
    }

    @ApiOperation(value = "Creates brand new resource", notes = "Use POST to create resources when you do not know the resource identifier. Returns the location of created resource.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void updatePost(@RequestBody PostView view, HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, "/api/posts/" + String.valueOf(postService.addPost(PostView.fromView(view))));
    }

    @ApiOperation(value = "Makes partial update", notes = "You can use POST to send either all available values or just a subset of available values. Returns the location of updated resource.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updatePost(@RequestBody PostView view, @PathVariable Long id, HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, String.valueOf(view.getId()));
    }

    @ApiOperation(value = "Makes fully update of resource under identifier.", notes = "Since PUT is idempotent, you must send all possible values. Returns the location of updated resource.")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void addNewPost(@RequestBody PostView view, @PathVariable Long id, HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, "/api/posts/" + String.valueOf(postService.update(PostView.fromView(view))));
    }

    @ApiOperation(value = "Returns collection of posts")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Post> getPosts(@RequestParam(required = false) String filteredDate) throws ParseException {
        if (filteredDate == null) {
            return postService.getAllPosts();
        }
        return postService.filterPostByDate(getCalendarFromString(filteredDate));
    }

    // TODO handle the response statuses! http://stackoverflow.com/questions/2342579/http-status-code-for-update-and-delete
    @ApiOperation(value = "Deletes the specified post.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable Long id) {
        postService.remove(id);
    }

    private Calendar getCalendarFromString(String filteredDate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat(DATE_PATTERN).parse(filteredDate));
        return cal;
    }
}

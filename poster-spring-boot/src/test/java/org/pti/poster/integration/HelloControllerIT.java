package org.pti.poster.integration;

import com.jayway.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.pti.poster.model.Post;
import org.pti.poster.rest.view.PostView;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = Application.class)
public class HelloControllerIT extends BaseITest {

    @Parameterized.Parameter(value = 0)
    public /* NOT private */ Collection<String> tags;
    @Parameterized.Parameter(value = 1)
    public /* NOT private */ String postText;
    @Parameterized.Parameter(value = 2)
    public /* NOT private */ PostView view;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        final Collection<String> tagsNames = Arrays.asList("study tag", "university tag");
        HashSet<Post.Tag> tags = new HashSet<Post.Tag>() {{
            for (String tagName : tagsNames) {
                add(new Post.Tag(tagName));
            }
        }};

        String postText = "new post text";
        PostView view = new PostView(postText, tags, null);
        return Arrays.asList(new Object[][]{
                {tagsNames, postText, view}
        });
    }

    @Test
    public void newPostHaveBeenAdded() {
        // todo check why so much data id database !!!!!
        given().contentType(MediaType.APPLICATION_JSON_VALUE).body(view).when().post("rest/posts").then().statusCode(HttpStatus.CREATED.value());
        Post[] as = get("/rest/posts").as(Post[].class);
        Post post = getByPostText(as, postText);
        assertTestCase(post);
    }

    @Test
    public void tagToExistingPostHaveBeenAdded() {
        Response response = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(view).when().post("/rest/posts");
        String location = response.header(HttpHeaders.LOCATION);
        response.then().statusCode(HttpStatus.CREATED.value());

        Post.Tag tag = new Post.Tag("added tag");
        String newLocation = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(tag)
                .when().post("/rest/posts/" + getIdFromLocation(location) + "/tags/")
                .header(HttpHeaders.LOCATION);

        assertEquals(get("/rest/posts/" + getIdFromLocation(newLocation)).as(PostView.class).getTags().size(), tags.size() + 1);
    }

    private String getIdFromLocation(String location) {
        return location.substring(location.lastIndexOf("/") + 1);
    }

    private void assertTestCase(Post post) {
        assertNotNull(post);
        assertEquals(tags.size(), post.getPostTags().size());
        String tagText = post.getPostTags().iterator().next().getText();
        assertTrue(tags.contains(tagText));
    }

    private Post getByPostText(Post[] as, String text) {
        for (Post post : as) {
            if (post.getText().equals(text)) {
                return post;
            }
        }
        return null;
    }
}

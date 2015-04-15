package org.pti.poster.boot;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.pti.poster.model.Post;
import org.pti.poster.view.PostView;
import org.pti.poster.view.StringResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0"})
public class HelloControllerIT extends BaseITest {

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

    @Parameterized.Parameter(value = 0)
    public /* NOT private */ Collection<String> tags;
    @Parameterized.Parameter(value = 1)
    public /* NOT private */ String postText;
    @Parameterized.Parameter(value = 2)
    public /* NOT private */ PostView view;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUpRestAssured() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.authentication = form("user", "password", new FormAuthConfig("/login", "username", "password"));
    }

    @Test
    public void getHello() throws Exception {
        assertEquals(get("/hello").as(StringResponse.class), (new StringResponse("Greetings from Spring Boot!")));
    }

    @Test
    public void newPostHaveBeenAdded() {
        given().contentType(MediaType.APPLICATION_JSON_VALUE).body(view).when().post("rest/posts").then().statusCode(HttpStatus.CREATED.value());
        Post[] as = get("/rest/posts").as(Post[].class);
        Post post = getByPostText(as, postText);
        assertTestCase(post);
    }

    @Test
    public void tagToExistingPostHaveBeenAdded() {
        Response post1 = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(view).when().post("/rest/posts");
        String location  = post1.header(HttpHeaders.LOCATION);
        post1.then().statusCode(HttpStatus.CREATED.value());
        
        Post.Tag e = new Post.Tag("added tag");
        String newLocation = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(e)
                .when().post("/rest/posts/" + getIdFromLocation(location) + "/tags/")
                .header(HttpHeaders.LOCATION);

        assertEquals(get("/rest/posts/" + getIdFromLocation(newLocation)).as(PostView.class).getTags().size(), tags.size()+1);
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
        for (Post h : as) {
            if (h.getText().equals(text)) {
                return h;
            }
        }
        return null;
    }
}

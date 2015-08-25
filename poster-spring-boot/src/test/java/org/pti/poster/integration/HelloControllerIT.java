package org.pti.poster.integration;

import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.pti.poster.model.Post;
import org.pti.poster.model.Tag;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = Application.class)
@Slf4j
public class HelloControllerIT extends ATestIT {

    @Parameterized.Parameter(value = 0)
    public /* NOT private */ Collection<String> tags;
    @Parameterized.Parameter(value = 1)
    public /* NOT private */ String postText;
    @Parameterized.Parameter(value = 2)
    public /* NOT private */ Post view;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        final Collection<String> tagsNames = Arrays.asList("study tag", "university tag");
        
        Set<Tag> tags = new HashSet<Tag>() {{
            addAll(tagsNames.stream().map(Tag::new).collect(Collectors.toList()));
        }};
/*
        HashSet<Tag> tags = new HashSet<Tag>() {{
            for (String tagName : tagsNames) {
                add(new Tag(tagName));
            }
        }};
*/
        String postText = "new post text";
        Post view = new Post(postText, tags);
        return Arrays.asList(new Object[][]{
                {tagsNames, postText, view}
        });
    }

    @Test
    public void newPostHaveBeenAdded() {
        // check why so much data in database !!!!! do we use embedded mongo instance ? todo check
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

        Tag tag = new Tag("added tag");
        Response postResponse = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(tag)
                .when().post(location + "/tags/");
        
        assertEquals(postResponse.getStatusCode(), HttpStatus.CREATED.value());
        String newLocation = postResponse
                .header(HttpHeaders.LOCATION);

        assertNotNull(newLocation);
        assertEquals(get(newLocation).as(Post.class).getPostTags().size(), tags.size() + 1);
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

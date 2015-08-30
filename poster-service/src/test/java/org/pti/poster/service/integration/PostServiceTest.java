package org.pti.poster.service.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pti.poster.dao.basic.TestData;
import org.pti.poster.model.Person;
import org.pti.poster.model.Post;
import org.pti.poster.service.TestSprintServiceApplicationConfiguration;
import org.pti.poster.service.impl.PersonService;
import org.pti.poster.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestSprintServiceApplicationConfiguration.class})
@ActiveProfiles("test")
public class PostServiceTest {

    @Autowired private PersonService personService;
    @Autowired private PostService postService;

    @Test
    public void canAddPosts() {
        int count = 10;
        personService.save(TestData.mockedUsers(count));

        List<Person> all = personService.findAll();
        assertEquals(all.size(), count);

        Person person = all.get(3);

        int initialPostsNumber = postService.getAllPostsForUser(person).size();

        List<Post> posts = TestData.mockPosts();
        postService.addPost(person, posts);

        assertEquals(postService.getAllPostsForUser(person).size(), initialPostsNumber + posts.size());
    }
}
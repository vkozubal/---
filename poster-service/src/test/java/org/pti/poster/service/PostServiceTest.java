package org.pti.poster.service;


import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.pti.poster.dao.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {SpringServiceApplicationConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
public class PostServiceTest {

    @Autowired private IPostService postService;

    /*  autowire here the same instance of mock because of bean scope  {@see mockedPostRepository()}*/
    @Autowired private PostRepository repository;


    @Bean
    @Primary //used only for test purpose
    public PostRepository mockedPostRepository() {
        return mock(PostRepository.class);
    }
    
    /**
     * check that method is invoked with interval for the whole day.
     * probably useless test :)
     */
    @Test
    public void testFilterPostByDate() throws Exception {
        /*  for mocking autowired dependencies we can use https://bitbucket.org/kubek2k/springockito/wiki/Home
        or https://github.com/sgri/spring-reinject  */

        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Date> endDateCaptor = ArgumentCaptor.forClass(Date.class);

        postService.filterPostByDate(new Date());

        verify(repository).inRange(startDateCaptor.capture(), endDateCaptor.capture());

        DateTime start = new DateTime(startDateCaptor.getValue());
        DateTime end = new DateTime(endDateCaptor.getValue());

        assertEquals(start.getMillisOfDay(), 0);
        assertEquals(end.getMillisOfDay(), 0);
    }
}
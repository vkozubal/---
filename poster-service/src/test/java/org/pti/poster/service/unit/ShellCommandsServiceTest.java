package org.pti.poster.service.unit;


import org.joda.time.DateTime;
import org.mockito.ArgumentCaptor;
import org.pti.poster.dao.repository.PostRepository;
import org.pti.poster.service.interfaces.IShellCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

//@ContextConfiguration(classes = {SpringServiceApplicationConfiguration.class})
//@Configuration
//@RunWith(SpringJUnit4ClassRunner.class)
//Todo find out how to mock spring beans
public class ShellCommandsServiceTest {

    /**  autowire here the same instance of mock because of bean scope  {@link #mockedPostRepository()}**/
    
    @Autowired private PostRepository postRepository;
    
    @Autowired private IShellCommandsService shellCommandsService;


//    @Bean
    @Primary //used only for test purpose
    public PostRepository mockedPostRepository() {
        return mock(PostRepository.class);
    }
    
    /**
     * check that method is invoked with interval for the whole day.
     * probably useless test :)
     */
//    @Test
    public void testFilterPostByDate() throws Exception {
        /*  for mocking autowired dependencies we can use https://bitbucket.org/kubek2k/springockito/wiki/Home
        or https://github.com/sgri/spring-reinject  */

        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Date> endDateCaptor = ArgumentCaptor.forClass(Date.class);

        shellCommandsService.getPostsForASpecificDate(new Date());

        verify(postRepository).inRange(startDateCaptor.capture(), endDateCaptor.capture());

        DateTime start = new DateTime(startDateCaptor.getValue());
        DateTime end = new DateTime(endDateCaptor.getValue());

        assertEquals(start.getMillisOfDay(), 0);
        assertEquals(end.getMillisOfDay(), 0);
    }
}
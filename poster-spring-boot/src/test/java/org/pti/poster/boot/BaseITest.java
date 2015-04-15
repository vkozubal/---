package org.pti.poster.boot;

import org.junit.Before;
import org.springframework.test.context.TestContextManager;

public class BaseITest {
    
    @Before // instead of @RunWith(SpringJUnit4ClassRunner.class) so we can use another runner for e.g {@link }
    public void setUpContext() throws Exception {
        //this is where the magic happens, we actually do "by hand" what the spring runner would do for us,
        // read the JavaDoc for the class bellow to know exactly what it does, the method names are quite accurate though
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
}

package org.pti.poster.integration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.parsing.Parser;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestContextManager;

import static com.jayway.restassured.RestAssured.form;

@WebIntegrationTest({"server.port:0"})
public class BaseITest {

    @Value("${local.server.port}")
    protected int port;

    @Before // instead of @RunWith(SpringJUnit4ClassRunner.class) so we can use another runner for e.g {@link }
    public void setUpContext() throws Exception {
        //this is where the magic happens, we actually do "by hand" what the spring runner would do for us,
        // read the JavaDoc for the class bellow to know exactly what it does, the method names are quite accurate though
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
        setUpRestAssured();
    }

    /*  Rest Assured  configuration */
    private void setUpRestAssured() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.authentication = form("user", "password", new FormAuthConfig("/login", "username", "password"));
    }
}

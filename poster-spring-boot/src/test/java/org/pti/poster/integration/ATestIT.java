package org.pti.poster.integration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.parsing.Parser;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.junit.Before;
import org.pti.poster.dao.TestMongoConfiguration;
import org.pti.poster.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContextManager;

import static com.jayway.restassured.RestAssured.form;

@WebIntegrationTest({"server.port:0"})
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = {Application.class, TestMongoConfiguration.class})
public class ATestIT  {
    
    @Autowired private MongoOperations mongoOps;
    
    @Value("${local.server.port}")
    protected int port;
    
    @Before
    /** instead of @RunWith(SpringJUnit4ClassRunner.class) so we can use another runner for e.g {@link org.junit.runners.Parameterized} **/
    public void setUpContext() throws Exception {
        // this is where the magic happens, we actually do "by hand" what the spring runner would do for us,
        // read the JavaDoc for the class bellow to know exactly what it does, the method names are quite accurate though
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);

        // drop if collection already exists from previous test.
        if (mongoOps.collectionExists(Person.class)){
            mongoOps.dropCollection(Person.class);
        }
        // insert user credentials in db
        DBCollection collection = mongoOps.createCollection(Person.class);
        collection.insert((DBObject) JSON.parse("{\"name\" : \"BMh\", \"password\" : \"lUKNdpwNsiNq\", \"active\" : true, \"roles\" : [ \"ADMIN_ROLE\" ]}"));

        setUpRestAssured();
    }

    /*  Rest Assured  configuration */
    private void setUpRestAssured() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.authentication = form("BMh", "lUKNdpwNsiNq", new FormAuthConfig("/login", "username", "password"));
    }
}

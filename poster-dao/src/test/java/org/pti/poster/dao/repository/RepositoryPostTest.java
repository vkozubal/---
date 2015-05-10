package org.pti.poster.dao.repository;

import com.google.common.collect.Iterables;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pti.poster.dao.TestData;
import org.pti.poster.dao.config.MongoConfiguration;
import org.pti.poster.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.pti.poster.dao.Constants.DATABASE.POSTS_COLLECTION_NAME;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ContextConfiguration(classes = {MongoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryPostTest {

    @Autowired PostRepository postRepository;
    @Autowired MongoOperations mongoOps;

    @Before
    public void cleanDatabase() {
        if (mongoOps.collectionExists(Post.class)) {
            mongoOps.dropCollection(Post.class);
        }
    }

    @Test
    public void baseCrudOperationsTest() {
        List<Post> posts = TestData.mockPosts();
        
        postRepository.save(posts);
        assertTrue("There should be all entities",
                (long) posts.size() == postRepository.count());

        BigInteger id = postRepository.findByText("fourth post").getId();
        assertTrue("Should work search by id",
                postRepository.exists(id));
        postRepository.delete(id);

        assertTrue("One entity should be deleted",
                (long) posts.size() - 1 == postRepository.count());

        assertFalse("Should not exist",
                postRepository.exists(id));
    }
    
    @Test
    public void inRangePostCreationDateTest() throws IOException, ParseException {
        // we can't change the creation date of post in Post entity so we populate those data from json file directly in db
        insertDateToDbFromJsonFile("test-data.json");
        
        Iterable<Post> posts = postRepository.inRange(getDateFromString("2015-05-02"), getDateFromString("2015-05-04"));
        assertEquals(Iterables.size(posts), 3);
    }

    private Date getDateFromString(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(org.pti.poster.model.Constants.DATE_PATTERN, Locale.ENGLISH);
        return format.parse(date);
    }
    
    
    /* inserts data into the database from json file */
    private void insertDateToDbFromJsonFile(String name) throws IOException {
        DBCollection usersCollection = mongoOps.getCollection(POSTS_COLLECTION_NAME);
        InputStream asStream = getClass().getClassLoader().getResourceAsStream(name);
        String jsonString = IOUtils.toString(asStream);

        // convert JSON to DBObject directly
        BasicDBList dbUsers = (BasicDBList) JSON.parse(jsonString);
        usersCollection.insert(getAsList(dbUsers));
    }

    private ArrayList<BasicDBObject> getAsList(BasicDBList dbUsers) {
        ArrayList<BasicDBObject> dbObjects = new ArrayList<>();
        for (Object y : dbUsers.toArray()){
            dbObjects.add((BasicDBObject) y);
        }
        return dbObjects;
    }
}

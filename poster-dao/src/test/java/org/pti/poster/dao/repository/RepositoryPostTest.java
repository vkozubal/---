package org.pti.poster.dao.repository;

import com.google.common.collect.Iterables;
import org.junit.Test;
import org.pti.poster.dao.AbstractEmbeddedMongoDBTest;
import org.pti.poster.dao.basic.TestData;
import org.pti.poster.model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;
import static org.pti.poster.dao.Constants.DATABASE.POSTS_COLLECTION_NAME;
import static org.pti.poster.dao.basic.Util.getDateFromString;
import static org.pti.poster.dao.basic.Util.insertDateToDbFromJsonFile;

/// /@Transactional
//@TransactionConfiguration

public class  RepositoryPostTest extends AbstractEmbeddedMongoDBTest<Post> {

    @Autowired PostRepository postRepository;

    @Test
    public void baseCrudOperationsTest() {
        List<Post> posts = TestData.mockPosts();

        postRepository.save(posts);
        assertEquals("There should be all entities", (long) posts.size(), postRepository.count());

        BigInteger id = postRepository.findByText("fourth post").getId();
        assertTrue("Should work search by id", postRepository.exists(id));
        postRepository.delete(id);

        assertEquals("One entity should be deleted", (long) posts.size() - 1, postRepository.count());

        assertFalse("Should not exist", postRepository.exists(id));
    }

    @Test
    public void inRangePostCreationDateTest() throws IOException, ParseException {
        // we can't change the creation date of post in Post entity so we populate those data from json file directly in db
        insertDateToDbFromJsonFile(mongoOps, getResourceAsStream("test-data.json"), POSTS_COLLECTION_NAME);

        Iterable<Post> posts = postRepository.inRange(getDateFromString("2015-05-02"), getDateFromString("2015-05-04"));
        assertEquals(Iterables.size(posts), 3);
    }

    private InputStream getResourceAsStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}

package org.pti.poster.dao.repository;

import org.pti.poster.model.Post;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Date;

public interface PostRepository extends CrudRepository<Post, BigInteger> {

    Post findByText(String text);
    
    /**
     * Searches for the posts which were created within specified time period
     * @param start lower bound of range inclusive
     * @param end upper bound of range exclusive
     */
    @Query("{ creationDate : { $gte : ?0, $lt : ?1 }}")
    Iterable<Post> inRange(Date start, Date end);
}

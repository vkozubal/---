package org.pti.poster.service.interfaces;

import org.pti.poster.model.Post;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.List;

public interface IPostService {

    void addPost(UserDetails person, Iterable<Post> posts);

    /**
     * adds post to persistence
     *
     * @param post    the object to be added
     * @param details details of owner of the post
     * @return the id of the created post
     */
    BigInteger addPost(UserDetails details, Post post);

    List<Post> getAllPostsForUser(UserDetails details);

    void remove(UserDetails userDetails, final BigInteger id);

    Post getById(UserDetails userDetails, final BigInteger id);
}

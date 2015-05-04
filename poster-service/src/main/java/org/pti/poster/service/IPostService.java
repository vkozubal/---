package org.pti.poster.service;

import org.pti.poster.model.Post;

import java.math.BigInteger;
import java.util.Date;

public interface IPostService {

    void save(Post post);

    void save(Iterable<Post> posts);
        
    Iterable<Post> filterPostByDate(Date dateTime);

    BigInteger addPost(Post post);

    BigInteger update(Post post);

    Iterable<Post> getAllPosts();

    void remove(final BigInteger id);

    Post getById(final BigInteger id);
}

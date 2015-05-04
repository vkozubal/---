package org.pti.poster.service;


import org.joda.time.DateTime;
import org.pti.poster.dao.repository.PostRepository;
import org.pti.poster.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;

@Service
public class PostService implements IPostService {

    @Autowired private PostRepository postRepository;
    
    public void save(Post post){
        postRepository.save( post);
    }

    public void save(Iterable<Post> posts){
        postRepository.save(posts);
    }

    public Iterable<Post> filterPostByDate(Date dateTime) {
        DateTime now = new DateTime(dateTime);
        return postRepository.inRange(now.withTimeAtStartOfDay().toDate(),
                now.plusDays(1).withTimeAtStartOfDay().toDate());
    }

    /**
     * adds post to persistence
     *
     * @param post the object to be added
     * @return the id of the created post
     */
    public BigInteger addPost(Post post) {
        return postRepository.save(post).getId();
    }

    //  todo write test for update !!
    public BigInteger update(Post post) {
        return postRepository.save(post).getId();
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void remove(final BigInteger id) {
        postRepository.delete(id);
    }

    public Post getById(final BigInteger id) {
        return postRepository.findOne(id);
    }

    /**
     * todo review this method and write test if necessary
     * return  adds a tag to post with id {@param postId}
     *
     * @param postId id of {@link org.pti.poster.model.Post} to be updated
     * @param tag    {@link org.pti.poster.model.Post.Tag}
     * @return id of updated created post
     */

    @Deprecated
    public BigInteger addTag(BigInteger postId, Post.Tag tag) {
        getById(postId).getPostTags().add(tag);
        return postId;
    }
}

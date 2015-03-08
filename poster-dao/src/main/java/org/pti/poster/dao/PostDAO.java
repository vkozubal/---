package org.pti.poster.dao;

import java.util.List;

import org.pti.poster.model.Post;

public interface PostDAO {

    public List<Post> getPosts();

    public Post getPostById(String id);

    public Long deletePostById(Long id);

    public Long createPost(Post podcast);

    public int updatePost(Post podcast);

    public void deletePosts();
}

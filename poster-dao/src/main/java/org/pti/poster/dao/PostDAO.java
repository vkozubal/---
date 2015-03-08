package org.pti.poster.dao;

import org.pti.poster.model.Post;

public interface PostDAO {

    public Post getPostById(String id);

    public Long deletePostById(Long id);

    public Long createPost(Post post);

    public int updatePost(Post post);

    public void deletePosts();
}

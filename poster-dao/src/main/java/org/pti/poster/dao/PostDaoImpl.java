package org.pti.poster.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.pti.poster.model.Post;
import org.springframework.stereotype.Controller;

@Controller("postDAO")
public class PostDaoImpl implements PostDAO {

    private Map<String, Post> posts;
    
    @PostConstruct
    public void init()
    {
	posts=new HashMap<>();
	
	posts.put("1", new Post("Text1"));
	posts.put("2", new Post("Text2"));
	posts.put("3", new Post("Text3"));
	
    }

    @Override
    public Post getPostById(String id) {
	return posts.get(id);
    }

    @Override
    public Long deletePostById(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Long createPost(Post post) {
	posts.put(post.getId(), post);
	return null;
    }

    @Override
    public int updatePost(Post post) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void deletePosts() {
	// TODO Auto-generated method stub

    }

}

package org.pti.poster.repository.post;


import org.pti.poster.model.post.GenericPost;

import java.io.Serializable;
import java.util.List;

public interface PostRepository {

	public GenericPost getPostById(String id);

	public List<GenericPost> getLastPosts(int number);

	public GenericPost savePost(GenericPost post);
}

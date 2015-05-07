package org.pti.poster.service.post;

import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;

public interface PostService {

	GenericPostDto findPostById(String id);

	GenericPostCollectionDto getLastPosts(int number);

	GenericPostDto savePost(GenericPostDto newPostDto);

}

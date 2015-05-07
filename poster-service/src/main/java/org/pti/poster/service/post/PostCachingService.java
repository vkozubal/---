package org.pti.poster.service.post;

import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;

public interface PostCachingService {

	void cachePost(GenericPostDto newPostDto);

	GenericPostCollectionDto getCachedPosts();
}

package org.pti.poster.service.post;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.util.QueryResultSet;
import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.hazelcast.HazelcastConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("postCachingService")
public class PostCachingServiceImpl implements PostCachingService {

	@Autowired
	private HazelcastConfiguration hazelcastConfiguration;
	private HazelcastInstance hazelcastInstance;

	@PostConstruct
	public void init() {
		hazelcastInstance = hazelcastConfiguration.getHazelcastInstance();
	}

	@Override
	public void cachePost(GenericPostDto newPostDto) {
		setPostDtoRandomId(newPostDto);
		hazelcastInstance.getMap("posts").put(newPostDto.getId(), newPostDto);
	}

	@Override
	public GenericPostCollectionDto getCachedPosts() {
		return getGenericPostCollectionDtoFrom((QueryResultSet) hazelcastInstance.getMap("posts").values());
	}

	private void setPostDtoRandomId(GenericPostDto post) {
		post.setId(UUID.randomUUID().toString());
	}

	private GenericPostCollectionDto getGenericPostCollectionDtoFrom(QueryResultSet set) {
		List<GenericPostDto> posts = new ArrayList<>();
		for (Object obj : set.toArray()) {
			posts.add((GenericPostDto) obj);
		}
		return new GenericPostCollectionDto(posts);
	}
}

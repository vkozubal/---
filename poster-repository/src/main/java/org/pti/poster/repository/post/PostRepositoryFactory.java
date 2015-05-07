package org.pti.poster.repository.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("postRepositoryFactory")
public class PostRepositoryFactory {

	@Autowired
	private InMemoryPostRepository inMemoryRepo;

	public PostRepository getRepositoryOfType(PostRepositoryType type) {
		if (PostRepositoryType.IN_MEMORY.equals(type)) {
			return inMemoryRepo;
		}
		return null;
	}
}

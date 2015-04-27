package org.pti.poster.service.post;

import org.pti.poster.assembler.GenericPostAssembler;
import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.model.post.GenericPost;
import org.pti.poster.model.post.GenericPostType;
import org.pti.poster.repository.post.PostRepository;
import org.pti.poster.repository.post.PostRepositoryFactory;
import org.pti.poster.repository.post.PostRepositoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("postService")
public class PostService {

	@Autowired
	private PostRepositoryFactory postRepositoryFactory;
	private PostRepository postRepository;

	@PostConstruct
	public void init() {
		postRepository = postRepositoryFactory.getRepositoryOfType(PostRepositoryType.INMEMORY);
	}

	public GenericPostDto findPostById(String id) {
		GenericPost queryResult = postRepository.getPostById(id);
		return GenericPostAssembler.toDto(queryResult);
	}

	public GenericPostCollectionDto getLastPosts(int number) {
		List<GenericPost> queryResult = postRepository.getLastPosts(number);
		List<GenericPostDto> queryResultDto = GenericPostAssembler.toDto(queryResult);

		return new GenericPostCollectionDto(queryResultDto);
	}

	public GenericPostDto savePost(GenericPostDto postDto) {
		GenericPost post = GenericPostAssembler.fromDto(postDto);
		post.setType(GenericPostType.REGISTERED_POST);
		GenericPost queryResult = postRepository.savePost(post);
		return GenericPostAssembler.toDto(queryResult);
	}
}

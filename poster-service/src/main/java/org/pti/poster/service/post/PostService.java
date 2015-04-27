package org.pti.poster.service.post;

import org.pti.poster.assembler.GenericPostAssembler;
import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.model.post.GenericPost;
import org.pti.poster.model.post.GenericPostType;
import org.pti.poster.repository.post.PostRepository;
import org.pti.poster.repository.post.PostRepositoryFactory;
import org.pti.poster.repository.post.PostRepositoryType;
import org.pti.poster.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("postService")
public class PostService {

	@Autowired
	private PostRepositoryFactory postRepositoryFactory;
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

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

	public GenericPostDto savePost(GenericPostDto unsavedPostDto) {
		GenericPost post = GenericPostAssembler.fromDto(unsavedPostDto);

		if (isPostCanBeSaved(post)) {
			setPostRegistered(post);
			GenericPost queryResult = postRepository.savePost(post);
			return GenericPostAssembler.toDto(queryResult);
		} else {
			return unsavedPostDto;
		}
	}

	private void setPostRegistered(GenericPost post) {
		post.setType(GenericPostType.REGISTERED_POST);
	}

	private boolean postUserExists(GenericPost post) {
		return userService.findUserById(post.getUserId()) != null;
	}

	private boolean isPostCanBeSaved(GenericPost post) {
		if (postUserExists(post)) {
			return true;
		} else {
			return false;
		}
	}
}

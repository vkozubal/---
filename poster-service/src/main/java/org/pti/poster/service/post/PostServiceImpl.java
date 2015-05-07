package org.pti.poster.service.post;

import org.pti.poster.assembler.GenericPostAssembler;
import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.dto.post.UnregisteredPostDto;
import org.pti.poster.model.post.GenericPost;
import org.pti.poster.model.post.GenericPostType;
import org.pti.poster.repository.post.PostRepository;
import org.pti.poster.repository.post.PostRepositoryFactory;
import org.pti.poster.repository.post.PostRepositoryType;
import org.pti.poster.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepositoryFactory postRepositoryFactory;
	private PostRepository postRepository;

	@Autowired
	private UserServiceImpl userService;

	@PostConstruct
	public void init() {
		postRepository = postRepositoryFactory.getRepositoryOfType(PostRepositoryType.IN_MEMORY);
	}

	@Override
	public GenericPostDto findPostById(String id) {
		GenericPost queryResult = postRepository.getPostById(id);
		return GenericPostAssembler.toDto(queryResult);
	}

	@Override
	public GenericPostCollectionDto getLastPosts(int number) {
		List<GenericPost> queryResult = postRepository.getLastPosts(number);
		List<GenericPostDto> queryResultDto = GenericPostAssembler.toDto(queryResult);

		return new GenericPostCollectionDto(queryResultDto);
	}

	@Override
	public GenericPostDto savePost(GenericPostDto newPostDto) {
		GenericPost newPost = GenericPostAssembler.fromDto(newPostDto);

		if (isPostCanBeSaved(newPost)) {
			setPostRegistered(newPost);
			GenericPost registeredPost = postRepository.savePost(newPost);
			return GenericPostAssembler.toDto(registeredPost);
		} else {
			UnregisteredPostDto unregistredPost = new UnregisteredPostDto();
			GenericPostAssembler.copyFieldsFromTo(newPostDto, unregistredPost);
			unregistredPost.getErrorMessages().add("Post cannot be saved");
			return unregistredPost;
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

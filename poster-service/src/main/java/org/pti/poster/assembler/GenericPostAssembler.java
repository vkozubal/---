package org.pti.poster.assembler;

import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.dto.post.RegisteredPostDto;
import org.pti.poster.dto.post.UnregisteredPostDto;
import org.pti.poster.model.post.AbstractPost;
import org.pti.poster.model.post.GenericPost;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class GenericPostAssembler extends AbstractAssembler {

	public static GenericPost fromDto(GenericPostDto postDto) {
		return convertFromDto(postDto);
	}

	public static GenericPostDto toDto(GenericPost post) {
		GenericPostDto result = null;

		try {
			result = convertToDto(post);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static List<GenericPostDto> toDto(List<GenericPost> posts) {
		List<GenericPostDto> result = new ArrayList<>();

		for (GenericPost post : posts) {
			result.add(toDto(post));
		}

		return result;
	}

	public static void copyFieldsFromTo(AbstractPost from, AbstractPost to) {
		to.setType(from.getType());
		to.setUserId(from.getUserId());
		to.setId(from.getId());
		to.setText(from.getText());
		to.setDate(from.getDate());
	}

	private static GenericPostDto convertToDto(AbstractPost post) throws Exception {
		String className;

		switch (post.getType()) {
			case UNREGISTERED_POST:
				className = UnregisteredPostDto.class.getName();
				break;
			case REGISTERED_POST:
				className = RegisteredPostDto.class.getName();
				break;
			default:
				className = "";
				break;
		}

		GenericPostDto postDto = (GenericPostDto) getNewInstanceFor(className);
		copyFieldsFromTo(post, postDto);

		return postDto;
	}

	private static GenericPost convertFromDto(GenericPostDto postDto) {
		GenericPost post = new GenericPost();
		copyFieldsFromTo(postDto, post);

		return post;
	}

}

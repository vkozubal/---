package org.pti.poster.dto.post;

import org.pti.poster.model.post.GenericPostType;

public class RegisteredPostDto extends GenericPostDto {

	public RegisteredPostDto(String id, String date, String userId, String text) {
		super(GenericPostType.REGISTERED_POST, id, date, userId, text);
	}
}

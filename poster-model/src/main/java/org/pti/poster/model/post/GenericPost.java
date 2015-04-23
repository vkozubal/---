package org.pti.poster.model.post;

import org.pti.poster.model.AbstractPost;

public class GenericPost extends AbstractPost {

	public GenericPost(GenericPostType type, String id, String date, String userId, String text) {
		super(type, id, date, userId, text);
	}
}

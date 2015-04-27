package org.pti.poster.dto.post;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.AbstractPost;
import org.pti.poster.model.post.GenericPostType;

@JsonFilter("postFilter")
public abstract class GenericPostDto extends AbstractPost {

	@JsonIgnore
	@Override
	public GenericPostType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "#" + id + ": " + text;
	}

}

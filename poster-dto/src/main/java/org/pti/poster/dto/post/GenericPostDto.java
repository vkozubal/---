package org.pti.poster.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.pti.poster.dto.AbstractDtoEntity;
import org.pti.poster.model.post.AbstractPost;
import org.pti.poster.model.post.GenericPostType;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericPostDto extends AbstractPost implements AbstractDtoEntity {

	@Getter
	protected List<String> errorMessages;

	public GenericPostDto() {
		errorMessages = new ArrayList<>();
	}

	@JsonIgnore
	@Override
	public GenericPostType getType() {
		return type;
	}

}

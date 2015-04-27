package org.pti.poster.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.post.GenericPostType;

import java.util.List;

public class NewPostDto extends GenericPostDto {

	public NewPostDto() {
		type = GenericPostType.NEW_POST;
		date = "";
		id = "";
	}

	@JsonIgnore
	@Override
	public String getId() {
		return super.getId();
	}

	@JsonIgnore
	@Override
	public String getDate() {
		return super.getDate();
	}

	@JsonIgnore
	@Override
	public List<String> getErrorMessages() {
		return super.getErrorMessages();
	}
}

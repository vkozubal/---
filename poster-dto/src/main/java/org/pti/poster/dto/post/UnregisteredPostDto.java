package org.pti.poster.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.post.GenericPostType;

public class UnregisteredPostDto extends GenericPostDto {

	public UnregisteredPostDto() {
		type = GenericPostType.UNREGISTERED_POST;
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

}

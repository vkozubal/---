package org.pti.poster.model;

import lombok.Getter;
import lombok.Setter;
import org.pti.poster.model.post.GenericPostType;

@Getter
@Setter
public abstract class AbstractPost {

	public final static String DATE_FORMAT = "dd.MM.yyyy";

	protected GenericPostType type;

	protected String id;

	protected String date;

	protected String text;

	protected String userId;

	public AbstractPost() {
	}

	public AbstractPost(GenericPostType type, String id, String date, String userId, String text) {
		this.type = type;
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractPost that = (AbstractPost) o;

		if (!id.equals(that.id)) return false;
		if (!date.equals(that.date)) return false;
		return userId.equals(that.userId);

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + date.hashCode();
		result = 31 * result + userId.hashCode();
		return result;
	}
}

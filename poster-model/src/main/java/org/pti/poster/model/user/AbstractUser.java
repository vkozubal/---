package org.pti.poster.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractUser {

	protected GenericUserType type;

	protected String userId;

	protected String userNickName;

	protected String userName;

	public AbstractUser() {
	}

	public AbstractUser(GenericUserType type, String userId, String userNickName, String userName) {
		this.type = type;
		this.userId = userId;
		this.userNickName = userNickName;
		this.userName = userName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractUser that = (AbstractUser) o;

		if (!userId.equals(that.userId)) return false;
		return userNickName.equals(that.userNickName);

	}

	@Override
	public int hashCode() {
		int result = userId.hashCode();
		result = 31 * result + userNickName.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "#" + userId + " | " + userNickName;
	}
}

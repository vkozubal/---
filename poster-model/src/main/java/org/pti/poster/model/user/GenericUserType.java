package org.pti.poster.model.user;


public enum GenericUserType {
	COMMON_USER;

	private String userName;

	private GenericUserType() {
		this.userName = name().replaceAll("_", ".").toLowerCase();
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return userName;
	}
}

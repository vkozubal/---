package org.pti.poster.model.user;


public enum GenericUserType {
	REGISTERED_USER, UNREGISTERED_USER;

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

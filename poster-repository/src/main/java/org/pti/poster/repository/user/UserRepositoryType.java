package org.pti.poster.repository.user;

public enum UserRepositoryType {
	INMEMORY;

	private String typeName;

	private UserRepositoryType() {
		this.typeName = name().replaceAll("_", ".").toLowerCase();
	}

	public String getTypeName() {
		return typeName;
	}

	@Override
	public String toString() {
		return typeName;
	}
}

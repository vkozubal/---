package org.pti.poster.model.post;


public enum GenericPostType {
	NEW_POST, UNREGISTERED_POST, REGISTERED_POST;

	private String typeName;

	private GenericPostType() {
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

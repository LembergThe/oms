package com.softserveinc.edu.oms.web.itemManagement.util;

public enum ProductSelectField {
	PRODUCT_NAME("Product Name"), DESCRIPTION("Description");

	private final String displayName;

	private ProductSelectField(final String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}

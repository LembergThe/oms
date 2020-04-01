package com.softserveinc.edu.oms.web.itemManagement.util;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

public class ProductSortPropertiesFactory {
	private ProductSortPropertiesFactory() {
	}

	private static final String PRODUCT_NAME_PARAM_NAME = "productName";
	private static final String DESCRIPTION_PARAM_NAME = "productDescription";
	private static final String PRICE_PARAM_NAME = "productPrice";

	public static SortProperties createSortPropertiesForProduct(
			final String propertyName) {
		if (propertyName.equalsIgnoreCase(PRODUCT_NAME_PARAM_NAME)) {
			return createSortPropertiesForProductName();

		} else if (propertyName.equalsIgnoreCase(DESCRIPTION_PARAM_NAME)) {
			return createSortPropertiesForDescription();

		} else if (propertyName.equalsIgnoreCase(PRICE_PARAM_NAME)) {
			return createSortPropertiesForPrice();

		} else {
			return new SortProperties();
		}
	}

	public static SortProperties createSortPropertiesForProductName() {
		return new SortProperties(PRODUCT_NAME_PARAM_NAME);
	}

	public static SortProperties createSortPropertiesForDescription() {
		return new SortProperties(DESCRIPTION_PARAM_NAME);
	}

	public static SortProperties createSortPropertiesForPrice() {
		return new SortProperties(PRICE_PARAM_NAME);
	}
}

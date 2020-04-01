package com.softserveinc.edu.oms.web.itemManagement.util;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.web.util.SearchModel;

public class ProductSortResolver {
	private final static ProductSortResolver INSTANCE = new ProductSortResolver();

	private ProductSortResolver() {
	}

	public static ProductSortResolver getInstance() {
		return INSTANCE;
	}

	public SearchModel handleSort(final SearchModel searchModel,
			final String propertyName) {
		if (propertyName != null && !propertyName.equalsIgnoreCase("")) {

			SortProperties availableProperties = searchModel
					.getSortProperties();

			if (availableProperties != null
					&& availableProperties.containsPropertyName(propertyName)) {

				if (availableProperties.isSortAscending(propertyName)) {
					availableProperties.setSortDescending(propertyName);
				} else {
					availableProperties.setSortAscending(propertyName);
				}

				searchModel.setSortProperties(availableProperties);
			} else {
				SortProperties properties = ProductSortPropertiesFactory
						.createSortPropertiesForProduct(propertyName);

				searchModel.setSortProperties(properties);
			}
		}

		return searchModel;
	}
}

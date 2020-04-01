package com.softserveinc.edu.oms.web.user.util;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSortPropertiesFactory;
import com.softserveinc.edu.oms.web.util.SearchModel;

public class UserSortResolver {
	private final static UserSortResolver INSTANCE = new UserSortResolver();

	private UserSortResolver() {
	}

	public static UserSortResolver getInstance() {
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
				SortProperties properties = UserSortPropertiesFactory
						.createSortPropertiesForUser(propertyName);

				searchModel.setSortProperties(properties);
			}
		}

		return searchModel;
	}
}

//
// ProductSortModel
//
// 10 11
//
package com.softserveinc.edu.oms.web.product.model;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

/**
 * @author Ivanka
 * 
 */
public class ProductSortModel {

	private String propertyName;
	private Boolean ascending;

	public ProductSortModel(final String sortProperty, final Boolean ascending) {
		super();
		propertyName = sortProperty;
		this.ascending = ascending;
	}

	public SortProperties createSortProperties() {
		SortProperties properties = new SortProperties();

		if (propertyName.equals(ListProductModel.ITEM_NAME)) {
			properties.addPropertyForSort(ListProductModel.ITEM_NAME_VALUE,
					ascending);
		} else if (propertyName.equals(ListProductModel.ITEM_DESCRIPTION)) {
			properties.addPropertyForSort(
					ListProductModel.ITEM_DESCRIPTION_VALUE, ascending);
		} else {
			properties.addPropertyForSort(ListProductModel.ITEM_NAME_VALUE,
					ascending);
		}

		return properties;
	}
}

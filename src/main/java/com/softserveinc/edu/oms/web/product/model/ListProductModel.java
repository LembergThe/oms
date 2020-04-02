//
// ListProductModel
//
// 10 . 2011
//
package com.softserveinc.edu.oms.web.product.model;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.edu.oms.persistence.dao.params.DimensionTypesEnum;

/**
 * @author Ivanka
 * 
 */
public class ListProductModel {
	public static final String ITEM_NAME_VALUE = "productName";
	public static final String ITEM_DESCRIPTION_VALUE = "productDescription";
	public static final String ITEM_NAME = "Item Name";
	public static final String ITEM_DESCRIPTION = "Item Description";

	private static final String DEFAULT_QUANTITY = "1";
	private static final String DEFAULT_DIMENSION = DimensionTypesEnum.ITEM
			.getDimensionTypeName();

	public static final List<String> DIMENSIONS = new ArrayList<String>();

	static {
		DIMENSIONS.add(DimensionTypesEnum.ITEM.getDimensionTypeName());
		DIMENSIONS.add(DimensionTypesEnum.BOX.getDimensionTypeName());
		DIMENSIONS.add(DimensionTypesEnum.PACKAGE.getDimensionTypeName());

	}

	private final static Boolean isIntegerParameterValid(final String parameter) {
		if (parameter == null || parameter.equals("")) {
			return false;
		}
		try {
			Integer.parseInt(parameter);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private String sortPropertyName;
	private Boolean ascending;

	private String searchValue;
	private String searchProperty;

	private Integer orderId;
	private Integer orderItemId;

	private Integer productId;

	private String quantity;
	private String dimension;

	public final String getSortPropertyName() {
		return sortPropertyName;
	}

	public final void setSortPropertyName(final String sortProperty) {
		sortPropertyName = sortProperty;
	}

	public final String getQuantity() {
		return quantity;
	}

	public final void setQuantity(final String quantity) {
		this.quantity = quantity;
	}

	public final String getDimension() {
		return dimension;
	}

	public final void setDimension(final String dimension) {
		this.dimension = dimension;
	}

	public final Boolean getAscending() {
		return ascending;
	}

	public final void setAscending(final Boolean ascending) {
		this.ascending = ascending;
	}

	public final String getSearchValue() {
		return searchValue;
	}

	public final void setSearchValue(final String searchValue) {
		this.searchValue = searchValue;
	}

	public final String getSearchProperty() {
		return searchProperty;
	}

	public final void setSearchProperty(final String searchProperty) {
		this.searchProperty = searchProperty;
	}

	public final Integer getOrderId() {
		return orderId;
	}

	public final void setOrderId(final Integer orderId) {
		this.orderId = orderId;
	}

	public final Integer getOrderItemId() {
		return orderItemId;
	}

	public final void setOrderItemId(final Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public final Integer getProductId() {
		return productId;
	}

	public final void setProductId(final Integer productId) {
		this.productId = productId;
	}

	public final Boolean isQuantityValid() {
		return ListProductModel.isIntegerParameterValid(quantity);
	}

	@Override
	public final String toString() {
		return "ListProductModel [sortPropertyName=" + sortPropertyName
				+ ", ascending=" + ascending + ", searchValue=" + searchValue
				+ ", searchProperty=" + searchProperty + ", orderId=" + orderId
				+ ", orderItemId=" + orderItemId + ", productId=" + productId
				+ ", quantity=" + quantity + ", dimension=" + dimension + "]";
	}

	public final Boolean isEmpty() {
		return sortPropertyName == null || ascending == null
				|| searchProperty == null;
	}

	public final ProductSortModel getSortProperties() {
		return new ProductSortModel(sortPropertyName, ascending);

	}

	/**
	 * 
	 */
	public final void setDefaultValues() {
		setSortPropertyName(ITEM_NAME);
		setAscending(true);

		setSearchProperty(ITEM_NAME);
		setSearchValue("");

		quantity = DEFAULT_QUANTITY;
		dimension = DEFAULT_DIMENSION;
	}

	public final void changeSortPropertiesName(final String newSortPropertyName) {
		if (newSortPropertyName.equals(ITEM_NAME)
				|| newSortPropertyName.equals(ITEM_DESCRIPTION)) {

			if (newSortPropertyName.equals(sortPropertyName)) {
				setSearchProperty(newSortPropertyName);
				ascending = !ascending;

			} else {
				setSortPropertyName(newSortPropertyName);
				setAscending(true);
			}
		}
	}

	/**
	 * 
	 */
	public final void reset() {
		productId = null;
		quantity = DEFAULT_QUANTITY;
		dimension = DEFAULT_DIMENSION;
	}
}

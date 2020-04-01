/**
 * 
 */
package com.softserveinc.edu.oms.web.order;

import java.util.HashMap;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

/**
 * @author marko
 * 
 */
public class OrderSortProperties extends SortProperties {
	private static final String ORDERNAME = "orderName";
	private static final String TOTAL_PRICE = "totalPrice";
	private static final String MAX_DISCOUNT = "maxDiscount";
	private static final String DELIVERY_DATE = "deliveryDate";
	private static final String STATUS = "orderStatus";
	private static final String ASSIGNEE = "assigne";
	private static final String ROLE = "assigne.role";

	public enum SortPropertiesValues {
		ORDER_NAME, ORDER_TOTAL_PRICE, ORDER_MAX_DISCOUNT, ORDER_DELIVERY_DATE, ORDER_STATUS, ORDER_ASSIGNEE, ORDER_ROLE
	}

	public void setSortOption(SortPropertiesValues value) {
		switch (value) {
		case ORDER_ASSIGNEE:

			if (this.properties.containsKey(ASSIGNEE)) {
				boolean val = this.properties.get(ASSIGNEE);
				this.properties.remove(ASSIGNEE);
				this.properties.put(ASSIGNEE, !val);
			} else {
				this.properties = new HashMap<String, Boolean>();
				this.setSortDescending(ASSIGNEE);
			}
			break;
		case ORDER_NAME:

			if (this.properties.containsKey(ORDERNAME)) {
				boolean val = this.properties.get(ORDERNAME);
				this.properties.remove(ORDERNAME);
				this.properties.put(ORDERNAME, !val);
			} else {
				this.properties = new HashMap<String, Boolean>();
				this.setSortDescending(ORDERNAME);
			}
			break;
		case ORDER_TOTAL_PRICE:

			if (this.properties.containsKey(TOTAL_PRICE)) {
				boolean val = this.properties.get(TOTAL_PRICE);
				this.properties.remove(TOTAL_PRICE);
				this.properties.put(TOTAL_PRICE, !val);
			} else {
				this.properties = new HashMap<String, Boolean>();
				this.setSortDescending(TOTAL_PRICE);
			}
			break;
		case ORDER_MAX_DISCOUNT:

			if (this.properties.containsKey(MAX_DISCOUNT)) {
				boolean val = this.properties.get(MAX_DISCOUNT);
				this.properties.remove(MAX_DISCOUNT);
				this.properties.put(MAX_DISCOUNT, !val);
			} else {
				this.properties = new HashMap<String, Boolean>();
				this.setSortDescending(MAX_DISCOUNT);
			}
			break;
		case ORDER_DELIVERY_DATE:

			if (this.properties.containsKey(DELIVERY_DATE)) {
				boolean val = this.properties.get(DELIVERY_DATE);
				this.properties.remove(DELIVERY_DATE);
				this.properties.put(DELIVERY_DATE, !val);
			} else {
				this.properties = new HashMap<String, Boolean>();
				this.setSortDescending(DELIVERY_DATE);
			}
			break;
		case ORDER_STATUS:

			if (this.properties.containsKey(STATUS)) {
				boolean val = this.properties.get(STATUS);
				this.properties.remove(STATUS);
				this.properties.put(STATUS, !val);
			} else {
				this.properties = new HashMap<String, Boolean>();
				this.setSortAscending(STATUS);
			}
			break;

		case ORDER_ROLE:

			if (this.properties.containsKey(ROLE)) {
				boolean val = this.properties.get(ROLE);
				this.properties.remove(ROLE);
				this.properties.put(ROLE, !val);
			} else {
				this.properties = new HashMap<String, Boolean>();
				this.setSortDescending(ROLE);
			}
			break;

		default:
			// sort by id
			this.properties = new HashMap<String, Boolean>();

			break;
		}
	}

}

package com.softserveinc.edu.oms.persistence.dao.params;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.softserveinc.edu.oms.persistence.dao.IDao;

/**
 * {@link SortProperties} holds properties for sorting, used in {@link IDao}.
 * 
 * @author Orest
 */
public class SortProperties {
	protected Map<String, Boolean> properties = new HashMap<String, Boolean>();

	public SortProperties() {
	}

	/**
	 * Creates {@link SortProperties} and adds one property statement.
	 * 
	 * Sort will be ascending by this property name.
	 * 
	 * @param propertyName
	 *            type property name which defines the order.
	 */
	public SortProperties(final String propertyName) {
		properties.put(propertyName, true);
	}

	/**
	 * Creates {@link SortProperties} and adds one property statement.
	 * 
	 * @param propertyName
	 *            type property name which defines the order.
	 * @param ascending
	 *            defines if order is acsending.
	 */
	public SortProperties(final String propertyName, final boolean ascending) {
		properties.put(propertyName, ascending);
	}

	/**
	 * Adds one property statement.
	 * 
	 * @param propertyName
	 *            type property name which defines the order.
	 * @param ascending
	 *            defines if order is acsending.
	 */
	public void addPropertyForSort(final String propertyName,
			final boolean ascending) {
		properties.put(propertyName, ascending);
	}

	/**
	 * @return {@link Set} of all property names used in sorting.
	 */
	public Set<String> getPropertyNames() {
		return properties.keySet();
	}

	/**
	 * Defines if sorting contains propertyName.
	 * 
	 * @param propertyName
	 *            property name to check
	 * @return true if sorting contains propertyName, false otherwise.
	 */
	public boolean containsPropertyName(final String propertyName) {
		return properties.containsKey(propertyName);
	}

	/**
	 * Defines if sorting for propertyName is acsending.
	 * 
	 * @param propertyName
	 *            property name to check
	 * @return true if sorting for propertyName is acsending, false otherwise.
	 */
	public boolean isSortAscending(final String propertyName) {
		if (!properties.containsKey(propertyName)) {
			return false;
		}
		return properties.get(propertyName);
	}

	/**
	 * Sets ascending sorting.
	 * 
	 * @param propertyName
	 *            property name to sort.
	 */
	public void setSortAscending(final String propertyName) {
		properties.put(propertyName, true);
	}

	/**
	 * Sets descending sorting.
	 * 
	 * @param propertyName
	 *            property name to sort.
	 */
	public void setSortDescending(final String propertyName) {
		properties.put(propertyName, false);
	}
}

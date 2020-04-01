//
// OrderItemsLinesNumber
//
// 23 ρεπο. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivanka
 * 
 */
public class OrderItemsLinesNumberModel {

	private final List<Integer> rowsNumbers = new ArrayList<Integer>();
	private Integer selectedValue;

	public OrderItemsLinesNumberModel() {
		rowsNumbers.add(Integer.valueOf(10));
		rowsNumbers.add(Integer.valueOf(25));

		selectedValue = rowsNumbers.get(0);
	}

	/**
	 * return number or rows that should be displayed
	 * 
	 * @return Integer value of rows
	 */
	public final Integer getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param newSelect
	 */
	public final void select(final int newSelect) {
		if (rowsNumbers.contains(newSelect)) {
			selectedValue = newSelect;
		}
	}

	public final Boolean isSelected(final Integer value) {
		return selectedValue.equals(value);
	}

	public final List<Integer> getRowsNumbers() {
		return rowsNumbers;
	}

}

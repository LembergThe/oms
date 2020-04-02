//
// OrderItemsLinesNumberModel
//
// 9 . 2011
//
package com.softserveinc.edu.oms.web.orderitem.model;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Ivanka
 * 
 */
public class OrderItemsLinesNumberModelTest extends TestCase {
	private OrderItemsLinesNumberModel linesNumberModel;

	private Integer twoLines = 2;
	private Integer fiveLines = 5;

	@Test
	public void testSelect() {
		fillData();

		linesNumberModel.select(twoLines);
		assertTrue(linesNumberModel.isSelected(twoLines));

		linesNumberModel.select(twoLines + fiveLines);
		assertFalse(linesNumberModel.getSelectedValue().equals(
				twoLines + fiveLines));
	}

	private final void fillData() {
		linesNumberModel = new OrderItemsLinesNumberModel();

		linesNumberModel.getRowsNumbers().clear();

		linesNumberModel.getRowsNumbers().add(twoLines);
		linesNumberModel.getRowsNumbers().add(fiveLines);

	}
}

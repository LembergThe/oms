//
// TempOrderItemTest
//
// 12 . 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Ivanka
 * 
 */
public class TempOrderItemTest extends TestCase {

	@Test
	public void testEquals() {
		TemporaryOrderItem orderItem = new TemporaryOrderItem(1, null);
		assertTrue(orderItem.equals(orderItem));
		assertFalse(orderItem.equals(null));
		assertTrue(orderItem.equals(1));

		assertFalse(orderItem.equals(""));

		TemporaryOrderItem orderItem2 = new TemporaryOrderItem(1, null);
		assertTrue(orderItem.equals(orderItem2));

		orderItem2 = new TemporaryOrderItem(null, null);
		assertFalse(orderItem.equals(orderItem2));

		orderItem2 = new TemporaryOrderItem(2, null);
		assertFalse(orderItem.equals(orderItem2));

		orderItem = new TemporaryOrderItem(null, null);
		assertFalse(orderItem.equals(orderItem2));

	}
}

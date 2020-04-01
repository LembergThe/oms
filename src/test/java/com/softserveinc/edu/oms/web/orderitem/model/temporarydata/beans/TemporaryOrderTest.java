//
// TemporaryOrderTest
//
// 16 вер. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Ivanka
 * 
 */
public class TemporaryOrderTest extends TestCase {

	@Test
	public void testEquals() {
		Integer tempOrderId = 1;
		TemporaryOrder tempOrder = new TemporaryOrder(tempOrderId, null);
		assertTrue(tempOrder.equals(tempOrder));
		assertFalse(tempOrder.equals(null));

		assertTrue(tempOrder.equals(tempOrderId));
		assertFalse(tempOrder.equals(""));

		tempOrder.setId(null);

		TemporaryOrder tempOrder2 = new TemporaryOrder(null, null);
		assertTrue(tempOrder.equals(tempOrder2));

		tempOrder2.setId(tempOrderId);
		assertFalse(tempOrder.equals(tempOrder2));

		tempOrder.setId(tempOrderId);
		assertTrue(tempOrder.equals(tempOrder2));

		tempOrder2.setId(tempOrderId + 1);
		assertFalse(tempOrder.equals(tempOrder2));
	}
}

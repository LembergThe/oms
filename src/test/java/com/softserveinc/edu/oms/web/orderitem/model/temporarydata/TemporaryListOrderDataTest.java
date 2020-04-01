//
// TemporaryListOrderDataTest
//
// 16 вер. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata;

import org.junit.Test;

/**
 * @author Ivanka
 * 
 */
public class TemporaryListOrderDataTest extends TemporaryDataTest {

	@Test
	public void testAddExistingOrderData() {
		this.fillData();
		TemporaryListOrderData listOrderData = new TemporaryListOrderData();

		ITemporaryOrderData orderData1 = listOrderData.addOrderData(order,
				orderItems);
		assertNotNull(listOrderData.getTemporaryOrderData(orderData1
				.getTempOrder().getId()));

		ITemporaryOrderData orderData2 = listOrderData.addOrderData(order,
				orderItems);
		assertNotNull(listOrderData.getTemporaryOrderData(orderData2
				.getTempOrder().getId()));

		assertTrue(orderData1.getTempOrder().getId()
				.equals(orderData2.getTempOrder().getId()));

		order.setId(2);
		ITemporaryOrderData orderData3 = listOrderData.addOrderData(order,
				orderItems);
		assertNotNull(listOrderData.getTemporaryOrderData(order.getId()));
		assertFalse(orderData2.getTempOrder().getId()
				.equals(orderData3.getTempOrder().getId()));
	}

	@Test
	public void testAddOrderData() {
		this.fillData();
		TemporaryListOrderData listOrderData = new TemporaryListOrderData();
		ITemporaryOrderData orderData1 = listOrderData.addOrderData();
		assertNotNull(listOrderData.getTemporaryOrderData(orderData1
				.getTempOrder().getId()));

		ITemporaryOrderData orderData2 = listOrderData.addOrderData();
		assertNotNull(listOrderData.getTemporaryOrderData(orderData2
				.getTempOrder().getId()));

		assertFalse(orderData1.getTempOrder().getId()
				.equals(orderData2.getTempOrder().getId()));

	}

	@Test
	public void testRemoveOrderData() {
		this.fillData();
		TemporaryListOrderData listOrderData = new TemporaryListOrderData();
		ITemporaryOrderData orderData = listOrderData.addOrderData();

		listOrderData.removeOrderData(orderData.getTempOrder().getId());

		assertNull(listOrderData.getTemporaryOrderData(orderData.getTempOrder()
				.getId()));
	}
}

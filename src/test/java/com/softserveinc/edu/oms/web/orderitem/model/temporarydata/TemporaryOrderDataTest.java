//
// TemporaryOrderDataTest
//
// 12 . 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata;

import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans.TemporaryOrder;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans.TemporaryOrderItem;

/**
 * @author Ivanka
 * 
 */
public class TemporaryOrderDataTest extends TemporaryDataTest {

	@Test
	public void testConstructorWithIntegerParameter() {
		ITemporaryOrderData orderData = new TemporaryOrderData(tempOrderId);

		TemporaryOrder tempOrder = new TemporaryOrder(tempOrderId, new Order());
		assertEquals(tempOrder, orderData.getTempOrder());
	}

	@Test
	public void testConstructor() {
		this.fillData();
		ITemporaryOrderData orderData = new TemporaryOrderData(tempOrderId,
				order, orderItems);

		Double orderCost = 0.;
		for (TemporaryOrderItem tempOrderItem : orderData.getOrderItems()) {
			Double cost = tempOrderItem.getOrderItem().getItemPrice()
					* tempOrderItem.getOrderItem().getQuantity()
					* tempOrderItem.getOrderItem().getDimension()
							.getNumberOfProduct();

			orderCost += cost;
			assertEquals(tempOrderItem.getOrderItem().getCost(), cost);
		}
		assertEquals(orderData.getOrder().getTotalPrice(), orderCost);
	}

	@Test
	public void testInsert() {
		this.fillData();
		ITemporaryOrderData orderData = new TemporaryOrderData(tempOrderId,
				order, orderItems);

		Double orderOldCost = orderData.getOrder().getTotalPrice();
		Integer oldNumberItems = orderData.getNumberOfItems();

		OrderItem orderItem = createOrderItem();

		Integer oldSize = orderData.getOrderItems().size();
		Integer orderItemNumber = numberOfItems(orderItem);

		TemporaryOrderItem tempOrderItem = new TemporaryOrderItem(null,
				orderItem);

		orderData.insertOrUpdate(tempOrderItem);

		assertEquals(orderOldCost + orderItem.getCost(), orderData.getOrder()
				.getTotalPrice());
		assertTrue(orderData.getNumberOfItems().equals(
				oldNumberItems + orderItemNumber));

		assertFalse(orderData.getIsSaved());
		assertEquals(oldSize + 1, orderData.getOrderItems().size());
	}

	@Test
	public void testUpdate() {
		this.fillData();
		ITemporaryOrderData orderData = new TemporaryOrderData(tempOrderId,
				order, orderItems);

		TemporaryOrderItem oldTempOrderItem = orderData.getOrderItems().get(0);

		Integer oldSize = orderData.getOrderItems().size();
		Double totalPrice = orderData.getOrder().getTotalPrice()
				- oldTempOrderItem.getOrderItem().getCost();
		Integer numberOfItems = orderData.getNumberOfItems()
				- numberOfItems(oldTempOrderItem.getOrderItem());

		TemporaryOrderItem newOrderItem = new TemporaryOrderItem(
				oldTempOrderItem.getTemporaryId(), createOrderItem());
		newOrderItem.getOrderItem().setItemPrice(
				newOrderItem.getOrderItem().getItemPrice() + 1);

		orderData.insertOrUpdate(newOrderItem);

		assertTrue(oldSize.equals(orderData.getOrderItems().size()));
		assertEquals(totalPrice + newOrderItem.getOrderItem().getCost(),
				orderData.getOrder().getTotalPrice());

		assertTrue(orderData.getNumberOfItems().equals(
				numberOfItems + numberOfItems(newOrderItem.getOrderItem())));
	}

	@Test
	public void testGetOrderItems() {
		this.fillData();
		ITemporaryOrderData orderData = new TemporaryOrderData(tempOrderId,
				order, orderItems);

		assertEquals(orderData.getOrderItems(0, 10).size(), orderData
				.getOrderItems().size());
		assertEquals(orderData.getOrderItems(-1, 10).size(), 2);
		assertEquals(orderData.getOrderItems(1, 10).size(), 1);
		assertEquals(orderData.getOrderItems(5, 10).size(), 0);
	}

	@Test
	public void testRemove() {
		this.fillData();
		ITemporaryOrderData orderData = new TemporaryOrderData(tempOrderId,
				order, orderItems);

		Integer oldNumberItems = orderData.getNumberOfItems();
		Double oldTotalPrice = orderData.getOrder().getTotalPrice();
		Integer oldSize = orderData.getOrderItems().size();

		TemporaryOrderItem tempOrderItem = orderData.getOrderItems().get(0);
		orderData.remove(tempOrderItem.getTemporaryId());

		assertEquals(oldSize - 1, orderData.getOrderItems().size());

		assertTrue(orderData.getNumberOfItems().equals(
				oldNumberItems - numberOfItems(tempOrderItem.getOrderItem())));
		assertTrue(orderData.getOrder().getTotalPrice()
				.equals(oldTotalPrice - tempOrderItem.getOrderItem().getCost()));
	}

	@Test
	public void testFindById() {
		this.fillData();
		ITemporaryOrderData orderData = new TemporaryOrderData(tempOrderId,
				order, orderItems);

		Integer tempOrderItemId = orderData.getOrderItems().get(0)
				.getTemporaryId();
		TemporaryOrderItem tempOrderItem = orderData.findById(tempOrderItemId);
		assertEquals(tempOrderItem.getTemporaryId(), tempOrderItemId);

		tempOrderItemId = -1;
		assertNull(orderData.findById(tempOrderItemId));
	}

}

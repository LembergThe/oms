//
// TemporaryDataTest
//
// 16 . 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Dimension;
import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.domain.entities.User;

/**
 * @author Ivanka
 * 
 */
public class TemporaryDataTest extends TestCase {
	protected final static Integer tempOrderId = 1;

	protected Order order;
	protected List<OrderItem> orderItems;

	@Test
	public void testData() {
		this.fillData();
		assertNotNull(order);
		assertNotNull(orderItems);
	}

	protected Integer numberOfItems(final OrderItem orderItem) {
		return orderItem.getQuantity()
				* orderItem.getDimension().getNumberOfProduct();
	}

	protected void fillData() {
		Role role = new Role();
		role.setRoleName("Administrator");

		User user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setLogin("test");
		user.setPassword("test");
		user.setRole(role);
		user.setEmail("email@gmail.com");

		order = new Order();
		order.setId(1);
		order.setAssigne(user);
		order.setCustomer(user);
		order.setOrderNumber(0);

		orderItems = new ArrayList<OrderItem>();
		orderItems.add(createOrderItem());
		orderItems.add(createOrderItem());

	}

	protected OrderItem createOrderItem() {
		Product product1 = new Product(1, "ProductName1",
				"ProductDescription1", 1.0);
		product1.setActive(true);

		Dimension itemDimension = new Dimension(1, "Item", 1);

		return new OrderItem(1, product1, itemDimension,
				itemDimension.getNumberOfProduct(), order,
				product1.getProductPrice(), null);

	}

}

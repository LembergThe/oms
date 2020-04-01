/**
 * OrderItemTest
 *
 * 11 лип. 2011
 */
package com.softserveinc.edu.oms.persistence.entities;

import junit.framework.TestCase;

import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.domain.entities.Product;

/**
 * @author Ivanka
 * 
 */
public class OrderItemTest extends TestCase {
	@Test
	public void testEquals() {
		OrderItem orderItem1 = new OrderItem();
		assertFalse(orderItem1.equals(null));
		assertFalse(orderItem1.equals(new Integer(2)));

		OrderItem orderItem2 = new OrderItem();
		assertTrue(orderItem1.equals(orderItem2));

		orderItem1.setCost(1.);
		assertFalse(orderItem1.equals(orderItem2));

		orderItem2.setCost(1.);
		assertTrue(orderItem1.equals(orderItem2));

		orderItem1.setId(1);
		assertTrue(orderItem1.equals(orderItem2));

		orderItem2.setItemPrice(1.);
		assertFalse(orderItem1.equals(orderItem2));

		orderItem1.setItemPrice(1.);
		assertTrue(orderItem1.equals(orderItem2));

		orderItem1.setQuantity(2);
		orderItem2.setQuantity(2);
		assertTrue(orderItem1.equals(orderItem2));

		Product product1 = new Product(null, "productName",
				"productDescription", 1.);
		Product product2 = new Product(null, "productName",
				"productDescription", 1.);

		boolean isProductsEquals = product1.equals(product2);

		orderItem1.setProduct(product1);
		orderItem2.setProduct(product2);
		assertEquals(orderItem1.equals(orderItem2), isProductsEquals);
	}
}

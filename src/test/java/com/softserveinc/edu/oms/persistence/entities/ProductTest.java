package com.softserveinc.edu.oms.persistence.entities;

import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Product;

import junit.framework.TestCase;

public class ProductTest extends TestCase {

	@Test
	public void testEquals() {
		Product product1 = new Product();
		assertFalse(product1.equals(null));

		Product product2 = new Product();
		assertTrue(product1.equals(product2));

		product2.setProductName("productName2");
		assertFalse(product1.equals(product2));

		product1.setProductName("productName2");
		assertTrue(product1.equals(product2));

		product2.setProductDescription("productDescription2");
		assertFalse(product1.equals(product2));

		product1.setProductDescription("productDescription2");
		assertTrue(product1.equals(product2));

		product2.setProductPrice(100.0);
		assertFalse(product1.equals(product2));

		product1.setProductPrice(100.0);
		assertTrue(product1.equals(product2));

	}
}

/**
 * OrderItemTest
 *
 * 11 лип. 2011
 */

package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Dimension;
import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

/**
 * 
 * @author Ivanka
 * 
 */
public class OrderItemDaoTest extends CleanUpDBTestCase {

	private Product product;
	private Dimension dimension;
	private Order order1;
	private Order order2;

	private OrderItem orderItem1;
	private OrderItem orderItem2;

	@Before
	public void setUp() {
		cleanDB();
		addAuxiliaryData();
		insertOrderItems();
	}

	@After
	public void tearDown() throws Exception {
		cleanDB();
	}

	@Test
	public void testFindAll() {
		assertEquals(2, orderItemDao.findAll().size());
	}

	@Test
	public void testFindAllWithSorting() {
		SortProperties properties = new SortProperties();
		properties.addPropertyForSort("cost", false);

		List<OrderItem> list = orderItemDao.findAll(properties);

		assertEquals(orderItem2.getId(), list.get(0).getId());
		assertEquals(orderItem1.getId(), list.get(1).getId());

	}

	@Test
	public void testRowCountWithCriterions() {
		assertEquals(new Long(2), orderItemDao.getRowCount());
		assertEquals(new Long(1), orderItemDao.getRowCountFromOrder(order1));

	}

	@Test
	public void testPaging() {
		assertEquals(1, orderItemDao.getOrderItemsFromOrder(order1, 0, 2)
				.size());

		assertEquals(0, orderItemDao.getOrderItemsFromOrder(order1, 4, 2)
				.size());

	}

	@Test
	public void testFindByOrder() {
		assertEquals(1, orderItemDao.getOrderItemsFromOrder(order1).size());
	}

	@Test
	public void testFindById() {
		OrderItem orderItem = orderItemDao.findByID(orderItem1.getId());

		assertEquals(orderItem.getId(), orderItem1.getId());
	}

	private void addAuxiliaryData() {
		order1 = new Order();
		order2 = new Order();
		order1.setOrderNumber(100002);
		order2.setOrderNumber(100001);

		orderDao.insertOrUpdate(order1);
		orderDao.insertOrUpdate(order2);

		product = new Product(null, "ProductName", "ProductDescription", 1.0);
		product = productDao.insertOrUpdate(product);

		dimension = new Dimension(null, "dimensionName", 5);
		dimension = dimensionDao.insertOrUpdate(dimension);
	}

	private void insertOrderItems() {
		orderItem1 = new OrderItem(null, product, dimension, 1, order1, 1., 5.);
		orderItem2 = new OrderItem(null, product, dimension, 2, order2, 1., 10.);

		orderItemDao.insertOrUpdate(orderItem1);
		orderItemDao.insertOrUpdate(orderItem2);
	}

}

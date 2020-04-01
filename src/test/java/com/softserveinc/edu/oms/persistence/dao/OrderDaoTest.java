/*
 * OrderDaoTest.java
 *
 * Version 1.0
 *
 * 10.07.2011
 *
 * (c) Arthur Borisow arthur.borisow[at]gmail.com
 */

package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.persistence.dao.params.SelectCondition;

/**
 * @author arthur.borisow
 * 
 */
public class OrderDaoTest extends CleanUpDBTestCase {

	private User user;
	private OrderStatus orderStatus;
	private Date d;

	private Order order1;
	private Order order2;
	private Order order3;

	@Before
	public void setUp() throws Exception {

		cleanDB();
		fillOrders();
	}

	@After
	public void tearDown() {
		cleanDB();
	}

	@Test
	public void testFindAll() {
		List<Order> o = orderDao.findAll();
		assertEquals(3, o.size());
	}

//	@Test
//	public void findForOrderStatusLike() {
//		List<Order> o = orderDao.findForOrderStatusLike(0, 5, new SortProperties(), "Pen%");
//		for (int i = 0; i < o.size(); i++) {
////			System.out.println(o.get(i));
//		}
//		assertTrue(true);
//
//	}
	@Test
	public void testFindFindById() {
		List<Order> orders = orderDao.findAll();
		for (Order o : orders) {
			Order order = orderDao.findByID(o.getId());
			assertEquals(o.getId(), order.getId());
		}
	}

	@Test
	public void testInsertOrUpdate() {
		Order o = new Order();
		o.setAssigne(user);
		o.setCustomer(user);
		o.setDeliveryDate(new Date());
		o.setOrderDate(new Date());
		o.setOrderStatus(orderStatus);
		o.setTotalPrice(100d);
		o.setOrderNumber(100001);
		orderDao.insertOrUpdate(o);

		List<Order> orders = orderDao.findAll();
		assertEquals(4, orders.size());
	}

	@Test
	public void testDelete() {
		List<Order> o = orderDao.findAll();
		orderDao.delete(o.get(0));
		o = orderDao.findAll();
		assertEquals(2, o.size());
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistence.dao.concrete.OrderDao#findByTotalPrice(double, com.softserverinc.edu.oms.persistence.dao.params.SelectCondition)}
	 * .
	 */
	@Test
	public void testFindByTotalPriceEquals() {
		assertEquals(3, orderDao.findByTotalPrice(100, SelectCondition.EQUALS)
				.size());
	}

	@Test
	public void testFindByTotalPriceLess() {
		assertEquals(3,
				orderDao.findByTotalPrice(200, SelectCondition.LESS_THAN)
						.size());
	}

	@Test
	public void testFindByTotalPriceMore() {
		assertEquals(3,
				orderDao.findByTotalPrice(50, SelectCondition.GREATER_THAN)
						.size());
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistence.dao.concrete.OrderDao#findByDeliveryDate(java.util.Date, com.softserverinc.edu.oms.persistence.dao.params.SelectCondition)}
	 * .
	 */
	@Test
	public void testFindByDeliveryDateEquals() {
		assertEquals(3, orderDao.findByDeliveryDate(d, SelectCondition.EQUALS)
				.size());
	}

	@Test
	public void testFindByDeliveryDateMore() {
		assertEquals(0,
				orderDao.findByDeliveryDate(d, SelectCondition.GREATER_THAN)
						.size());
	}

	@Test
	public void testFindByDeliveryDateLess() {
		assertEquals(
				3,
				orderDao.findByDeliveryDate(new Date(d.getTime() + 100000),
						SelectCondition.LESS_THAN).size());
	}

	@Test
	public void testFindByOrderDateEquals() {
		assertEquals(3, orderDao.findByOrderDate(d, SelectCondition.EQUALS)
				.size());
	}

	@Test
	public void testFindByOrderDateMore() {
		assertEquals(0,
				orderDao.findByOrderDate(d, SelectCondition.GREATER_THAN)
						.size());
	}

	@Test
	public void testFindByOrderDateLess() {
		assertEquals(
				3,
				orderDao.findByOrderDate(new Date(d.getTime() + 100000),
						SelectCondition.LESS_THAN).size());
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistence.dao.concrete.OrderDao#findByOrderStatus(com.softserveinc.edu.oms.domain.entities.softserverinc.edu.oms.persistence.entities.OrderStatus)}
	 * .
	 */
	@Test
	public void testFindByOrderStatus() {
		assertEquals(3, orderDao.findByOrderStatus(orderStatus).size());
	}

	/**
	 * 
	 */
	private void fillOrders() {
		orderStatus = new OrderStatus();
		orderStatus.setOrderStatusName("Pending");
		new HibernateDao<OrderStatus>(OrderStatus.class, super.sessionFactory)
				.insertOrUpdate(orderStatus);

		Role role = new Role();
		role.setRoleName("Administrator");
		new HibernateDao<Role>(Role.class, super.sessionFactory)
				.insertOrUpdate(role);

		user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setLogin("test");
		user.setPassword("test");
		user.setRole(role);
		user.setEmail("email@gmail.com");
		new HibernateDao<User>(User.class, super.sessionFactory)
				.insertOrUpdate(user);

		Date date = new Date();
		d = date;
		order1 = new Order();
		order1.setAssigne(user);
		order1.setCustomer(user);
		order1.setDeliveryDate(date);
		order1.setOrderDate(date);
		order1.setOrderStatus(orderStatus);
		order1.setTotalPrice(100d);
		order1.setOrderNumber(100002);
		orderDao.insertOrUpdate(order1);

		order2 = new Order();
		order2.setAssigne(user);
		order2.setCustomer(user);
		order2.setDeliveryDate(date);
		order2.setOrderDate(date);
		order2.setOrderStatus(orderStatus);
		order2.setTotalPrice(100d);
		order2.setOrderNumber(100003);
		orderDao.insertOrUpdate(order2);

		order3 = new Order();
		order3.setAssigne(user);
		order3.setCustomer(user);
		order3.setDeliveryDate(date);
		order3.setOrderDate(date);
		order3.setOrderStatus(orderStatus);
		order3.setTotalPrice(100d);
		order3.setOrderNumber(100004);
		orderDao.insertOrUpdate(order3);
	}
}

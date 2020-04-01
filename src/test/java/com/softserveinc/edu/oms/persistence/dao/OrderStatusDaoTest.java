package com.softserveinc.edu.oms.persistence.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.OrderStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContex.xml" })
@TransactionConfiguration
@Transactional
public class OrderStatusDaoTest extends CleanUpDBTestCase {
	private static final String STATUS_NAME = "Abandoned";

	private OrderStatus status1 = null;
	private OrderStatus status2 = null;
	private OrderStatus status3 = null;

	private final int NUM_OF_STATUSES = 3;

	@Before
	public void setUp() {
		createStatuses();
	}

	@After
	public void tearDown() {
		cleanDB();
	}

	@Test
	public void testFindAll() {
		int size = orderStatusDao.findAll().size();
		Assert.assertEquals(NUM_OF_STATUSES, size);
	}

	@Test
	public void testFindByID() {
		Assert.assertEquals(status1.getId(),
				orderStatusDao.findByID(status1.getId()).getId());
	}

	@Test
	public void testFindOrderStatusByName() {
		OrderStatus status = new OrderStatus();
		status.setOrderStatusName(STATUS_NAME);
		orderStatusDao.insertOrUpdate(status);
		int size = orderStatusDao.findOrderStatusByName(STATUS_NAME).size();

		Assert.assertEquals(1, size);
		orderStatusDao.delete(status);
	}

	@Test
	public void testInsertOrUpdate() {
		status1 = orderStatusDao.insertOrUpdate(status1);
		status2 = orderStatusDao.insertOrUpdate(status2);
		status3 = orderStatusDao.insertOrUpdate(status3);

		Assert.assertNotNull(status1.getId());
		Assert.assertNotNull(status2.getId());
		Assert.assertNotNull(status3.getId());

		Assert.assertEquals(3, orderStatusDao.findAll().size());
	}

	@Test
	public void testDelete() {
		orderStatusDao.delete(status1);
		Assert.assertEquals(orderStatusDao.findByID(status1.getId()), null);
	}

	private void createStatuses() {
		status1 = new OrderStatus();
		status2 = new OrderStatus();
		status3 = new OrderStatus();

		status1.setOrderStatusName("Delivered");
		status2.setOrderStatusName("In process");
		status3.setOrderStatusName("Ordered");

		orderStatusDao.insertOrUpdate(status1);
		orderStatusDao.insertOrUpdate(status2);
		orderStatusDao.insertOrUpdate(status3);
	}
}
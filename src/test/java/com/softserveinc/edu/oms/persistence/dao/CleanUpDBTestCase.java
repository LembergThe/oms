package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.persistence.dao.concrete.CustomerTypeDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.DimensionDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.OrderDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.OrderItemDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.OrderStatusDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.ProductDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.RegionDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.RoleDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContex.xml" })
@TransactionConfiguration
@Transactional
public class CleanUpDBTestCase {

	@Autowired
	protected CustomerTypeDao customerTypeDao;
	@Autowired
	protected DimensionDao dimensionDao;
	@Autowired
	protected OrderDao orderDao;
	@Autowired
	protected OrderItemDao orderItemDao;
	@Autowired
	protected OrderStatusDao orderStatusDao;
	@Autowired
	protected ProductDao productDao;
	@Autowired
	protected RegionDao regionDao;
	@Autowired
	protected RoleDao roleDao;
	@Autowired
	protected UserDao userDao;
	@Autowired
	protected SessionFactory sessionFactory;

	@Test
	public void testContextFile() {
		assertNotNull(sessionFactory);

		assertNotNull(customerTypeDao);
		assertNotNull(dimensionDao);
		assertNotNull(orderDao);
		assertNotNull(orderItemDao);
		assertNotNull(orderStatusDao);
		assertNotNull(productDao);
		assertNotNull(regionDao);
		assertNotNull(roleDao);
		assertNotNull(userDao);
	}

	protected void cleanDB() {
		cleanOrderItem();
		cleanDimension();
		cleanProduct();
		cleanOrder();
		cleanOrderStatus();
		cleanUser();
		cleanCustomerType();
		cleanRegion();
		cleanRole();
	}

	protected void cleanUser() {

		sessionFactory.getCurrentSession().createQuery("Delete From User")
				.executeUpdate();

	}

	protected void cleanRole() {

		sessionFactory.getCurrentSession().createQuery("Delete From Role")
				.executeUpdate();

	}

	protected void cleanRegion() {

		sessionFactory.getCurrentSession().createQuery("Delete From Region")
				.executeUpdate();

	}

	protected void cleanProduct() {

		sessionFactory.getCurrentSession().createQuery("Delete From Product")
				.executeUpdate();

	}

	protected void cleanOrderStatus() {

		sessionFactory.getCurrentSession()
				.createQuery("Delete From OrderStatus").executeUpdate();

	}

	protected void cleanOrderItem() {

		sessionFactory.getCurrentSession().createQuery("Delete From OrderItem")
				.executeUpdate();

	}

	protected void cleanOrder() {

		sessionFactory.getCurrentSession().createQuery("Delete From Order")
				.executeUpdate();

	}

	protected void cleanDimension() {

		sessionFactory.getCurrentSession().createQuery("Delete From Dimension")
				.executeUpdate();

	}

	protected void cleanCustomerType() {

		sessionFactory.getCurrentSession()
				.createQuery("Delete From CustomerType").executeUpdate();

	}

	// protected void startSession() {
	// session = TestHibernateSessionFactory.getSessionFactory()
	// .getCurrentSession();
	// }
}

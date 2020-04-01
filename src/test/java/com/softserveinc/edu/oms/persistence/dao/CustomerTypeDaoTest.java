/**
 * 
 */
package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.CustomerType;

/**
 * @author roman
 * 
 */

public class CustomerTypeDaoTest extends CleanUpDBTestCase {

	private final String standart = "Standard";
	private final String silver = "Silver";
	private final String gold = "Gold";
	private final String platinum = "Platinum";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		CustomerType type = new CustomerType();
		type.setTypeName(standart);
		type.setMinRange(0.0);
		type.setMaxRange(9999999.9);
		type.setDiscount(1.2);
		customerTypeDao.insertOrUpdate(type);
		type = new CustomerType(null, silver, 0.0, 99999.0, 1.2);
		customerTypeDao.insertOrUpdate(type);
		type = new CustomerType(null, gold, 0.0, 99999.0, 1.2);
		customerTypeDao.insertOrUpdate(type);
		type = new CustomerType(null, platinum, 0.0, 99999.0, 1.2);
		customerTypeDao.insertOrUpdate(type);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() {
		cleanDB();
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.HibernateDao#findByID(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindByID() {
		CustomerType customer = customerTypeDao.getStandartTypeDiscount();
		assertEquals(standart, customerTypeDao.findByID(customer.getId())
				.getTypeName());
		assertEquals(silver, customerTypeDao.findByID(customer.getId() + 1)
				.getTypeName());
		assertEquals(gold, customerTypeDao.findByID(customer.getId() + 2)
				.getTypeName());
		assertEquals(platinum, customerTypeDao.findByID(customer.getId() + 3)
				.getTypeName());
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.concrete.CustomerTypeDao#getStandartTypeDiscount()}
	 * .
	 */
	@Test
	public void testGetStandartTypeDiscount() {
		assertEquals(standart, customerTypeDao.getStandartTypeDiscount()
				.getTypeName());

	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.concrete.CustomerTypeDao#getSilverTypeDiscount()}
	 * .
	 */
	@Test
	public void testGetSilverTypeDiscount() {
		assertEquals(silver, customerTypeDao.getSilverTypeDiscount()
				.getTypeName());
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.concrete.CustomerTypeDao#getGoldTypeDiscount()}
	 * .
	 */
	@Test
	public void testGetGoldTypeDiscount() {
		assertEquals(gold, customerTypeDao.getGoldTypeDiscount().getTypeName());
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.concrete.CustomerTypeDao#getPlatinumTypeDiscount()}
	 * .
	 */
	@Test
	public void testGetPlatinumTypeDiscount() {
		assertEquals(platinum, customerTypeDao.getPlatinumTypeDiscount()
				.getTypeName());
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.HibernateDao#findAll()}.
	 */
	@Test
	public void testFindAll() {
		assertEquals(4, customerTypeDao.findAll().size());
		// assertEquals(true, typeDao.findAll().contains(new CustomerType(null,
		// silver, 0.0, 99999.0, 1.2)));
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.HibernateDao#findByCriterions(org.hibernate.criterion.Criterion[])}
	 * .
	 */
	@Test
	public void testFindByCriterions() {
		String typeName = standart;
		int idMax = 500;
		Criterion criterion1 = Restrictions.eq("typeName", typeName);
		Criterion criterion2 = Restrictions.le("id", idMax);
		List<CustomerType> customers = customerTypeDao.findByCriterions(
				criterion1, criterion2);
		for (CustomerType customer : customers) {
			assertTrue(customer.getId() < idMax
					&& (customer.getTypeName().equals(typeName)));
		}
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.HibernateDao#findByOneOfCriterions(org.hibernate.criterion.Criterion[])}
	 * .
	 */
	@Test
	public void testFindByOneOfCriterions() {
		String typeName = standart;
		int idMax = 500;
		Criterion criterion1 = Restrictions.eq("typeName", typeName);
		Criterion criterion2 = Restrictions.le("id", idMax);
		List<CustomerType> customers = customerTypeDao.findByCriterions(
				criterion1, criterion2);
		for (CustomerType customer : customers) {
			assertTrue(customer.getId() < idMax
					|| (customer.getTypeName().equals(typeName)));
		}
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.HibernateDao#insertOrUpdate(com.softserveinc.edu.oms.persistence.AbstractEntity)}
	 * .
	 */
	@Test
	public void testInsertOrUpdate() {
		super.cleanCustomerType();
		customerTypeDao.insertOrUpdate(new CustomerType(null, standart, 0.0,
				99999999.9, 1.2));
		assertEquals(standart, customerTypeDao.getStandartTypeDiscount()
				.getTypeName());
	}

	/**
	 * Test method for
	 * {@link com.softserveinc.edu.oms.persistence.dao.HibernateDao#delete(com.softserveinc.edu.oms.persistence.AbstractEntity)}
	 * .
	 */
	@Test
	public void testDelete() {
		customerTypeDao
				.delete(new CustomerType(null, silver, 0.0, 99999.0, 1.2));
		customerTypeDao.getStandartTypeDiscount();
	}

}

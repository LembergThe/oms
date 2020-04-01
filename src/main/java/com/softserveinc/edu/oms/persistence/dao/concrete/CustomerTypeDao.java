package com.softserveinc.edu.oms.persistence.dao.concrete;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.entities.CustomerType;
import com.softserveinc.edu.oms.persistence.dao.HibernateDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.ICustomerTypeDao;

@Repository
public class CustomerTypeDao extends HibernateDao<CustomerType> implements
		ICustomerTypeDao {

	/**
	 * 
	 * @param sessionFactory
	 */
	public CustomerTypeDao() {
		super(CustomerType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.ICustomerTypeDAO#
	 * getStandartTypeDiscount()
	 */
	@Override
	public CustomerType getStandartTypeDiscount() {
		CustomerType customerType;

		startSession();

		// creating a query
		Query query = session.createQuery(
				"from CustomerType customerT "
						+ "where customerT.typeName = :Standard").setString(
				"Standard", "Standard");
		;
		customerType = (CustomerType) query.uniqueResult();

		return customerType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.ICustomerTypeDAO#
	 * getSilverTypeDiscount()
	 */
	@Override
	public CustomerType getSilverTypeDiscount() {
		CustomerType customerType;

		startSession();

		// creating a query
		Query query = session.createQuery(
				"from CustomerType customerT "
						+ "where customerT.typeName = :Silver").setString(
				"Silver", "Silver");
		customerType = (CustomerType) query.uniqueResult();

		return customerType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.ICustomerTypeDAO#
	 * getGoldTypeDiscount()
	 */
	@Override
	public CustomerType getGoldTypeDiscount() {
		CustomerType customerType;

		startSession();

		// creating a query
		Query query = session.createQuery(
				"from CustomerType customerT "
						+ "where customerT.typeName = :Gold").setString("Gold",
				"Gold");
		customerType = (CustomerType) query.uniqueResult();

		return customerType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.ICustomerTypeDAO#
	 * getPlatinumTypeDiscount()
	 */
	@Override
	public CustomerType getPlatinumTypeDiscount() {
		CustomerType customerType;

		startSession();

		// creating a query
		Query query = session.createQuery(
				"from CustomerType customerT "
						+ "where customerT.typeName = :Platinum").setString(
				"Platinum", "Platinum");
		customerType = (CustomerType) query.uniqueResult();

		return customerType;
	}
}

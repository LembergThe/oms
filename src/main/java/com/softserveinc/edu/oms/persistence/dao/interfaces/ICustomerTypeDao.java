package com.softserveinc.edu.oms.persistence.dao.interfaces;

import com.softserveinc.edu.oms.domain.entities.CustomerType;
import com.softserveinc.edu.oms.persistence.dao.IDao;

public interface ICustomerTypeDao extends IDao<CustomerType>{

	/**
	 * 
	 * @return CustomerType Object with details about Standard type
	 * Discount
	 */
	CustomerType getStandartTypeDiscount();

	/**
	 * 
	 * @return CustomerType Object with details about Silver type
	 * Discount
	 */
	CustomerType getSilverTypeDiscount();

	/**
	 * 
	 * @return CustomerType Object with details about Gold type
	 * Discount
	 */
	CustomerType getGoldTypeDiscount();

	/**
	 * 
	 * @return CustomerType Object with details about Platinum type
	 * Discount
	 */
	CustomerType getPlatinumTypeDiscount();

}
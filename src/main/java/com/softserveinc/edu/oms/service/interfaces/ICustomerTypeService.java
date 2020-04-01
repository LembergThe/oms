package com.softserveinc.edu.oms.service.interfaces;

import com.softserveinc.edu.oms.domain.entities.CustomerType;

public interface ICustomerTypeService {
	/**
	 * 
	 * @return CustomerType Object with details about Standard type Discount
	 */
	CustomerType getStandartTypeDiscount();

	/**
	 * 
	 * @return CustomerType Object with details about Silver type Discount
	 */
	CustomerType getSilverTypeDiscount();

	/**
	 * 
	 * @return CustomerType Object with details about Gold type Discount
	 */
	CustomerType getGoldTypeDiscount();

	/**
	 * 
	 * @return CustomerType Object with details about Platinum type Discount
	 */
	CustomerType getPlatinumTypeDiscount();
}

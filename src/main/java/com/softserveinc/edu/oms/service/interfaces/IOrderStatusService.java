package com.softserveinc.edu.oms.service.interfaces;

import com.softserveinc.edu.oms.domain.entities.OrderStatus;

public interface IOrderStatusService extends Service<OrderStatus> {
	OrderStatus getByName(String name);

	/**
	 * @return instance of type OrderStatus with name "Created"
	 * @author Ivanka
	 */
	OrderStatus getCreatedStatus();

	/**
	 * @return instance of type OrderStatus with name "Pending"
	 * @author Ivanka
	 */
	OrderStatus getPendingStatus();

	/**
	 * @return instance of type OrderStatus with name "Ordered"
	 * @author Ivanka
	 */
	OrderStatus getOrderedStatus();

	/**
	 * @return instance of type OrderStatus with name "Delivered"
	 * @author Ivanka
	 */
	OrderStatus getDeliveredStatus();

}

package com.softserveinc.edu.oms.persistence.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.persistence.dao.IDao;
import com.softserveinc.edu.oms.persistence.dao.params.SelectCondition;

public interface IOrderDao extends IDao<Order>{

	List<Order> findByTotalPrice(final double totalPrice,
			final SelectCondition cond);

	List<Order> findByDeliveryDate(final Date deliveryDate,
			final SelectCondition cond);

	List<Order> findByOrderDate(final Date orderDate,
			final SelectCondition cond);

	List<Order> findByOrderStatus(final OrderStatus orderStatus);

}
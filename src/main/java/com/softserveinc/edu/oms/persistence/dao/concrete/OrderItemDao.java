/**
 * OrderItemDao
 *
 * 11 лип. 2011
 */
package com.softserveinc.edu.oms.persistence.dao.concrete;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.persistence.dao.HibernatePageableDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IOrderItemDao;

/**
 * 
 * @author Ivanka
 * 
 */
@Repository
public class OrderItemDao extends HibernatePageableDao<OrderItem> implements
		IOrderItemDao {

	public OrderItemDao() {
		super(OrderItem.class);

	}

	/**
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IOrderItemDAO#
	 *      getOrderItemsFromOrder
	 *      (com.softserveinc.edu.oms.persistence.entities.Order,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public final List<OrderItem> getOrderItemsFromOrder(final Order order,
			final Integer startingFrom, final Integer maxResult) {
		return findByCriterions(startingFrom, maxResult,
				Restrictions.eq("order", order));
	}

	/**
	 * 
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IOrderItemDAO#
	 *      getOrderItemsFromOrder
	 *      (com.softserveinc.edu.oms.persistence.entities.Order)
	 */
	@Override
	public final List<OrderItem> getOrderItemsFromOrder(final Order order) {
		return findByCriterions(Restrictions.eq("order", order));
	}

	/**
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.interfaces.IOrderItemDao#
	 *      getRowCountFromOrder(com.softserveinc.edu.oms.domain.entities.Order)
	 */
	@Override
	public final Long getRowCountFromOrder(final Order order) {
		return super.getRowCountByCriterions(Restrictions.eq("order", order));
	}

}

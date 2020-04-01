/**
 * OrderItemDao
 *
 * 11 лип. 2011
 */
package com.softserveinc.edu.oms.persistence.dao.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.persistence.dao.IPageableDao;

/**
 * Interface provides method for order item dao
 * 
 * @author Ivanka
 * 
 */
public interface IOrderItemDao extends IPageableDao<OrderItem> {

	/**
	 * returns part of list of order items which belong to specified order. List
	 * contains items from startingFrom to startingFrom+maxResult
	 * 
	 * @param order
	 * @param startingFrom
	 *            position of the first row which will be added to result
	 * @param maxResult
	 *            max number of items in list
	 * @return list of order items
	 */
	List<OrderItem> getOrderItemsFromOrder(final Order order,
			final Integer startingFrom, final Integer maxResult);

	/**
	 * returns list of order items which belong to specified order.
	 * 
	 * @param order
	 * @return list of order items
	 */
	List<OrderItem> getOrderItemsFromOrder(final Order order);

	/**
	 * returns number of order items in specified order
	 * 
	 * @param order
	 * @return long value representing number of order items
	 */
	Long getRowCountFromOrder(final Order order);

}
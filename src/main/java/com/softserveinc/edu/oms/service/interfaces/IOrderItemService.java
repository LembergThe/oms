//
// IOrderItemService
//
// 2011 
//
package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;

/**
 * @author Ivanka
 * 
 */
public interface IOrderItemService extends ServiceForPaging<OrderItem> {

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

	/**
	 * returns part of list of order items which belong to specified order. List
	 * contains items from startingFrom to startingFrom+maxResult
	 * 
	 * @param orderId
	 * @param startingFrom
	 *            position of the first row which will be added to result
	 * @param maxResult
	 *            max number of items in list
	 * @return list of order items
	 */
	List<OrderItem> getOrderItemsFromOrder(final Integer orderId,
			final Integer startingFrom, final Integer maxResult);

	/**
	 * returns list of order items which belong to specified order.
	 * 
	 * @param orderId
	 * @return list of order items
	 */
	List<OrderItem> getOrderItemsFromOrder(final Integer orderId);

	/**
	 * returns number of order items in specified order
	 * 
	 * @param orderId
	 * @return long value representing number of order items
	 */
	Long getRowCountFromOrder(final Integer orderId);

	/**
	 * 
	 * @param orderItemId
	 */
	void delete(Integer orderItemId);

}

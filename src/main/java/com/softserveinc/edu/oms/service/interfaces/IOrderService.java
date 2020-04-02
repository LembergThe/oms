//
// IOrderService
//
// 21 . 2011
//
package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.web.order.SearchFilterOptions;

/**
 * @author marko
 * 
 */
public interface IOrderService extends ServiceForPaging<Order> {
	List<Order> find(Integer startingFrom, Integer maxResult,
			SearchFilterOptions options, SortProperties sortProperties);

	Long getRowCount(SearchFilterOptions options);

	/**
	 * return max order number from table Orders
	 * 
	 * @return
	 */
	Integer getMaxOrderNumber();

	/**
	 * returns false if specified orderNumber doesn't exists in table
	 * 
	 * @param orderNumber
	 * @return
	 */
	Boolean orderNumberExists(Integer orderNumber);
}

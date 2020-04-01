//
// ITemporaryOrderService
//
// 29 ρεπο. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.service.interfaces.IOrderItemService;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans.TemporaryOrder;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans.TemporaryOrderItem;

/**
 * @author Ivanka
 * 
 */
public interface ITemporaryOrderData {

	TemporaryOrder getTempOrder();

	List<TemporaryOrderItem> getOrderItems();

	Integer getNumberOfItems();

	Boolean getIsSaved();

	Boolean getIsEditable();

	void setIsEditable(boolean b);

	/**
	 * @return
	 */
	Order getOrder();

	/**
	 * List of temporaryOrderItem
	 * 
	 * @param startingFrom
	 *            index of first TemporaryOrderItem to be added to result
	 * @param maxResult
	 *            max number of rows in result
	 * @return
	 */
	List<TemporaryOrderItem> getOrderItems(Integer startingFrom,
			Integer maxResult);

	void insertOrUpdate(final TemporaryOrderItem orderItem);

	/**
	 * removes TemporaryOrderItem object with id orderItemId from list of items
	 * 
	 * @param orderItemId
	 *            id of TemporaryOrderItem, which will be deleted
	 */
	void remove(Integer orderItemId);

	/**
	 * saves all changes to database
	 * 
	 * @param orderService
	 * @param orderItemService
	 * @param orderStatusService
	 */
	void submit(IOrderService orderService, IOrderItemService orderItemService,
			IOrderStatusService orderStatusService);

	/**
	 * @param parseInt
	 * @return
	 */
	TemporaryOrderItem findById(int parseInt);
}

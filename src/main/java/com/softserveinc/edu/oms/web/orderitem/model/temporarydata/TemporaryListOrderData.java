//
// TemporaryOrderListData
//
// 29 . 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;

/**
 * @author Ivanka
 * 
 */
public class TemporaryListOrderData {

	private Map<Integer, ITemporaryOrderData> orders = new HashMap<Integer, ITemporaryOrderData>();
	private List<Integer> openedOrderId = new ArrayList<Integer>();

	public TemporaryListOrderData() {
	}

	/**
	 * adds TemporaryOrderData for specified order.
	 * 
	 * @param order
	 * @param orderItems
	 */
	public final ITemporaryOrderData addOrderData(final Order order,
			final List<OrderItem> orderItems) {

		if (!openedOrderId.contains(order.getId())) {
			openedOrderId.add(order.getId());

			Integer newId;
			if (orders.keySet().size() == 0) {
				newId = 1;
			} else {
				newId = Collections.max(orders.keySet()) + 1;
			}
			orders.put(newId, new TemporaryOrderData(newId, order, orderItems));
			return orders.get(newId);
		} else {
			for (ITemporaryOrderData tempOrderData : orders.values()) {
				if (tempOrderData.getOrder().getId().equals(order.getId())) {
					return tempOrderData;
				}
			}
		}

		return null;
	}

	public final ITemporaryOrderData addOrderData() {
		Integer newId;
		if (orders.keySet().size() == 0) {
			newId = 1;
		} else {
			newId = Collections.max(orders.keySet()) + 1;
		}
		ITemporaryOrderData orderData = new TemporaryOrderData(newId);

		orders.put(newId, orderData);

		return orderData;
	}

	/**
	 * @param orderId
	 * @return
	 */
	public final ITemporaryOrderData getTemporaryOrderData(final Integer orderId) {
		return orders.get(orderId);
	}

	/**
	 * removes TemporaryOrderData with id=orderId from list
	 * 
	 * @param orderId
	 */
	public final void removeOrderData(final Integer orderId) {
		if (orders.containsKey(orderId)) {
			ITemporaryOrderData orderData = orders.get(orderId);
			if (orderData.getOrder().getId() != null) {
				openedOrderId.remove(orderData.getOrder().getId());
			}
			orders.remove(orderId);
		}
	}

}

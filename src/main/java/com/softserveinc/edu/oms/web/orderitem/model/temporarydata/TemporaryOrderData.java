//
// OrderTemporaryData
//
// 29 ρεπο. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.service.interfaces.IOrderItemService;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans.TemporaryOrder;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans.TemporaryOrderItem;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;

/**
 * @author Ivanka
 * 
 */
public class TemporaryOrderData implements ITemporaryOrderData {

	private TemporaryOrder tempOrder;
	private final List<TemporaryOrderItem> orderItems = new ArrayList<TemporaryOrderItem>();

	private Integer nextId = 0;

	private Integer numberOfItems;
	private Boolean isSaved;

	private Boolean isEditable = false;

	public TemporaryOrderData(final Integer tempOrderId) {
		Order order = new Order();

		order.setCustomer(SessionExplorer.getLoggedUser());
		order.setTotalPrice(0.);
		tempOrder = new TemporaryOrder(tempOrderId, order);

		nextId = 0;

		numberOfItems = 0;
		isSaved = false;

		isEditable = true;

	}

	public TemporaryOrderData(final Integer tempOrderId, final Order order,
			final List<OrderItem> orderItems) {
		tempOrder = new TemporaryOrder(tempOrderId, order);

		numberOfItems = 0;
		tempOrder.getOrder().setTotalPrice(0.);

		Double cost = 0.;
		for (OrderItem orderItem : orderItems) {
			TemporaryOrderItem tempOrderItem = new TemporaryOrderItem(nextId,
					orderItem);
			this.orderItems.add(tempOrderItem);
			changeCostOfOrderItem(tempOrderItem);

			nextId++;

			numberOfItems += orderItem.getDimension().getNumberOfProduct()
					* orderItem.getQuantity();
			cost += orderItem.getCost();
		}

		tempOrder.getOrder().setTotalPrice(cost);

	}

	/**
	 * @see com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData#insertOrUpdate(com.softserveinc.edu.oms.domain.entities.OrderItem)
	 */
	@Override
	public final void insertOrUpdate(final TemporaryOrderItem orderItem) {
		isSaved = false;
		changeCostOfOrderItem(orderItem);
		orderItem.getOrderItem().setOrder(tempOrder.getOrder());
		if (orderItem.getTemporaryId() != null) {
			update(orderItem);
		} else {
			insert(orderItem);
		}
	}

	/**
	 * @param tempOrderItem
	 */
	private void changeCostOfOrderItem(final TemporaryOrderItem tempOrderItem) {
		Double cost = numberOfItemsInOrderItem(tempOrderItem)
				* tempOrderItem.getOrderItem().getItemPrice();
		tempOrderItem.getOrderItem().setCost(cost);
	}

	/**
	 * @param tempOrderItem
	 */
	private final void update(final TemporaryOrderItem tempOrderItem) {
		Integer index = orderItems.indexOf(tempOrderItem);
		TemporaryOrderItem oldOrderItem = orderItems.get(index);

		numberOfItems -= numberOfItemsInOrderItem(oldOrderItem);
		reduceTotalPrice(oldOrderItem);

		numberOfItems += numberOfItemsInOrderItem(tempOrderItem);
		increaseTotalPrice(tempOrderItem);

		oldOrderItem.setOrderItem(tempOrderItem.getOrderItem());
	}

	/**
	 * @param tempOrderItem
	 */
	private final void insert(final TemporaryOrderItem tempOrderItem) {
		tempOrderItem.setId(nextId);
		nextId++;

		numberOfItems += numberOfItemsInOrderItem(tempOrderItem);
		increaseTotalPrice(tempOrderItem);

		orderItems.add(tempOrderItem);

	}

	/**
	 * @see com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData#getOrderItems(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public final List<TemporaryOrderItem> getOrderItems(
			final Integer startingFrom, final Integer maxResult) {
		Integer beginIndex = startingFrom;
		Integer lastIndex = beginIndex + maxResult;

		if (startingFrom < 0) {
			beginIndex = 0;
		}

		return orderItems.subList(Math.min(beginIndex, orderItems.size()),
				Math.min(lastIndex, orderItems.size()));
	}

	/**
	 * @see com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData#remove(java.lang.Integer)
	 */
	@Override
	public final void remove(final Integer orderItemId) {
		for (TemporaryOrderItem temporaryOrderItem : orderItems) {
			if (temporaryOrderItem.equals(orderItemId)) {
				numberOfItems -= numberOfItemsInOrderItem(temporaryOrderItem);
				reduceTotalPrice(temporaryOrderItem);
				orderItems.remove(temporaryOrderItem);

				isSaved = false;
				break;
			}
		}
	}

	private final Integer numberOfItemsInOrderItem(
			final TemporaryOrderItem tempOrderItem) {
		return tempOrderItem.getOrderItem().getQuantity()
				* tempOrderItem.getOrderItem().getDimension()
						.getNumberOfProduct();
	}

	private final void reduceTotalPrice(
			final TemporaryOrderItem temporaryOrderItem) {
		Double reduced = tempOrder.getOrder().getTotalPrice()
				- temporaryOrderItem.getOrderItem().getCost();
		tempOrder.getOrder().setTotalPrice(reduced);
	}

	private final void increaseTotalPrice(
			final TemporaryOrderItem temporaryOrderItem) {
		Double increased = tempOrder.getOrder().getTotalPrice()
				+ temporaryOrderItem.getOrderItem().getCost();
		tempOrder.getOrder().setTotalPrice(increased);
	}

	/**
	 * @see com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData#submit(com.softserveinc.edu.oms.service.interfaces.IOrderService,
	 *      com.softserveinc.edu.oms.service.interfaces.IOrderItemService)
	 */
	@Override
	public final void submit(final IOrderService orderService,
			final IOrderItemService orderItemService,
			final IOrderStatusService statusService) {
		List<Integer> orderItemsIndexes = new ArrayList<Integer>();

		tempOrder.setOrder(orderService.insertOrUpdate(tempOrder.getOrder()));

		for (TemporaryOrderItem temporaryOrderItem : orderItems) {
			orderItemService.insertOrUpdate(temporaryOrderItem.getOrderItem());
			orderItemsIndexes.add(temporaryOrderItem.getOrderItem().getId());
		}

		List<OrderItem> dbOrderItems = orderItemService
				.getOrderItemsFromOrder(tempOrder.getOrder());
		for (OrderItem dbOrderItem : dbOrderItems) {
			if (!orderItemsIndexes.contains(dbOrderItem.getId())) {
				orderItemService.delete(dbOrderItem.getId());
			}
		}

		tempOrder.getOrder().setOrderStatus(statusService.getCreatedStatus());
		orderService.insertOrUpdate(tempOrder.getOrder());

		isSaved = true;
	}

	/**
	 * @see com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData#findById(int)
	 */
	@Override
	public final TemporaryOrderItem findById(final int tempOrderItemId) {
		for (TemporaryOrderItem orderItem : orderItems) {
			if (orderItem.equals(tempOrderItemId)) {
				return orderItem;
			}
		}
		return null;
	}

	@Override
	public final void setIsEditable(final boolean editable) {
		this.isEditable = editable;
	}

	@Override
	public final Boolean getIsEditable() {
		return isEditable;
	}

	@Override
	public final TemporaryOrder getTempOrder() {
		return tempOrder;
	}

	@Override
	public final List<TemporaryOrderItem> getOrderItems() {
		return orderItems;
	}

	@Override
	public final Integer getNumberOfItems() {
		return numberOfItems;
	}

	@Override
	public final Boolean getIsSaved() {
		return isSaved;
	}

	/**
	 * @see com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData#getOrder()
	 */
	@Override
	public final Order getOrder() {
		return tempOrder.getOrder();
	}
}

/**
 * MerchandiserEditHandler
 *
 * Version 1.0
 *
 * Date 15.09.11
 *
 * Copyright Softserve
 */
package com.softserveinc.edu.oms.web.merchandiserpages.handlers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;
import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.ParametersValidator;
import com.softserveinc.edu.oms.web.abstracthandlers.AbstractCRUDHandler;
import com.softserveinc.edu.oms.web.merchandiserpages.MerchandiserPageParameters;

/**
 * This class used in MerchandiserEditControler class for realization of
 * merchandiser order edit.
 * 
 * @author Vitalik
 * 
 */
public class MerchandiserEditHandler extends AbstractCRUDHandler {
	// Fields

	/**
	 * Order Service exemplar for order data access
	 */
	private IOrderService orderService;

	/**
	 * Order Item Service exemplar for order data access
	 */
	private IOrderStatusService orderStatusService;

	// Constructor
	
	/**
	 * Constructor for MerchandiserEditHandler class. Need some services for
	 * use.
	 * 
	 * @param orderService
	 *            - IOrderService realization object
	 * @param orderStatusService
	 *            - IOrderStatusService realization object
	 */
	public MerchandiserEditHandler(IOrderService orderService,
			IOrderStatusService orderStatusService) {
		this.orderService = orderService;
		this.orderStatusService = orderStatusService;
	}

	// Overriding of AbstractHandler methods

	/**
	 * This method check existence of "orderStatus" and "deliveryDate"
	 * parameters and validate them.
	 */
	@Override
	protected boolean isValidRequiredParameters(final HttpServletRequest request)
			throws Exception {
		return (ParametersValidator.isValidStringParameter(request,
				MerchandiserPageParameters.ORDER_STATUS)
				&& ParametersValidator.isValidDateParameter(request,
						MerchandiserPageParameters.DELIVERY_DATE, "dd-MM-yyyy") && ParametersValidator
					.isValidIntegerParameter(request, ParameterNames.ORDER_ID));
	}

	// Overriding of AbstractCRUDHandler methods

	/**
	 * This method check returnJspName, orderService and orderStatusService
	 * fields.
	 */
	@Override
	protected void initialize() throws Exception {
		super.initialize();
		if (orderService == null) {
			throw new Exception("IOrderService object can not be null");
		}
		if (orderStatusService == null) {
			throw new Exception("IOrderStatusService object can not be null");
		}
	}

	// Realization of abstract AbstractCRUDHandler methods

	/**
	 * Realization of UPDATE CRUD operation for orders at merchandiser page.
	 */
	protected void executeCRUDOperation(HttpServletRequest request)
			throws Exception {
		Order order = getSelectedOrder(request);
		// Check if input delivery is before order date
		Date deliveryDate = getDeliveryDateFromRequest(request);
		if (order.getOrderDate().after(deliveryDate))
			throw new Exception("Delivery date can not be before order date:"
					+ order.transformOrderDateToStr());
		// Set new order status, delivery date and isGift values
		order.setOrderStatus(getOrderStatusFromRequest(request));
		order.setDeliveryDate(getDeliveryDateFromRequest(request));
		order.setIsGift(getIsGiftFromRequest(request));
		// Save changes
		orderService.insertOrUpdate(order);
	}

	// Private methods for getting values from HTTP request

	/**
	 * Get and parse "deliveryDate" parameter value from HTTP request.
	 * 
	 * @param request
	 *            - HTTP request object
	 * @return new delivery date value.
	 * @throws ParseException
	 *             if there is String-to-Date parse Exception.
	 */
	private Date getDeliveryDateFromRequest(final HttpServletRequest request)
			throws ParseException {
		String dateStr = request
				.getParameter(MerchandiserPageParameters.DELIVERY_DATE);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return (Date) formatter.parse(dateStr);
	}

	/**
	 * Get "orderStatus" parameter from request and look for OrderStatus object
	 * with such name in DB.
	 * 
	 * @param request
	 *            - HTTP request object.
	 * @return new OrderStatus value.
	 * @throws Exception
	 *             if there is no OrderStatus object in DB with such name.
	 */
	private OrderStatus getOrderStatusFromRequest(
			final HttpServletRequest request) throws Exception {
		String orderStatusStr = request
				.getParameter(MerchandiserPageParameters.ORDER_STATUS);
		for (OrderStatus orderStatus : orderStatusService.findAll()) {
			String orderStatusS = orderStatus.getOrderStatusName();
			if (orderStatusS.equals(orderStatusStr))
				return orderStatus;
		}
		throw new Exception("MerchandiserEditHandler. Unknown order status.");
	}

	/**
	 * Get "isGift" parameter from request and convert it to Boolean.
	 * 
	 * @param request
	 *            - HTTP request object.
	 * @return Boolean class value
	 * @throws Exception
	 *             if there is bad "isGift" parameter value in request.
	 */
	private Boolean getIsGiftFromRequest(final HttpServletRequest request)
			throws Exception {
		String idGiftStr = request
				.getParameter(MerchandiserPageParameters.IS_GIFT);
		if (idGiftStr == null) {
			return false;
		}
		if (idGiftStr.equals("on")) {
			return true;
		}
		throw new Exception("MerchandiserEditHandler. Bad isGift parameter.");
	}

	// Private methods for data access from services

	/**
	 * Get Order object by "orderId" parameter from request, using orderService
	 * 
	 * @param request
	 *            - HTTP request from controller
	 * @return Order object with "orderId" parameter Id
	 */
	private Order getSelectedOrder(final HttpServletRequest request) {
		Integer orderId = Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID));
		return orderService.findByID(orderId);
	}
}

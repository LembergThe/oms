/**
 * MerchandiserPageableHandler
 *
 * Version 1.0
 *
 * Date 15.09.11
 *
 * Copyright Softserve
 */
package com.softserveinc.edu.oms.web.merchandiserpages.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.service.interfaces.IOrderItemService;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.ParametersValidator;
import com.softserveinc.edu.oms.web.abstracthandlers.AbstractPageableHandler;
import com.softserveinc.edu.oms.web.merchandiserpages.MerchandiserPageParameters;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;
import com.softserveinc.edu.oms.web.util.pageable.PageModel;
import com.softserveinc.edu.oms.web.util.pageable.ParameterPair;

/**
 * This class used in MerchandiserShowPageControler class for realization of
 * order items pageable list.
 * 
 * @author Vitalik
 * 
 */
public final class MerchandiserPageableHandler extends AbstractPageableHandler {
	// Fields

	/**
	 * Order Service exemplar for order data access
	 */
	private IOrderService orderService;

	/**
	 * Order Item Service exemplar for order data access
	 */
	private IOrderItemService orderItemService;

	// Constructor

	/**
	 * Constructor for MerchandiserPageableHandler class. Need some services for
	 * use.
	 * 
	 * @param orderService
	 *            - IOrderService realization object
	 * @param orderItemService
	 *            - IOrderItemService realization object
	 * @throws Exception
	 */
	public MerchandiserPageableHandler(IOrderService orderService,
			IOrderItemService orderItemService) {
		this.orderService = orderService;
		this.orderItemService = orderItemService;
	}

	// Overriding of AbstractHandler methods

	/**
	 * This method check existence of "orderId" parameter and validate it. Also
	 * hear we change returnJspName if order is alredy delivered.
	 */
	@Override
	protected final boolean isValidRequiredParameters(
			final HttpServletRequest request) throws Exception {
		Boolean result = ParametersValidator.isValidIntegerParameter(request,
				ParameterNames.ORDER_ID);
		return result;
	}

	/**
	 * Check if selected order assignee is current merchandiser.
	 */
	@Override
	protected boolean checkCurrentUserPermissions(
			final HttpServletRequest request) throws Exception {
		Order order = getSelectedOrder(request);
		User currentMerchandiser = SessionExplorer.getLoggedUser();
		if (order.getAssigne().equals(currentMerchandiser)) {
			return true;
		} else {
			throw new Exception(
					"You can not see order details with another assignee.");
		}
	}

	// Realization of abstract AbstractPageableHandler methods

	/**
	 * This method get number of order items for selected order.
	 */
	@Override
	protected final int getNumberOfAllElementsForPageModel(
			HttpServletRequest request) {
		Order order = getSelectedOrder(request);
		return orderItemService.getOrderItemsFromOrder(order).size();
	}

	// Overriding of AbstractPageableHandler methods

	/**
	 * This method check pageJspType, jspName, orderService and orderItemService
	 * fields.
	 */
	@Override
	protected final void initialize() throws Exception {
		super.initialize();
		if (orderService == null) {
			throw new Exception("IOrderService object can not be null");
		}
		if (orderItemService == null) {
			throw new Exception("IOrderItemService object can not be null");
		}
	}

	/**
	 * This method set general page data for merchandiser page.
	 */
	@Override
	protected final void setGeneralPageData(HttpServletRequest request,
			ModelMap modelMap, PageModel pageModel) {
		Order selectedOrder = getSelectedOrder(request);
		// Add customer name
		modelMap.addAttribute(MerchandiserPageParameters.CUSTOMER_NAME,
				selectedOrder.getCustomer().getFirstName() + " "
						+ selectedOrder.getCustomer().getLastName());
		// Add customer type
		modelMap.addAttribute(MerchandiserPageParameters.CUSTOMER_TYPE,
				selectedOrder.getCustomer().getCustomerType().getTypeName());
		// Add order number
		modelMap.addAttribute(MerchandiserPageParameters.ORDER_NUMBER,
				selectedOrder.getOrderNumber());
		// Add total price
		modelMap.addAttribute(MerchandiserPageParameters.TOTAL_PRICE,
				selectedOrder.getOrderNumber());
		// Add total number of items
		modelMap.addAttribute(MerchandiserPageParameters.TOTAL_NUMBER_OF_ITEMS,
				orderItemService.getOrderItemsFromOrder(selectedOrder).size());
		// Add assignee
		modelMap.addAttribute(MerchandiserPageParameters.ASSIGNEE_NAME,
				selectedOrder.getAssigne().getFirstName() + " "
						+ selectedOrder.getAssigne().getLastName());
		// Add date of ordering
		modelMap.addAttribute(MerchandiserPageParameters.DATE_OF_ORDERING,
				selectedOrder.transformOrderDateToStr());
		// Add preferable Delivery Date
		modelMap.addAttribute(
				MerchandiserPageParameters.PREFERABLE_DELIVERY_DATE,
				selectedOrder.transformPreferableDeliveryDateToStr());
		// Add order status
		modelMap.addAttribute(MerchandiserPageParameters.ORDER_STATUS,
				selectedOrder.getOrderStatus().getOrderStatusName());
		// Add delivery date
		modelMap.addAttribute(MerchandiserPageParameters.DELIVERY_DATE,
				selectedOrder.transformDeliveryDateToStr());
		// Add isGift
		modelMap.addAttribute(MerchandiserPageParameters.IS_GIFT,
				selectedOrder.getIsGift());
		// Add selected order id
		modelMap.addAttribute(MerchandiserPageParameters.SELECTED_ORDER_ID,
				selectedOrder.getId());
	}

	/**
	 * This method set current page data for merchandiser page.
	 */
	@Override
	protected final void setCurrentPageData(HttpServletRequest request,
			ModelMap modelMap, PageModel pageModel) {
		// Add pageModel with required parameters
		pageModel.getListOfRequiredParameters().add(
				new ParameterPair(ParameterNames.ORDER_ID, request
						.getParameter(ParameterNames.ORDER_ID)));
		modelMap.addAttribute(MerchandiserPageParameters.PAGE_MODEL, pageModel);
		// Add list of order items
		Order selectedOrder = getSelectedOrder(request);
		modelMap.addAttribute(MerchandiserPageParameters.LIST_OF_ORDER_ITEMS,
				orderItemService.getOrderItemsFromOrder(selectedOrder,
						pageModel.selectStartPos(),
						pageModel.selectMaxElements()));
	}

	// Private methods for data access from services

	/**
	 * Get Order object by "orderId" parameter from request, using orderService
	 * 
	 * @param request
	 *            - HTTP request from controller
	 * @return Order object with "orderId" parameter Id
	 */
	private final Order getSelectedOrder(final HttpServletRequest request) {
		Integer orderId = Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID));
		return orderService.findByID(orderId);
	}
}

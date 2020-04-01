//
// OrderItemControllerUtil
//
// 23 ρεπο. 2011
//
package com.softserveinc.edu.oms.web.orderitem;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.service.interfaces.IOrderItemService;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;
import com.softserveinc.edu.oms.service.interfaces.IRoleService;
import com.softserveinc.edu.oms.service.interfaces.IUserService;
import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.orderitem.model.OrderItemsLinesNumberModel;
import com.softserveinc.edu.oms.web.orderitem.model.OrderItemsPageModel;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.TemporaryListOrderData;
import com.softserveinc.edu.oms.web.orderitem.util.OrderItemParameters;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;
import com.softserveinc.edu.oms.web.security.UserSecurityData;

/**
 * @author Ivanka
 * 
 */
public class OrderItemControllerUtil {
	protected IOrderItemService orderItemService;
	protected IOrderService orderService;
	protected IUserService userService;
	protected IRoleService roleService;
	protected IOrderStatusService orderStatusService;

	@Autowired
	public void setOrderStatusService(
			final IOrderStatusService orderStatusService) {
		this.orderStatusService = orderStatusService;
	}

	@Autowired
	public void setOrderItemService(final IOrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@Autowired
	public void setOrderService(final IOrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(final IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * return number of page which should be displayed
	 * 
	 * @param request
	 * @return
	 */
	protected Integer getPageNumberFromRequest(final HttpServletRequest request) {

		String pageParameter = request
				.getParameter(OrderItemParameters.PAGE_NUMBER);
		if (pageParameter == null || pageParameter.equals("")) {
			return 0;
		}

		Integer page;
		try {
			page = Integer.parseInt(pageParameter);
		} catch (NumberFormatException e) {
			return 0;
		}

		return page;
	}

	/**
	 * reads session to get {@link OrderItemLinesNumberModel}.
	 * 
	 * @param request
	 * @return
	 */
	protected OrderItemsLinesNumberModel getOrderItemsLinesNumberModel(
			final HttpServletRequest request) {
		OrderItemsLinesNumberModel linesNumber = (OrderItemsLinesNumberModel) request
				.getSession().getAttribute(OrderItemParameters.NUMBER_OF_ROWS);

		if (linesNumber == null) {
			linesNumber = new OrderItemsLinesNumberModel();
			request.getSession(true).setAttribute(
					OrderItemParameters.NUMBER_OF_ROWS, linesNumber);
		}

		return linesNumber;
	}

	/**
	 * @param request
	 * @param orderId
	 * @param linesNumber
	 * @return
	 */
	protected OrderItemsPageModel getOrderItemPageModel(
			final HttpServletRequest request, final Integer orderId,
			final OrderItemsLinesNumberModel linesNumber) {

		ITemporaryOrderData orderData = SessionExplorer.getTemporaryOrderData(
				request, orderId);

		Integer size = orderData.getOrderItems().size();
		Integer pageSize = linesNumber.getSelectedValue();

		Integer numberOfPages = (int) (size / pageSize);

		if (size % pageSize > 0) {
			numberOfPages++;
		}

		return new OrderItemsPageModel(numberOfPages,
				getPageNumberFromRequest(request));
	}

	private Boolean isIntegerParameterValid(final String parameter) {
		if (parameter == null || parameter.equals("")) {
			return false;
		}
		try {
			Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;

	}

	/**
	 * checks if request has parameter ParameterNames.ORDER_ID, is not empty an
	 * is instance of Integer
	 * 
	 * @param request
	 * @return true if orderId parameter is valid
	 */
	protected Boolean isOrderIdValid(final HttpServletRequest request) {
		String orderIdParameter = request.getParameter(ParameterNames.ORDER_ID);

		if (isIntegerParameterValid(orderIdParameter)) {
			Integer orderId = Integer.parseInt(orderIdParameter);
			if (orderService.findByID(orderId) == null) {
				return false;
			}
			return true;
		}
		return false;
	}

	protected Boolean isOrderTempIdValid(final HttpServletRequest request) {
		String orderIdParameter = request.getParameter(ParameterNames.ORDER_ID);

		if (isIntegerParameterValid(orderIdParameter)) {
			Integer orderTempId = Integer.parseInt(orderIdParameter);
			if (SessionExplorer.getTemporaryOrderData(request, orderTempId) == null) {
				return false;
			}
			return true;
		}
		return false;
	}

	protected Boolean isOrderNumberValid(final HttpServletRequest request) {
		String orderNumberParameter = request
				.getParameter(OrderItemParameters.ORDER_NUMBER);
		return isIntegerParameterValid(orderNumberParameter);
	}

	protected String addPageParameter(final String url, final Integer page) {
		return url + OrderItemParameters.PAGE_NUMBER + "=" + page;
	}

	/**
	 * return new string which consist of url, parameter name and orderId
	 * 
	 * @param url
	 * @param orderId
	 * @return
	 */
	protected String addOrderIdParameter(final String url, final Integer orderId) {
		return url + ParameterNames.ORDER_ID + "=" + orderId;
	}

	/**
	 * return string to redirect to merchandiser page
	 * 
	 * @param orderId
	 *            - will be added as parameter
	 * @return
	 */
	protected String redirectToMerchandiserPage(final Integer orderId) {
		String url = "redirect:" + ParameterNames.MERCHANDISER_PAGE + "?";
		url = addOrderIdParameter(url, orderId);

		return url;
	}

	/**
	 * return string to redirect to order view page
	 * 
	 * @param orderId
	 *            - will be added to url as parameter
	 * @param page
	 * @return
	 */
	protected String redirectToOrderView(final Integer orderId,
			final Integer page) {
		String url = "redirect:orderItemsView.htm?";
		url = addOrderIdParameter(url, orderId) + "&";
		url = addPageParameter(url, page);

		return url;
	}

	/**
	 * return String to redirect to edit order items page
	 * 
	 * @param orderId
	 *            - will be added to url as orderId parameter
	 * @param page
	 *            - will be added to url as page parameter
	 * @return
	 */
	protected String redirectUrl(final Integer orderId, final Integer page) {
		String url = "redirect:orderItems.htm?";
		url = addOrderIdParameter(url, orderId) + "&";
		url = addPageParameter(url, page);

		return url;
	}

	/**
	 * @param request
	 * @param orderId
	 * @return
	 */
	protected ITemporaryOrderData addOrderData(
			final HttpServletRequest request, final Integer orderId) {
		TemporaryListOrderData listOrderData = SessionExplorer
				.getTemporaryListData(request);

		Order order = orderService.findByID(orderId);
		List<OrderItem> orderItems = orderItemService
				.getOrderItemsFromOrder(orderId);

		return listOrderData.addOrderData(order, orderItems);
	}

	protected void fillModelMap(final HttpServletRequest request,
			final ModelMap modelMap, final ITemporaryOrderData orderData,
			final Locale locale) {

		Integer orderId = orderData.getTempOrder().getId();

		OrderItemsLinesNumberModel linesNumber = getOrderItemsLinesNumberModel(request);
		OrderItemsPageModel pageModel = getOrderItemPageModel(request, orderId,
				linesNumber);
		pageModel.validatePageNumber();

		modelMap.put(
				"orderItems",
				orderData.getOrderItems(pageModel.getCurrentPage()
						* linesNumber.getSelectedValue(),
						linesNumber.getSelectedValue()));

		modelMap.put("locale", locale);

		modelMap.put(ParameterNames.ORDER_ID, orderId);
		modelMap.put(ParameterNames.ORDER_TEMPORARY_DATA, orderData);
		modelMap.put(OrderItemParameters.NUMBER_OF_ROWS, linesNumber);
		modelMap.put(OrderItemParameters.PAGE_NUMBER, pageModel);
	}

	protected User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		UserSecurityData userSecurityData = (UserSecurityData) principal;
		return userSecurityData.getUser();
	}

}

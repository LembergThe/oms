/**
 * MerchandiserEditControler
 *
 * Version 1.0
 *
 * Date 15.09.11
 *
 * Copyright Softserve
 */
package com.softserveinc.edu.oms.web.merchandiserpages.controlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;
import com.softserveinc.edu.oms.web.AbstractControler;
import com.softserveinc.edu.oms.web.merchandiserpages.handlers.MerchandiserEditHandler;

/**
 * This class implements controllers for edit order at the merchandiser page.
 * 
 * @author Vitalik
 * 
 */
@Controller
public class MerchandiserEditControler extends AbstractControler {
	// Fields

	/**
	 * Order Service exemplar for order data access
	 */
	private IOrderService orderService;

	/**
	 * Order Status Service exemplar for order status data access
	 */
	private IOrderStatusService orderStatusService;
	
	/**
	 * This is URL that catch this controller methods
	 */
	private final static String CONTROLER_NAME = "merchandiserEdit.htm";
	
	/**
	 * Names of JSP files that return this controller. This JSP name user in
	 * Tiles
	 */
	private final static String RETURN_JSP_STRING = "redirect:order.htm";
	
	// Initialization

	/**
	 * This method automatically set orderService field.
	 * 
	 * @param orderService
	 *            - IOrderService object.
	 */
	@Autowired
	public void setOrderService(final IOrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * This method automatically set orderStatusService field.
	 * 
	 * @param orderStatusService
	 *            - IOrderStatusService object.
	 */
	@Autowired
	public void setOrderStatusService(
			final IOrderStatusService orderStatusService) {
		this.orderStatusService = orderStatusService;
	}

	// Controllers

	/**
	 * This method implements saving of order change at merchandiser page.
	 * 
	 * @param request
	 *            - HTTP request
	 * @param modelMap
	 *            - ModelMap object for JSP
	 * @return JSP name to show
	 */
	@RequestMapping(value = CONTROLER_NAME)
	public String handleMerchandiserControler(final HttpServletRequest request,
			final ModelMap modelMap) {
		MerchandiserEditHandler handler = new MerchandiserEditHandler(
				orderService, orderStatusService);
		handler.setReturnJspName(RETURN_JSP_STRING);
		return this.makeUpRequest(request, modelMap, handler);
	}
}

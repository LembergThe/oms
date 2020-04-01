/**
 * MerchandiserShowPageControler
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

import com.softserveinc.edu.oms.service.interfaces.IOrderItemService;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.web.AbstractControler;
import com.softserveinc.edu.oms.web.merchandiserpages.handlers.MerchandiserPageableHandler;
import com.softserveinc.edu.oms.web.util.pageable.PageJspType;

/**
 * This class implements controllers for showing merchandiser page (with and
 * without AJAX).
 * 
 * @author Vitalik
 * 
 */
@Controller
public final class MerchandiserShowPageControler extends AbstractControler {
	// Fields

	/**
	 * Order Service exemplar for order data access
	 */
	private IOrderService orderService;

	/**
	 * Order Item Service exemplar for order data access
	 */
	private IOrderItemService orderItemService;

	/**
	 * This is URL that catch this controller methods
	 */
	private final static String CONTROLER_WITH_AJAX_URL = "merchandiser.htm";
	private final static String CONTROLER_WITHOUT_AJAX_URL = "merchandiserWithoutAjax.htm";
	private final static String PAGEABLE_CONTROLER_URL = "merchandiserPageable.htm";

	/**
	 * Names of JSP files that return this controller. This JSP name user in
	 * Tiles
	 */
	private final static String JSP_WITH_AJAX_NAME = "merchandiser/merchandiserWithAjax";
	private final static String JSP_WITHOUT_AJAX_NAME = "merchandiser/merchandiserWithoutAjax";
	private final static String PAGEABLE_JSP_NAME = "merchandiser/components/merchandiserPageable";

	
	// Initialization

	/**
	 * This method automatically set orderItemService field.
	 * 
	 * @param orderItemService
	 *            - IOrderItemService object.
	 */
	@Autowired
	public void setOrderItemService(final IOrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

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

	// Controllers

	/**
	 * This method implements merchandiser page with AJAX.
	 * 
	 * @param request
	 *            - HTTP request
	 * @param modelMap
	 *            - ModelMap object for JSP
	 * @return JSP name to show
	 */
	@RequestMapping(value = CONTROLER_WITH_AJAX_URL)
	public String handleMerchandiserControler(final HttpServletRequest request,
			final ModelMap modelMap) {
		MerchandiserPageableHandler handler = new MerchandiserPageableHandler(
				orderService, orderItemService);
		handler.setPageJspType(PageJspType.JspWithAjax);
		handler.setJspName(JSP_WITH_AJAX_NAME);
		return this.makeUpRequest(request, modelMap, handler);
	}

	/**
	 * This method implements merchandiser page without AJAX.
	 * 
	 * @param request
	 *            - HTTP request
	 * @param modelMap
	 *            - ModelMap object for JSP
	 * @return JSP name to show
	 */
	@RequestMapping(value = CONTROLER_WITHOUT_AJAX_URL)
	public String handleMerchandiserWithoutAjaxControler(
			final HttpServletRequest request, final ModelMap modelMap) {
		MerchandiserPageableHandler handler = new MerchandiserPageableHandler(
				orderService, orderItemService);
		handler.setPageJspType(PageJspType.JspWithoutAjax);
		handler.setJspName(JSP_WITHOUT_AJAX_NAME);
		return this.makeUpRequest(request, modelMap, handler);
	}

	/**
	 * This method implements merchandiser current page for page with AJAX.
	 * 
	 * @param request
	 *            - HTTP request
	 * @param modelMap
	 *            - ModelMap object for JSP
	 * @return JSP name to show
	 */
	@RequestMapping(value = PAGEABLE_CONTROLER_URL)
	public String handleMerchandiserPageableControler(
			final HttpServletRequest request, final ModelMap modelMap) {
		MerchandiserPageableHandler handler = new MerchandiserPageableHandler(
				orderService, orderItemService);
		handler.setPageJspType(PageJspType.JspForCurrentPage);
		handler.setJspName(PAGEABLE_JSP_NAME);
		return this.makeUpRequest(request, modelMap, handler);
	}
}

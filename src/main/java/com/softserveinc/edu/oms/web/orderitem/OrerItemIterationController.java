//
// OrerItemIterationController
//
// 24 ρεπο. 2011
//
package com.softserveinc.edu.oms.web.orderitem;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.orderitem.model.OrderItemsLinesNumberModel;
import com.softserveinc.edu.oms.web.orderitem.model.OrderItemsPageModel;

/**
 * @author Ivanka
 * 
 */
@Controller
public class OrerItemIterationController extends OrderItemControllerUtil {

	@RequestMapping(value = "orderItemsFirst.htm", method = RequestMethod.POST)
	public String firstPage(final HttpServletRequest request, final ModelMap map) {

		Integer orderId = Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID));

		return redirectUrl(orderId, 0);
	}

	@RequestMapping(value = "orderItemsBackward.htm", method = RequestMethod.POST)
	public String prevPage(final HttpServletRequest request, final ModelMap map) {

		Integer page = getPageNumberFromRequest(request);
		Integer orderId = Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID));
		page--;

		return redirectUrl(orderId, page);
	}

	@RequestMapping(value = "orderItemsForward.htm", method = RequestMethod.POST)
	public String nextPage(final HttpServletRequest request, final ModelMap map) {

		Integer page = getPageNumberFromRequest(request);
		Integer orderId = Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID));
		page++;

		return redirectUrl(orderId, page);
	}

	@RequestMapping(value = "orderItemsLast.htm", method = RequestMethod.POST)
	public String lastPage(final HttpServletRequest request, final ModelMap map) {

		Integer orderId = Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID));

		OrderItemsLinesNumberModel linesNumber = getOrderItemsLinesNumberModel(request);
		OrderItemsPageModel pageModel = getOrderItemPageModel(request, orderId,
				linesNumber);

		pageModel.lastPage();
		return redirectUrl(orderId, pageModel.getCurrentPage());
	}
}

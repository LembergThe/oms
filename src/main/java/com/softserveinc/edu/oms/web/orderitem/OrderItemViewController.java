//
// OrderItemViewController
//
// 10 . 2011
//
package com.softserveinc.edu.oms.web.orderitem;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData;
import com.softserveinc.edu.oms.web.orderitem.util.OrderItemParameters;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;

/**
 * @author Ivanka
 * 
 */
@Controller
public class OrderItemViewController extends OrderItemControllerUtil {

	@RequestMapping(value = "orderItemsView.htm", method = RequestMethod.GET)
	public String listOfOrderItems(final HttpServletRequest request,
			final Locale locale, final ModelMap modelMap) {

		if (!isOrderIdValid(request)) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE + "="
					+ "select order to show its items!";
		}

		Integer orderId = Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID));

		ITemporaryOrderData orderData = SessionExplorer.getTemporaryOrderData(
				request, orderId);
		orderData.setIsEditable(false);
		fillModelMap(request, modelMap, orderData, locale);

		return "listViewOrderItems";
	}
}

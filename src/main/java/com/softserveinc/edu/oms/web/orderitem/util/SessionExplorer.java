//
// SessionExplorer
//
// 1 . 2011
//
package com.softserveinc.edu.oms.web.orderitem.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.TemporaryListOrderData;
import com.softserveinc.edu.oms.web.security.UserSecurityData;

/**
 * @author Ivanka
 * 
 */
public final class SessionExplorer {
	private SessionExplorer() {
	}

	public static TemporaryListOrderData getTemporaryListData(
			final HttpServletRequest request) {
		TemporaryListOrderData listOrderData = (TemporaryListOrderData) request
				.getSession().getAttribute(ParameterNames.ORDER_TEMPORARY_DATA);
		if (listOrderData == null) {
			listOrderData = new TemporaryListOrderData();
			request.getSession(true).setAttribute(
					ParameterNames.ORDER_TEMPORARY_DATA, listOrderData);
		}
		return listOrderData;
	}

	public static ITemporaryOrderData getTemporaryOrderData(
			final HttpServletRequest request, final Integer orderId) {
		TemporaryListOrderData listOrderData = SessionExplorer
				.getTemporaryListData(request);

		return listOrderData.getTemporaryOrderData(orderId);
	}

	public static User getLoggedUser() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();

			UserSecurityData userSecurityData = (UserSecurityData) principal;
			return userSecurityData.getUser();
		}
		return null;
	}
}

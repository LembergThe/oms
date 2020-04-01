package com.softserveinc.edu.oms.web.unifiederrorpage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Roman Nazarevych
 * 
 */
@Controller
public class ErrorController {

	/**
	 * 
	 * @param map
	 * @param errorCause
	 * @return
	 */
	@RequestMapping(value = "errorpage.htm", method = RequestMethod.GET)
	public String showErrorPage(final Map<String, Object> map,
			final HttpServletRequest request) {

		map.put("errorinfo", request.getSession().getAttribute("errorinfo"));
		request.getSession().removeAttribute("errorinfo");

		return "error";

	}

	/**
	 * This method return string that redirects to eror page To use this write
	 * something like this in your controller: @
	 * {@code 
	 * ErrorInfo errorInfo = new
	 * ErrorInfo(); errorInfo.setMessageToUser("description for end user");
	 * errorInfo .setException(new
	 * Exception("this can be exception that you had caught"));
	 * 
	 * return ErrorController.redirectToErrorPage(errorInfo, request); -
	 * redirect user to error page 
	 * }
	 * 
	 * @param errorInfo
	 *            object with information about error
	 * @param request
	 *            - HttpServletRequest
	 * @return redirect to Error Page
	 */
	static public String redirectToErrorPage(final ErrorInfo errorInfo,
			final HttpServletRequest request) {
		request.getSession(true).setAttribute("errorinfo", errorInfo);
		// System.out.println(request.getSession().getAttribute("errorinfo"));

		return "redirect:errorpage.htm";
	}

}

/**
 * 
 */
package com.softserveinc.edu.oms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

/**
 * @author Vitalik
 * 
 */
public interface IHandler {
	/**
	 * This method can (and must) check current user permissions, validate
	 * required parameters, create PageModel object, etc. Also it set modelMap
	 * data for some PageJspType.
	 * 
	 * @param request
	 *            - HTTP request object
	 * @param modelMap
	 *            - view model map object
	 * @return JSP name to show
	 * @throws Exception
	 *             if it throw exception, there is something bad happen. This
	 *             exception must catch AbstractControler method "makeUpRequest"
	 */
	public String handle(final HttpServletRequest request,
			final ModelMap modelMap) throws Exception;
}

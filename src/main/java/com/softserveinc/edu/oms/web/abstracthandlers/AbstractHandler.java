/**
 * AbstractHandler
 *
 * Version 1.0
 *
 * Date 16.09.11
 *
 * Copyright Softserve
 */
package com.softserveinc.edu.oms.web.abstracthandlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.softserveinc.edu.oms.web.IHandler;

/**
 * This is AbstractHandler class. If you want create your own handler
 * realization you can inheritance this class.
 * 
 * @author Vitalik
 * 
 */
public abstract class AbstractHandler implements IHandler {
	// IHadler method implementation

	/*
	 * In this implementation method check current user permissions and validate
	 * required parameters. After this it call handlerLogic method.
	 * 
	 * @see com.softserveinc.edu.oms.web.IHandler#handle(javax.servlet.http.
	 * HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	public final String handle(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		this.initialize();
		if (isValidRequiredParameters(request)) {
			if (checkCurrentUserPermissions(request)) {
				return this.hanlerLogic(request, modelMap);
			}
		}
		throw new Exception("AbstractHandler class unknown exception");
	}

	// Methods for overriding

	/**
	 * This method must check object fields (for example, ==null).
	 */
	protected void initialize() throws Exception {
	}

	/**
	 * This method check if HTTP request contain all needed parameters for this
	 * page. You must override this method if there is some required parameters
	 * for your page (like "orderId","userId", etc.).
	 * 
	 * @param request
	 *            - HTTP request from users browser
	 * @return boolean value that mean validation result.
	 * @throws ControlerErrorException
	 *             Throw this exception in such situations: 1) request don't
	 *             contain required parameter with some name; 2) request contain
	 *             required parameter but there is parse exception;
	 */
	protected boolean isValidRequiredParameters(final HttpServletRequest request)
			throws Exception {
		return true;
	}

	/**
	 * This method check if current user have permissions for viewing
	 * information on this page (Role, Tenant, etc.). You must override this
	 * method if there is some security on your page.
	 * 
	 * @return boolean value that mean check result.
	 * @throws ControlerErrorException
	 *             Throw this exception in such situations: 1) current user
	 *             haven't permissions for this information; 2) there is some
	 *             other exception
	 */
	protected boolean checkCurrentUserPermissions(
			final HttpServletRequest request) throws Exception {
		return true;
	}

	// Abstract methods for implementation

	/**
	 * This method contain all handler logic: getting parameters values from
	 * request, work with services, putting data in modelMap
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
	protected abstract String hanlerLogic(HttpServletRequest request,
			ModelMap modelMap) throws Exception;
}

/**
 * AbstractCRUDHandler
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

/**
 * This class can be used for extending and realization of create(?), edit and
 * delete JSP.
 * 
 * @author Vitalik
 * 
 */
public abstract class AbstractCRUDHandler extends AbstractHandler {
	// Fields
	/**
	 * This name of JSP that returns if there is no exception happen.
	 */
	private String returnJspName;

	// AbstractHandler methods overriding

	/**
	 * This method run executeCRUDOperation method and return returnJspName if
	 * no exception happen.
	 */
	@Override
	protected final String hanlerLogic(HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		executeCRUDOperation(request);
		return this.returnJspName;
	}

	/**
	 * This method check returnJspName field.
	 */
	@Override
	protected void initialize() throws Exception {
		if (this.returnJspName == null) {
			throw new Exception(
					"AbstractCRUDHandler must have not null \"returnJspName\" field");
		} else if (this.returnJspName.equals("")) {
			throw new Exception(
					"AbstractCRUDHandler must have not empty(\"\") \"returnJspName\" field");
		}
	}

	// Abstract methods

	/**
	 * This method must contain code for executing of some CRUD operation
	 * 
	 * @param request
	 *            - HTTP request object
	 * @throws Exception
	 *             if it throw exception, there is something bad happen. This
	 *             exception must catch AbstractControler method "makeUpRequest"
	 */
	protected abstract void executeCRUDOperation(HttpServletRequest request)
			throws Exception;

	// Getters and Setters

	/**
	 * Get name of JSP that returns if there is no exception happen.
	 * 
	 * @return the jspName from this object.
	 */
	public final String getReturnJspName() {
		return returnJspName;
	}

	/**
	 * Set name of JSP that returns if there is no exception happen.
	 * 
	 * @param returnJspName
	 *            - the jspName to set.
	 */
	public final void setReturnJspName(String returnJspName) {
		this.returnJspName = returnJspName;
	}
}

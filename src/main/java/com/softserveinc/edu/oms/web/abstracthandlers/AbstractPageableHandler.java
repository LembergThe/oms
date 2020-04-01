/**
 * AbstractPageableHandler
 *
 * Version 1.0
 *
 * Date 15.09.11
 *
 * Copyright Softserve
 */
package com.softserveinc.edu.oms.web.abstracthandlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.softserveinc.edu.oms.web.util.pageable.PageJspType;
import com.softserveinc.edu.oms.web.util.pageable.PageModel;

/**
 * This class can be used for extending and realization of pageable JSP.
 * 
 * @author Vitalik
 * 
 */
public abstract class AbstractPageableHandler extends AbstractHandler {
	// Fields

	/**
	 * PageJspType enumerable value that used in hanlerLogic method for deciding
	 * what page data set in modelMap.
	 */
	private PageJspType pageJspType;

	/**
	 * This is name of JSP that returns if there is no exception happen.
	 */
	private String jspName;

	// AbstractHandler methods overriding

	/**
	 * This method create PageModel object and set modelMap data for some
	 * PageJspType.
	 * 
	 * @param request
	 *            - HTTP request object
	 * @param modelMap
	 *            - view model map object
	 * @return
	 */
	@Override
	protected final String hanlerLogic(HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		// Create pageModel object
		PageModel pageModel = new PageModel(
				getNumberOfAllElementsForPageModel(request), request);
		// Set page data
		switch (pageJspType) {
		case JspWithAjax:
			setGeneralPageData(request, modelMap, pageModel);
			break;
		case JspWithoutAjax:
			setGeneralPageData(request, modelMap, pageModel);
			setCurrentPageData(request, modelMap, pageModel);
			break;
		case JspForCurrentPage:
			setCurrentPageData(request, modelMap, pageModel);
			break;
		default:
			throw new Exception(
					"AbstractPageableHandler don't support such PageJspType");
		}
		return jspName;
	}

	/**
	 * This method check pageJspType and jspName fields.
	 */
	@Override
	protected void initialize() throws Exception {
		if (this.pageJspType == null) {
			throw new Exception(
					"AbstractPageableHandler must have not null \"pageJspType\" field");
		}
		if (this.jspName == null) {
			throw new Exception(
					"AbstractPageableHandler must have not null \"jspName\" field");
		} else if (this.jspName.equals("")) {
			throw new Exception(
					"AbstractPageableHandler must have not empty(\"\") \"jspName\" field");
		}
	}

	// Abstract methods

	/**
	 * This method get number of elements that that satisfy request, using
	 * services. You must implement this method if you want use
	 * AbstractPageableHandler.
	 * 
	 * @param request
	 *            - HTTP request from users browser
	 * @return integer number of elements that that satisfy request
	 */
	protected abstract int getNumberOfAllElementsForPageModel(
			HttpServletRequest request);

	// Methods that can be override if needed

	/**
	 * This method add general page data (such data that don't change if you go
	 * from page 1 to another page of pageable list) to the modelMap.
	 * 
	 * @param request
	 *            - HTTP request object
	 * @param modelMap
	 *            - view model map object
	 * @param pageModel
	 *            - view pageModel object
	 */
	protected void setGeneralPageData(final HttpServletRequest request,
			final ModelMap modelMap, final PageModel pageModel) {
	}

	/**
	 * This method add data for current page (for example, page 1 from 3 pages)
	 * of pageable JSP to the modelMap.
	 * 
	 * @param request
	 *            - HTTP request object
	 * @param modelMap
	 *            - view model map object
	 * @param pageModel
	 *            - view pageModel object
	 */
	protected void setCurrentPageData(final HttpServletRequest request,
			final ModelMap modelMap, final PageModel pageModel) {
	}

	// Getters and Setters

	/**
	 * Get PageJspType enumerable value that used in hanlerLogic method for
	 * deciding what page data set in modelMap.
	 * 
	 * @return PageJspType enumerable value
	 */
	public final PageJspType getPageJspType() {
		return pageJspType;
	}

	/**
	 * Set PageJspType enumerable value that used in hanlerLogic method for
	 * deciding what page data set in modelMap.
	 * 
	 * @param pageJspType
	 *            - PageJspType enumerable value.
	 */
	public final void setPageJspType(PageJspType pageJspType) {
		this.pageJspType = pageJspType;
	}

	/**
	 * Get name of JSP that returns if there is no exception happen.
	 * 
	 * @return the jspName from this object.
	 */
	public final String getJspName() {
		return jspName;
	}

	/**
	 * Set name of JSP that returns if there is no exception happen.
	 * 
	 * @param jspName
	 *            - the jspName to set.
	 */
	public final void setJspName(String jspName) {
		this.jspName = jspName;
	}
}

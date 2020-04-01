/**
 * PageableParameters
 *
 * Version 1.0
 *
 * Date 15.09.11
 *
 * Copyright Softserve
 */

package com.softserveinc.edu.oms.web.util.pageable;

/**
 * This class contain parameters values for PageModel class.
 * 
 * @author Vitalik
 * 
 */
public class PageableParameters {
	public static final String PAGE = "page";
	public static final String SHOW_ELEMENTS = "showElements";
	public static final String CONTROLER_WITHOUT_AJAX_URL = "controlerWithoutAjaxUrl";

	/**
	 * This is private constructor that don't allow to create objects of this
	 * class.
	 */
	private PageableParameters() {
	}
}

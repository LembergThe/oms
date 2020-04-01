/**
 * PageShowElements
 *
 * Version 1.1
 *
 * Date 15.09.11
 *
 * Copyright Softserve
 */

package com.softserveinc.edu.oms.web.util.pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * This enumerable contain max number of elements that can be shown on some
 * page. It used in PageModel class and pageable JSPs.
 * 
 * @author Vitalik
 */
public enum PageShowElements {
	/**
	 * This is list of allowed max number of elements that can be shown on some
	 * page.
	 */
	One(1), Two(2), Five(5), Ten(10), TwentyFive(25), Fifty(50);
	/**
	 * Max number of elements that can be shown on some page.
	 */
	private int showElements;

	/**
	 * This constructor create enumerable values.
	 * 
	 * @param number
	 *            - max number of elements that can be shown on some page
	 */
	private PageShowElements(final int number) {
		this.showElements = number;
	}

	/**
	 * This method get max number of elements that can be shown on some page
	 * with such PageShowElements value in PageModel object.
	 * 
	 * @return max number of elements that can be shown on some page
	 */
	public int getIntegerValue() {
		return this.showElements;
	}

	/**
	 * This method return list of all available PageShowElements enumerable
	 * values.
	 * 
	 * @return list of PageShowElements
	 */
	public static List<PageShowElements> getAllPageShowElementsList() {
		List<PageShowElements> resaultList = new ArrayList<PageShowElements>();
		for (PageShowElements pageShowElements : PageShowElements.values()) {
			resaultList.add(pageShowElements);
		}
		return resaultList;
	}

	/**
	 * This method return such PageShowElements value that respond to some
	 * integer.
	 * 
	 * @param integerValue
	 *            - integer number from HTTP request.
	 * @return PageShowElements value that respond to integerValue
	 * @throws ControlerErrorException
	 *             Throw this exception if there is no such PageShowElements
	 *             value that respond to integerValue
	 */
	public static PageShowElements getPageShowElementsFromInteger(
			final int integerValue) throws Exception {
		for (PageShowElements pageShowElement : PageShowElements.values()) {
			if (pageShowElement.getIntegerValue() == integerValue) {
				return pageShowElement;
			}
		}
		throw new Exception(
				"There is no PageShowElements object for this integer: "
						+ integerValue);
	}
}

/**
 * 
 */
package com.softserveinc.edu.oms.web.pageablecontroler.util;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.edu.oms.web.ControlerErrorException;

/**
 * @author Vitalik
 * 
 */
public enum PageShowElements {
	One(1), Two(2), Five(5), Ten(10), TwentyFive(25), Fifty(50);
	private int showElements;

	private PageShowElements(int number) {
		this.showElements = number;
	}

	public int getIntegerValue() {
		return this.showElements;
	}

	public static List<PageShowElements> getAllPageShowElementsList() {
		List<PageShowElements> resaultList = new ArrayList<PageShowElements>();
		for (PageShowElements pageShowElements : PageShowElements.values()) {
			resaultList.add(pageShowElements);
		}
		return resaultList;
	}

	public static PageShowElements getPageShowElementsFromInteger(
			int integerValue) throws ControlerErrorException {
		for (PageShowElements pageShowElement : PageShowElements.values()) {
			if (pageShowElement.getIntegerValue() == integerValue)
				return pageShowElement;
		}
		throw new ControlerErrorException("",
				"There is no PageShowElements object for integer: "
						+ integerValue);
	}
}

/**
 * 
 */
package com.softserveinc.edu.oms.web.util.pageable;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Vitalik
 * 
 */
public enum PageActions {
	FirstPageAction("First Page"), PreviousPageAction("Previous page"), NextPageAction(
			"Next Page"), LastPageAction("Last Page");
	private String actionName;

	private PageActions(String actionName) {
		this.actionName = actionName;
	}

	public String getActionName() {
		return this.actionName;
	}

	public static List<PageActions> getAllPageActionsList() {
		ArrayList<PageActions> resaultList = new ArrayList<PageActions>();
		for (PageActions pageActions : PageActions.values()) {
			resaultList.add(pageActions);
		}
		return resaultList;
	}

	public int getActionResultPage(PageModel pageModel) {
		switch (this) {
		case FirstPageAction:
			return 1;
		case PreviousPageAction:
			return pageModel.getCurrentPage() - 1;
		case NextPageAction:
			return pageModel.getCurrentPage() + 1;
		case LastPageAction:
			return pageModel.getNumberOfPages();
		default:
			return 0;
		}
	}
}

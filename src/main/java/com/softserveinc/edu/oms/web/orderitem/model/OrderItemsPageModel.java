//
// OrderItemPage
//
// 23 ρεπο. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivanka
 * 
 */
public class OrderItemsPageModel {
	public static final String FIRST_PAGE = "First";
	public static final String PREV_PAGE = "Backward";
	public static final String NEXT_PAGE = "Forward";
	public static final String LAST_PAGE = "Last";

	private final List<String> actionNames;
	{
		actionNames = new ArrayList<String>();
		actionNames.add(FIRST_PAGE);
		actionNames.add(PREV_PAGE);
		actionNames.add(NEXT_PAGE);
		actionNames.add(LAST_PAGE);
	}

	private final Integer numberOfPages;
	private Integer currentPage = 0;

	public OrderItemsPageModel(final Integer numberOfPages,
			final Integer currentPage) {
		super();
		this.numberOfPages = numberOfPages;
		this.currentPage = currentPage;
	}

	public void nextPage() {
		currentPage++;
	}

	public void prevPage() {
		currentPage--;
	}

	public void firstPage() {
		currentPage = 0;
	}

	public void lastPage() {
		currentPage = numberOfPages - 1;
	}

	public Boolean isLastPage() {
		if (numberOfPages.equals(0)) {
			return true;
		}
		return currentPage.equals(numberOfPages - 1);
	}

	public Boolean isFirstPage() {
		if (numberOfPages.equals(0)) {
			return true;
		}
		return currentPage.equals(0);
	}

	public Boolean hasPrevPage() {
		return currentPage > 0;
	}

	public Boolean hasNextPage() {
		return currentPage < numberOfPages - 1;
	}

	public Boolean isDisabled(final String actionName) {
		if (actionName.equals(FIRST_PAGE)) {
			return isFirstPage();
		}
		if (actionName.equals(PREV_PAGE)) {
			return !hasPrevPage();
		}
		if (actionName.equals(NEXT_PAGE)) {
			return !hasNextPage();
		}
		if (actionName.equals(LAST_PAGE)) {
			return isLastPage();
		}
		return false;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return
	 */
	public boolean isPageValid() {
		if (numberOfPages.equals(0)) {
			return currentPage.equals(0);

		}
		return currentPage >= 0 && currentPage < numberOfPages;
	}

	public Integer validatePageNumber() {
		if (getCurrentPage() >= numberOfPages) {
			currentPage = numberOfPages - 1;
		}

		if (getCurrentPage() <= 0) {
			currentPage = 0;
		}

		return currentPage;
	}

	public List<String> getActionNames() {
		return actionNames;
	}

	/**
	 * @return
	 */
	public Integer getNumberOfPages() {
		return numberOfPages;
	}

}

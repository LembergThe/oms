package com.softserveinc.edu.oms.web.util;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

public class SearchModel {
	public final static Integer DEFAULT_PAGE_SIZE = 5;
	public final static Integer DEFAULT_PAGE_SIZE_CHANGE = 10;
	public final static Integer DEFAULT_PAGE = 1;
	public final static Integer DEFAULT_PAGE_START = 0;

	private String selectField;
	private String selectCondition;
	private String searchValue;
	private String pageStartPosition;
	private String pageSize;
	private String pageSizeChange;
	private String recordsFound;

	private boolean nextPage;
	private boolean previousPage;
	private boolean lastPage;
	private boolean firstPage;

	private SortProperties sortProperties;

	public SearchModel() {
		pageSize = DEFAULT_PAGE_SIZE.toString();
		pageSizeChange = DEFAULT_PAGE_SIZE_CHANGE.toString();
		pageStartPosition = DEFAULT_PAGE_START.toString();

		setSortProperties(new SortProperties());
	}

	public String getSelectField() {
		return selectField;
	}

	public void setSelectField(final String selectField) {
		this.selectField = selectField;
	}

	public String getSelectCondition() {
		return selectCondition;
	}

	public void setSelectCondition(final String selectCondition) {
		this.selectCondition = selectCondition;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(final String searchValue) {
		this.searchValue = searchValue;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(final String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageSizeChange() {
		return pageSizeChange;
	}

	public void setPageSizeChange(final String pageSizeChange) {
		this.pageSizeChange = pageSizeChange;
	}

	public String getPageStartPosition() {
		return pageStartPosition;
	}

	public void setPageStartPosition(final String pageStartPosition) {
		this.pageStartPosition = pageStartPosition;
	}

	@Override
	public String toString() {
		return "UserSearchModel [selectField=" + selectField
				+ ", selectCondition=" + selectCondition + ", searchValue="
				+ searchValue + ", pageSize=" + pageSize + ", pageSizeChange="
				+ pageSizeChange + ", usersFound=" + getRecordsFound() + "]";
	}

	public SortProperties getSortProperties() {
		return sortProperties;
	}

	public void setSortProperties(final SortProperties sortProperties) {
		this.sortProperties = sortProperties;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(final boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(final boolean lastPage) {
		this.lastPage = lastPage;
	}

	public boolean isPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(final boolean previousPage) {
		this.previousPage = previousPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(final boolean nextPage) {
		this.nextPage = nextPage;
	}

	public String getRecordsFound() {
		return recordsFound;
	}

	public void setRecordsFound(final String recordsFound) {
		this.recordsFound = recordsFound;
	}
}

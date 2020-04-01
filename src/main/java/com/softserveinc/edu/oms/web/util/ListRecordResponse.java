package com.softserveinc.edu.oms.web.util;

import java.util.List;

public class ListRecordResponse<T> {
	private List<T> records;
	private String recordsFound;
	private String page;
	private String pageCount;

	public ListRecordResponse() {

	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(final List<T> records) {
		this.records = records;
	}

	public String getPage() {
		return page;
	}

	public void setPage(final String page) {
		this.page = page;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(final String pageCount) {
		this.pageCount = pageCount;
	}

	public String getRecordsFound() {
		return recordsFound;
	}

	public void setRecordsFound(String recordsFound) {
		this.recordsFound = recordsFound;
	}

}

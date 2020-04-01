package com.softserveinc.edu.oms.web.util;

public class PagingResolver {
	private final static PagingResolver INSTANCE = new PagingResolver();

	private PagingResolver() {
	}

	public static PagingResolver getInstance() {
		return INSTANCE;
	}

	public SearchModel handlePaging(final SearchModel searchModel,
			final String pageCommand) {
		if ("first".equalsIgnoreCase(pageCommand)) {

			return handleFirstPage(searchModel);
		} else if ("last".equalsIgnoreCase(pageCommand)) {
			return handleLastPage(searchModel);
		} else if ("next".equalsIgnoreCase(pageCommand)) {

			return handleNextPage(searchModel);
		} else if ("previous".equalsIgnoreCase(pageCommand)) {

			return handlePreviousPage(searchModel);
		}

		return searchModel;
	}

	private SearchModel handleFirstPage(final SearchModel searchModel) {
		searchModel.setPageStartPosition(SearchModel.DEFAULT_PAGE_START
				.toString());

		return searchModel;
	}

	private SearchModel handleLastPage(final SearchModel searchModel) {
		Integer pageSize = Integer.parseInt(searchModel.getPageSize());
		Long size = Long.parseLong(searchModel.getRecordsFound());

		int lastPageSize = (int) (size % pageSize);

		Long startPosition = size - lastPageSize;

		if (startPosition.equals(size)) {
			startPosition -= pageSize;
		}

		searchModel.setPageStartPosition(startPosition.toString());

		return searchModel;
	}

	private SearchModel handleNextPage(final SearchModel searchModel) {
		Integer startPosition = Integer.parseInt(searchModel
				.getPageStartPosition());
		Integer pageSize = Integer.parseInt(searchModel.getPageSize());

		startPosition += pageSize;

		Long size = Long.parseLong(searchModel.getRecordsFound());

		if (startPosition < size) {
			searchModel.setPageStartPosition(startPosition.toString());
		}

		return searchModel;
	}

	private SearchModel handlePreviousPage(final SearchModel searchModel) {
		Integer startPosition = Integer.parseInt(searchModel
				.getPageStartPosition());
		Integer pageSize = Integer.parseInt(searchModel.getPageSize());

		startPosition -= pageSize;

		if (startPosition < 0) {
			startPosition = 0;
		}

		searchModel.setPageStartPosition(startPosition.toString());

		return searchModel;
	}
}

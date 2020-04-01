package com.softserveinc.edu.oms.web.user;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;
import com.softserveinc.edu.oms.service.interfaces.IUserService;
import com.softserveinc.edu.oms.web.user.util.UserSortResolver;
import com.softserveinc.edu.oms.web.util.ListRecordResponse;
import com.softserveinc.edu.oms.web.util.PagingResolver;
import com.softserveinc.edu.oms.web.util.SearchModel;

@Controller
public abstract class AbstractListUsersController {
	private static final int FIRST_PAGE = 1;
	protected static final String PAGE_COMMAND_PARAM_NAME = "pageCommand";
	protected static final String PAGE_PARAM_NAME = "pageNumber";
	protected static final String PAGES_PARAM_NAME = "pages";
	protected static final String SEARCH_MODEL_PARAM_NAME = "searchModel";

	protected static final Integer PAGE_MODE_1 = SearchModel.DEFAULT_PAGE_SIZE;
	protected static final Integer PAGE_MODE_2 = SearchModel.DEFAULT_PAGE_SIZE_CHANGE;

	protected IUserService userService;

	protected abstract String redirectAfterAction();

	protected abstract String jspCall();

	protected abstract void publishLinks(final ModelMap modelMap);

	protected ListRecordResponse<User> getResponse(
			final HttpServletRequest request, final ModelMap modelMap) {
		SearchModel searchModel = (SearchModel) request.getSession()
				.getAttribute(SEARCH_MODEL_PARAM_NAME);

		if (searchModel == null) {
			searchModel = new SearchModel();
			request.getSession(true).setAttribute(SEARCH_MODEL_PARAM_NAME,
					searchModel);
		}

		publishLinks(modelMap);

		ListRecordResponse<User> response = new ListRecordResponse<User>();

		response.setRecordsFound(countFoundUsers(searchModel).toString());

		Integer page = definePage(searchModel, modelMap);
		Integer pages = countPages(searchModel, modelMap);

		validateButtons(searchModel, page, pages);

		response.setPage(page.toString());
		response.setPageCount(pages.toString());

		response.setRecords(processSearch(searchModel, modelMap));

		return response;
	}

	private void validateButtons(final SearchModel searchModel,
			final Integer page, final Integer pages) {
		if (page.equals(FIRST_PAGE)) {
			searchModel.setFirstPage(false);
			searchModel.setPreviousPage(false);
		} else {
			searchModel.setFirstPage(true);
			searchModel.setPreviousPage(true);
		}

		if (page.equals(pages)) {
			searchModel.setLastPage(false);
			searchModel.setNextPage(false);
		} else {
			searchModel.setLastPage(true);
			searchModel.setNextPage(true);
		}
	}

	public String handleRequest(final HttpServletRequest request,
			final ModelMap modelMap) {
		getResponse(request, modelMap);

		return jspCall();
	}

	public ListRecordResponse<User> handleAjaxRequest(
			final HttpServletRequest request, final ModelMap modelMap,
			final SearchModel updatedSearchModel) {
		updateSearchModel(request, updatedSearchModel);

		return getResponse(request, modelMap);
	}

	public String handleSort(final HttpServletRequest request,
			final ModelMap modelMap) {
		SearchModel searchModel = (SearchModel) request.getSession()
				.getAttribute(SEARCH_MODEL_PARAM_NAME);

		final String propertyName = request.getParameter("propertyName");

		UserSortResolver.getInstance().handleSort(searchModel, propertyName);

		request.getSession(true).setAttribute(SEARCH_MODEL_PARAM_NAME,
				searchModel);

		return redirectAfterAction();
	}

	public String handlePageSize(final HttpServletRequest request) {
		SearchModel searchModel = (SearchModel) request.getSession()
				.getAttribute(SEARCH_MODEL_PARAM_NAME);
		String oldPageSize = searchModel.getPageSize();

		if (oldPageSize.equals(PAGE_MODE_1.toString())) {
			searchModel.setPageSize(PAGE_MODE_2.toString());
			searchModel.setPageSizeChange(PAGE_MODE_1.toString());
		} else {
			searchModel.setPageSize(PAGE_MODE_1.toString());
			searchModel.setPageSizeChange(PAGE_MODE_2.toString());
		}

		return redirectAfterAction();
	}

	public String handleSearch(final HttpServletRequest request,
			final SearchModel updatedSearchModel, final BindingResult result) {
		updateSearchModel(request, updatedSearchModel);

		return redirectAfterAction();
	}

	protected void updateSearchModel(final HttpServletRequest request,
			final SearchModel updatedSearchModel) {
		SearchModel searchModel = (SearchModel) request.getSession()
				.getAttribute(SEARCH_MODEL_PARAM_NAME);

		searchModel.setPageStartPosition(SearchModel.DEFAULT_PAGE_START
				.toString());
		searchModel.setSearchValue(updatedSearchModel.getSearchValue());
		searchModel.setSelectCondition(updatedSearchModel.getSelectCondition());
		searchModel.setSelectField(updatedSearchModel.getSelectField());

		request.getSession(true).setAttribute(SEARCH_MODEL_PARAM_NAME,
				searchModel);
	}

	public String handlePage(final HttpServletRequest request,
			final Locale locale) {
		SearchModel searchModel = (SearchModel) request.getSession()
				.getAttribute(SEARCH_MODEL_PARAM_NAME);

		final String pageCommand = request
				.getParameter(PAGE_COMMAND_PARAM_NAME);
		searchModel = PagingResolver.getInstance().handlePaging(searchModel,
				pageCommand);

		request.getSession(true).setAttribute(SEARCH_MODEL_PARAM_NAME,
				searchModel);
		return redirectAfterAction();
	}

	protected List<User> processSearch(final SearchModel searchModel,
			final ModelMap modelMap) {
		List<User> users;

		String searchValue = searchModel.getSearchValue();

		Integer startPosition = Integer.parseInt(searchModel
				.getPageStartPosition());
		Integer pageSize = Integer.parseInt(searchModel.getPageSize());

		if (searchValue == null || searchValue.equalsIgnoreCase("")) {
			users = userService.findAll(startPosition, pageSize,
					searchModel.getSortProperties());
		} else {
			UserSelectField selectField = defineUserSelectField(searchModel
					.getSelectField());
			UserSelectWayCondition condition = defineSelectCondition(searchModel
					.getSelectCondition());

			users = userService.findUsersBySearchValue(searchValue,
					selectField, condition, startPosition, pageSize,
					searchModel.getSortProperties());
		}

		modelMap.addAttribute("users", users);
		modelMap.addAttribute(SEARCH_MODEL_PARAM_NAME, searchModel);
		modelMap.addAttribute("selectConditions",
				UserSelectWayCondition.values());
		modelMap.addAttribute("userSelectFields", UserSelectField.values());

		return users;
	}

	protected Long countFoundUsers(final SearchModel searchModel) {
		final Long size;

		String searchValue = searchModel.getSearchValue();

		if (searchValue == null || searchValue.equalsIgnoreCase("")) {
			size = userService.getRowCount();
		} else {
			UserSelectField selectField = defineUserSelectField(searchModel
					.getSelectField());
			UserSelectWayCondition condition = defineSelectCondition(searchModel
					.getSelectCondition());

			size = userService.countUsersBySearchValue(searchValue,
					selectField, condition);
		}

		searchModel.setRecordsFound(size.toString());
		return size;
	}

	protected Integer countPages(final SearchModel searchModel,
			final ModelMap modelMap) {
		Long size = Long.parseLong(searchModel.getRecordsFound().toString());
		Integer pageSize = Integer.parseInt(searchModel.getPageSize());

		Integer pages = (int) (size / pageSize);

		if ((int) (size % pageSize) > 0) {
			pages++;
		}

		if (pages.equals(0)) {
			pages++;
		}

		modelMap.addAttribute(PAGES_PARAM_NAME, pages);

		return pages;
	}

	protected Integer definePage(final SearchModel searchModel,
			final ModelMap modelMap) {
		Integer pageSize = Integer.parseInt(searchModel.getPageSize());

		Integer startPosition = Integer.parseInt(searchModel
				.getPageStartPosition());

		Integer page = startPosition / pageSize + 1;

		if (page.equals(0)) {
			page++;
		}

		startPosition = (page - 1) * pageSize;

		Long size = Long.parseLong(searchModel.getRecordsFound().toString());

		if (size <= startPosition) {
			startPosition -= pageSize;
			page--;
		}

		searchModel.setPageStartPosition(startPosition.toString());

		modelMap.addAttribute(SEARCH_MODEL_PARAM_NAME, searchModel);
		modelMap.addAttribute(PAGE_PARAM_NAME, page);

		return page;
	}

	protected UserSelectField defineUserSelectField(final String value) {
		for (UserSelectField selectField : UserSelectField.values()) {
			if (selectField.name().equals(value)) {
				return selectField;
			}
		}
		return UserSelectField.ALL;
	}

	protected UserSelectWayCondition defineSelectCondition(final String value) {
		for (UserSelectWayCondition condition : UserSelectWayCondition.values()) {
			if (condition.name().equals(value)) {
				return condition;
			}
		}
		return UserSelectWayCondition.STARTS_WITH;
	}

	@Autowired
	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}
}

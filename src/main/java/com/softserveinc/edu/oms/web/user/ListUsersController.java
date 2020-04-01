package com.softserveinc.edu.oms.web.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.util.ListRecordResponse;
import com.softserveinc.edu.oms.web.util.SearchModel;

@Controller
public class ListUsersController extends AbstractListUsersController {

	private static final String URL = "users.htm";

	private static final String DELETE_USER_URL = "deleteUser.htm";
	private static final String PAGE_URL = "page.htm";
	private static final String SEARCH_USERS_URL = "searchUsers.htm";
	private static final String RESIZE_USERS_LIST_URL = "resizeUsersList.htm";
	private static final String USERS_SORT_URL = "usersSort.htm";

	private static final String DELETE_USER_URL_PARAM_NAME = "deleteUser";
	private static final String PAGE_URL_PARAM_NAME = "page";
	private static final String SEARCH_USERS_URL_PARAM_NAME = "searchUsers";
	private static final String RESIZE_USERS_LIST_URL_PARAM_NAME = "resizeUsersList";
	private static final String USERS_SORT_URL_PARAM_NAME = "usersSort";

	@Override
	protected String redirectAfterAction() {
		return "redirect:" + URL;
	}

	@Override
	protected String jspCall() {
		return "listUsers";
	}

	@Override
	protected void publishLinks(final ModelMap modelMap) {
		modelMap.put(DELETE_USER_URL_PARAM_NAME, DELETE_USER_URL);
		modelMap.put(PAGE_URL_PARAM_NAME, PAGE_URL);
		modelMap.put(RESIZE_USERS_LIST_URL_PARAM_NAME, RESIZE_USERS_LIST_URL);
		modelMap.put(SEARCH_USERS_URL_PARAM_NAME, SEARCH_USERS_URL);
		modelMap.put(USERS_SORT_URL_PARAM_NAME, USERS_SORT_URL);
	}

	@Override
	@RequestMapping("usersAJAX.htm")
	public @ResponseBody
	ListRecordResponse<User> handleAjaxRequest(
			final HttpServletRequest request, final ModelMap modelMap,
			final SearchModel updatedSearchModel) {
		return super.handleAjaxRequest(request, modelMap, updatedSearchModel);
	}

	@Override
	@RequestMapping(URL)
	public String handleRequest(final HttpServletRequest request,
			final ModelMap modelMap) {
		return super.handleRequest(request, modelMap);
	}

	@Override
	@RequestMapping(USERS_SORT_URL)
	public String handleSort(final HttpServletRequest request,
			final ModelMap modelMap) {
		return super.handleSort(request, modelMap);
	}

	@Override
	@RequestMapping(RESIZE_USERS_LIST_URL)
	public String handlePageSize(final HttpServletRequest request) {
		return super.handlePageSize(request);
	}

	@Override
	@RequestMapping(SEARCH_USERS_URL)
	public String handleSearch(final HttpServletRequest request,
			final SearchModel updatedSearchModel, final BindingResult result) {
		return super.handleSearch(request, updatedSearchModel, result);
	}

	@Override
	@RequestMapping(PAGE_URL)
	public String handlePage(final HttpServletRequest request,
			final Locale locale) {
		return super.handlePage(request, locale);
	}

	@RequestMapping(DELETE_USER_URL)
	public String handleDelete(final HttpServletRequest request,
			final ModelMap modelMap) throws Exception {

		String userID = request.getParameter("userID");

		Integer id = Integer.parseInt(userID);

		User user = userService.findByID(id);

		try {
			userService.delete(user);
		} catch (Exception exception) {
			modelMap.put("error", "User was deleted by another administrator");
			return "userDeleteError";
		}

		return "redirect:users.htm";
	}
}

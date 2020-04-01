package com.softserveinc.edu.oms.web.user;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;
import com.softserveinc.edu.oms.web.util.SearchModel;

@Controller
public class ReportListUserController extends AbstractListUsersController {

	private static final String URL = "/reportUsers.htm";

	private static final String GET_PDF_REPORT_USER_URL = "getReport.htm";
	private static final String PDF_REPORT_USER_URL = "report/pdf.htm";
	private static final String PAGE_URL = "report/page.htm";
	private static final String SEARCH_USERS_URL = "report/searchUsers.htm";
	private static final String RESIZE_USERS_LIST_URL = "report/resizeUsersList.htm";
	private static final String USERS_SORT_URL = "report/usersSort.htm";

	private static final String GET_PDF_REPORT_USER_URL_PARAM_NAME = "getPDF";
	private static final String PDF_REPORT_USER_URL_PARAM_NAME = "pdf";
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
		return "reportUsers";
	}

	@Override
	protected void publishLinks(final ModelMap modelMap) {
		modelMap.put(GET_PDF_REPORT_USER_URL_PARAM_NAME,
				GET_PDF_REPORT_USER_URL);
		modelMap.put(PDF_REPORT_USER_URL_PARAM_NAME, PDF_REPORT_USER_URL);
		modelMap.put(PAGE_URL_PARAM_NAME, PAGE_URL);
		modelMap.put(RESIZE_USERS_LIST_URL_PARAM_NAME, RESIZE_USERS_LIST_URL);
		modelMap.put(SEARCH_USERS_URL_PARAM_NAME, SEARCH_USERS_URL);
		modelMap.put(USERS_SORT_URL_PARAM_NAME, USERS_SORT_URL);
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

	@RequestMapping(GET_PDF_REPORT_USER_URL)
	public String getPDFReport(final HttpServletRequest request,
			final ModelMap modelMap) throws Exception {
		super.handleRequest(request, modelMap);
		return "user/report";
	}

	@RequestMapping(PDF_REPORT_USER_URL)
	public String printPDFReport(final HttpServletRequest request,
			final ModelMap modelMap) throws Exception {
		List<User> users = userService.findAll();

		SearchModel searchModel = (SearchModel) request.getSession()
				.getAttribute(SEARCH_MODEL_PARAM_NAME);

		String searchValue = searchModel.getSearchValue();

		if (searchValue == null || searchValue.equals("")) {
			users = userService.findAll(searchModel.getSortProperties());
		} else {
			UserSelectField selectField = defineUserSelectField(searchModel
					.getSelectField());
			UserSelectWayCondition condition = defineSelectCondition(searchModel
					.getSelectCondition());

			Integer size = countFoundUsers(searchModel).intValue();

			users = userService.findUsersBySearchValue(searchValue,
					selectField, condition, 0, size,
					searchModel.getSortProperties());
		}

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
				users);

		modelMap.put("dataSource", dataSource);

		modelMap.addAttribute("format", "pdf");

		return "reportUsersFile";
	}
}

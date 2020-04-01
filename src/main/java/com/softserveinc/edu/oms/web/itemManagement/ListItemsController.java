package com.softserveinc.edu.oms.web.itemManagement;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.web.util.ListRecordResponse;
import com.softserveinc.edu.oms.web.util.SearchModel;

@Controller
public class ListItemsController extends AbstractListItemsController {

	private static final String URL = "/itemManagement.htm";

	private static final String DELETE_ITEM_URL = "deleteItem.htm";
	private static final String PAGE_URL = "itemManagement/page.htm";
	private static final String SEARCH_ITEMS_URL = "itemManagement/searchItems.htm";
	private static final String RESIZE_ITEMS_LIST_URL = "itemManagement/resizeItemList.htm";
	private static final String ITEMS_SORT_URL = "itemManagement/itemSort.htm";

	private static final String DELETE_ITEM_URL_PARAM_NAME = "deleteItem";
	private static final String PAGE_URL_PARAM_NAME = "page";
	private static final String SEARCH_ITEMS_URL_PARAM_NAME = "searchItems";
	private static final String RESIZE_ITEMS_LIST_URL_PARAM_NAME = "resizeItemsList";
	private static final String ITEMS_SORT_URL_PARAM_NAME = "itemSort";

	@Override
	protected String redirectAfterAction() {
		return "redirect:" + URL;
	}

	@Override
	protected String jspCall() {
		return "listItems";
	}

	@Override
	protected void publishLinks(final ModelMap modelMap) {
		modelMap.put(DELETE_ITEM_URL_PARAM_NAME, DELETE_ITEM_URL);
		modelMap.put(PAGE_URL_PARAM_NAME, PAGE_URL);
		modelMap.put(RESIZE_ITEMS_LIST_URL_PARAM_NAME, RESIZE_ITEMS_LIST_URL);
		modelMap.put(SEARCH_ITEMS_URL_PARAM_NAME, SEARCH_ITEMS_URL);
		modelMap.put(ITEMS_SORT_URL_PARAM_NAME, ITEMS_SORT_URL);
	}

	@Override
	@RequestMapping("productsAJAX.htm")
	public @ResponseBody
	ListRecordResponse<Product> handleAjaxRequest(
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
	@RequestMapping(ITEMS_SORT_URL)
	public String handleSort(final HttpServletRequest request,
			final ModelMap modelMap) {
		return super.handleSort(request, modelMap);
	}

	@Override
	@RequestMapping(RESIZE_ITEMS_LIST_URL)
	public String handlePageSize(final HttpServletRequest request) {
		return super.handlePageSize(request);
	}

	@Override
	@RequestMapping(SEARCH_ITEMS_URL)
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

	@RequestMapping(DELETE_ITEM_URL)
	public String handleDelete(final HttpServletRequest request,
			final ModelMap modelMap) throws Exception {

		String productID = request.getParameter("productID");

		Integer id = Integer.parseInt(productID);

		Product product = productService.findByID(id);

		try {
			productService.delete(product);
		} catch (Exception exception) {
			modelMap.put("error", "This product is used, you cannot delete it");
			return "productDeleteError";
		}

		return redirectAfterAction();
	}
}

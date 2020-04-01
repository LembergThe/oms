package com.softserveinc.edu.oms.web.itemManagement;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.web.itemManagement.util.ProductSelectField;
import com.softserveinc.edu.oms.web.util.SearchModel;

@Controller
public class ReportListProductController extends AbstractListItemsController {

	private static final String URL = "/reportItems.htm";

	private static final String GET_PDF_REPORT_ITEM_URL = "getItemReport.htm";
	private static final String PDF_REPORT_ITEM_URL = "reportItem/pdf.htm";
	private static final String PAGE_URL = "reportItem/page.htm";
	private static final String SEARCH_ITEMS_URL = "reportItem/searchProducts.htm";
	private static final String RESIZE_ITEMS_LIST_URL = "reportItem/resizeProductsList.htm";
	private static final String ITEMS_SORT_URL = "reportItem/productsSort.htm";

	private static final String GET_PDF_REPORT_ITEM_URL_PARAM_NAME = "getPDF";
	private static final String PDF_REPORT_ITEM_URL_PARAM_NAME = "pdf";
	private static final String PAGE_URL_PARAM_NAME = "page";
	private static final String SEARCH_ITEMS_URL_PARAM_NAME = "searchProducts";
	private static final String RESIZE_ITEMS_LIST_URL_PARAM_NAME = "resizeItemList";
	private static final String ITEMS_SORT_URL_PARAM_NAME = "itemsSort";

	@Override
	protected String redirectAfterAction() {
		return "redirect:" + URL;
	}

	@Override
	protected String jspCall() {
		return "reportItems";
	}

	@Override
	protected void publishLinks(final ModelMap modelMap) {
		modelMap.put(GET_PDF_REPORT_ITEM_URL_PARAM_NAME,
				GET_PDF_REPORT_ITEM_URL);
		modelMap.put(PDF_REPORT_ITEM_URL_PARAM_NAME, PDF_REPORT_ITEM_URL);
		modelMap.put(PAGE_URL_PARAM_NAME, PAGE_URL);
		modelMap.put(RESIZE_ITEMS_LIST_URL_PARAM_NAME, RESIZE_ITEMS_LIST_URL);
		modelMap.put(SEARCH_ITEMS_URL_PARAM_NAME, SEARCH_ITEMS_URL);
		modelMap.put(ITEMS_SORT_URL_PARAM_NAME, ITEMS_SORT_URL);
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

	@RequestMapping(GET_PDF_REPORT_ITEM_URL)
	public String getPDFReport(final HttpServletRequest request,
			final ModelMap modelMap) throws Exception {
		super.handleRequest(request, modelMap);
		return "itemsManagement/report";
	}

	@RequestMapping(PDF_REPORT_ITEM_URL)
	public String printPDFReport(final HttpServletRequest request,
			final ModelMap modelMap) throws Exception {
		List<Product> products = productService.findAll();

		SearchModel searchModel = (SearchModel) request.getSession()
				.getAttribute(SEARCH_MODEL_PARAM_NAME);

		String searchValue = searchModel.getSearchValue();

		if (searchValue == null || searchValue.equalsIgnoreCase("")) {
			products = productService.findAll(searchModel.getSortProperties());
		} else {
			ProductSelectField selectField = defineProductSelectField(searchModel
					.getSelectField());

			Integer size = countFoundProducts(searchModel);

			products = productService.findBySearchValue(searchValue,
					selectField, 0, size, searchModel.getSortProperties());
		}

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
				products);

		modelMap.put("dataSource", dataSource);

		modelMap.addAttribute("format", "pdf");

		return "reportItemsFile";
	}
}

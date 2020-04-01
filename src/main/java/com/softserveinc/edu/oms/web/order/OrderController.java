/**
 * 
 */
package com.softserveinc.edu.oms.web.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;
import com.softserveinc.edu.oms.service.interfaces.IRoleService;
import com.softserveinc.edu.oms.web.order.OrderSortProperties.SortPropertiesValues;

/**
 * @author marko
 * 
 */

@Controller
// @SessionAttributes(value = OrderController.SEARCH_FILTER)
public class OrderController {

	protected static final String SEARCH_FILTER = "searchFilter";
	protected static final String SORT_PROPERTIES = "sortProperties";
	private static final Integer PAGE_SIZE = 2;

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IOrderStatusService orderStatusService;

	@RequestMapping(value = "order.htm", method = RequestMethod.GET)
	public String listOrders(HttpServletRequest request, final ModelMap map) {

		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request
				.getSession().getAttribute(SEARCH_FILTER);

		if (searchFilterOptions == null) {
			searchFilterOptions = new SearchFilterOptions();
			SearchFilterOptions.setFilter1(this.createFilterOrder());
			SearchFilterOptions.setFilter2(this.createFilterRole());
			searchFilterOptions.setFilterBy("orderStatus");
			request.getSession().setAttribute(SEARCH_FILTER,
					searchFilterOptions);
		}
		searchFilterOptions.setAllFoundAndFiltered(orderService
				.getRowCount(searchFilterOptions));
		System.err.println(searchFilterOptions);

		OrderSortProperties sortProperties = (OrderSortProperties) request
				.getSession().getAttribute(SORT_PROPERTIES);

		if (sortProperties == null) {
			sortProperties = new OrderSortProperties();
			request.getSession().setAttribute(SORT_PROPERTIES, sortProperties);
		}

		map.addAttribute(SEARCH_FILTER, searchFilterOptions);
		// map.addAttribute(SORT_PROPERTIES, sortProperties);

		prepareData(request, map);
		return "order";
	}

	@RequestMapping(value = "order.htm", method = RequestMethod.POST)
	public String listFilteredOrders(
			@ModelAttribute(SEARCH_FILTER) SearchFilterOptions searchFilterOptions,
			HttpServletRequest request, final ModelMap map) {
		System.out.println(searchFilterOptions);

		searchFilterOptions.setStart(0L);
		searchFilterOptions.setAllFoundAndFiltered(orderService
				.getRowCount(searchFilterOptions));

		request.getSession().setAttribute(SEARCH_FILTER, searchFilterOptions);
		request.getSession().setAttribute(SORT_PROPERTIES,
				new OrderSortProperties());

		prepareData(request, map);

		return "order";
	}

	@RequestMapping("deleteOrder.htm")
	public String handleDelete(HttpServletRequest request,
			final HttpServletResponse arg1) {
		Integer id = Integer.parseInt(request.getParameter("orderID"));
		try {
			orderService.delete(orderService.findByID(id));
		} catch (Exception exception) {
			// TODO add something here .... to show that cannot be deleted
		}

		return "redirect:order.htm";
	}

	@RequestMapping("editOrder.htm")
	public String handleEdit(HttpServletRequest request, ModelMap map) {
		Integer id = Integer.parseInt(request.getParameter("orderID"));
		Order order = orderService.findByID(id);
		map.addAttribute("order", order);
		return "redirect:order.htm";
	}

	@RequestMapping("orderFirstPage.htm")
	public String handleFirstPage(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request
				.getSession().getAttribute(SEARCH_FILTER);

		searchFilterOptions.setStart(0L);
		prepareData(request, map);
		return "redirect:order.htm";
	}

	@RequestMapping("orderLastPage.htm")
	public String handleLastPage(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request
				.getSession().getAttribute(SEARCH_FILTER);
		// TODO add code for getting number of all found and filtered orders

		searchFilterOptions.setStart((PAGE_SIZE
				* (searchFilterOptions.getAllFoundAndFiltered() / PAGE_SIZE)
				- PAGE_SIZE + searchFilterOptions.getAllFoundAndFiltered()
				% PAGE_SIZE));
		prepareData(request, map);
		return "redirect:order.htm";
	}

	@RequestMapping("orderNextPage.htm")
	public String handleNextPage(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request
				.getSession().getAttribute(SEARCH_FILTER);
		// TODO add code for getting number of all found and filtered orders
		searchFilterOptions
				.setStart(Math.min(
						PAGE_SIZE
								* (searchFilterOptions.getAllFoundAndFiltered() / PAGE_SIZE)
								- PAGE_SIZE
								+ searchFilterOptions.getAllFoundAndFiltered()
								% PAGE_SIZE, searchFilterOptions.getStart()
								+ PAGE_SIZE));
		prepareData(request, map);
		return "redirect:order.htm";
	}

	@RequestMapping("orderPreviousPage.htm")
	public String handlePreviousPage(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request
				.getSession().getAttribute(SEARCH_FILTER);
		searchFilterOptions.setStart(Math.max(searchFilterOptions.getStart()
				- PAGE_SIZE, 0));
		prepareData(request, map);
		return "redirect:order.htm";
	}

	private void prepareData(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request
				.getSession().getAttribute(SEARCH_FILTER);
		OrderSortProperties sortProperties = (OrderSortProperties) request
				.getSession().getAttribute(SORT_PROPERTIES);

		String sortValue = request.getParameter("propertyName");

		System.out.println();

		if ((sortValue != null) && (!sortValue.isEmpty())) {
			if (sortValue.equals("orderName")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_NAME);
			}
			if (sortValue.equals("totalPrice")) {
				sortProperties
						.setSortOption(SortPropertiesValues.ORDER_TOTAL_PRICE);
			}
			if (sortValue.equals("maxDiscount")) {
				sortProperties
						.setSortOption(SortPropertiesValues.ORDER_MAX_DISCOUNT);
			}
			if (sortValue.equals("deliveryDate")) {
				sortProperties
						.setSortOption(SortPropertiesValues.ORDER_DELIVERY_DATE);
			}
			if (sortValue.equals("status")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_STATUS);
			}
			if (sortValue.equals("assignee")) {
				sortProperties
						.setSortOption(SortPropertiesValues.ORDER_ASSIGNEE);
			}
			if (sortValue.equals("role")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_ROLE);
			}
		}

		map.addAttribute("orders", orderService.find(searchFilterOptions
				.getStart().intValue(), PAGE_SIZE, searchFilterOptions,
				sortProperties));

	}

	private String[] createFilterOrder() {

		List<OrderStatus> list = orderStatusService.findAll();
		String[] res = new String[list.size()+1];
		res[0] = "None";
		for (int i = 1; i < res.length; i++) {
			res[i] = list.get(i-1).getOrderStatusName();
		}
		return res;
	}

	private String[] createFilterRole() {

		List<Role> list = roleService.findAll();
		String[] res = new String[list.size()+1];
		res[0] = "None";
		for (int i = 1; i < res.length; i++) {
			res[i] = list.get(i-1).getRoleName();
		}
		return res;
	}

}

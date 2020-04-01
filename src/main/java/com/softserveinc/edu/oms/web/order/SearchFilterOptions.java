/**
 * 
 */
package com.softserveinc.edu.oms.web.order;

import java.util.Arrays;

//import org.springframework.stereotype.Component;

/**
 * @author marko
 * 
 */
// @Component
public class SearchFilterOptions {

	// @Autowired
	// private IOrderStatusService orderStatusService;
	// @Autowired
	// private IRoleService roleService;

	// private static final String[] filter1 = { "None", "Ordered", "Pending",
	// "Delivered" };
	// private static final String[] filter2 = { "None", "Merchandiser",
	// "Administrator", "Supervisor" };
	private static String[] filter1;
	private static String[] filter2;
	private String filterBy;
	private String filterValue;

	private String search;
	private String searchValue;
	private String[] filterValues;

	// for paging
	private Long start = 0L;
	private Long allFoundAndFiltered = 0L;

	public SearchFilterOptions() {
		this.filterBy = "orderStatus";
		this.filterValue = "";
		this.filterValues = filter1;

		this.search = "orderName";
		this.searchValue = "";
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
		if (filterBy.equalsIgnoreCase("orderStatus")) {
			this.filterValues = filter1;
		} else {
			if (filterBy.equalsIgnoreCase("Role")) {
				this.filterValues = filter2;
			}
		}
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long num) {
		this.start = num;
	}

	public void setAllFoundAndFiltered(Long allFoundAndFiltered) {
		this.allFoundAndFiltered = allFoundAndFiltered;
	}

	public Long getAllFoundAndFiltered() {
		return allFoundAndFiltered;
	}

	public String[] getFilterValues() {
		return filterValues;
	}

	public String getFilter1() {
		// List<OrderStatus> list = orderStatusService.findAll();
		StringBuffer sb = new StringBuffer("[\"");
		sb.append(filter1[0]);
		sb.append("\"");

		for (int i = 1; i < filter1.length; i++) {
			String orderStatus = filterValues[i];
			sb.append(", \"");
			sb.append(orderStatus);
			sb.append("\"");

		}
		sb.append("]");
		return sb.toString();
	}

	public String getFilter2() {
		StringBuffer sb = new StringBuffer("[\"");
		sb.append(filter2[0]);
		sb.append("\"");

		for (int i = 1; i < filter2.length; i++) {
			String role = filter2[i];
			sb.append(", \"");
			sb.append(role);
			sb.append("\"");

		}
		sb.append("]");
		return sb.toString();
	}

	public void setFilterValues(String[] filterValues) {
		this.filterValues = filterValues;
	}

	@Override
	public String toString() {
		return "SearchFilterOptions [filterBy=" + filterBy + ", filterValue="
				+ filterValue + ", search=" + search + ", searchValue="
				+ searchValue + ", filterValues="
				+ Arrays.toString(filterValues) + ", start=" + start
				+ ", allFoundAndFiltered=" + allFoundAndFiltered + "]";
	}

	public static void setFilter1(String[] filter1) {
		SearchFilterOptions.filter1 = filter1;
	}

	public static void setFilter2(String[] filter2) {
		SearchFilterOptions.filter2 = filter2;
	}

}

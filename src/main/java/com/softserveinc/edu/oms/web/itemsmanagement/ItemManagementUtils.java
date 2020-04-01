package com.softserveinc.edu.oms.web.itemsmanagement;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Product;

public class ItemManagementUtils {

	public static List<Product> paging(final int page, final int itemsPerPage,
			final List<Product> inList) {
		List<Product> list = new ArrayList<Product>();
		int count = 0;
		for (Product entry : inList) {
			if ((count >= page * itemsPerPage - itemsPerPage)
					&& (count < page * itemsPerPage)) {
				list.add(entry);
			}
			count++;
		}

		return list;
	}

}

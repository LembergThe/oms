//
// OrderService
//
// 21 . 2011
//
package com.softserveinc.edu.oms.service.implementation;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.persistence.dao.concrete.OrderDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.OrderStatusDao;
import com.softserveinc.edu.oms.persistence.dao.concrete.RoleDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.web.order.SearchFilterOptions;

/**
 * @author marko
 * 
 */
@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderDao dao;

	@Autowired
	private OrderStatusDao orderStatusDAO;

	@Autowired
	private RoleDao roleDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.ServiceForPaging#findAll(
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Transactional
	@Deprecated
	@Override
	public List<Order> findAll(final Integer startingFrom,
			final Integer maxResult) {
		// TODO Auto-generated method stub
		return dao.findAll(startingFrom, maxResult);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.ServiceForPaging#findAll(
	 * java.lang.Integer, java.lang.Integer,
	 * com.softserveinc.edu.oms.persistence.dao.params.SortProperties)
	 */
	@Transactional
	@Deprecated
	@Override
	public List<Order> findAll(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties) {
		return dao.findAll(startingFrom, maxResult, sortProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.Service#getRowCount()
	 */
	@Transactional
	@Override
	public Long getRowCount() {
		return dao.getRowCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.Service#findAll()
	 */
	@Transactional
	@Deprecated
	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#findAll(com.softserveinc
	 * .edu.oms.persistence.dao.params.SortProperties)
	 */
	@Transactional
	@Deprecated
	@Override
	public List<Order> findAll(final SortProperties sortProperties) {
		return dao.findAll(sortProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#findByID(java.lang
	 * .Integer)
	 */
	@Transactional
	@Override
	public Order findByID(final Integer id) {
		return dao.findByID(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#insertOrUpdate(com
	 * .softserveinc.edu.oms.domain.AbstractEntity)
	 */
	@Transactional
	@Override
	public Order insertOrUpdate(final Order entity) {
		return dao.insertOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#delete(com.softserveinc
	 * .edu.oms.domain.AbstractEntity)
	 */

	@Transactional
	@Override
	public void delete(final Order entity) {
		dao.delete(entity);
	}

	@SuppressWarnings("unused")
	@Deprecated
	private Criterion createFilter(final SearchFilterOptions options) {
		Criterion filter = null;

		if (options.getFilterBy().equalsIgnoreCase("orderStatus")) {
			filter = Restrictions.eq(options.getFilterBy(),
					orderStatusDAO.getByName(options.getFilterValue()));
		}
		if (options.getFilterBy().equalsIgnoreCase("Role")) {
			// filter = Restrictions.eq(options.getFilterBy(),
			filter = Restrictions.eq("asss.role",
					roleDAO.findByRole(options.getFilterValue()));
		}

		if (options.getFilterValue().equals("")
				|| options.getFilterValue().equalsIgnoreCase("None")) {
			filter = null;
		}
		return filter;
	}

	@SuppressWarnings("unused")
	@Deprecated
	private Criterion createSearch(final SearchFilterOptions options) {
		Criterion search = null;
		if (options.getSearch().equalsIgnoreCase("orderName")) {
			search = Restrictions.like(options.getSearch(),
					options.getSearchValue() + "%");
		}

		if (options.getSearch().equalsIgnoreCase("orderStatus")) {
			search = Restrictions.like(options.getSearch(),
					options.getSearchValue() + "%");
		}

		if (options.getSearch().equalsIgnoreCase("assignee")) {
			search = Restrictions.like(options.getSearch(),
					options.getSearchValue() + "%");
		}

		if (options.getSearchValue().equals("")) {
			search = null;
		}
		return search;
	}

	@Transactional
	@Override
	public List<Order> find(final Integer startingFrom,
			final Integer maxResult, final SearchFilterOptions options,
			final SortProperties sortProperties) {
		return dao.find(startingFrom, maxResult, sortProperties, options);
		// Criterion search = this.createSearch(options);
		// Criterion filter = this.createFilter(options);
		//
		// if (search == null) {
		// if (filter == null) {
		// return dao.findByCriterions(startingFrom, maxResult,
		// sortProperties);
		// } else {
		// return dao.findByCriterions(startingFrom, maxResult,
		// sortProperties, filter);
		// }
		// } else {
		// if (options.getSearch().equalsIgnoreCase("orderName")) {
		// if (filter == null) {
		// return dao.findByCriterions(startingFrom, maxResult,
		// sortProperties, search);
		// } else {
		// return dao.findByCriterions(startingFrom, maxResult,
		// sortProperties, search, filter);
		// }
		// } else {
		// if (options.getSearch().equalsIgnoreCase("orderStatus")) {
		// return dao.findForOrderStatusLike(startingFrom, maxResult,
		// sortProperties, options.getSearchValue() + "%",
		// filter);
		// } else {
		// if (options.getSearch().equalsIgnoreCase("assignee")) {
		// return dao.findForAssigneeLike(startingFrom, maxResult,
		// sortProperties, options.getSearchValue() + "%",
		// filter);
		// }
		// return dao.findAll(startingFrom, maxResult, sortProperties);
		// }
		// }
		//
		// }

	}

	@Transactional
	@Override
	public Long getRowCount(final SearchFilterOptions options) {
		return dao.countRowNumbers(options);
		// Criterion search = this.createSearch(options);
		// Criterion filter = this.createFilter(options);
		//
		// if (search == null) {
		// if (filter == null) {
		// return dao.getRowCount();
		// } else {
		// return dao.getRowCountByCriterions(filter);
		// }
		// } else {
		// if (filter == null) {
		// return dao.getRowCountByCriterions(search);
		// } else {
		// return dao.getRowCountByCriterions(filter, search);
		// }
		// }
		// Criterion search = this.createSearch(options);
		// Criterion filter = this.createFilter(options);
		//
		// if (search == null) {
		// if (filter == null) {
		// return dao.getRowCount();
		// } else {
		// return dao.getRowCountByCriterions(filter);
		// }
		// } else {
		// if (options.getSearch().equalsIgnoreCase("orderName")) {
		// if (filter == null) {
		// return dao.getRowCountByCriterions(search);
		// } else {
		// return dao.getRowCountByCriterions(filter, search);
		// }
		// } else {
		// if (options.getSearch().equalsIgnoreCase("orderStatus")) {
		// return dao.countRowNumbersForOrderStatusLike(
		// options.getSearchValue() + "%", filter);
		// } else {
		// if (options.getSearch().equalsIgnoreCase("assignee")) {
		// return dao.countRowNumbersForOrderStatusLike(
		// options.getSearchValue() + "%", filter);
		// }
		// return dao.getRowCount();
		// }
		// }
		//
		// }
	}

	/**
	 * @see com.softserveinc.edu.oms.service.interfaces.IOrderService#getMaxOrderNumber()
	 */
	@Transactional
	@Override
	public Integer getMaxOrderNumber() {
		return dao.findOrderNumber();
	}

	/**
	 * @see com.softserveinc.edu.oms.service.interfaces.IOrderService#orderNumberExists(java.lang.Integer)
	 */
	@Transactional
	@Override
	public Boolean orderNumberExists(final Integer orderNumber) {

		if (dao.findByOrderNumber(orderNumber) == null) {
			return false;
		}
		return true;
	}
}

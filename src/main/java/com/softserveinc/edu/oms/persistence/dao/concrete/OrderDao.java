/*
 * OrderDao.java
 *
 * Version 1.0
 *
 * 10.07.2011
 *
 * (c) Arthur Borisow arthur.borisow[at]gmail.com
 */

package com.softserveinc.edu.oms.persistence.dao.concrete;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.annotations.Type;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.persistence.dao.HibernatePageableDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IOrderDao;
import com.softserveinc.edu.oms.persistence.dao.params.SelectCondition;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.web.order.SearchFilterOptions;

/**
 * @authors arthur.borisow, marko
 * @version 2.0
 * 
 */
@Repository
public class OrderDao extends HibernatePageableDao<Order> implements IOrderDao {

	@Autowired
	private OrderStatusDao orderStatusDao;

	@Autowired
	private RoleDao roleDao;

	private static final String CUSTOMER = "customer";
	private static final String ASSIGNEE = "assigne";
	private static final String ORDER_STATUS = "orderStatus";

	private static final String CUSTOMER_ALIAS = "cust";
	private static final String ASSIGNEE_ALIAS = "assig";
	private static final String ORDER_STATUS_ALIAS = "status";

	public OrderDao() {
		super(Order.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.persistence.dao.concrete.IOrderDAO#findByTotalPrice
	 * (double, com.softserveinc.edu.oms.persistence.dao.params.SelectCondition)
	 */
	@Override
	public List<Order> findByTotalPrice(final double totalPrice,
			final SelectCondition cond) {
		return findByCriterions(cond.createDoubleCriterion("totalPrice",
				totalPrice));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IOrderDAO#
	 * findByDeliveryDate(java.util.Date,
	 * com.softserveinc.edu.oms.persistence.dao.params.SelectCondition)
	 */
	@Override
	public List<Order> findByDeliveryDate(final Date deliveryDate,
			final SelectCondition cond) {
		return findByCriterions(cond.createDateCriterion("deliveryDate",
				deliveryDate));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.persistence.dao.concrete.IOrderDAO#findByOrderDate
	 * (java.util.Date,
	 * com.softserveinc.edu.oms.persistence.dao.params.SelectCondition)
	 */
	@Override
	public List<Order> findByOrderDate(final Date orderDate,
			final SelectCondition cond) {
		return findByCriterions(cond
				.createDateCriterion("orderDate", orderDate));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.persistence.dao.concrete.IOrderDAO#findByOrderStatus
	 * (com.softserveinc.edu.oms.persistence.entities.OrderStatus)
	 */
	@Override
	public List<Order> findByOrderStatus(final OrderStatus orderStatus) {
		return findByCriterions(Restrictions.eq("orderStatus", orderStatus));
	}

	@Deprecated
	private Criteria createCriteriaForOrderStatusLike(final String name) {
		startSession();
		Criteria criteria = session.createCriteria(persistentClass)
				.createAlias("orderStatus", "status")
				.add(Restrictions.like("status.orderStatusName", name));
		return criteria;

	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public List<Order> findForOrderStatusLike(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties,
			final String name, final Criterion filter) {
		Criteria criteria = this.createCriteriaForOrderStatusLike(name);
		addSortPropertiesToCriteria(sortProperties, criteria);
		if (filter != null) {
			criteria.add(filter);
		}
		criteria.setFirstResult(startingFrom);
		criteria.setMaxResults(maxResult);
		return criteria.list();
	}

	@Deprecated
	public long countRowNumbersForOrderStatusLike(final String name,
			final Criterion filter) {
		Criteria criteria = this.createCriteriaForOrderStatusLike(name);
		if (filter != null) {
			criteria.add(filter);
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count;
	}

	@Deprecated
	private Criteria createCriteriaForAssigneeLike(final String name) {
		startSession();
		// Criteria criteria = session.createCriteria(persistentClass)
		// .createCriteria("assigne").createCriteria("role")
		// .add(Restrictions.like("roleName", name));
		Criteria criteria = session.createCriteria(persistentClass)
				.createAlias("assigne", "assig");
		// addSortPropertiesToCriteria(sortProperties, criteria);
		// criteria.createCriteria("assigne")
		criteria.add(Restrictions.like("assig.login", name));

		return criteria;
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public List<Order> findForAssigneeLike(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties,
			final String name, final Criterion filter) {
		Criteria criteria = this.createCriteriaForAssigneeLike(name);
		addSortPropertiesToCriteria(sortProperties, criteria);
		if (filter != null) {
			criteria.add(filter);
		}

		criteria.setFirstResult(startingFrom);
		criteria.setMaxResults(maxResult);

		return criteria.list();
	}

	@Deprecated
	public long countRowNumbersForAssigneeLike(final String name,
			final Criterion filter) {
		Criteria criteria = this.createCriteriaForAssigneeLike(name);
		if (filter != null) {
			criteria.add(filter);
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Order> find(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties,
			final SearchFilterOptions searchFilterOptions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		criteria = this.setAliases(criteria);
		criteria = this.addFilter(criteria, searchFilterOptions);
		criteria = this.addSearch(criteria, searchFilterOptions);

		criteria.setFirstResult(startingFrom);
		criteria.setMaxResults(maxResult);

		this.addSortPropertiesToCriteria(sortProperties, criteria);

		return criteria.list();
	}

	public long countRowNumbers(final SearchFilterOptions searchFilterOptions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		criteria = this.setAliases(criteria);
		criteria = this.addFilter(criteria, searchFilterOptions);
		criteria = this.addSearch(criteria, searchFilterOptions);

		System.err.println(criteria.toString());

		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count;
	}

	private Criteria addSearch(final Criteria criteria,
			final SearchFilterOptions searchFilterOptions) {
		if (searchFilterOptions.getSearchValue().equals(null)
				|| searchFilterOptions.getSearchValue()
						.equalsIgnoreCase("none")
				|| searchFilterOptions.getSearchValue().equalsIgnoreCase("")) {
			return criteria;
		}
		if (searchFilterOptions.getSearch().equalsIgnoreCase("orderName")) {
			criteria.add(Restrictions.like("orderName",
					searchFilterOptions.getSearchValue() + "%"));
		} else {
			if (searchFilterOptions.getSearch().equalsIgnoreCase("orderStatus")) {
				criteria.add(Restrictions.like(ORDER_STATUS_ALIAS
						+ ".orderStatusName",
						searchFilterOptions.getSearchValue() + "%"));
			} else {
				if (searchFilterOptions.getSearch()
						.equalsIgnoreCase("assignee")) {
					criteria.add(Restrictions.like(ASSIGNEE_ALIAS + ".login",
							searchFilterOptions.getSearchValue() + "%"));
				}
			}
		}

		return criteria;
	}

	private Criteria addFilter(final Criteria criteria,
			final SearchFilterOptions searchFilterOptions) {
		if (searchFilterOptions.getFilterValue().equalsIgnoreCase("none")
				|| searchFilterOptions.getFilterValue().equalsIgnoreCase("")) {
			return criteria;
		}
		if (searchFilterOptions.getFilterBy().equalsIgnoreCase("orderStatus")) {
			criteria.add(Restrictions.eq(ORDER_STATUS, orderStatusDao
					.getByName(searchFilterOptions.getFilterValue())));
		} else {
			// Role filter
			if (searchFilterOptions.getFilterBy().equalsIgnoreCase("role")) {
				criteria.add(Restrictions.eq(ASSIGNEE_ALIAS + ".role", roleDao
						.findByRole(searchFilterOptions.getFilterValue())));
			}
		}

		return criteria;
	}

	@Override
	protected void addSortPropertiesToCriteria(
			final SortProperties sortProperties, final Criteria criteria) {
		if (sortProperties.getPropertyNames().contains("orderStatus")) {

			if (sortProperties.isSortAscending("orderStatus")) {
				criteria.addOrder(org.hibernate.criterion.Order
						.asc(ORDER_STATUS_ALIAS + ".orderStatusName"));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order
						.desc(ORDER_STATUS_ALIAS + ".orderStatusName"));
			}
		} else {
			if (sortProperties.getPropertyNames().contains("assigne")) {

				if (sortProperties.isSortAscending("assigne")) {
					criteria.addOrder(org.hibernate.criterion.Order
							.asc(ASSIGNEE_ALIAS + ".login"));
				} else {
					criteria.addOrder(org.hibernate.criterion.Order
							.desc(ASSIGNEE_ALIAS + ".login"));
				}
			} else {
				if (sortProperties.getPropertyNames().contains("assigne.role")) {

					if (sortProperties.isSortAscending("assigne.role")) {
						criteria.addOrder(org.hibernate.criterion.Order
								.asc(ASSIGNEE_ALIAS + ".role"));
					} else {
						criteria.addOrder(org.hibernate.criterion.Order
								.desc(ASSIGNEE_ALIAS + ".role"));
					}
				} else {
					super.addSortPropertiesToCriteria(sortProperties, criteria);
				}
			}
		}
	}

	private Criteria setAliases(final Criteria criteria) {
		criteria.createAlias(ASSIGNEE, ASSIGNEE_ALIAS);
		criteria.createAlias(CUSTOMER, CUSTOMER_ALIAS);
		criteria.createAlias(ORDER_STATUS, ORDER_STATUS_ALIAS);
		return criteria;
	}

	@Type(type = "text")
	public Integer findOrderNumber() {
		startSession();
		Integer maxOrderNumber = (Integer) session.createSQLQuery(
				"SELECT max(OrderNumber) FROM Orders").uniqueResult();

		return maxOrderNumber;
	}

	public Order findByOrderNumber(final Integer orderNumber) {
		startSession();

		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("orderNumber", orderNumber));
		@SuppressWarnings("unchecked")
		final List<Order> entities = criteria.list();

		return entities.size() < 1 ? null : entities.get(0);
	}
}

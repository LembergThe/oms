package com.softserveinc.edu.oms.persistence.dao.concrete;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.persistence.dao.HibernateDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IOrderStatusDao;

@Repository
public class OrderStatusDao extends HibernateDao<OrderStatus> implements
		IOrderStatusDao {

	public OrderStatusDao() {
		super(OrderStatus.class);
	}

	public List<OrderStatus> findOrderStatusByName(final String orderStatusName) {
		List<OrderStatus> statuses = findByCriterions(Restrictions.eq(
				"orderStatusName", orderStatusName));
		return statuses;
	}

	public OrderStatus getByName(final String name) {
		Criterion nameCriteria = Restrictions.eq("orderStatusName", name);
		List<OrderStatus> entities = findByCriterions(nameCriteria);
		return entities.size() < 1 ? null : entities.get(0);
	}

	/*
	 * public List<OrderStatus> findOrderStatusByName( final OrderStatusEnum
	 * orderStatusType) { List<OrderStatus> statuses =
	 * findByCriterions(Restrictions.eq( "orderStatusName",
	 * orderStatusType.getValue())); return statuses; }
	 */

}

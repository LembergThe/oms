/**
 * 
 */
package com.softserveinc.edu.oms.service.implementation;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.persistence.dao.concrete.OrderStatusDao;
import com.softserveinc.edu.oms.persistence.dao.params.OrderSatusTypes;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;

/**
 * @author marko
 * 
 */
@Service
public class OrderStatusService implements IOrderStatusService {

	@Autowired
	OrderStatusDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.Service#getRowCount()
	 */

	@Transactional
	@Deprecated
	@Override
	public Long getRowCount() {
		// TODO Auto-generated method stub
		return dao.getRowCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.Service#findAll()
	 */
	@Transactional
	@Override
	public List<OrderStatus> findAll() {
		// TODO Auto-generated method stub
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
	public List<OrderStatus> findAll(final SortProperties sortProperties) {
		// TODO Auto-generated method stub
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
	public OrderStatus findByID(final Integer id) {
		// TODO Auto-generated method stub
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
	@Deprecated
	@Override
	public OrderStatus insertOrUpdate(final OrderStatus entity) {
		// TODO Auto-generated method stub
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
	@Deprecated
	@Override
	public void delete(final OrderStatus entity) {
		dao.delete(entity);
	}

	@Transactional
	@Override
	public OrderStatus getByName(final String name) {
		Criterion nameCriteria = Restrictions.eq("orderStatusName", name);
		List<OrderStatus> entities = dao.findByCriterions(nameCriteria);
		return entities.size() < 1 ? null : entities.get(0);
	}

	/**
	 * @see com.softserveinc.edu.oms.service.interfaces.IOrderStatusService#getCreatedStatus()
	 */
	@Transactional
	@Override
	public OrderStatus getCreatedStatus() {
		return getByName(OrderSatusTypes.CREATED);
	}

	/**
	 * @see com.softserveinc.edu.oms.service.interfaces.IOrderStatusService#getPendingStatus()
	 */
	@Transactional
	@Override
	public OrderStatus getPendingStatus() {
		return getByName(OrderSatusTypes.PENDING);
	}

	/**
	 * @see com.softserveinc.edu.oms.service.interfaces.IOrderStatusService#getOrderedStatus()
	 */
	@Transactional
	@Override
	public OrderStatus getOrderedStatus() {
		return getByName(OrderSatusTypes.ORDERED);
	}

	/**
	 * @see com.softserveinc.edu.oms.service.interfaces.IOrderStatusService#getDeliveredStatus()
	 */
	@Transactional
	@Override
	public OrderStatus getDeliveredStatus() {
		return getByName(OrderSatusTypes.DELIVERED);
	}

}

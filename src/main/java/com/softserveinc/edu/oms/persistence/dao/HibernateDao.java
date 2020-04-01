package com.softserveinc.edu.oms.persistence.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.AbstractEntity;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

@Repository
public class HibernateDao<T extends AbstractEntity> implements IDao<T> {

	protected final Class<T> persistentClass;

	protected SessionFactory sessionFactory;

	protected Session session;
	protected Transaction transaction;

	public HibernateDao(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;

	}

	public HibernateDao(final Class<T> persistentClass,
			final SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		this.sessionFactory = sessionFactory;
	}

	protected void startSession() {
		session = sessionFactory.getCurrentSession();
	}

	@Override
	public Long getRowCount() {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();

		return count;
	}

	@Override
	public List<T> findAll() {
		return findByCriterions();
	}

	@Override
	public List<T> findAll(final SortProperties sortProperties) {
		return findByCriterions(sortProperties);
	}

	@Override
	public List<T> findByCriterions(final Criterion... criterions) {
		return findByCriterions(new SortProperties(), criterions);
	}

	@Override
	public List<T> findByCriterions(final SortProperties sortProperties,
			final Criterion... criterions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		addSortPropertiesToCriteria(sortProperties, criteria);

		int nullCriterionsCount = 0;
		for (Criterion criterion : criterions) {
			if (criterion != null) {
				criteria.add(criterion);
			} else {
				nullCriterionsCount++;
			}
		}

		List<T> entities;
		entities = processCriteria(criteria, nullCriterionsCount, criterions);

		return entities;
	}

	@Override
	public List<T> findByOneOfCriterions(final Criterion... criterions) {
		return findByOneOfCriterions(new SortProperties(), criterions);
	}

	@Override
	public List<T> findByOneOfCriterions(final SortProperties sortProperties,
			final Criterion... criterions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		addSortPropertiesToCriteria(sortProperties, criteria);

		int nullCriterionsCount = 0;
		Disjunction disjunction = Restrictions.disjunction();
		for (Criterion criterion : criterions) {
			if (criterion != null) {
				disjunction.add(criterion);
			} else {
				nullCriterionsCount++;
			}
		}

		criteria.add(disjunction);

		List<T> entities;
		entities = processCriteria(criteria, nullCriterionsCount, criterions);

		return entities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByID(final Integer id) {
		if (id == null) {
			return null;
		}

		T entity = null;

		startSession();

		entity = (T) session.get(persistentClass, id);

		return entity;
	}

	@Override
	public T insertOrUpdate(final T entity) {
		if (entity.getId() == null) {
			return insert(entity);
		} else {
			return update(entity);
		}
	}

	@Override
	public void delete(final T entity) {
		startSession();

		session.delete(entity);

	}

	protected void addSortPropertiesToCriteria(
			final SortProperties sortProperties, final Criteria criteria) {
		for (String propertyName : sortProperties.getPropertyNames()) {
			if (sortProperties.isSortAscending(propertyName)) {
				criteria.addOrder(Order.asc(propertyName));
			} else {
				criteria.addOrder(Order.desc(propertyName));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<T> processCriteria(final Criteria criteria,
			final int nullCriterions, final Criterion... criterions) {
		List<T> entities;
		if (nullCriterions == criterions.length && nullCriterions != 0) {
			entities = new LinkedList<T>();
		} else {
			entities = criteria.list();
		}
		return entities;
	}

	private T insert(final T entity) {
		startSession();

		Integer id = (Integer) session.save(entity);
		entity.setId(id);

		return entity;
	}

	private T update(final T entity) {
		startSession();

		session.update(entity);

		return entity;
	}

	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

package com.softserveinc.edu.oms.persistence.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.AbstractEntity;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

@Repository
public class HibernatePageableDao<T extends AbstractEntity> extends
		HibernateDao<T> implements IPageableDao<T> {

	public HibernatePageableDao(final Class<T> persistentClass) {
		super(persistentClass);
	}

	@Override
	public Long getRowCountByCriterions(final Criterion... criterions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		int nullCriterions = 0;
		for (Criterion criterion : criterions) {
			if (criterion != null) {
				criteria.add(criterion);
			} else {
				nullCriterions++;
			}
		}

		final Long count;
		count = processCriteriaForRowCount(criteria, nullCriterions, criterions);

		return count;
	}

	@Override
	public Long getRowCountByOneOfCriterions(final Criterion... criterions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		int nullCriterions = 0;
		Disjunction disjunction = Restrictions.disjunction();
		for (Criterion criterion : criterions) {
			if (criterion != null) {
				disjunction.add(criterion);
			} else {
				nullCriterions++;
			}
		}

		criteria.add(disjunction);

		final Long count;
		count = processCriteriaForRowCount(criteria, nullCriterions, criterions);

		return count;
	}

	@Override
	public List<T> findAll(final Integer startingFrom, final Integer maxResult) {
		return findByCriterions(startingFrom, maxResult);
	}

	@Override
	public List<T> findAll(final Integer startingFrom, final Integer maxResult,
			final SortProperties sortProperties) {
		return findByCriterions(startingFrom, maxResult, sortProperties);
	}

	@Override
	public List<T> findByCriterions(final Integer startingFrom,
			final Integer maxResult, final Criterion... criterions) {
		return findByCriterions(startingFrom, maxResult, new SortProperties(),
				criterions);
	}

	@Override
	public List<T> findByCriterions(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties,
			final Criterion... criterions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);
		

		addSortPropertiesToCriteria(sortProperties, criteria);

		int nullCriterions = 0;
		for (Criterion criterion : criterions) {
			if (criterion != null) {
				criteria.add(criterion);
			} else {
				nullCriterions++;
			}
		}

		List<T> entities;
		entities = processCriteria(startingFrom, maxResult, criteria,
				nullCriterions, criterions);

		return entities;
	}

	@Override
	public List<T> findByOneOfCriterions(final Integer startingFrom,
			final Integer maxResult, final Criterion... criterions) {
		return findByOneOfCriterions(startingFrom, maxResult,
				new SortProperties(), criterions);
	}

	@Override
	public List<T> findByOneOfCriterions(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties,
			final Criterion... criterions) {
		startSession();

		Criteria criteria = session.createCriteria(persistentClass);

		addSortPropertiesToCriteria(sortProperties, criteria);

		int nullCriterions = 0;
		Disjunction disjunction = Restrictions.disjunction();
		for (Criterion criterion : criterions) {
			if (criterion != null) {
				disjunction.add(criterion);
			} else {
				nullCriterions++;
			}
		}

		criteria.add(disjunction);

		List<T> entities;
		entities = processCriteria(startingFrom, maxResult, criteria,
				nullCriterions, criterions);

		return entities;
	}

	@SuppressWarnings("unchecked")
	private List<T> processCriteria(final Integer startingFrom,
			final Integer maxResult, final Criteria criteria,
			final int nullCriterions, final Criterion... criterions) {
		List<T> entities;
		if (nullCriterions == criterions.length && nullCriterions != 0) {
			entities = new LinkedList<T>();
		} else {
			criteria.setFirstResult(startingFrom);
			criteria.setMaxResults(maxResult);

			entities = criteria.list();
		}

		return entities;
	}

	private Long processCriteriaForRowCount(final Criteria criteria,
			final int nullCriterions, final Criterion... criterions) {
		final Long count;
		if (nullCriterions == criterions.length && nullCriterions != 0) {
			count = (long) 0;
		} else {

			criteria.setProjection(Projections.rowCount());
			count = (Long) criteria.uniqueResult();
		}
		return count;
	}
}

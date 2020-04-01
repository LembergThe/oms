package com.softserveinc.edu.oms.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.softserveinc.edu.oms.domain.AbstractEntity;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

public interface IPageableDao<T extends AbstractEntity> extends IDao<T> {
	/**
	 * @return count of all rows of the appropriate table in the DB, that
	 *         matches all the criterions.
	 */
	Long getRowCountByCriterions(final Criterion... criterions);

	/**
	 * @return count of rows of the appropriate table in the DB, that matches at
	 *         least one of the criterions.
	 */
	Long getRowCountByOneOfCriterions(final Criterion... criterions);

	/**
	 * @param startingFrom
	 *            - position in table to start reading rows.
	 * @param maxResult
	 *            - max count of rows, that can be read.
	 * @return of all rows of the appropriate table in the DB.
	 */
	List<T> findAll(final Integer startingFrom, final Integer maxResult);

	/**
	 * @param startingFrom
	 *            - position in table to start reading rows.
	 * @param maxResult
	 *            - max count of rows, that can be read.
	 * @param sortProperties
	 *            properties for sorting {@link SortProperties}
	 * @return of all rows of the appropriate table in the DB.
	 */
	List<T> findAll(final Integer startingFrom, final Integer maxResult,
			final SortProperties sortProperties);

	/**
	 * @param startingFrom
	 *            - position in table to start reading rows.
	 * @param maxResult
	 *            - max count of rows, that can be read.
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB, filtered by all
	 *         criterions.
	 */
	List<T> findByCriterions(final Integer startingFrom,
			final Integer maxResult, final Criterion... criterions);

	/**
	 * 
	 * @param startingFrom
	 *            - position in table to start reading rows.
	 * @param maxResult
	 *            - max count of rows, that can be read.
	 * @param sortProperties
	 *            properties for sorting {@link SortProperties}.
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB, filtered by all
	 *         criterions.
	 */
	List<T> findByCriterions(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties,
			final Criterion... criterions);

	/**
	 * @param startingFrom
	 *            - position in table to start reading rows.
	 * @param maxResult
	 *            - max count of rows, that can be read.
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB,filtered by one
	 *         of criterions, inteprating them like one disjunction.
	 */
	List<T> findByOneOfCriterions(final Integer startingFrom,
			final Integer maxResult, final Criterion... criterions);

	/**
	 * @param startingFrom
	 *            - position in table to start reading rows.
	 * @param maxResult
	 *            - max count of rows, that can be read.
	 * @param sortProperties
	 *            properties for sorting {@link SortProperties}.
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB,filtered by one
	 *         of criterions, inteprating them like one disjunction.
	 */
	List<T> findByOneOfCriterions(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties,
			final Criterion... criterions);

}

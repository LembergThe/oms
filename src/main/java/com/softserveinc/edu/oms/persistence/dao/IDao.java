package com.softserveinc.edu.oms.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.softserveinc.edu.oms.domain.AbstractEntity;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

/**
 * Basic data access object, for all entites.
 * 
 * @author Orest
 * 
 * @param <T>
 *            anything, that has ID field.
 */
public interface IDao<T extends AbstractEntity> {
	/**
	 * @return count of all rows of the appropriate table in the DB.
	 */
	Long getRowCount();

	/**
	 * @return list of all rows of the appropriate table in the DB.
	 */
	List<T> findAll();

	/**
	 * @param sortProperties
	 *            properties for sorting {@link SortProperties}
	 * @return list of all rows of the appropriate table in the DB.
	 */
	List<T> findAll(final SortProperties sortProperties);

	/**
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB,filtered by all
	 *         criterions.
	 */
	List<T> findByCriterions(final Criterion... criterions);

	/**
	 * @param sortProperties
	 *            properties for sorting {@link SortProperties}
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB, filtered by all
	 *         criterions.
	 */
	List<T> findByCriterions(final SortProperties sortProperties,
			final Criterion... criterions);

	/**
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB,filtered by one
	 *         of criterions, inteprating them like one disjunction.
	 */
	List<T> findByOneOfCriterions(final Criterion... criterions);

	/**
	 * @param sortProperties
	 *            properties for sorting {@link SortProperties}
	 * @param criterions
	 *            criterions for search.
	 * @return list of rows of the appropriate table in the DB,filtered by one
	 *         of criterions, inteprating them like one disjunction.
	 */
	List<T> findByOneOfCriterions(final SortProperties sortProperties,
			final Criterion... criterions);

	/**
	 * @param id
	 *            - ID to get
	 * @return the entity from db with appropriate ID.
	 */
	T findByID(final Integer id);

	/**
	 * if entity is seen first time it is inserted into the db, updated
	 * otherwise.
	 * 
	 * @param entity
	 *            entity to proceed.
	 * @return the updated value of entity, already with ID.
	 */
	T insertOrUpdate(final T entity);

	/**
	 * Removes entity from db.
	 * 
	 * @param entity
	 *            entity to delete.
	 */
	void delete(final T entity);
}

package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.AbstractEntity;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

public interface Service<T extends AbstractEntity> {
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

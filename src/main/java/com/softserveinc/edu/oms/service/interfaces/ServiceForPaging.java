package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.AbstractEntity;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

public interface ServiceForPaging<T extends AbstractEntity> extends
		Service<T> {
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

}

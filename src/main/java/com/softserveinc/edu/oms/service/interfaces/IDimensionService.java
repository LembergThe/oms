package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Dimension;
import com.softserveinc.edu.oms.persistence.dao.params.DimensionTypesEnum;

public interface IDimensionService extends Service<Dimension> {
	List<Dimension> findDimensionByName(
			final String dimensionName);

	List<Dimension> findDimensionByName(
			final DimensionTypesEnum dimensionType);

	List<Dimension> findDimensionByNumberOfProducts(
			final int numberOfProduct);

	List<Dimension> findDimensionByNumberOfProducts(
			final DimensionTypesEnum dimensionType);

	Dimension getItemDimension();

	Dimension getBoxDimension();

	Dimension getPackageDimension();
}

/*
 * DimensionService.java
 *
 * version - v1.0
 *
 * 24 серп. 2011 - 10:24:08
 *
 * (c) sh.muro[at]gmail.com
 *
 */
package com.softserveinc.edu.oms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.Dimension;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IDimensionDao;
import com.softserveinc.edu.oms.persistence.dao.params.DimensionTypesEnum;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.service.interfaces.IDimensionService;

/**
 * @author Мирослав
 * 
 */
@Service
public class DimensionService implements IDimensionService {

	private IDimensionDao dimensionDao;

	@Autowired
	public void setDimensionDao(IDimensionDao dimensionDao) {
		this.dimensionDao = dimensionDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.Service#getRowCount()
	 */
	@Override
	@Transactional
	public Long getRowCount() {
		return dimensionDao.getRowCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.Service#findAll()
	 */
	@Override
	@Transactional
	public List<Dimension> findAll() {
		return dimensionDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#findAll(com.softserveinc
	 * .edu.oms.persistence.dao.params.SortProperties)
	 */
	@Override
	@Transactional
	public List<Dimension> findAll(SortProperties sortProperties) {
		return dimensionDao.findAll(sortProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#findByID(java.lang
	 * .Integer)
	 */
	@Override
	@Transactional
	public Dimension findByID(Integer id) {
		return dimensionDao.findByID(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#insertOrUpdate(com
	 * .softserveinc.edu.oms.domain.AbstractEntity)
	 */
	@Override
	@Transactional
	public Dimension insertOrUpdate(Dimension entity) {
		return dimensionDao.insertOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.Service#delete(com.softserveinc
	 * .edu.oms.domain.AbstractEntity)
	 */
	@Override
	@Transactional
	public void delete(Dimension entity) {
		dimensionDao.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.IDimensionService#
	 * findDimensionByName(java.lang.String)
	 */
	@Override
	@Transactional
	public List<Dimension> findDimensionByName(String dimensionName) {
		return dimensionDao.findDimensionByName(dimensionName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.IDimensionService#
	 * findDimensionByName
	 * (com.softserveinc.edu.oms.persistence.dao.params.DimensionTypesEnum)
	 */
	@Override
	@Transactional
	public List<Dimension> findDimensionByName(DimensionTypesEnum dimensionType) {
		return dimensionDao.findDimensionByName(dimensionType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.IDimensionService#
	 * findDimensionByNumberOfProducts(int)
	 */
	@Override
	@Transactional
	public List<Dimension> findDimensionByNumberOfProducts(int numberOfProduct) {
		return dimensionDao.findDimensionByNumberOfProducts(numberOfProduct);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.IDimensionService#
	 * findDimensionByNumberOfProducts
	 * (com.softserveinc.edu.oms.persistence.dao.params.DimensionTypesEnum)
	 */
	@Override
	@Transactional
	public List<Dimension> findDimensionByNumberOfProducts(
			DimensionTypesEnum dimensionType) {
		return dimensionDao.findDimensionByNumberOfProducts(dimensionType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.IDimensionService#
	 * getItemDimension()
	 */
	@Override
	@Transactional
	public Dimension getItemDimension() {
		return dimensionDao.getItemDimension();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.service.interfaces.IDimensionService#getBoxDimension
	 * ()
	 */
	@Override
	@Transactional
	public Dimension getBoxDimension() {
		return dimensionDao.getBoxDimension();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.service.interfaces.IDimensionService#
	 * getPackageDimension()
	 */
	@Override
	@Transactional
	public Dimension getPackageDimension() {
		return dimensionDao.getPackageDimension();
	}
}

package com.softserveinc.edu.oms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.Region;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IRegionDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.service.interfaces.IRegionService;

@Service
public class RegionService implements IRegionService {
	private IRegionDao regionDao;

	@Autowired
	public void setRegionDao(final IRegionDao regionDao) {
		this.regionDao = regionDao;
	}

	@Transactional
	@Override
	public Long getRowCount() {
		return regionDao.getRowCount();
	}

	@Transactional
	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Transactional
	@Override
	public List<Region> findAll(final SortProperties sortProperties) {
		return regionDao.findAll(sortProperties);
	}

	@Transactional
	@Override
	public Region findByID(final Integer id) {
		return regionDao.findByID(id);
	}

	@Transactional
	@Override
	public Region insertOrUpdate(final Region entity) {
		return regionDao.insertOrUpdate(entity);
	}

	@Transactional
	@Override
	public void delete(final Region entity) {
		regionDao.delete(entity);
	}

	@Transactional
	@Override
	public List<Region> findRegionByName(final String regionName) {
		return regionDao.findRegionByName(regionName);
	}
}

package com.softserveinc.edu.oms.persistence.dao.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Region;
import com.softserveinc.edu.oms.persistence.dao.IDao;

public interface IRegionDao extends IDao<Region>{

	List<Region> findRegionByName(final String regionName);

}
package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Region;

public interface IRegionService extends Service<Region> {

	List<Region> findRegionByName(final String regionName);
}

package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Region;

public class RegionDaoTest extends CleanUpDBTestCase {
	private static final String REG2_NAME = "Bibrka";

	private Region region1;
	private Region region2;
	private Region region3;

	private final int NUM_OF_REGIONS = 3;

	@Before
	public void setUp() {
		createRegions();
	}

	@After
	public void tearDown() {
		cleanRegion();
	}

	@Test
	public void testFindAll() {
		int size = regionDao.findAll().size();
		Assert.assertEquals(NUM_OF_REGIONS, size);
	}

	@Test
	public void testFindByID() {
		Assert.assertEquals(region1.getId(), regionDao
				.findByID(region1.getId()).getId());
	}

	@Test
	public void testFindByRegionName() {
		int size = regionDao.findRegionByName(REG2_NAME).size();
		Assert.assertEquals(1, size);
	}

	@Test
	public void testFindByOneOfCriterions() {
		Assert.assertEquals(false, false);
	}

	@Test
	public void testInsertOrUpdate() {
		region1 = regionDao.insertOrUpdate(region1);
		region2 = regionDao.insertOrUpdate(region2);
		region3 = regionDao.insertOrUpdate(region3);

		assertNotNull(region1.getId());
		assertNotNull(region2.getId());
		assertNotNull(region3.getId());

		assertEquals(3, regionDao.findAll().size());
	}

	@Test
	public void testDelete() {
		regionDao.delete(region1);
		Assert.assertEquals(regionDao.findByID(region1.getId()), null);
	}

	private void createRegions() {
		region1 = new Region();
		region2 = new Region();
		region3 = new Region();

		region1.setRegionName("Donetsk");
		region2.setRegionName("Bibrka");
		region3.setRegionName("Pidrahuilivka");

		regionDao.insertOrUpdate(region1);
		regionDao.insertOrUpdate(region2);
		regionDao.insertOrUpdate(region3);
	}
}

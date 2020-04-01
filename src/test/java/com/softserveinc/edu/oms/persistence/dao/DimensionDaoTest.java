package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Dimension;
import com.softserveinc.edu.oms.persistence.dao.params.DimensionTypesEnum;

public class DimensionDaoTest extends CleanUpDBTestCase {

	private Dimension itemDimension;
	private Dimension boxDimension;
	private Dimension packageDimension;

	@Before
	public void setUp() {
		cleanDB();

		itemDimension = new Dimension();
		itemDimension.setDimensionName(DimensionTypesEnum.ITEM
				.getDimensionTypeName());
		itemDimension.setNumberOfProduct(DimensionTypesEnum.ITEM
				.getNumberOfProductInThisType());
		dimensionDao.insertOrUpdate(itemDimension);
		boxDimension = new Dimension();
		boxDimension.setDimensionName(DimensionTypesEnum.BOX
				.getDimensionTypeName());
		boxDimension.setNumberOfProduct(DimensionTypesEnum.BOX
				.getNumberOfProductInThisType());
		dimensionDao.insertOrUpdate(boxDimension);
		packageDimension = new Dimension();
		packageDimension.setDimensionName(DimensionTypesEnum.PACKAGE
				.getDimensionTypeName());
		packageDimension.setNumberOfProduct(DimensionTypesEnum.PACKAGE
				.getNumberOfProductInThisType());
		dimensionDao.insertOrUpdate(packageDimension);
	}

	@After
	public void tearDown() throws Exception {
		super.cleanDimension();
	}

	@Test
	public void testFindDimensionByName_String() {
		String expected = "Item";
		assertEquals(expected, dimensionDao.findDimensionByName(expected)
				.get(0).getDimensionName());
		expected = "Box";
		assertEquals(expected, dimensionDao.findDimensionByName(expected)
				.get(0).getDimensionName());
		expected = "Package";
		assertEquals(expected, dimensionDao.findDimensionByName(expected)
				.get(0).getDimensionName());
	}

	@Test
	public void testFindDimensionByName_DimensionTypesEnum() {
		String expected = "Item";
		assertEquals(expected,
				dimensionDao.findDimensionByName(DimensionTypesEnum.ITEM)
						.get(0).getDimensionName());
		expected = "Box";
		assertEquals(expected,
				dimensionDao.findDimensionByName(DimensionTypesEnum.BOX).get(0)
						.getDimensionName());
		expected = "Package";
		assertEquals(expected,
				dimensionDao.findDimensionByName(DimensionTypesEnum.PACKAGE)
						.get(0).getDimensionName());
	}

	@Test
	public void testFindDimensionByNumberOfProducts_Int() {
		Integer expected = 1;
		assertEquals(expected,
				dimensionDao.findDimensionByNumberOfProducts(expected).get(0)
						.getNumberOfProduct());
		expected = 5;
		assertEquals(expected,
				dimensionDao.findDimensionByNumberOfProducts(expected).get(0)
						.getNumberOfProduct());
		expected = 10;
		assertEquals(expected,
				dimensionDao.findDimensionByNumberOfProducts(expected).get(0)
						.getNumberOfProduct());
	}

	@Test
	public void testFindDimensionByNumberOfProducts_DimensionTypesEnum() {
		Integer expected = 1;
		assertEquals(
				expected,
				dimensionDao
						.findDimensionByNumberOfProducts(
								DimensionTypesEnum.ITEM).get(0)
						.getNumberOfProduct());
		expected = 5;
		assertEquals(
				expected,
				dimensionDao
						.findDimensionByNumberOfProducts(DimensionTypesEnum.BOX)
						.get(0).getNumberOfProduct());
		expected = 10;
		assertEquals(
				expected,
				dimensionDao
						.findDimensionByNumberOfProducts(
								DimensionTypesEnum.PACKAGE).get(0)
						.getNumberOfProduct());
	}

	@Test
	public void testGetItemDimension() {
		String expected = "Item";
		assertEquals(expected, dimensionDao.getItemDimension()
				.getDimensionName());
	}

	@Test
	public void testGetBoxDimension() {
		String expected = "Box";
		assertEquals(expected, dimensionDao.getBoxDimension()
				.getDimensionName());
	}

	@Test
	public void testGetPackageDimension() {
		String expected = "Package";
		assertEquals(expected, dimensionDao.getPackageDimension()
				.getDimensionName());
	}

}

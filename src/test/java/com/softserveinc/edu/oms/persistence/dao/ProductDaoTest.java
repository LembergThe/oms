package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

public class ProductDaoTest extends CleanUpDBTestCase {

	Product product;

	@Before
	public void setUp() throws Exception {

		product = new Product();
		product.setProductName("name1");
		productDao.insertOrUpdate(product);
		product.setProductDescription("description1");
		productDao.insertOrUpdate(product);
		product.setProductPrice(100.0);
		productDao.insertOrUpdate(product);

		product = new Product();
		product.setProductName("name2");
		productDao.insertOrUpdate(product);
		product.setProductDescription("description2");
		productDao.insertOrUpdate(product);
		product.setProductPrice(200.0);
		productDao.insertOrUpdate(product);

		product = new Product();
		product.setProductName("name3");
		productDao.insertOrUpdate(product);
		product.setProductDescription("description3");
		productDao.insertOrUpdate(product);
		product.setProductPrice(300.0);
		productDao.insertOrUpdate(product);
	}

	@After
	public void tearDown() throws Exception {
		super.cleanProduct();
	}

	/************************************************************** Tests ***************************************/
	@Test
	public void testfindByName() {
		String expected = "%am%";
		List<Product> listProduct = productDao.findAll();
		for (int i = 0; i < listProduct.size(); i++) {
			assertTrue(expected.contains(productDao.findByName(expected).get(i)
					.getProductName().substring(1, 3)));
		}
	}

	@Test
	public void testcontainsProductByName() {
		List<Product> listProduct = productDao.findAll();
		for (Product product : listProduct) {
			assertTrue(productDao.containsProductByName(product
					.getProductName()));
		}
		assertFalse(productDao.containsProductByName("name7"));
	}

	@Test
	public void testnumberOfProductByName() {
		int expected = 1;
		assertEquals(expected, productDao.numberOfProductByName("name1"));
	}

	@Test
	public void testfindByDescription() {
		String expected = "%esc%";
		List<Product> listProduct = productDao.findAll();
		for (int j = 0; j < listProduct.size(); j++) {
			assertTrue(expected.contains(productDao.findByDescription(expected)
					.get(j).getProductDescription().substring(1, 4)));
		}
	}

	@Test
	public void testcontainsProductByDescription() {
		List<Product> listProduct = productDao.findAll();
		for (Product product : listProduct) {
			assertTrue(productDao.containsProductByDescription(product
					.getProductDescription()));
		}
		assertFalse(productDao.containsProductByDescription("description7"));
	}

	@Test
	public void testnumberOfProductByDescription() {
		assertEquals(1, productDao.numberOfProductByDescription("description1"));
		assertEquals(0, productDao.numberOfProductByDescription("description5"));
	}

	@Test
	public void testfindByPrice() {
		Double expected = 200.0;
		List<Product> listProduct = productDao.findByPrice(expected);
		for (Product product : listProduct) {
			assertEquals(expected, product.getProductPrice());
		}
	}

	@Test
	public void testfindByPriceFromTo() {
		Double productPriceFrom = 10.0;
		Double productPriceTo = 150.0;
		Double expected = 100.0;
		List<Product> listProduct = productDao.findByPriceFromTo(
				productPriceFrom, productPriceTo);
		for (Product product : listProduct) {
			assertEquals(expected, product.getProductPrice());
		}
	}

	@Test
	public void testFindByCriterions() {
		String productName = "name1";
		String productDescription = "description1";
		Criterion cr1 = Restrictions.eq("productName", productName);
		Criterion cr2 = Restrictions.eq("productDescription",
				productDescription);
		List<Product> listProducts = productDao.findByCriterions(cr1, cr2);
		for (Product pr : listProducts) {
			assertTrue((productName.equals(pr.getProductName()))
					& productDescription.equals(pr.getProductDescription()));
		}
	}

	@Test
	public void testFindByOneOfCriterions() {
		String productName = "name1";
		String productDescription = "description3";
		Criterion cr1 = Restrictions.eq("productName", productName);
		Criterion cr2 = Restrictions.eq("productDescription",
				productDescription);
		List<Product> listProducts = productDao.findByOneOfCriterions(cr1, cr2);
		for (Product pr : listProducts) {
			assertTrue(productName.equals(pr.getProductName())
					| productDescription.equals(pr.getProductDescription()));
		}
	}

	@Test
	public void testInsertOrUpdate() {
		Product productExpected = new Product();
		productExpected.setProductName("productName");
		productExpected.setProductDescription("productDescription");
		productExpected.setProductPrice(400.0);
		productDao.insertOrUpdate(productExpected);
		assertEquals(productExpected,
				productDao.findByID(productExpected.getId()));

		Product productExpected2 = new Product();
		productExpected2.setProductName("productName2");
		productExpected2.setProductDescription("productDescription2");
		productExpected2.setProductPrice(800.0);
		productDao.insertOrUpdate(productExpected2);
		assertFalse(productExpected2.equals(productExpected));
	}

	@Test
	public void testDelete() {
		Product product = new Product();
		product.setProductName("name");
		product.setProductDescription("productDescription");
		product.setProductPrice(1000.0);
		productDao.delete(product);
		for (Product pr : productDao.findAll()) {
			assertFalse(product.equals(pr));
		}
	}

	@Test
	public void testGetRowCount() {
		Long l = (long) 3;
		assertEquals(l, productDao.getRowCount());
	}

	@Test
	public void testfindByID() {
		List<Product> listProduct = productDao.findAll();
		for (Product product : listProduct) {
			Product prod = productDao.findByID(product.getId());
			assertEquals(prod, product);
		}
	}

	@Test
	public void testfindByNameSorting() {
		String expected = "%am%";
		List<Product> listProduct = productDao.findByName(expected,
				new SortProperties("productName", true));

		for (int i = 0; i < listProduct.size(); i++) {
			assertTrue(expected.contains(listProduct.get(i).getProductName()
					.substring(1, 3)));
		}

		for (int i = 0; i < listProduct.size() - 1; i++) {
			assertTrue(listProduct.get(i).compareTo(listProduct.get(i + 1)) <= 0);
		}

		listProduct = productDao.findByName(expected, new SortProperties(
				"productName", false));
		for (int i = 0; i < listProduct.size() - 1; i++) {
			assertTrue(listProduct.get(i).compareTo(listProduct.get(i + 1)) >= 0);
		}
	}

	@Test
	public void testfindByDescriptionSorting() {
		String expected = "%esc%";
		List<Product> listProduct = productDao.findByDescription(expected,
				new SortProperties("productDescription", true));
		for (int j = 0; j < listProduct.size(); j++) {
			assertTrue(expected.contains(listProduct.get(j)
					.getProductDescription().substring(1, 4)));
		}
		for (int i = 0; i < listProduct.size() - 1; i++) {
			assertTrue(listProduct.get(i).compareTo(listProduct.get(i + 1)) <= 0);
		}

		listProduct = productDao.findByDescription(expected,
				new SortProperties("productDescription", false));
		for (int i = 0; i < listProduct.size() - 1; i++) {
			assertTrue(listProduct.get(i).compareTo(listProduct.get(i + 1)) >= 0);
		}
	}

	@Test
	public void testfindByPriceFromToSorting() {
		Double productPriceFrom = 50.0;
		Double productPriceTo = 450.0;
		Double expected1 = 100.0;
		Double expected2 = 200.0;
		Double expected3 = 300.0;
		List<Product> listProduct = productDao.findByPriceFromTo(
				productPriceFrom, productPriceTo, new SortProperties(
						"productPrice", true));
		for (int i = 0; i < listProduct.size(); i++) {
			assertEquals(expected1, listProduct.get(0).getProductPrice());
			assertEquals(expected2, listProduct.get(1).getProductPrice());
			assertEquals(expected3, listProduct.get(2).getProductPrice());
		}

		for (int i = 0; i < listProduct.size() - 1; i++) {
			assertTrue(listProduct.get(i).getProductPrice() < listProduct.get(
					i + 1).getProductPrice());
		}

		listProduct = productDao.findByPriceFromTo(productPriceFrom,
				productPriceTo, new SortProperties("productPrice", false));
		for (int i = 0; i < listProduct.size() - 1; i++) {
			assertTrue(listProduct.get(i).getProductPrice() > listProduct.get(
					i + 1).getProductPrice());
		}
	}
}

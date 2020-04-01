//
// OrderItemsPageModelTest
//
// 9 вер. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Ivanka
 * 
 */
public class OrderItemsPageModelTest extends TestCase {
	private final Integer numberOfActions = 4;
	private final Integer maxNumberOfPages = 4;
	private final Integer firstPage = 0;

	private OrderItemsPageModel orderItemsPageModel;

	@Test
	public void testActionNames() {
		fillData();

		assertEquals(orderItemsPageModel.getActionNames().size(),
				numberOfActions.intValue());

		assertEquals(orderItemsPageModel.getActionNames().get(0),
				OrderItemsPageModel.FIRST_PAGE);
		assertEquals(orderItemsPageModel.getActionNames().get(1),
				OrderItemsPageModel.PREV_PAGE);
		assertEquals(orderItemsPageModel.getActionNames().get(2),
				OrderItemsPageModel.NEXT_PAGE);
		assertEquals(orderItemsPageModel.getActionNames().get(3),
				OrderItemsPageModel.LAST_PAGE);
	}

	@Test
	public void testPageChanging() {
		fillData();

		assertEquals(maxNumberOfPages, orderItemsPageModel.getNumberOfPages());

		Integer currentPage = orderItemsPageModel.getCurrentPage();
		currentPage = firstPage;
		orderItemsPageModel.firstPage();
		assertEquals(orderItemsPageModel.getCurrentPage(), currentPage);

		orderItemsPageModel.prevPage();
		assertEquals(orderItemsPageModel.getCurrentPage(), --currentPage);

		orderItemsPageModel.nextPage();
		assertEquals(orderItemsPageModel.getCurrentPage(), ++currentPage);

		orderItemsPageModel.lastPage();
		currentPage = maxNumberOfPages - 1;
		assertEquals(orderItemsPageModel.getCurrentPage(), currentPage);
	}

	@Test
	public void testIsFirstPage() {
		fillData();
		orderItemsPageModel.firstPage();
		assertTrue(orderItemsPageModel.isFirstPage());

		orderItemsPageModel.nextPage();
		assertFalse(orderItemsPageModel.isFirstPage());

		orderItemsPageModel = new OrderItemsPageModel(0, 2);
		assertTrue(orderItemsPageModel.isFirstPage());
	}

	@Test
	public void testIsLastPage() {
		fillData();
		orderItemsPageModel.lastPage();
		assertTrue(orderItemsPageModel.isLastPage());

		orderItemsPageModel.prevPage();
		assertFalse(orderItemsPageModel.isLastPage());

		orderItemsPageModel = new OrderItemsPageModel(0, 2);
		assertTrue(orderItemsPageModel.isLastPage());
	}

	@Test
	public void testHasPrevPage() {
		fillData();
		orderItemsPageModel.firstPage();
		assertFalse(orderItemsPageModel.hasPrevPage());

		orderItemsPageModel.lastPage();
		assertTrue(orderItemsPageModel.hasPrevPage());
	}

	@Test
	public void testHasNextPage() {
		fillData();
		orderItemsPageModel.firstPage();
		assertTrue(orderItemsPageModel.hasNextPage());

		orderItemsPageModel.lastPage();
		assertFalse(orderItemsPageModel.hasNextPage());
	}

	@Test
	public void testIsDisabled() {
		fillData();
		assertTrue(orderItemsPageModel
				.isDisabled(OrderItemsPageModel.FIRST_PAGE));
		assertTrue(orderItemsPageModel
				.isDisabled(OrderItemsPageModel.PREV_PAGE));

		assertFalse(orderItemsPageModel
				.isDisabled(OrderItemsPageModel.NEXT_PAGE));
		assertFalse(orderItemsPageModel
				.isDisabled(OrderItemsPageModel.LAST_PAGE));

		assertFalse(orderItemsPageModel
				.isDisabled(OrderItemsPageModel.FIRST_PAGE
						+ OrderItemsPageModel.LAST_PAGE));
	}

	@Test
	public void testIsPageValid() {
		fillData();
		assertTrue(orderItemsPageModel.isPageValid());

		orderItemsPageModel.lastPage();
		orderItemsPageModel.nextPage();
		assertFalse(orderItemsPageModel.isPageValid());

		orderItemsPageModel = new OrderItemsPageModel(0, 0);
		assertTrue(orderItemsPageModel.isPageValid());

		orderItemsPageModel.prevPage();
		assertFalse(orderItemsPageModel.isPageValid());
	}

	@Test
	public void testValidatePage() {
		fillData();

		Integer currPage = orderItemsPageModel.getCurrentPage();
		orderItemsPageModel.validatePageNumber();
		assertEquals(currPage, orderItemsPageModel.getCurrentPage());

		currPage = firstPage;
		orderItemsPageModel.firstPage();
		orderItemsPageModel.prevPage();
		orderItemsPageModel.validatePageNumber();
		assertEquals(currPage, orderItemsPageModel.getCurrentPage());

		currPage = maxNumberOfPages - 1;
		orderItemsPageModel.lastPage();
		orderItemsPageModel.nextPage();
		orderItemsPageModel.validatePageNumber();
		assertEquals(currPage, orderItemsPageModel.getCurrentPage());
	}

	private void fillData() {
		orderItemsPageModel = new OrderItemsPageModel(maxNumberOfPages,
				firstPage);
	}
}

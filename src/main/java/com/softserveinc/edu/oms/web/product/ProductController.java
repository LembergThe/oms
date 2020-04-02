//
// ListProductController
//
// 11 . 2011
//
package com.softserveinc.edu.oms.web.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.OrderItem;
import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.service.interfaces.IDimensionService;
import com.softserveinc.edu.oms.service.interfaces.IProductService;
import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.TemporaryListOrderData;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans.TemporaryOrderItem;
import com.softserveinc.edu.oms.web.orderitem.util.OrderItemParameters;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;
import com.softserveinc.edu.oms.web.product.model.ListProductModel;

/**
 * @author Ivanka
 * 
 */
@Controller
public class ProductController {
	private final static String SORT = "sort";
	private final static String SELECT = "select";
	private final static String RESET = "reset";
	private final static String DONE = "done";

	private IProductService productService;
	private IDimensionService dimensionService;

	@Autowired
	public void setDimensionService(final IDimensionService dimensionService) {
		this.dimensionService = dimensionService;
	}

	@Autowired
	public void setProductService(final IProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "products.htm", method = RequestMethod.POST)
	public String listProducts(final HttpServletRequest request,
			final ModelMap map, final ListProductModel productModel) {

		if (productModel.isEmpty()) {
			fillProductModel(productModel, request);
		}

		if (request.getParameter(SORT) != null) {
			productModel.changeSortPropertiesName(request.getParameter(SORT));
		}

		if (request.getParameter(SELECT) != null) {
			productModel.setProductId(Integer.parseInt(request
					.getParameter(SELECT)));
		}

		if (request.getParameter(RESET) != null) {
			productModel.reset();
		}

		if (request.getParameter(DONE) != null) {
			return saveChanges(request, productModel);
		}

		List<Product> products = findProducts(productModel);
		if (productModel.getProductId() != null) {
			map.put("selectedProduct",
					productService.findByID(productModel.getProductId()));
		}

		map.put("model", productModel);
		map.put("products", products);

		return "listProducts";
	}

	/**
	 * @param request
	 * @param productModel
	 * @return
	 * 
	 */
	private String saveChanges(final HttpServletRequest request,
			final ListProductModel productModel) {
		ITemporaryOrderData orderData = SessionExplorer.getTemporaryListData(
				request).getTemporaryOrderData(productModel.getOrderId());

		if (productModel.getProductId() == null) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE + "=" + "Pick product";
		}

		if (!productModel.isQuantityValid()) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE
					+ "="
					+ "Quantity is int value (cannot be empty and contain symbols except numbers)";
		}

		TemporaryOrderItem tempOrderItem = new TemporaryOrderItem(null, null);

		if (productModel.getOrderItemId() != null) {
			tempOrderItem.setId(productModel.getOrderItemId());
		}

		OrderItem orderItem = new OrderItem();

		Product product = productService.findByID(productModel.getProductId());
		orderItem.setItemPrice(product.getProductPrice());
		orderItem.setProduct(product);

		orderItem.setQuantity(Integer.parseInt(productModel.getQuantity()));

		orderItem.setDimension(dimensionService.findDimensionByName(
				productModel.getDimension()).get(0));

		tempOrderItem.setOrderItem(orderItem);

		orderData.insertOrUpdate(tempOrderItem);

		return "redirect:orderItems.htm?" + ParameterNames.ORDER_ID + "="
				+ orderData.getTempOrder().getId();

	}

	/**
	 * @param productModel
	 * @return
	 */
	private List<Product> findProducts(final ListProductModel productModel) {
		SortProperties sortProperties = productModel.getSortProperties()
				.createSortProperties();

		if (productModel.getSearchProperty().equals(
				ListProductModel.ITEM_DESCRIPTION)) {
			return productService.findByDescription(
					productModel.getSearchValue(), sortProperties);
		}
		if (productModel.getSearchProperty().equals(ListProductModel.ITEM_NAME)) {
			return productService.findByName(productModel.getSearchValue(),
					sortProperties);
		}
		return new ArrayList<Product>();
	}

	private void fillProductModel(final ListProductModel productModel,
			final HttpServletRequest request) {

		productModel.setOrderId(Integer.parseInt(request
				.getParameter(ParameterNames.ORDER_ID)));
		if (request.getParameter(ParameterNames.ORDER_ITEM_ID) != null) {
			productModel.setOrderItemId(Integer.parseInt(request
					.getParameter(ParameterNames.ORDER_ITEM_ID)));
		}

		productModel.setDefaultValues();

		if (productModel.getOrderItemId() != null) {

			TemporaryListOrderData listOrderData = SessionExplorer
					.getTemporaryListData(request);
			ITemporaryOrderData orderData = listOrderData
					.getTemporaryOrderData(productModel.getOrderId());

			TemporaryOrderItem tempOrderItem = orderData.findById(productModel
					.getOrderItemId());

			productModel.setProductId(tempOrderItem.getOrderItem().getProduct()
					.getId());

			productModel.setQuantity(tempOrderItem.getOrderItem().getQuantity()
					.toString());
			productModel.setDimension(tempOrderItem.getOrderItem()
					.getDimension().getDimensionName());
		}

	}
}

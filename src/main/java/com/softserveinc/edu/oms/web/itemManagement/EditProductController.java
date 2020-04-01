package com.softserveinc.edu.oms.web.itemManagement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.web.itemManagement.model.ProductModel;

@Controller
@RequestMapping("editItem.htm")
public class EditProductController extends AbstractFormProductController {

	private static final String PRODUCT_ID = "productID";

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String prepareForm(final ModelMap modelMap,
			final HttpServletRequest request) {
		String param = request.getParameter(PRODUCT_ID);
		Integer id;

		if (param == null) {
			id = (Integer) request.getSession(true).getAttribute(PRODUCT_ID);
		} else {
			id = Integer.parseInt(param);
			request.getSession(true).setAttribute(PRODUCT_ID, id);
		}

		Product product = productService.findByID(id);

		ProductModel productModel = createProductModel(product);

		modelMap.addAttribute("productModel", productModel);

		return "itemForm";
	}

	private ProductModel createProductModel(final Product product) {
		ProductModel productModel = new ProductModel();

		productModel.setId(product.getId().toString());
		productModel.setProductName(product.getProductName());
		productModel.setProductDescription(product.getProductDescription());
		productModel.setProductPrice(product.getProductPrice().toString());

		return productModel;
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("productModel") final ProductModel productModel,
			final BindingResult result) {
		Product product = createProduct(productModel);

		product.setId(Integer.parseInt(productModel.getId()));

		productService.insertOrUpdate(product);
		return "redirect:itemManagement.htm";
	}
}

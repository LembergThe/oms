package com.softserveinc.edu.oms.web.itemManagement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.web.itemManagement.model.ProductModel;

@Controller
public class AddProductController extends AbstractFormProductController {

	@Override
	@RequestMapping(value = "addItem.htm", method = RequestMethod.GET)
	public String prepareForm(final ModelMap modelMap,
			final HttpServletRequest request) {
		ProductModel productModel = new ProductModel();

		modelMap.addAttribute("productModel", productModel);

		return "itemForm";
	}

	@Override
	@RequestMapping(value = "addItem.htm", method = RequestMethod.POST)
	public String onSubmit(final ProductModel productModel,
			final BindingResult result) {
		Product product = createProduct(productModel);

		productService.insertOrUpdate(product);

		return "redirect:itemManagement.htm";
	}
}

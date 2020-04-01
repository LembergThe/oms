package com.softserveinc.edu.oms.web.itemsmanagement;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.service.interfaces.IProductService;
import com.softserveinc.edu.oms.web.unifiederrorpage.ErrorController;
import com.softserveinc.edu.oms.web.unifiederrorpage.ErrorInfo;

/**
 * 
 * @author roman
 * 
 */

@Controller
public class ItemManagementController {
	private IProductService productService;

	@Inject
	public void setProductService(final IProductService productService) {
		this.productService = productService;
	}

	/**
	 * Delete product controller
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteproduct.htm")
	public String deleteProduct(@RequestParam("productID") final String id,
			final Model model) {
		productService.delete(productService.findByID(Integer.valueOf(id)));
		model.addAttribute("products", productService.findAll());
		System.out.println(productService.findAll().size());

		return "redirect:productslist.htm";
	}

	/**
	 * product list controller
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("productslist.htm")
	public String productList(final Map<String, Object> map,
			final HttpServletRequest request) {
		if (true) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setMessageToUser("test error");
			errorInfo
					.setException(new Exception("this is test sdsdds sd sd sd"));

			return ErrorController.redirectToErrorPage(errorInfo, request);
		}
		map.put("products",
				ItemManagementUtils.paging(1, 50, productService.findAll()));
		System.out.println(productService.findAll().size());

		return "productsList";
	}

	/**
	 * Add new product controller
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addprotuct.htm", method = RequestMethod.GET, params = "new")
	public String addNewProduct(final Model model) {
		model.addAttribute(new Product());
		return "productAddEdit";
	}

	/**
	 * Write information from add product form to DB
	 * 
	 * @param product
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addprotuct.htm", method = RequestMethod.POST)
	public String addProductFromForm(@Valid final Product product,
			final BindingResult bindingResult, final Map<String, Object> map) {
		if (bindingResult.hasErrors()) {
			return "productAddEdit";
		}
		if (productService.containsProductByName(product.getProductName())) {
			map.put("productError", "Product with such name exists");
			return "productAddEdit";
		}
		productService.insertOrUpdate(product);
		return "redirect:productslist.htm";
	}

	/**
	 * Edit existing product
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editproduct.htm", method = RequestMethod.GET)
	public String editProduct(@RequestParam("productID") final String id,
			final Model model) {
		model.addAttribute(productService.findByID(Integer.valueOf(id)));
		return "productAddEdit";
	}

	/**
	 * Write information from edit product form to DB
	 * 
	 * @param product
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "editproduct.htm", method = RequestMethod.POST)
	public String editProductFromForm(@Valid final Product product,
			final BindingResult bindingResult, final Map<String, Object> map) {
		if (bindingResult.hasErrors()) {
			return "productAddEdit";
		}
		productService.insertOrUpdate(product);
		return "redirect:productslist.htm";
	}

}

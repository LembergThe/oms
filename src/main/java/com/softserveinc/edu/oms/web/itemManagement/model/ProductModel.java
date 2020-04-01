package com.softserveinc.edu.oms.web.itemManagement.model;

/**
 * @author Orest
 * 
 */
public class ProductModel {
	private String id;
	private String productName;
	private String productDescription;
	private String productPrice;

	public ProductModel() {

	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(final String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(final String productPrice) {
		this.productPrice = productPrice;
	}

}

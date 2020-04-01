package com.softserveinc.edu.oms.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "Products")
public class Product extends AbstractEntity implements Comparable<Product> {
	private Integer id;
	private String productName;
	private String productDescription;
	private Double productPrice;
	private Boolean active;

	public Product() {
		active = true;
	}

	public Product(final Integer id, final String productName,
			final String productDescription, final Double productPrice) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	@Override
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	@Column(name = "ProductName")
	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	@Column(name = "ProductDescription")
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(final String productDescription) {
		this.productDescription = productDescription;
	}

	@Column(name = "ProductPrice")
	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(final Double productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "IsProductActive")
	public Boolean isActive() {
		return active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "productName = " + productName;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		if (productDescription == null) {
			if (other.productDescription != null) {
				return false;
			}
		} else if (!productDescription.equals(other.productDescription)) {
			return false;
		}
		if (productName == null) {
			if (other.productName != null) {
				return false;
			}
		} else if (!productName.equals(other.productName)) {
			return false;
		}
		if (productPrice == null) {
			if (other.productPrice != null) {
				return false;
			}
		} else if (!productPrice.equals(other.productPrice)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(final Product product) {
		if (productName.compareTo(product.productName) < 0) {
			return -1;
		} else if (productName.compareTo(product.productName) > 0) {
			return 1;
		} else {
			return productDescription.compareTo(product.productDescription);
		}
	}
}

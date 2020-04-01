package com.softserveinc.edu.oms.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "OrderItems")
public class OrderItem extends AbstractEntity {
	private Integer id;
	private Product product;
	private Dimension dimension;
	private Integer quantity;
	private Order order;
	private Double itemPrice;
	private Double cost;

	private final static Integer numberOfFields = 6;

	public OrderItem() {
	}

	public OrderItem(final Integer id, final Product product,
			final Dimension dimension, final Integer quantity,
			final Order order, final Double itemPrice, final Double cost) {
		super();
		this.id = id;
		this.product = product;
		this.dimension = dimension;
		this.quantity = quantity;
		this.order = order;
		this.itemPrice = itemPrice;
		this.cost = cost;
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

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ProductRef")
	public Product getProduct() {
		return product;
	}

	public void setProduct(final Product product) {
		this.product = product;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "DimensionRef")
	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(final Dimension dimension) {
		this.dimension = dimension;
	}

	@Column(name = "Quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "OrderRef")
	public Order getOrder() {
		return order;
	}

	public void setOrder(final Order order) {
		this.order = order;
	}

	@Column(name = "ItemPrice")
	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(final Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Column(name = "Cost")
	public Double getCost() {
		return cost;
	}

	public void setCost(final Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", product=" + product + ", dimension="
				+ dimension + ", quantity=" + quantity + ", order=" + order
				+ ", itemPrice=" + itemPrice + ", cost=" + cost + "]";
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof OrderItem) {
			OrderItem other = (OrderItem) obj;

			for (int i = 0; i < numberOfFields; i++) {
				Object thisField = getField(i);
				Object otherFiled = other.getField(i);

				if (thisField == null && otherFiled != null) {
					return false;
				}
				if (thisField != null && !thisField.equals(otherFiled)) {
					return false;
				}
			}

		} else {
			return false;
		}

		return true;
	}

	private Object getField(final Integer number) {
		switch (number) {

		case 0:
			return product;
		case 1:
			return dimension;
		case 2:
			return quantity;
		case 3:
			return order;
		case 4:
			return itemPrice;
		case 5:
			return cost;
		default:
			return null;
		}
	}
}

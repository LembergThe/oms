package com.softserveinc.edu.oms.domain.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

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
@Table(name = "Orders")
public class Order extends AbstractEntity {
	private Integer id;
	private int orderNumber;
	private String orderName;
	private int maxDiscount;
	private User customer;
	private User assigne;
	private Double totalPrice;
	private Date deliveryDate;
	private Date preferableDeliveryDate;
	private Date orderDate;
	private OrderStatus orderStatus;
	private Boolean isGift;

	@Column(name = "PreferableDeliveryDate")
	public Date getPreferableDeliveryDate() {
		return preferableDeliveryDate;
	}

	public String transformPreferableDeliveryDateToStr() {
		if (preferableDeliveryDate == null)
			return "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(preferableDeliveryDate);
	}

	public void setPreferableDeliveryDate(Date preferableDeliveryDate) {
		this.preferableDeliveryDate = preferableDeliveryDate;
	}

	@Column(name = "IsGift")
	public Boolean getIsGift() {
		return isGift;
	}

	public void setIsGift(Boolean isGift) {
		this.isGift = isGift;
	}

	public Order() {
	}

	public Order(final Integer id, final User customer, final User assigne,
			final Double totalPrice, final Date deliveryDate,
			final Date orderDate, final OrderStatus orderStatus) {
		super();
		this.id = id;
		this.customer = customer;
		this.assigne = assigne;
		this.totalPrice = totalPrice;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
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
	@JoinColumn(name = "Customer")
	public User getCustomer() {
		return customer;
	}

	public void setCustomer(final User customer) {
		this.customer = customer;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Assigne")
	public User getAssigne() {
		return assigne;
	}

	public void setAssigne(final User assigne) {
		this.assigne = assigne;
	}

	@Column(name = "TotalPrice")
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(final Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "DeliveryDate")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public String transformDeliveryDateToStr() {
		if (deliveryDate == null)
			return "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(deliveryDate);
	}

	public void setDeliveryDate(final Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "OrderDate")
	public Date getOrderDate() {
		return orderDate;
	}

	public String transformOrderDateToStr() {
		if (orderDate == null)
			return "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"HH:mm dd-MM-yyyy");
		return simpleDateFormat.format(orderDate);
	}

	public void setOrderDate(final Date orderDate) {
		this.orderDate = orderDate;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "OrderStatusRef")
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(final OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "OrderName", length = 100)
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(final String orderName) {
		this.orderName = orderName;
	}

	public void setMaxDiscount(final int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	@Column(name = "MaxDiscount")
	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setOrderNumber(final int orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "OrderNumber", unique = true)
	public Integer getOrderNumber() {
		return orderNumber;
	}

}

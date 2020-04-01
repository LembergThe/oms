package com.softserveinc.edu.oms.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "OrderStatuses")
public class OrderStatus extends AbstractEntity {
	private Integer id;
	private String orderStatusName;

	public OrderStatus() {
	}

	public OrderStatus(final Integer id, final String orderStatusName) {
		super();
		this.id = id;
		this.orderStatusName = orderStatusName;
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

	@Column(name = "OrederStatusName")
	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(final String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	@Override
	public String toString() {
		return orderStatusName;
	}

	public boolean hasThisOrderStatusName(String name) {
		return orderStatusName.equals(name);
	}

}

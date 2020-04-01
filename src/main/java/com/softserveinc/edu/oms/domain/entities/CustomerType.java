package com.softserveinc.edu.oms.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "CustomerTypes")
public class CustomerType extends AbstractEntity implements Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -561768177172795025L;

	private Integer id;
	private String typeName;
	private Double minRange;
	private Double maxRange;
	private Double discount;

	public CustomerType() {
	}

	public CustomerType(final Integer id, final String typeName,
			final Double minRange, final Double maxRange, final Double discount) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.discount = discount;
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

	@Column(name = "TypeName")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "MinRange")
	public Double getMinRange() {
		return minRange;
	}

	public void setMinRange(final Double minRange) {
		this.minRange = minRange;
	}

	@Column(name = "MaxRange")
	public Double getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(final Double maxRange) {
		this.maxRange = maxRange;
	}

	@Column(name = "Discount")
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(final Double discount) {
		this.discount = discount;
	}

}

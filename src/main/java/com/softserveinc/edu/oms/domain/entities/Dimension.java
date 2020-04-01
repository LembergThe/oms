package com.softserveinc.edu.oms.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "Dimensions")
public class Dimension extends AbstractEntity {
	private Integer id;
	private String dimensionName;
	private Integer numberOfProduct;

	public Dimension() {
	}

	public Dimension(final Integer id, final String dimensionName,
			final Integer numberOfProduct) {
		super();
		this.id = id;
		this.dimensionName = dimensionName;
		this.numberOfProduct = numberOfProduct;
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

	@Column(name = "DimensionName")
	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(final String dimensionName) {
		this.dimensionName = dimensionName;
	}

	@Column(name = "NumberOfProducts")
	public Integer getNumberOfProduct() {
		return numberOfProduct;
	}

	public void setNumberOfProduct(final Integer numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}

	@Override
	public String toString() {
		return dimensionName;
	}
}

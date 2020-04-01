/*
 * DimensionTypesEnum.java
 *
 * Version 1.0
 *
 * 11.07.2011
 *
 * (c) Vitalik Nobis nobisvitaliy@gmail.com
 */
package com.softserveinc.edu.oms.persistence.dao.params;

/**
 * @author Vitalik
 * 
 */
public enum DimensionTypesEnum {
	ITEM("Item", 1), BOX("Box", 5), PACKAGE("Package", 10);

	private final String dimensionTypeName;
	private final int numberOfProductInThisType;

	private DimensionTypesEnum(String str, int numberOfProduct) {
		dimensionTypeName = str;
		this.numberOfProductInThisType = numberOfProduct;
	}

	public String getDimensionTypeName() {
		return this.dimensionTypeName;
	}

	public int getNumberOfProductInThisType() {
		return this.numberOfProductInThisType;
	}
}

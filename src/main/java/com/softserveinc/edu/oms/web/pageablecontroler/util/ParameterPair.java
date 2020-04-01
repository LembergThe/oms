/**
 * 
 */
package com.softserveinc.edu.oms.web.pageablecontroler.util;

/**
 * @author Vitalik
 * 
 */
public class ParameterPair {
	private String parameterName;
	private String parameterValue;

	public ParameterPair(String parameterName, String parameterValue) {
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
}

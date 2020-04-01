/**
 * 
 */
package com.softserveinc.edu.oms.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vitalik
 * 
 */
public class ParametersValidator {
	/**
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static Boolean isValidIntegerParameter(
			final HttpServletRequest request, String parameterName) {
		String integerParameter = request.getParameter(parameterName);
		if (integerParameter == null) {
			return false;
		} else if (integerParameter.equals("")) {
			return false;
		} else {
			try {
				Integer.parseInt(integerParameter);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static Boolean isValidStringParameter(
			final HttpServletRequest request, String parameterName) {
		String stringParameter = request.getParameter(parameterName);
		if (stringParameter == null) {
			return false;
		} else if (stringParameter.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static Boolean isValidParameterWithoutValue(
			final HttpServletRequest request, String parameterName) {
		String stringParameter = request.getParameter(parameterName);
		if (stringParameter == null) {
			return false;
		} else {
			return true;
		}
	}

	public static Boolean isValidDateParameter(
			final HttpServletRequest request, String parameterName,
			String datePattern) {
		String dateParameter = request.getParameter(parameterName);
		if (dateParameter == null) {
			return false;
		} else if (dateParameter.equals("")) {
			return false;
		} else {
			try {
				DateFormat formatter = new SimpleDateFormat(datePattern);
				formatter.parse(dateParameter);
				return true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
	}
}

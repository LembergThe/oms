/**
 * 
 */
package com.softserveinc.edu.oms.web;

/**
 * @author Vitalik
 * 
 */
public class ControlerErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String exceptionMessage;
	protected String controlerName;

	public ControlerErrorException() {
		super();
		this.exceptionMessage = "no error message";
		this.controlerName = "no contrpler name";
	}

	public ControlerErrorException(String controlerName, String exceptionMessage) {
		super();
		this.controlerName = controlerName;
		this.exceptionMessage = exceptionMessage;
	}

	public String toString() {
		return "Controler " + this.controlerName + ": " + this.exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getControlerName() {
		return controlerName;
	}

	public void setControlerName(String controlerName) {
		this.controlerName = controlerName;
	}
}

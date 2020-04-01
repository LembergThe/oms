/**
 * 
 */
package com.softserveinc.edu.oms.web;

/**
 * @author Vitalik
 * 
 */
public class ControlerRedirectErrorException extends ControlerErrorException {
	private static final long serialVersionUID = 2L;
	private String redirectUrl;

	public ControlerRedirectErrorException() {
		super("no contrpler name", "no error message");
		this.redirectUrl = "no redirect url";
	}

	public ControlerRedirectErrorException(String controlerName,
			String exceptionMessage, String redirectUrl) {
		super(controlerName, exceptionMessage);
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}
}

package com.softserveinc.edu.oms.web.unifiederrorpage;

/**
 * 
 * @author roman
 * 
 */
public class ErrorInfo {
	private String messageToUser;
	private String description;
	private Exception exception;

	public ErrorInfo() {
		messageToUser = "N/A";
		description = "";
		exception = new Exception("N/A");
	}

	public String getMessageToUser() {
		return messageToUser;
	}

	public void setMessageToUser(final String messageToUser) {
		this.messageToUser = messageToUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(final Exception exception) {
		this.exception = exception;
	}

}

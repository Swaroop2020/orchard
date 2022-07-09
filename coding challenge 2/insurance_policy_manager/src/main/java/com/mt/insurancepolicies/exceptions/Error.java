package com.mt.insurancepolicies.exceptions;

import java.util.Date;

public class Error {
	private Date timestamp;
	private String description;
	private String errorPath;
	
	public Error() {
		super();
	}
	public Error(Date timestamp, String description, String message) {
		super();
		this.timestamp = timestamp;
		this.description = description;
		this.errorPath = message;
		
	}
	public String getErrorPath() {
		return errorPath;
	}
	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMessage() {
		return errorPath;
	}
	public void setMessage(String message) {
		this.errorPath = message;
	}

}

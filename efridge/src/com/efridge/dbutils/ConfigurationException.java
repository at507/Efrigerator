package com.efridge.dbutils;

public class ConfigurationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ConfigurationException() {
		super();
	}
	
	public ConfigurationException(String message) {
		super(message);
	}
	
	public ConfigurationException(Throwable t) {
		super(t);
	}
	
}

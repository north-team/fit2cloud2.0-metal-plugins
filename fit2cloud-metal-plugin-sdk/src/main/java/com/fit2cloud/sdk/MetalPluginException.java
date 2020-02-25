package com.fit2cloud.sdk;

public class MetalPluginException extends Exception {

	private static final long serialVersionUID = -7430603197031343440L;

	public MetalPluginException() {
		super();
	}

	public MetalPluginException(String message, Throwable cause,
								boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MetalPluginException(String message, Throwable cause) {
		super(message, cause);
	}

	public MetalPluginException(String message) {
		super(message);
	}

	public MetalPluginException(Throwable cause) {
		super(cause);
	}
}

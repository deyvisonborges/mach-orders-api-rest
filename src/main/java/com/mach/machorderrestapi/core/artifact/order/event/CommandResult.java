package com.mach.machorderrestapi.core.artifact.order.event;

public class CommandResult {
	private boolean isSuccess;
	private String message;

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getMessage() {
		return message;
	}
}

package com.mach.machorderrestapi.shared.exception;

public class BadRequestException extends RuntimeException{
	public BadRequestException(String message) {
		super(message);
	}
}

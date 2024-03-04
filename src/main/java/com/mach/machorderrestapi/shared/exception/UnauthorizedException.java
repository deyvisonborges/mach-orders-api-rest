package com.mach.machorderrestapi.shared.exception;

public class UnauthorizedException extends RuntimeException{
	public UnauthorizedException(String message) {
		super(message);
	}
}

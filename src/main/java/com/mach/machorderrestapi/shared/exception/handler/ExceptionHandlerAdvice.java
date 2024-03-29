package com.mach.machorderrestapi.shared.exception.handler;

import com.mach.machorderrestapi.shared.exception.BadRequestException;
import com.mach.machorderrestapi.shared.exception.NotFoundException;
import com.mach.machorderrestapi.shared.exception.UnauthorizedException;
import com.mach.machorderrestapi.shared.exception.dto.ErrorResponseRecord;
import com.mach.machorderrestapi.shared.exception.util.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponseRecord> notFoundException(NotFoundException n) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
			new ErrorResponseRecord(n.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value())
		);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseRecord> badRequestException(BadRequestException n) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorResponseRecord(n.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value())
		);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponseRecord> unauthorizedException(UnauthorizedException n) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
				new ErrorResponseRecord(n.getMessage(), HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value())
		);
	}

	@ExceptionHandler(InstantiationException.class)
	public ResponseEntity<ErrorResponseRecord> integrationException(InstantiationException n) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
				new ErrorResponseRecord(n.getMessage(), HttpStatus.BAD_GATEWAY, HttpStatus.BAD_GATEWAY.value())
		);
	}

	@ExceptionHandler(WebClientResponseException.class)
	public ResponseEntity<ErrorResponseRecord> webClientResponseException(WebClientResponseException n) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
				new ErrorResponseRecord(n.getMessage(), HttpStatus.BAD_GATEWAY, HttpStatus.BAD_GATEWAY.value())
		);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponseRecord> validationException(ValidationException v){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorResponseRecord(v.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value())
		);
	}
}

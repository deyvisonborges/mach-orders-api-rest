package com.mach.machorderrestapi.shared.exception.util.validator;

import java.util.*;
import java.util.regex.Pattern;

public class Validator {
	public final Map<String, List<String>> errors = new HashMap<>();

	public void addError(String attribute, String message) {
		if(!errors.containsKey(attribute)) {
			errors.put(attribute, new ArrayList<>());
		}
		errors.get(attribute).add(message);
	}

	public void hasMin(String attribute, String value, Integer length, String message) {
		if(value != null && (value.length() < length)) {
			addError(attribute, message);
		}
	}

	public void hasMax(String attribute, String value, Integer length, String message) {
		if(value.length() > length) {
			addError(attribute, message);
		}
	}

	public void hasLength(String attribute, Integer length, String value, String message) {
		if(value != null && (value.length() != length)) {
			addError(attribute, message);
		}
	}

	public void isRequired(String attribute, String value) {
		if(Objects.isNull(value) || value.isEmpty()) {
			addError(attribute, "attribute " + attribute + " is required");
		}
	}

	public void isValidEmail(String attribute, String email, String message) {
		final String pattern = "^(.+)@(\\S+)$";

		if(email != null ) {
			var isValid = Pattern.compile(pattern)
					.matcher(email)
					.matches();

			if(!isValid) {
				addError(attribute, message);
			}
		}
	}

	public void nonNull(String attribute, Object value, String message) {
		if (value != null) {
			addError(attribute, message);
		}
	}

	public void isNull(String attribute, Object value, String message) {
		if (value == null) {
			addError(attribute, message);
		}
	}

	public Boolean validate() {
		return errors.isEmpty();
	}

	public List<String> getErrorsByAttribute(String attribute) {
		return errors.getOrDefault(attribute, new ArrayList<>());
	}

	public Map<String, List<String>> getAllErrors() {
		return errors;
	}

	public void clearErrors() {
		errors.clear();
	}

	public void finish(Validator errors) {
		if(Boolean.FALSE.equals(errors.validate())) {
			Map<String, List<String>> mappedErrors = new HashMap<>(errors.getAllErrors());
			errors.clearErrors();
			throw new ValidationException(mappedErrors);
		}
	}
}
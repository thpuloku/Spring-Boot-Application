package com.efuture.demo.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class GenericValidator<T> {
	protected abstract void validate(T t);

	protected void validateRequiredField(String fieldName, String value) {
		if (StringUtils.isBlank(value)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error.".concat(fieldName).concat(".required"));
		}
	}



}

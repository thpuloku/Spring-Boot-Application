package com.efuture.demo.validator;

import com.efuture.demo.exception.ProductCreationException;
import lombok.RequiredArgsConstructor;
import com.efuture.demo.model.api.request.ProductRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator extends  GenericValidator<ProductRequestDTO>{


	@Override
	public void validate(ProductRequestDTO requestDTO) {
		validateRequiredField("productName", requestDTO.getName());
		//validateRequiredField("productPrice", requestDTO.getPrice());
	}
	public void validateDouble(String fieldName, String value, double max, double min, boolean required)
	//throws ValidationException
	{
		if (value == null || StringUtils.isBlank(value)) {
			if (required) {
				throw new ProductCreationException(HttpStatus.BAD_REQUEST, "error.".concat(fieldName).concat(".required"));
			} else {
				return;
			}
		}
		Double doubleValue;
		try {
			doubleValue = Double.parseDouble(value);
		} catch (NumberFormatException e) {
				throw new ProductCreationException(HttpStatus.BAD_REQUEST, "error.".concat(fieldName).concat(".valueIsInvalid"));
		}

//		if (doubleValue > max) {
//			//throw new ProductCreationException(ERROR_INVALID_MAX_VALUE, fieldName);
//		}

//		if (doubleValue < min) {
//		//	throw new ValidationException(ERROR_INVALID_MIN_VALUE, fieldName);
//		}
	}

}

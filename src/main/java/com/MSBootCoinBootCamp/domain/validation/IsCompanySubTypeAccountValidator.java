package com.MSBootCoinBootCamp.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class IsCompanySubTypeAccountValidator implements ConstraintValidator<IsCompanySubTypeAccount, String> {

	List<String> subType = Arrays.asList("STD", "PY");
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return subType.contains(value);
	}

}

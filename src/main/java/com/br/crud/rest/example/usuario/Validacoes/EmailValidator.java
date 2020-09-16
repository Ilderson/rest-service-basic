package com.br.crud.rest.example.usuario.Validacoes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidate, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(value);
	    if (matcher.matches() && (value.length() < 255)) {
	    	return true;
	    }
	    return false;
	}
}

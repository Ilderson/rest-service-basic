package com.br.crud.rest.example.usuario.Validacoes;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateValidate, java.util.Date> {

	private final SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
	private DateValidate constraintAnnotation;
	
	@Override
	public void initialize(DateValidate constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		try {
            final Date min = dateParser.parse(constraintAnnotation.min());
            final Date max = dateParser.parse(constraintAnnotation.max());
            return value == null ||
                    (value.after(min) && value.before(max));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
	}

}

package com.br.crud.rest.example.usuario.Validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CompanyIDValidator implements ConstraintValidator<CompanyID, Integer> {
	List<Integer> idsValid = Arrays.asList(1, 2, 5, 7, 10);

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return idsValid.contains(value);

    }
}


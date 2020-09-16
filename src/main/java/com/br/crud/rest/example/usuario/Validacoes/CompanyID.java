package com.br.crud.rest.example.usuario.Validacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CompanyIDValidator.class)
@Documented
public @interface CompanyID {

    String message() default "CompanyID is not allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


package com.fabio.neostoreapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CnpjValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cnpj {
    
	String message() default "CNPJ inválido";
	
	boolean isRequired() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


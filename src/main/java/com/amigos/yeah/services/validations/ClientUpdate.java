package com.amigos.yeah.services.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// Código pronto da Internet para gerar anotações @

@Constraint(validatedBy = ClientUpdateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientUpdate {
    String message() default "Erro de Validação"; 

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

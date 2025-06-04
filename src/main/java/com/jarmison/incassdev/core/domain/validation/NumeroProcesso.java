package com.jarmison.incassdev.core.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumeroProcessoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroProcesso {
    String message() default "Número de processo inválido. Formato esperado: 0000000-00.0000.0.00.0000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

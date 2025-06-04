package com.jarmison.incassdev.core.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NumeroProcessoValidator implements ConstraintValidator<NumeroProcesso, String> {

    private static final String NUMERO_PROCESSO_REGEX = "^\\d{7}-\\d{2}\\.\\d{4}\\.\\d\\.\\d{2}\\.\\d{4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false; 
        }
        return Pattern.matches(NUMERO_PROCESSO_REGEX, value);
    }
}

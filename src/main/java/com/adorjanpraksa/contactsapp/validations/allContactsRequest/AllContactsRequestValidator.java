package com.adorjanpraksa.contactsapp.validations.allContactsRequest;

import com.adorjanpraksa.contactsapp.web.dto.AllContactsRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class AllContactsRequestValidator implements ConstraintValidator<AllContactsRequestValidation, AllContactsRequestDto> {
    private String errorMessage;

    @Override
    public void initialize(AllContactsRequestValidation constraintAnnotation) {

        errorMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(AllContactsRequestDto value, ConstraintValidatorContext context) {

        if (Objects.isNull(value.getFrom()) || Objects.isNull(value.getTo())) {
            return true;
        }

        boolean isValid = value.getFrom().isBefore(value.getTo());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addPropertyNode("from")
                    .addConstraintViolation();
        }
        return isValid;
    }
}

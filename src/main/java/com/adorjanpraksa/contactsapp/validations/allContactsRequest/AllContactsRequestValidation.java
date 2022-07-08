package com.adorjanpraksa.contactsapp.validations.allContactsRequest;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = AllContactsRequestValidator.class)
public @interface AllContactsRequestValidation {

    String message() default "Property 'from' must be Date and Time before the property 'to'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

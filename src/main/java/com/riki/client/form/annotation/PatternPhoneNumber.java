package com.riki.client.form.annotation;

import com.riki.client.form.validation.PhoneNumberValidator;

import jakarta.validation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface PatternPhoneNumber {
	
	public String message() default "invalid phone number";
	
	public String messageNotBlank() default "phone number must not blank";

    public String messagePhoneNumberDigit() default "only numbers, -(dash), +(plus) is allowed";
    
    public String messagePhoneNumberLength() default "max phone number length is 15";
    
    public String messagePhoneNumberFormat() default "only numbers, -(dash), +(plus) is allowed with max length 15";

    public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};

}

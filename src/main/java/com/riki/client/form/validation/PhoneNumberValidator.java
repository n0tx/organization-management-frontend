package com.riki.client.form.validation;

import com.riki.client.form.annotation.PatternPhoneNumber;

import jakarta.validation.*;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PatternPhoneNumber, String> {

	private String message;
	private String messageNotBlank;
	private String messagePhoneNumberDigit;
	private String messagePhoneNumberLength;
    private String messagePhoneNumberFormat;
	
	
	@Override
    public void initialize(PatternPhoneNumber field) {
        messageNotBlank = field.messageNotBlank();
        messagePhoneNumberDigit = field.messagePhoneNumberDigit();
        messagePhoneNumberLength = field.messagePhoneNumberLength();
        messagePhoneNumberFormat = field.messagePhoneNumberFormat();
    }
	
	@Override
	 public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {

		boolean result = true;
		int maxPhoneLength = 15;
		boolean isAllowedLength = (phoneNumber.length() > 0 && phoneNumber.length() <= maxPhoneLength);
		boolean isAllowedDigit = Pattern.matches("^[0-9-+]*$", phoneNumber);
		
        context.disableDefaultConstraintViolation();
	        
	        if (phoneNumber.isEmpty()) {
	            context.buildConstraintViolationWithTemplate(messageNotBlank).addConstraintViolation();
	            return false;
	        }
	    
	        if (!isAllowedDigit && !isAllowedLength) {
	        	message = messagePhoneNumberFormat;
	        	result = false;
	        } else if (!isAllowedDigit) {
	        	message = messagePhoneNumberDigit;
	        	result = false;
	        } else if (!isAllowedLength){
	        	message = messagePhoneNumberLength;
	        	result = false;
	        }
	        
	        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	        return result;
	    }
	
}
	
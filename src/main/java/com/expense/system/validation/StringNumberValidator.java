package com.expense.system.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringNumberValidator implements 
  ConstraintValidator<StringNumberConstraint, String> {

    @Override
    public void initialize(StringNumberConstraint stringNumber) {
    	
    }

    @Override
    public boolean isValid(String contactField,
      ConstraintValidatorContext cxt) {
    	System.out.println("Inner Validator");
        return contactField != null && contactField.matches("[0-9]+")
          && (contactField.length() > 8) && (contactField.length() < 14);
    }

}
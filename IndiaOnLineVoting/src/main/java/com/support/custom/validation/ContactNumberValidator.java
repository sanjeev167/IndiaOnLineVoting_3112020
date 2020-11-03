/**
 * 
 */
package com.support.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.support.custom.validation.fieldLevelValidator.ContactNumberConstraint;

/**
 * @author Sanjeev
 *
 */
public class ContactNumberValidator implements 
ConstraintValidator<ContactNumberConstraint, String> {

  @Override
  public void initialize(ContactNumberConstraint contactNumber) {
  }

  @Override
  public boolean isValid(String contactField,
    ConstraintValidatorContext cxt) {
      return contactField != null && contactField.matches("[0-9]+")
        && (contactField.length() > 8) && (contactField.length() < 14);
  }

}
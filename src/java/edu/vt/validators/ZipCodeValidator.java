/*
 * Created by Justin Kennedy on 2019.11.15  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
The @FacesValidator annotation on a class automatically registers the class with the runtime as a Validator. 
The "zipCodeValidator" attribute is the validator-id used in the JSF facelets page CreateAccount.xhtml within

    <f:validator validatorId="zipCodeValidator" />

to invoke the "validate" method of the annotated class given below.
- Balci
*/
@FacesValidator("zipCodeValidator")

public class ZipCodeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        // Type cast the inputted "value" to String type
        String zipcode = (String) value;

        if (zipcode == null || zipcode.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }
        
        /* REGular EXpression (regex) for validating the U.S. Postal ZIP code
        
            ^           start of regex
            [0-9]{5}    match a digit, exactly five times
            (?:         group but don't capture
            -           match a hyphen
            [0-9]{4}    match a digit, exactly four times
            )           end of the non-capturing group
            ?           make the group optional
            $           end of regex
        - Balci
        */

        // REGular EXpression (regex) to validate the U.S. Postal ZIP code entered
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
        
        if (!zipcode.matches(regex)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Zip Code Failed!", "The U.S. Postal ZIP code can consist of only 5 digits or "
                    + "5 digits, hyphen, and 4 digits!"));
        } 
    } 
    
}
package by.khrapovitsky.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;

@FacesValidator("loginValidator")
public class LoginValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {
        String login = value.toString();
        if(!StringUtils.isAlphanumeric(login)){
            throw new ValidatorException(new FacesMessage("Login must contain only letters and numbers."));
        }
    }
}

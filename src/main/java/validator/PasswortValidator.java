package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PasswortValidator")
public class PasswortValidator implements Validator {

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String feld=value.toString();
		
		if (feld.length() < 8)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwort zu kurz!", null);
			throw new ValidatorException(message);
		}		
	}
}

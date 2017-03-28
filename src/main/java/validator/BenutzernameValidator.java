package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("BenutzernameValidator")
public class BenutzernameValidator implements Validator {

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String feld=value.toString();
		
		if (feld.length() == 0)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pflichtfeld", null);
			throw new ValidatorException(message);
		}
		if (feld.length() < 5)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der Benutzername muss mindestens 5 Zeichen lang sein.", null);
			throw new ValidatorException(message);
		}	
	}
}

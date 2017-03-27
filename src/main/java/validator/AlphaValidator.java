package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("AlphaValidator")
public class AlphaValidator implements Validator {

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String feld=value.toString();
		
		if (feld.length() == 0)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pflichtfeld", null);
			throw new ValidatorException(message);
		}
		
		if(!feld.matches("[a-zA-Z]+"))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte nur Buchstaben (a-Z) verwenden!", null);
			throw new ValidatorException(message);
		}
		
	}

}

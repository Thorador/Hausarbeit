package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("NummericValidator")
public class NummericValidator implements Validator {

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String feld=value.toString();

		if(!feld.matches("[0-9]+"))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültige Anzahl", null);
			throw new ValidatorException(message);
		}		
		else 
		{	// Wert größer als 0
			if (feld.matches("[0]+"))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der Wert muss größer sein als Null.", null);
				throw new ValidatorException(message);
			}
		}
	}
}

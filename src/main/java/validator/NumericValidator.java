package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("NumericValidator")
public class NumericValidator implements Validator {

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String feld=value.toString();

		if(!feld.matches("[0-9]+"))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ung�ltige Anzahl", null);
			throw new ValidatorException(message);
		}		
		else 
		{	// Wert gr��er als 0
			if (feld.matches("[0]+"))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der Wert muss gr��er sein als Null.", null);
				throw new ValidatorException(message);
			}
		}
	}
}

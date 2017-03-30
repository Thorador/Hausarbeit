package view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MeineVeranstaltungenView {

	public String reservierungsuebersicht()
	{
		return "reservierungsuebersicht.jsf";
	}
	
	public String back()
	{
		return "home.jsf";
	}
	
	public String detailsAnzeigen()
	{
		return "veranstaltungsDetails.jsf";
	}

}
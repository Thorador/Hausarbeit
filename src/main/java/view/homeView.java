package view;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Veranstaltung;
import service.VeranstaltungService;

@Named
@RequestScoped
public class homeView {

		public String detailsAnzeigen()
		{
			return "details.jsf";
		}

}

package view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class ReservierungsuebersichtView implements Serializable {
	
	
	public String cancel()
	{
		return "frontpage.jsf";
	}
	
}

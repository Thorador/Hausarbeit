package view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.ReservierungService;
import service.SessionService;

@Named
@RequestScoped
public class ReservierungsuebersichtView implements Serializable {
	
		
	@Inject
	private SessionService sessionService;
	
	
	public String cancel()
	{
		return "frontpage.jsf";
	}
	
}

package view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.SessionService;

@Named
@RequestScoped
public class BaseView {
	
	@Inject
	private SessionService sessionService;
	
	public String logout()
	{
		sessionService.setActiveUser(null);
		return "frontpage";
	}
	
	public String veranstaltungErstellen()
	{
		return "veranstaltung";
	}

}

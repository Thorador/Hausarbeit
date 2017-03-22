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
		sessionService.logout();
		return "home";
	}
	
	public String veranstaltungErstellen()
	{
		return "veranstaltung";
	}

}

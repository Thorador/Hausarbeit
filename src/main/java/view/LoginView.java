package view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import service.SessionService;

@Named
@RequestScoped
public class LoginView implements Serializable {
	
	private String benutzername;
	private String passwort;
	
	@Inject
	private SessionService sessionService;
	
	public String login()
	{
		if ( sessionService.login(getBenutzername(), getPasswort()))
		{
			return "home.jsf";
		} else
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der angegebene Nutzer ist nicht vorhanden und/oder das Passwort ist falsch. Versuchen Sie es erneut." , null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "login.jsf";
		}		
	}
	public String cancel()
	{
		return "home.jsf";
	}
	public String getBenutzername() {
		return benutzername;
	}
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
}

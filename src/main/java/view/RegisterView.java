package view;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import service.SessionService;
import service.UserService;

@Named
@RequestScoped
public class RegisterView implements java.io.Serializable{

	private String benutzername;
	private String passwort;
	private String passwortbestaetigen;
	private String vorname;
	private String nachname;
	private boolean manager;
	private String geschlecht;
	
	@Inject
	private UserService userService;
	
	@Inject
	private SessionService sessionService;
	
	public RegisterView()
	{
		
	}
	
	public String register()
	{	
		if (getPasswort().equals(getPasswortbestaetigen()))
		{
			if (!userService.benutzernameVergeben(getBenutzername()))
			{
				userService.createUser(getBenutzername(), getPasswort(), getVorname(), getNachname(), getGeschlecht(), isManager());
				return "home.jsf";
			}
			else
			{// Benutzername schon vergeben
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der Benutzername ist bereits vegeben. Bitte wählen Sie einen anderen" , null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		else
		{// Passwörter stimmen nich überein
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die angegebenen Passwörter stimmen nicht überein. Bitte erneut eingeben" , null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "register.jsf";
	}
	
	public String cancel()
	{
		return "home.jsf";
	}
	
	
	
	
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
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
	public String getPasswortbestaetigen() {
		return passwortbestaetigen;
	}
	public void setPasswortbestaetigen(String passwortbestaetigen) {
		this.passwortbestaetigen = passwortbestaetigen;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}
}

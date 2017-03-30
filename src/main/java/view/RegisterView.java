package view;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.ValidationException;

import model.User;
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
				User user = new User();
				user.setBenutzername(getBenutzername());
				user.setPasswort(String.valueOf(getPasswort().hashCode()));
				user.setVorname(getVorname());
				user.setNachname(getNachname());
				user.setGeschlecht(getGeschlecht());
				user.setManager(isManager());
				userService.addUser(user);
				sessionService.setActiveUser(user);
				return "home.jsf";
			}
			else
			{// Benutzername schon vergeben -> Ausgabe Fehlermeldung
				setBenutzername("Benutzername schon vergeben.");
			}
		}
		else
		{// Passw�rter stimmen nich �berein -> Ausgabe Fehlermeldung
			
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

package view;



import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;
import service.SessionService;
import service.UserService;

@Named
@SessionScoped
public class MeinProfilView implements java.io.Serializable{

	private String benutzername;
	private String passwort;
	private String passwortbestaetigen;
	private String vorname;
	private String nachname;
	private String geschlecht;
	
	@Inject
	private UserService userService;
	
	@Inject
	private SessionService sessionService;
	
	public MeinProfilView()
	{
		
	}
	
	@PostConstruct
	public void init()
	{// Initialisierung der Werte f�r die MeinProfil-Oberfl�che
		if (sessionService != null && sessionService.isLoggedIn())
		{
			User user = sessionService.getActiveUser();
			setBenutzername(user.getBenutzername());
			setVorname(user.getVorname());
			setNachname(user.getNachname());
			setGeschlecht(user.getGeschlecht());
		}else
		{// wenn kein Nutzer eingeloggt...
			setBenutzername("Kein Benutzer eingeloggt!");
			setVorname("");
			setNachname("");
			setGeschlecht("");
		}
	}

	
	public String save()
	{// Aktualisieren des Nutzers
		if (getPasswort().equals(getPasswortbestaetigen()))
		{
			userService.updateUser(getPasswort(), getVorname(), getNachname(),getGeschlecht());	
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Die Änderungen wurden in ihr Profil übertragen", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die eingegebenen Passwörter stimmen nicht überein. Bitte erneut eingeben" , null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "meinProfil.jsf";
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
}

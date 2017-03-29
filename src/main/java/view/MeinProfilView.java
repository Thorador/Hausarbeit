package view;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;
import service.UserService;
import service.SessionService;

@Named
@SessionScoped
public class MeinProfilView implements java.io.Serializable{

	private String benutzername;
	private String passwort;
	private String passwortbestaetigen;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private String rolle;
	private String geschlecht;
	private String strasse;
	private String ort;
	private int plz;
	
	@Inject
	private UserService userService;
	
	@Inject
	private SessionService sessionService;
	
	public MeinProfilView()
	{
		
	}
	
	public String importBenutzer()
	{// F�llen der Werte auf der Benutzeroberf�che
		if (sessionService != null && sessionService.isLoggedIn())
		{
			User user = sessionService.getActiveUser();
			setBenutzername(user.getBenutzername());
			setVorname(user.getVorname());
			setNachname(user.getNachname());
			setGeschlecht(user.getGeschlecht());
		}else
		{
			setBenutzername("Kein Benutzer eingeloggt!");
			setVorname("");
			setNachname("");
			setGeburtsdatum(null);
			setGeschlecht("");
			setStrasse("");
			setOrt("");
			setPlz(0);
		}
		return "meinProfil.jsf";
	}
	
	public String save()
	{
		sessionService.getActiveUser().setVorname(getVorname());
		sessionService.getActiveUser().setNachname(getNachname());
		sessionService.getActiveUser().setGeschlecht(getGeschlecht());
		
		return "frontpage.jsf";
	}

	public String cancel()
	{
		return "frontpage.jsf";
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
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public String getRolle() {
		return rolle;
	}
	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	public String getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
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

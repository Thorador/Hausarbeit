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
import service.UserService;

@Named
@RequestScoped
public class RegisterView implements java.io.Serializable{

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
	
	public RegisterView()
	{
		
	}
	
	public String register()
	{	
		if (getPasswort().equals(getPasswortbestaetigen()))
		{
			if (!userService.benutzernameVergeben(getBenutzername()))
			{
				User user = userService.createUser(getBenutzername(), getPasswort(), getPasswortbestaetigen(),
						   getVorname(), getNachname(), getGeburtsdatum(), 
						   getRolle(), getGeschlecht(), getStrasse(), 
						   getOrt(), getPlz()); 

				return userService.addUser(user) ? "home.jsf" : "register.jsf";
			}
			else
			{// Benutzername schon vergeben -> Ausgabe Fehlermeldung
				setBenutzername("Benutzername schon vergeben.");								
			}
		}
		else
		{// Passw�rter stimmen nich �berein -> Ausgabe Fehlermeldung
			setPasswort("Passw�rter stimmen nicht �berein.");
			setPasswortbestaetigen("Passw�rter stimmen nicht �berein.");
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

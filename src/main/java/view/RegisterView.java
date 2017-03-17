package view;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.User;
import service.UserService;

@ManagedBean
@RequestScoped
public class RegisterView {

	private String benutzername;
	private String passwort;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private String rolle;
	private String geschlecht;
	private String strasse;
	private String ort;
	private int plz;
	
	@ManagedProperty("#{userSerivce}")
	private UserService userService = new UserService();
	
	public String register()
	{
		User user = new User(this.getBenutzername(), this.getPasswort());
		user.setId(1);
		user.setVorname("abc");
		user.setNachname("def");
		user.setGeburtsdatum(new Date(2011, 11, 11));
		user.setRolle("manager");
		user.setGeschlecht("männlich");
		user.setStrasse("ghi");
		user.setOrt("jkl");
		user.setPlz(32456);
		userService.addUser(user);
		return userService.addUser(user) ? "frontpage.jsf" : "register.jsf";
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String cancel()
	{
		return "";
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
}

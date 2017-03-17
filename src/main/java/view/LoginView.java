package view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.LoginService;

@Named
@RequestScoped
public class LoginView implements Serializable {
	
	private String benutzername;
	private String passwort;
	
	@Inject
	private LoginService loginService;
	
	public String login()
	{
		System.out.println(getBenutzername() + " " + getPasswort());
		return loginService.login(getBenutzername(), getPasswort()) ? "frontpage.jsf" : "login.jsf";			
	}
	public String cancel()
	{
		return "frontpage.jsf";
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

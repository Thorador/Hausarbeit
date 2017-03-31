package service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;

@Named
@SessionScoped
public class SessionService implements Serializable{
	
	private User activeUser;
	@Inject
	private UserService userService;

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}
	
	public boolean isLoggedIn()
	{
		if(getActiveUser() == null)
		{
			return false;
		}
		return true;
	}
	
	public boolean isNotLoggedIn()
	{
		if (getActiveUser() != null)
		{
			return false;
		}
		return true;
	}
	
	public boolean isManager()
	{
		if (isLoggedIn())
		{
			if (activeUser.isManager())
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean login(String benutzername, String passwort) {
		passwort = String.valueOf(passwort.hashCode());
		User user = userService.getUserByName(benutzername);
		if(user != null)
		{
			if(passwort.equals(user.getPasswort()))
				{
					setActiveUser(user);
					return true;
				}
		}
		return false;
	}
	
	public void logout(){
		setActiveUser(null);
	}


}

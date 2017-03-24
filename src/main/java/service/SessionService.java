package service;

import java.io.Serializable;
import java.util.Optional;

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
			if (activeUser.getRolle() == "manager")
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean login(String benutzername, String passwort) {
		passwort = String.valueOf(passwort.hashCode());
		Optional<User> user = userService.getUserByName(benutzername);
		if(user.isPresent())
		{
			if(passwort.equals(user.get().getPasswort()))
				{
					setActiveUser(user.get());
					return true;
				}
		}
		return false;
	}
	
	public void logout(){
		setActiveUser(null);
	}


}

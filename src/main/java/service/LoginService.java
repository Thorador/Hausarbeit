package service;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;

@Named
@RequestScoped

public class LoginService {
	
	@Inject
	private UserService userService;
	@Inject
	private SessionService sessionService;
	

	public boolean login(String benutzername, String passwort) {
		Optional<User> user = userService.getUserByName(benutzername);
		System.out.println("--> " + user.get().getBenutzername());
		if(user.isPresent())
		{
			if(passwort.equals(user.get().getPasswort()))
				{
					sessionService.setActiveUser(user.get());
					return true;
				}
		}
		return false;
	}

}

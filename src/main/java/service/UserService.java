package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import model.User;
import serviceInterface.IUserService;

@Named
@ApplicationScoped
public class UserService implements IUserService {

	List<User> users;

	public UserService()
	{
		users = new ArrayList<>();
		users.add(new User("bob","abc"));
	}
	
	@Override
	public User createUser(String username,
						   String passwort,
			  			   String vorname,
			  			   String nachname,
			  			   Date   geburtsdatum,
			  			   String rolle,
			  			   String geschlecht,
			  			   String strasse,
			  			   String ort,
			  			   int	  plz) {
		
		User user = new User(username, passwort);
		user.setId(users.get(users.size() - 1).getId() + 1);
			// ID wird hochgez�hlt (+1 als die letzte ID der User-Liste)
		user.setVorname(vorname);
		user.setNachname(nachname);
		user.setGeburtsdatum(geburtsdatum);
		user.setRolle(rolle);
		user.setGeschlecht(geschlecht);
		user.setStrasse(strasse);
		user.setOrt(ort);
		user.setPlz(plz);
		
		
		return user;
		
	}
	
	@Override
	public boolean addUser(User user) {
		
		if (getUserByName(user.getBenutzername()).isPresent() ==false)
		{
			return users.add(user);
		}
		return false;
		
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public Optional<User> getUserByName(String username) {
		return users.stream().filter(user -> user.getBenutzername().equals(username)).findFirst();
	}

}

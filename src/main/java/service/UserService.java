package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
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
		
		User user = new User();
		user.setBenutzername("admin");
		user.setPasswort("nimda");
		user.setRolle("manager");
		users.add(user);
		users.add(createUser("bob","abc", "abc", "Bob", "Baumeister", new Date(), "user", "w", "Landstraﬂe 34", "Heimatort", 45678));
	}
	
	@Override
	public User createUser(String username,
						   String passwort,
						   String passwortbestaetigen,
			  			   String vorname,
			  			   String nachname,
			  			   Date   geburtsdatum,
			  			   String rolle,
			  			   String geschlecht,
			  			   String strasse,
			  			   String ort,
			  			   int	  plz) {
		if (passwort.equals(passwortbestaetigen))
		{
			User user = new User();
			user.setId(users.get(users.size() - 1).getId() + 1);
				// ID wird hochgez‰hlt (+1 als die letzte ID der User-Liste)
			user.setBenutzername(username);
			user.setPasswort(String.valueOf(passwort.hashCode()));
			user.setVorname(vorname);
			user.setNachname(nachname);
			user.setGeburtsdatum(geburtsdatum);
			user.setRolle(rolle);
			user.setGeschlecht(geschlecht);
			user.setStrasse(strasse);
			user.setOrt(ort);
			user.setPlz(plz);
			return user;
		} else
		{
			return null;
		}		
	}
	
	@Override
	public boolean addUser(User user) {
		if (getUserByName(user.getBenutzername()).isPresent() == false)
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
	
	@Override
	public User getUserByID(int id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

}

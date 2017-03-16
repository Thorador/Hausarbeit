package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;

import model.User;
import serviceInterface.IUserService;

@ManagedBean
public class UserService implements IUserService {

	List<User> users;
	
	public UserService()
	{
		users = new ArrayList<>();
	}
	
	@Override
	public User createUser(String username,
						   String passwort,
			  			   String vorname,
			  			   String nachname,
			  			   Date   geburtsdatum,
			  			   String rolle,
			  			   String geschlecht,
			  			   String straﬂe,
			  			   String ort,
			  			   int	  plz) {
		
		User user = new User();
		user.setId(users.get(users.size() - 1).getId() + 1);
			// ID wird hochgez‰hlt (+1 als die letzte ID der User-Liste)
		user.setUsername(username);
		user.setPassword(passwort);
		user.setVorname(vorname);
		user.setNachname(nachname);
		user.setGeburtsdatum(geburtsdatum);
		user.setRolle(rolle);
		user.setGeschlecht(geschlecht);
		user.setStraﬂe(straﬂe);
		user.setOrt(ort);
		user.setPlz(plz);
		
		
		return user;
		
	}
	
	@Override
	public boolean addUser(User user) {
		
		if (getUserByName(user.getUsername()).isPresent())
		{
			
		}
		return false;
		
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public Optional<User> getUserByName(String username) {
		return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
	}

}

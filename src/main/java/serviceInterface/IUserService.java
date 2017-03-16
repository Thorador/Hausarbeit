package serviceInterface;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import model.User;

public interface IUserService {
	
	public User createUser(String username,String passwort, String vorname, String nachname, Date   geburtsdatum,
			   			   String rolle, String geschlecht, String strasse, String ort, int	  plz);
	public boolean addUser(User user);
	public List<User> getUsers();
	public Optional<User> getUserByName(String username);

}

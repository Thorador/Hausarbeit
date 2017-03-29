package serviceInterface;

import java.util.Date;

import model.User;

public interface IUserService {
	
	public User createUser(String username,String passwort, String passwortbestaetigen, String vorname, String nachname, Date   geburtsdatum,
			   			   String rolle, String geschlecht, String strasse, String ort, int	  plz);
	public void addUser(User user);
	public boolean benutzernameVergeben(String benutzername);
	public User getUserByName(String username);
}

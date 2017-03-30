package serviceInterface;

import java.util.Date;

import model.User;

public interface IUserService {
	
	public void addUser(User user);
	public boolean benutzernameVergeben(String benutzername);
	public User getUserByName(String username);
}

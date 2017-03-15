package serviceInterface;

import java.util.List;
import java.util.Optional;

import model.User;

public interface IUserService {
	
	public boolean addUser(User user);
	public List<User> getUsers();
	public Optional<User> getUserByName(String username);

}

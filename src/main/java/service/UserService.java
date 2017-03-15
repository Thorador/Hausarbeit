package service;

import java.util.ArrayList;
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
	public boolean addUser(User user) {
		
		if (getUserByName(user.getUsername()).isPresent())
		{
			
		}
		return false;
		
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> getUserByName(String username) {

		return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
	}

}

package service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import model.User;

@ManagedBean
@SessionScoped
public class SessionService implements Serializable{
	
	private User activeUser;

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}
	
	

}

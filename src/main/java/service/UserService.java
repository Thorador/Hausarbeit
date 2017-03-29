package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import model.User;
import serviceInterface.IUserService;


@ApplicationScoped
public class UserService implements IUserService {


	


	@Inject
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Produces
	public EntityManager createEntityManager()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
		return emf.createEntityManager();
	}
	public void disposeEntityManager(@Disposes EntityManager em){
		em.close();		
	}
	
	public UserService()
	{
		


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
			user.setBenutzername(username);
			user.setPasswort(String.valueOf(passwort.hashCode()));
			user.setVorname(vorname);
			user.setNachname(nachname);
			user.setManager(true);
			user.setGeschlecht(geschlecht);
			return user;
		} else
		{
			return null;
		}		
	}
	
	@Transactional
	@Override
	public void addUser(User user) {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(user);
			this.entityManager.getTransaction().commit();
	}
	
	@Override
	public boolean benutzernameVergeben(String benutzername)
	{
		if (getUserByName(benutzername) != null)
		{
			return true;
		}
		return false;
	}


	@Override
	public User getUserByName(String username) {
		try{
		TypedQuery<User> userQuery = entityManager.createQuery("Select u From User u where u.benutzername = :benutzername", User.class);
		User user = (User) userQuery.setParameter("benutzername", username).getSingleResult();
		return user;
		} catch (NoResultException e)
		{
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}	
	}
	

}

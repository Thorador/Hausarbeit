package service;




import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import model.User;


@ApplicationScoped
public class UserService {

	@Inject
	private SessionService sessionService;
	
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
	
	
	@Transactional
	public void addUser(User user) {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(user);
			this.entityManager.getTransaction().commit();
	}
	
	
	public boolean benutzernameVergeben(String benutzername)
	{// Versuch einen Benutzer mit identischem Namen zu finden (bei Erfolg = True)
		if (getUserByName(benutzername) != null)
		{
			return true;
		}
		return false;
	}


	
	public User getUserByName(String username) 
	{// Zurückgabe eines Users mit ausgewählten Namen sonst Return Null
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
	public User getUserById(int userId) 
	{// Zurückgabe eines Users mit ausgewählter ID sonst Return Null
		try{
		TypedQuery<User> userQuery = entityManager.createQuery("Select u From User u where u.id = :userId", User.class);
		User user = (User) userQuery.setParameter("userId", userId).getSingleResult();
		return user;
		} catch (NoResultException e)
		{
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}	
	}
	
	@Transactional
	public void updateUser(String passwort, String vorname, String nachname, String geschlecht) 
	{// Aktualisieren eines Users in der Datenbank
		User user = this.getUserById(sessionService.getActiveUser().getId());
		user.setPasswort(passwort);
		user.setVorname(vorname);
		user.setNachname(nachname);
		user.setGeschlecht(geschlecht);
		sessionService.setActiveUser(user);
		this.entityManager.getTransaction().begin();
		this.entityManager.getTransaction().commit();		
	}
	public void createUser(String benutzername, String passwort, String vorname, String nachname, String geschlecht,
			boolean manager) 
	{// Erstellen und Hinzufügen eines Users in die Datenbank
		User user = new User();
		user.setBenutzername(benutzername);
		user.setPasswort(String.valueOf(passwort.hashCode()));
		user.setVorname(vorname);
		user.setNachname(nachname);
		user.setGeschlecht(geschlecht);
		user.setManager(manager);
		this.addUser(user);
		sessionService.setActiveUser(user);		
	}
	

}

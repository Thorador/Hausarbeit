package service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Reservierung;
import model.Veranstaltung;
import serviceInterface.IReservierungService;


@Named
@ApplicationScoped
public class ReservierungService implements IReservierungService {

	List<Reservierung> reservierungen = new ArrayList<>();
	@Inject
	private SessionService sessionService;
	
	@Inject
	private VeranstaltungService veranstaltungService;
	
	@Inject
	private EntityManager entityManager;
	
	public ReservierungService()
	{		
		
	}

	@Override
	public void addReservierung(Reservierung reservierung) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(reservierung);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public List<Reservierung> getReservierungen() {
		TypedQuery<Reservierung> reservierungQuery = entityManager.createQuery("Select r From Reservierung r, Veranstaltung v where r.veranstaltung=v And v.manager=:manager", Reservierung.class);
		reservierungQuery.setParameter("manager", sessionService.getActiveUser());
		return reservierungQuery.getResultList();
	}

	public Reservierung reservieren(int veranstaltungsId, int reservierteTickets) {
		Reservierung reservierung = new Reservierung();
		Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungsId);
		veranstaltung.setBereitsReservierteTickets(veranstaltung.getBereitsReservierteTickets() + reservierteTickets);
		reservierung.setVeranstaltung(veranstaltung);
		reservierung.setGesamtPreis(reservierteTickets * veranstaltung.getPreis());
		veranstaltungService.updateVeranstaltung();
		reservierung.setAnzTickets(reservierteTickets);
		reservierung.setGesamtPreis(reservierteTickets * veranstaltung.getPreis());
		reservierung.setUser(sessionService.getActiveUser());
		this.addReservierung(reservierung);		
		return reservierung;
	}



	public EntityManager getEntityManager() {
		return entityManager;
	}



	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}

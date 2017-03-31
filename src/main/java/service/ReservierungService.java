package service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import model.Reservierung;
import model.Veranstaltung;


@Named
@ApplicationScoped
public class ReservierungService{

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

	@Transactional
	public void addReservierung(Reservierung reservierung) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(reservierung);
		entityManager.getTransaction().commit();
		
	}

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
		veranstaltungService.updateVeranstaltungAbschicken();
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

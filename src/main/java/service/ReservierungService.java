package service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

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
	
	
	
	//reservierungen.add();

	@Override
	public Reservierung createReservierung(int userID,
										   int veranstaltungID,
										   int anzTickets) {
		Reservierung reservierung = new Reservierung();
		int reservierungscode;
		
		do {
			reservierungscode = ((int)(Math.random() * 10000000));
		} while (reservierungscodeExist(reservierungscode) || reservierungscode < 1000000);
		
		reservierung.setReservierungscode(reservierungscode);
		reservierung.setUserID(userID);
		reservierung.setVeranstaltungID(veranstaltungID);
		reservierung.setAnzTickets(anzTickets);
		
		return reservierung;
		
	}
	
	@Override
	public void addReservierung(Reservierung reservierung) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(reservierung);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public List<Reservierung> getReservierungen() {
		return reservierungen;
	}

	@Override
	public boolean reservierungscodeExist(int reservierungscode) {
		boolean match = false;
		for (Reservierung reservierung : reservierungen) {
			if (reservierung.getReservierungscode() == reservierungscode)
				match = true;
		}
		return match;
	}



	public void reservieren(int veranstaltungsId, int reservierteTickets) {
		Reservierung reservierung = new Reservierung();
		reservierung.setVeranstaltungID(veranstaltungsId);
		Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungsId);
		veranstaltung.setBereitsReservierteTickets(veranstaltung.getBereitsReservierteTickets() + reservierteTickets);
		veranstaltungService.updateVeranstaltung();
		reservierung.setAnzTickets(reservierteTickets);
		reservierung.setUserID(sessionService.getActiveUser().getId());
		reservierung.setReservierungscode((int)(Math.random() * 10000000));
		this.addReservierung(reservierung);		
	}



	public EntityManager getEntityManager() {
		return entityManager;
	}



	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}

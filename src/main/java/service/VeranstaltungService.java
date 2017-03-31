package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.sun.faces.config.InitFacesContext;

import model.Veranstaltung;

@Named
@ApplicationScoped
public class VeranstaltungService {

	List<Veranstaltung> veranstaltungen;
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	private EntityManager entityManager;
	
	public VeranstaltungService()
	{

	}
	
//	@PostConstruct
//	public void init()
//	{
//		veranstaltungen = new ArrayList<>();
//		Veranstaltung veranstaltung = new Veranstaltung();
//		veranstaltung.setVeranstaltungsname("J-Law stalken");
//		veranstaltung.setBeschreibung("kommt zum stalken");
//		veranstaltung.setDatum(new Date(2011, 11, 11));
//		addVeranstaltung(veranstaltung);
//		Veranstaltung veranstaltung2 = new Veranstaltung();
//		veranstaltung2.setBeschreibung("kommt zum stalken");
//		veranstaltung2.setDatum(new Date(2011, 11, 11));
//		veranstaltung2.setVeranstaltungsname("Emma Watson stalken");
//		addVeranstaltung(veranstaltung2);
//		Veranstaltung veranstaltung3 = new Veranstaltung();
//		veranstaltung3.setBeschreibung("kommt zum stalken");
//		veranstaltung3.setDatum(new Date(2011, 11, 11));
//		veranstaltung3.setVeranstaltungsname("Ryan Gosling stalken");
//		addVeranstaltung(veranstaltung3);
//	}

	
	@Transactional
	public void addVeranstaltung(Veranstaltung veranstaltung) {
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(veranstaltung);
		this.entityManager.getTransaction().commit();
	}
	
	public void updateVeranstaltung(int id, String veranstaltungsname, String beschreibung, Date datum, String ort, int anzahlTickets, double preis, boolean veroeffentlicht)
	{// Aktualisieren einer Veranstaltung in der Datenbank
		Veranstaltung veranstaltung = this.getVeranstaltungById(id);
		veranstaltung.setVeranstaltungsname(veranstaltungsname);
		veranstaltung.setBeschreibung(beschreibung);
		veranstaltung.setDatum(datum);
		veranstaltung.setOrt(ort);
		veranstaltung.setMaxTickets(anzahlTickets);
		veranstaltung.setPreis(preis);
		veranstaltung.setVeroeffentlicht(veroeffentlicht);
		this.updateVeranstaltungAbschicken();
	}

	@Transactional
	public void updateVeranstaltungAbschicken() {
		this.entityManager.getTransaction().begin();
		this.entityManager.getTransaction().commit();		
	}

	public List<Veranstaltung> getVeranstaltungen() 
	{// Liste aller veröffentlichten Veranstaltungen
		TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.veroeffentlicht=1", Veranstaltung.class);
		List<Veranstaltung> veranstaltungen = veranstaltungenQuery.getResultList();
		return veranstaltungen;
	}
	
	public List<Veranstaltung> getMeineVeranstaltungen() 
	{// Rückgabe einer Liste aller Veranstaltungen bei denen man als Ersteller eingetragen ist 
		TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.manager=:user", Veranstaltung.class);
		veranstaltungenQuery.setParameter("user", sessionService.getActiveUser());
		List<Veranstaltung> veranstaltungen = veranstaltungenQuery.getResultList();
		return veranstaltungen;
	}

	public Veranstaltung getVeranstaltungById(int id) {
		try{
		TypedQuery<Veranstaltung> veranstaltungQuery = entityManager.createQuery("Select v from Veranstaltung v Where v.id = :id", Veranstaltung.class);
		veranstaltungQuery.setParameter("id", id);
		Veranstaltung veranstaltung = veranstaltungQuery.getSingleResult();
		return veranstaltung;
		} catch (NoResultException e)
		{
			return null;
		} catch (NonUniqueResultException e)
		{
			//sollte nicht auftreten, da Id primary key ist und eindeutig sein soll
			return null;
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void createVeranstaltung(String veranstaltungsname, String beschreibung, Date datum, String ort,
			int anzahlTickets, double preis, boolean veroeffentlicht) 
	{// Erstellen und Hinzufügen einer Veranstaltung in die Datenbank
		Veranstaltung veranstaltung = new Veranstaltung(veranstaltungsname, beschreibung, datum, ort, anzahlTickets, preis, veroeffentlicht);
		veranstaltung.setManager(sessionService.getActiveUser());
		this.addVeranstaltung(veranstaltung);
	}
}

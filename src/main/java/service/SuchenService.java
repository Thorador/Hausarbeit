package service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Veranstaltung;

@ApplicationScoped
public class SuchenService {
	
	@Inject
	private EntityManager entityManager;
	
	public List<Veranstaltung> suchen(String veranstaltungsname, Date von, Date bis)
	{
		TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.veranstaltungsname Like :veranstaltungsname And v.datum Between :von And :bis", Veranstaltung.class );
		veranstaltungenQuery.setParameter("veranstaltungsname", "%" + veranstaltungsname +"%");
		veranstaltungenQuery.setParameter("von", von);
		veranstaltungenQuery.setParameter("bis", bis);
		return veranstaltungenQuery.getResultList();
	}
}

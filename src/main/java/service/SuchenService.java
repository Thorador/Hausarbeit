package service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
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
		List<Veranstaltung> suchErgebnisse = null;
		if (!veranstaltungsname.trim().equals(""))
		{
			if (von != null)
			{
				if (bis != null)
				{
					//alle Werte gefüllt
					TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.veranstaltungsname Like :veranstaltungsname And v.datum Between :von And :bis And v.veroeffentlicht = true", Veranstaltung.class );
					veranstaltungenQuery.setParameter("veranstaltungsname", "%" + veranstaltungsname +"%");
					veranstaltungenQuery.setParameter("von", von);
					veranstaltungenQuery.setParameter("bis", bis);
					suchErgebnisse= veranstaltungenQuery.getResultList();
					return suchErgebnisse;
				} else
				{
					//bis nicht gefüllt
					TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.veranstaltungsname Like :veranstaltungsname And v.datum > :von And v.veroeffentlicht = true", Veranstaltung.class );
					veranstaltungenQuery.setParameter("veranstaltungsname", "%" + veranstaltungsname +"%");
					veranstaltungenQuery.setParameter("von", von);
					suchErgebnisse= veranstaltungenQuery.getResultList();
					return suchErgebnisse;
				}
			} else 
			{
				if (bis != null)
				{
					//von nicht gefüllt
					TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.veranstaltungsname Like :veranstaltungsname And v.datum < :bis And v.veroeffentlicht = true", Veranstaltung.class );
					veranstaltungenQuery.setParameter("veranstaltungsname", "%" + veranstaltungsname +"%");
					veranstaltungenQuery.setParameter("bis", bis);
					suchErgebnisse= veranstaltungenQuery.getResultList();
					return suchErgebnisse;
				} else
				{
					//von und bis nicht gefüllt
					TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.veranstaltungsname Like :veranstaltungsname And v.veroeffentlicht = true", Veranstaltung.class );
					veranstaltungenQuery.setParameter("veranstaltungsname", "%" + veranstaltungsname +"%");
					suchErgebnisse= veranstaltungenQuery.getResultList();
					return suchErgebnisse;
				}
			}
		} else
		{
			if (von != null)
			{
				if (bis != null)
				{
					//veranstaltungsname nicht gefüllt
					TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.datum Between :von And :bis And v.veroeffentlicht = true", Veranstaltung.class );
					veranstaltungenQuery.setParameter("von", von);
					veranstaltungenQuery.setParameter("bis", bis);
					suchErgebnisse= veranstaltungenQuery.getResultList();
					return suchErgebnisse;
				} else
				{
					//veranstaltungsname und bis nicht gefüllt
					TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.datum > :von And v.veroeffentlicht = true", Veranstaltung.class );
					veranstaltungenQuery.setParameter("von", von);
					suchErgebnisse= veranstaltungenQuery.getResultList();
					return suchErgebnisse;
				}
			} else 
			{
				if (bis != null)
				{
					//veranstaltungsname und von nicht gefüllt
					TypedQuery<Veranstaltung> veranstaltungenQuery = entityManager.createQuery("Select v From Veranstaltung v Where v.datum < :bis And v.veroeffentlicht = true", Veranstaltung.class );
					veranstaltungenQuery.setParameter("bis", bis);
					suchErgebnisse= veranstaltungenQuery.getResultList();
					return suchErgebnisse;
				} else
				{
					//kein Wert gefüllt
					return suchErgebnisse;
				}
			}
		}

	}
}

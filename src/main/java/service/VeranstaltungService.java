package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import model.Veranstaltung;
import serviceInterface.IVeranstaltungService;

@Named
@ApplicationScoped
public class VeranstaltungService implements IVeranstaltungService {

	List<Veranstaltung> veranstaltungen;
	
	@Inject
	SessionService sessionService;
	
	public VeranstaltungService()
	{
		veranstaltungen = new ArrayList<>();
		Veranstaltung veranstaltung = new Veranstaltung();
		veranstaltung.setVeranstaltungsname("J-Law stalken");
		veranstaltung.setBeschreibung("kommt zum stalken");
		veranstaltung.setDatum(new Date(2011, 11, 11));
		veranstaltungen.add(veranstaltung);
		Veranstaltung veranstaltung2 = new Veranstaltung();
		veranstaltung2.setBeschreibung("kommt zum stalken");
		veranstaltung2.setDatum(new Date(2011, 11, 11));
		veranstaltung2.setVeranstaltungsname("Emma Watson stalken");
		veranstaltungen.add(veranstaltung2);
		Veranstaltung veranstaltung3 = new Veranstaltung();
		veranstaltung3.setBeschreibung("kommt zum stalken");
		veranstaltung3.setDatum(new Date(2011, 11, 11));
		veranstaltung3.setVeranstaltungsname("Ryan Gosling stalken");
		veranstaltungen.add(veranstaltung3);
	}
	
	@Override
	public Veranstaltung createVeranstaltung(String veranstaltungsname,
											 String beschreibung,
											 Date datum,
											 int maxTickets,
											 String ort,
											 double preis) {
		
		if (sessionService != null && sessionService.isLoggedIn() && sessionService.isManager())
		{
			Veranstaltung veranstaltung = new Veranstaltung();
			veranstaltung.setId(veranstaltungen.get(veranstaltungen.size() - 1).getId() + 1);
				// Veranstaltungs-ID ist +1 als die letzte ID der Veranstaltungsliste
			veranstaltung.setVeranstaltungsname(veranstaltungsname);
			veranstaltung.setBeschreibung(beschreibung);
			veranstaltung.setDatum(datum);
			veranstaltung.setMaxTickets(maxTickets);
			veranstaltung.setBereitsReservierteTickets(0);
			veranstaltung.setOrt(ort);
			veranstaltung.setPreis(preis);
			
			return veranstaltung;
		}
		return null;
		
	}
	
	@Override
	public void addVeranstaltung(Veranstaltung veranstaltung) {
		
		veranstaltungen.add(veranstaltung);
		
	}

	@Override
	public List<Veranstaltung> getVeranstaltungen() {
		return veranstaltungen;
	}

	@Override
	public Optional<Veranstaltung> getVeranstaltungByName(String veranstaltungsname) {

		return veranstaltungen.stream().filter(veranstaltung -> veranstaltung.getVeranstaltungsname().equals(veranstaltungsname)).findFirst();
	}

}

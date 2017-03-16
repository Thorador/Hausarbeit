package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;

import model.Veranstaltung;
import serviceInterface.IVeranstaltungService;

@ManagedBean
public class VeranstaltungService implements IVeranstaltungService {

	List<Veranstaltung> veranstaltungen;
	
	public VeranstaltungService()
	{
		veranstaltungen = new ArrayList<>();
	}
	
	@Override
	public Veranstaltung createVeranstaltung(String veranstaltungsname,
											 String beschreibung,
											 Date datum,
											 int maxTickets,
											 String ort,
											 double preis) {
		
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
	
	@Override
	public boolean addVeranstaltung(Veranstaltung veranstaltung) {
		
		if (getVeranstaltungByName(veranstaltung.getVeranstaltungsname()).isPresent())
		{
			
		}
		return false;
		
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

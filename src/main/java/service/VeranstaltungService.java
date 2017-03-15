package service;

import java.util.ArrayList;
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
	public boolean addVeranstaltung(Veranstaltung veranstaltung) {
		
		if (getVeranstaltungByName(veranstaltung.getVeranstaltungsname()).isPresent())
		{
			
		}
		return false;
		
	}

	@Override
	public List<Veranstaltung> getVeranstaltungen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Veranstaltung> getVeranstaltungByName(String veranstaltungsname) {

		return veranstaltungen.stream().filter(veranstaltung -> veranstaltung.getVeranstaltungsname().equals(veranstaltungsname)).findFirst();
	}

}

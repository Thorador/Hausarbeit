package serviceInterface;

import java.util.List;
import java.util.Optional;

import model.Veranstaltung;

public interface IVeranstaltungService {
	
	public boolean addVeranstaltung(Veranstaltung veranstaltung);
	public List<Veranstaltung> getVeranstaltungen();
	public Optional<Veranstaltung> getVeranstaltungByName(String veranstaltungsname);

}

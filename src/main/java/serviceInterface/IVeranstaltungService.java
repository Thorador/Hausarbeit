package serviceInterface;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import model.Veranstaltung;

public interface IVeranstaltungService {
	
	public void addVeranstaltung(Veranstaltung veranstaltung);
	public List<Veranstaltung> getVeranstaltungen();
	public Veranstaltung getVeranstaltungById(int Id);

}

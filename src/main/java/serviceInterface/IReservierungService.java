package serviceInterface;

import java.util.List;

import model.Reservierung;

public interface IReservierungService {
	
	
	public Reservierung createReservierung(int userID, int veranstaltungID, int anzTickets);
	public void addReservierung(Reservierung reservierung);
	public List<Reservierung> getReservierungen();
	public boolean reservierungscodeExist(int reservierungscode);
}

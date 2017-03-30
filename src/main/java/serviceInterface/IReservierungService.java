package serviceInterface;

import java.util.List;

import model.Reservierung;

public interface IReservierungService {
	
	
	public void addReservierung(Reservierung reservierung);
	public List<Reservierung> getReservierungen();
}

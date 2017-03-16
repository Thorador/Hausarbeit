package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;

import model.Reservierung;
import serviceInterface.IReservierungService;

@ManagedBean
public class ReservierungService implements IReservierungService {

	List<Reservierung> reservierungen;
	
	public ReservierungService()
	{
		reservierungen = new ArrayList<>();
	}

	@Override
	public Reservierung createReservierung(int userID,
										   int veranstaltungID,
										   int anzTickets) {
		Reservierung reservierung = new Reservierung();
		int reservierungscode;
		
		do {
			reservierungscode = ((int)(Math.random() * 10000000));
		} while (reservierungscodeExist(reservierungscode));
		
		reservierung.setReservierungscode(reservierungscode);
		reservierung.setUserID(userID);
		reservierung.setVeranstaltungID(veranstaltungID);
		reservierung.setAnzTickets(anzTickets);
		
		return reservierung;
		
	}
	
	@Override
	public boolean addReservierung(Reservierung reservierung) {
		
		return false;
		
	}

	@Override
	public List<Reservierung> getReservierungen() {
		return reservierungen;
	}

	@Override
	public boolean reservierungscodeExist(int reservierungscode) {
		boolean match = false;
		for (Reservierung reservierung : reservierungen) {
			if (reservierung.getReservierungscode() == reservierungscode)
				match = true;
		}
		return match;
	}
	
}

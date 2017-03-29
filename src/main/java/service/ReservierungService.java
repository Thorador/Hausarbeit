package service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import model.Reservierung;
import serviceInterface.IReservierungService;


@Named
@ApplicationScoped
public class ReservierungService implements IReservierungService {

	List<Reservierung> reservierungen = new ArrayList<>();
	
	public ReservierungService()
	{		
		reservierungen.add(createReservierung(9, 5, 45));
		reservierungen.add(createReservierung(5, 5, 45));
		reservierungen.add(createReservierung(2, 5, 45));
		reservierungen.add(createReservierung(7, 5, 45));
		reservierungen.add(createReservierung(9, 4, 45));
		reservierungen.add(createReservierung(5, 4, 45));
		reservierungen.add(createReservierung(2, 4, 45));
		reservierungen.add(createReservierung(7, 23, 12));
	}
	
	
	
	//reservierungen.add();

	@Override
	public Reservierung createReservierung(int userID,
										   int veranstaltungID,
										   int anzTickets) {
		Reservierung reservierung = new Reservierung();
		int reservierungscode;
		
		do {
			reservierungscode = ((int)(Math.random() * 10000000));
		} while (reservierungscodeExist(reservierungscode) || reservierungscode < 1000000);
		
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

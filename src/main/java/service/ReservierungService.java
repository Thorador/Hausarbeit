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
	public boolean addReservierung(Reservierung reservierung) {
		
		return false;
		
	}

	@Override
	public List<Reservierung> getReservierungen() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

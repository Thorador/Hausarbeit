package model;

import java.util.Date;

public class Reservierung {

	private int id;  //Reservierungscode
	private int user_id;
	private int veranstaltung_id;
	private int anzTickets;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public int getVeranstaltungId() {
		return id;
	}
	public void setVeranstaltungId(int veranstaltung_id) {
		this.veranstaltung_id = veranstaltung_id;
	}
	public int getAnzTickets() {
		return anzTickets;
	}
	public void setAnzTickets(int anzTickets) {
		this.anzTickets = anzTickets;
	}
}

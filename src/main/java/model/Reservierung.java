package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservierung")
public class Reservierung {

	@Id @GeneratedValue
	private int id;
	private int reservierungscode;
	private int userID;
	private int veranstaltungID;
	private int anzTickets;
	
	
	public int getReservierungscode() {
		return reservierungscode;
	}
	public void setReservierungscode(int reservierungscode) {
		this.reservierungscode = reservierungscode;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getVeranstaltungID() {
		return veranstaltungID;
	}
	public void setVeranstaltungID(int veranstaltungID) {
		this.veranstaltungID = veranstaltungID;
	}
	
	public int getAnzTickets() {
		return anzTickets;
	}
	public void setAnzTickets(int anzTickets) {
		this.anzTickets = anzTickets;
	}
	
}

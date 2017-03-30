package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservierung")
public class Reservierung {

	@Id @GeneratedValue
	private int reservierungscode;
	@ManyToOne
	@JoinColumn(name="veranstaltungId")
	private Veranstaltung veranstaltung;
	@ManyToOne
	@JoinColumn(name="managerId")
	private User manager;
	private int anzTickets;
	
	
	public int getReservierungscode() {
		return reservierungscode;
	}
	public void setReservierungscode(int reservierungscode) {
		this.reservierungscode = reservierungscode;
	}
	
	
	public Veranstaltung getVeranstaltung() {
		return veranstaltung;
	}
	public void setVeranstaltung(Veranstaltung veranstaltung) {
		this.veranstaltung = veranstaltung;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public int getAnzTickets() {
		return anzTickets;
	}
	public void setAnzTickets(int anzTickets) {
		this.anzTickets = anzTickets;
	}
	
}

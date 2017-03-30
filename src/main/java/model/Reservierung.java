package model;


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
	@JoinColumn(name="userId")
	private User user;
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

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getAnzTickets() {
		return anzTickets;
	}
	public void setAnzTickets(int anzTickets) {
		this.anzTickets = anzTickets;
	}
	
}

package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="veranstaltung")
public class Veranstaltung {

	@Id @GeneratedValue
	private int id;
	private String veranstaltungsname;
	private String beschreibung;
	private Date datum;
	private int maxTickets;
	private int bereitsReservierteTickets;
	private String ort;
	private double preis;
	private boolean veroeffentlicht;
	@ManyToOne
	@JoinColumn(name="managerId")
	public User manager;
	@OneToMany(mappedBy="veranstaltung", cascade=CascadeType.ALL)
	public List<Reservierung> reservierungen;

	
	public List<Reservierung> getReservierungen() {
		return reservierungen;
	}
	public void setReservierungen(List<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public boolean isVeroeffentlicht() {
		return veroeffentlicht;
	}
	public void setVeroeffentlicht(boolean veroeffentlicht) {
		this.veroeffentlicht = veroeffentlicht;
	}
	public Veranstaltung(String veranstaltungsname, String beschreibung, Date datum, String ort, int anzahlTickets,
			boolean veroeffentlicht) {
		this.veranstaltungsname=veranstaltungsname;
		this.beschreibung=beschreibung;
		this.datum=datum;
		this.ort=ort;
		this.maxTickets=anzahlTickets;
		this.veroeffentlicht=veroeffentlicht;
	}
	public Veranstaltung() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVeranstaltungsname() {
		return veranstaltungsname;
	}
	public void setVeranstaltungsname(String veranstaltungsname) {
		this.veranstaltungsname = veranstaltungsname;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getMaxTickets() {
		return maxTickets;
	}
	public void setMaxTickets(int maxTickets) {
		this.maxTickets = maxTickets;
	}
	public int getBereitsReservierteTickets() {
		return bereitsReservierteTickets;
	}
	public void setBereitsReservierteTickets(int bereitsReservierteTickets) {
		this.bereitsReservierteTickets = bereitsReservierteTickets;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	
}

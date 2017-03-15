package model;

import java.util.Date;

public class Veranstaltung {

	private int id;
	private String veranstaltungsname;
	private String beschreibung;
	private Date datum;
	private int maxTickets;
	private int bereitsReservierteTickets;
	private String ort;

	
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
	
}

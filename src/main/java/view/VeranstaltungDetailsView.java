package view;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import model.Veranstaltung;
import service.ReservierungService;
import service.SessionService;
import service.VeranstaltungService;

@Named
@RequestScoped
public class VeranstaltungDetailsView {

	private int id;
	private String veranstaltungsname;
	private String beschreibung;
	private Date datum;
	private int maxTickets;
	private int bereitsReservierteTickets;
	private int anzTicketsReservierung;
	private String ort;
	private double preis;
	private boolean veroeffentlicht;
	
	@Inject
	VeranstaltungService veranstaltungService;
	
	@Inject
	ReservierungService reservierungService;
	
	@Inject
	SessionService sessionService;
	
	public void init()
	{
		Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungByName(getVeranstaltungsname()).get();
		this.setBeschreibung(veranstaltung.getBeschreibung());
	}

	public boolean reservieren() {
		reservierungService.createReservierung(sessionService.getActiveUser().getId(), 
											   veranstaltungService.getVeranstaltungByName(getVeranstaltungsname()).get().getId(),
											   getAnzTicketsReservierung());
		return true;
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
	public int getAnzTicketsReservierung() {
		return anzTicketsReservierung;
	}
	public void setAnzTicketsReservierung(int anzTicketsReservierung) {
		this.anzTicketsReservierung = anzTicketsReservierung;
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
	public boolean isVeroeffentlicht() {
		return veroeffentlicht;
	}
	public void setVeroeffentlicht(boolean veroeffentlicht) {
		this.veroeffentlicht = veroeffentlicht;
	}

}

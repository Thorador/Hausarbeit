package view;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import org.omg.CORBA.portable.ValueBase;

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
	private int freieTickets;
	private String anzTicketsReservierung;
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
		if (veranstaltungService.getVeranstaltungById(getId()) != null)
		{
		Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(getId());
		this.setVeranstaltungsname(veranstaltung.getVeranstaltungsname());
		this.setBeschreibung(veranstaltung.getBeschreibung());
		this.setDatum(veranstaltung.getDatum());
		this.setOrt(veranstaltung.getOrt());
		this.setFreieTickets(veranstaltung.getMaxTickets()-veranstaltung.getBereitsReservierteTickets());
		}
	}

	public String reservieren() {
//		reservierungService.createReservierung(sessionService.getActiveUser().getId(), 
//											   veranstaltungService.getVeranstaltungById(getId()),
//											   Integer.valueOf(getAnzTicketsReservierung()));
		return "VeranstaltungDetails.jsf";
	}
	
	public String cancel()
	{
		return "home.jsf";
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
	public String getAnzTicketsReservierung() {
		return anzTicketsReservierung;
	}
	public void setAnzTicketsReservierung(String anzTicketsReservierung) {
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
	public int getFreieTickets() {
		return freieTickets;
	}

	public void setFreieTickets(int freieTickets) {
		this.freieTickets = freieTickets;
	}

}

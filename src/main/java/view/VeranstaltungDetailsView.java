package view;

import java.io.Serializable;
import java.util.Date;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import model.Reservierung;
import model.Veranstaltung;
import service.ReservierungService;
import service.VeranstaltungService;

@Named
@SessionScoped
public class VeranstaltungDetailsView implements Serializable {

	private int id;
	private String veranstaltungsname;
	private String beschreibung;
	private Date datum;
	private int freieTickets;
	private int anzTicketsReservierung;
	private String ort;
	private double preis;
	private boolean veroeffentlicht;
	
	@Inject
	VeranstaltungService veranstaltungService;
	
	@Inject
	ReservierungService reservierungService;
	

	
	public void init()
	{// Initialisierung der Werte fuer die Detailansicht, wird beim Aufruf ausgeloest
		if (veranstaltungService.getVeranstaltungById(getId()) != null)
		{
			Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(getId());
			this.setVeranstaltungsname(veranstaltung.getVeranstaltungsname());
			this.setBeschreibung(veranstaltung.getBeschreibung());
			this.setDatum(veranstaltung.getDatum());
			this.setOrt(veranstaltung.getOrt());
			this.setFreieTickets(veranstaltung.getMaxTickets()-veranstaltung.getBereitsReservierteTickets());
			this.setPreis(veranstaltung.getPreis());
		}
	}


	public String reservieren() 
	{// Reservierung von Tickets zu einer ausgewaehlten Veranstaltung
		if (this.getFreieTickets() >= this.getAnzTicketsReservierung())
		{
			Reservierung reservierung = reservierungService.reservieren(getId(),getAnzTicketsReservierung());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Die Reservierung wurde mit dem Reservierungscode " + String.valueOf(reservierung.getReservierungscode()) 
												+ " zu einem Preis von " + getAnzTicketsReservierung() * getPreis() + " Euro gespeichert." , null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else 
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Leider sind keine " + String.valueOf(getAnzTicketsReservierung()) + " Ticket(s) mehr für diese Veranstaltung verfügbar." , null);
			FacesContext.getCurrentInstance().addMessage(null, msg);				
		}			
		return "veranstaltungDetails.jsf";
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
	public int getFreieTickets() {
		return freieTickets;
	}

	public void setFreieTickets(int freieTickets) {
		this.freieTickets = freieTickets;
	}

}

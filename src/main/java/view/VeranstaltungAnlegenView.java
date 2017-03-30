package view;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Veranstaltung;
import service.VeranstaltungService;

@Named
@RequestScoped
public class VeranstaltungAnlegenView {

	private String veranstaltungsname;
	private String beschreibung;
	private Date datum;
	private String ort;
	private String zeit;
	private int anzahlTickets;
	private boolean veroeffentlicht;
	
	@Inject
	VeranstaltungService veranstaltungService;
	
	public String create()
	{
		Veranstaltung veranstaltung = new Veranstaltung(getVeranstaltungsname(),getBeschreibung(),getDatum(),getOrt(),getAnzahlTickets(),isVeroeffentlicht());
		veranstaltungService.addVeranstaltung(veranstaltung);
		return "home.jsf";
	}
	
	public String cancel()
	{
		return "home.jsf";
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
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public int getAnzahlTickets() {
		return anzahlTickets;
	}
	public void setAnzahlTickets(int anzahlTickets) {
		this.anzahlTickets = anzahlTickets;
	}
	public boolean isVeroeffentlicht() {
		return veroeffentlicht;
	}
	public void setVeroeffentlicht(boolean veroeffentlicht) {
		this.veroeffentlicht = veroeffentlicht;
	}

	public String getZeit() {
		return zeit;
	}

	public void setZeit(String zeit) {
		this.zeit = zeit;
	}
}

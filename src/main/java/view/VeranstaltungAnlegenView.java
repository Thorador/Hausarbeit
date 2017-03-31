package view;

import java.io.Serializable;
import java.util.Date;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Veranstaltung;
import service.SessionService;
import service.VeranstaltungService;

@Named
@SessionScoped
public class VeranstaltungAnlegenView implements Serializable {

	private int id;
	private String veranstaltungsname;
	private String beschreibung;
	private Date datum;
	private String ort;
	private int anzahlTickets;
	private boolean veroeffentlicht;
	private boolean anlegen;
	private double preis;
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	private VeranstaltungService veranstaltungService;
	
	public void init()
	{
		this.setAnlegen(true);
		if (getId() != -1)
		{
			Veranstaltung veranstaltung= veranstaltungService.getVeranstaltungById(getId());
			this.setVeranstaltungsname(veranstaltung.getVeranstaltungsname());
			this.setBeschreibung(veranstaltung.getBeschreibung());
			this.setDatum(veranstaltung.getDatum());
			this.setOrt(veranstaltung.getOrt());
			this.setAnzahlTickets(veranstaltung.getMaxTickets());
			this.setVeroeffentlicht(false);
			this.setAnlegen(false);
			this.setPreis(veranstaltung.getPreis());
		}
	}
	
	public String safe()
	{
		if (isAnlegen())
		{
			veranstaltungService.createVeranstaltung(getVeranstaltungsname(),getBeschreibung(),getDatum(),getOrt(),getAnzahlTickets(),getPreis(),isVeroeffentlicht());
			return "home.jsf";		
		}else{
			veranstaltungService.updateVeranstaltung(getId(), getVeranstaltungsname(), getBeschreibung(), getDatum(), getOrt(), getAnzahlTickets(), getPreis(),isVeroeffentlicht());
			return "meineVeranstaltungen.jsf";
		}
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAnlegen() {
		return anlegen;
	}
	public void setAnlegen(boolean anlegen) {
		this.anlegen = anlegen;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
}

package view;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import model.Veranstaltung;
import service.SuchenService;

@RequestScoped
public class SuchenView {
	
	private String veranstaltungsname;
	private Date von;
	private Date bis;
	private List<Veranstaltung> suchErgebnisse;
	
	@Inject
	private SuchenService suchenService;
	
	public String suchen()
	{
		suchErgebnisse = suchenService.suchen(getVeranstaltungsname(), getVon(), getBis());
		return "suchen.jsf";
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
	public Date getVon() {
		return von;
	}
	public void setVon(Date von) {
		this.von = von;
	}
	public Date getBis() {
		return bis;
	}
	public void setBis(Date bis) {
		this.bis = bis;
	}
	public List<Veranstaltung> getSuchErgebnisse() {
		return suchErgebnisse;
	}
	public void setSuchErgebnisse(List<Veranstaltung> suchErgebnisse) {
		this.suchErgebnisse = suchErgebnisse;
	}
}

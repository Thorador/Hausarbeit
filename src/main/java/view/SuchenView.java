package view;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Veranstaltung;
import service.SuchenService;

@Named
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
		this.setSuchErgebnisse(suchenService.suchen(getVeranstaltungsname(), getVon(), getBis()));
		if (suchErgebnisse == null || suchErgebnisse.isEmpty())
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Keine Ergebnisse zur eingegebenen Suche", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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

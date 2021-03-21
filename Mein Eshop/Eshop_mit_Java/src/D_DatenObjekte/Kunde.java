package D_DatenObjekte;

import java.util.List;
import java.util.Vector;

public class Kunde extends Person {

//Attribute
	
	private int kundenNr; 
	private String strasse ="";
	private int plz;
	private String wohnort = "";
	public List <Warenkorbartikel> warenkorb = null;
			
//Konstruktor(en)
	


	public Kunde(String name, String nutzername, String passwort, int kundenNr, String strasse, int plz, String wohnort) {
		super(name, nutzername, passwort);
		this.kundenNr = kundenNr;
		this.strasse = strasse;
		this.plz = plz;
		this.wohnort = wohnort;
		warenkorb = new Vector<Warenkorbartikel>();
	}

	public Kunde() {
		
	}
	
	
	
//Methoden
	
	
	// Equals-Methode
	
	public boolean equals(Object andereObject) {
		if(andereObject instanceof Kunde) {
			Kunde einKunde = (Kunde) andereObject;
			return(this.getNutzername().equals(einKunde.getNutzername()) || this.getKundenNr() == einKunde.getKundenNr());
		}
		return false;
	}
	
	
	
	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getPlz() {
		return plz;
	}
	
	public String getWohnort() {
		return wohnort;
	}

	public int getKundenNr() {
		return kundenNr;
	}
	
	public void setPlz(int plz) {
		this.plz = plz;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}
	
	public void setKundenNr(int kundenNr) {
		this.kundenNr = kundenNr;
	}
	
	
	public List<Warenkorbartikel> getWarenkorb() {
		return this.warenkorb;
	}

	public void setWarenkorb(List<Warenkorbartikel> wk) {
		this.warenkorb = wk;
	}
	
}

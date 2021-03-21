package D_DatenObjekte;

public class Warenkorbartikel {

	//Attribute
	
	int stueckzahl;
	String name;
	int nummer;
	float preis;
	
	
	//Konstruktor(en)
	
	
	public Warenkorbartikel (String name, int nummer, int stueckzahl, float preis) {
		this.name = name;
		this.nummer = nummer;				
		this.stueckzahl = stueckzahl;
		this.preis = preis;
	}
	
	public Warenkorbartikel(String name, int nummer) {
		this.nummer = nummer;
		this.name = name;
	}
	
	public Warenkorbartikel(String name) {
		this.name = name;
	}
	
	public Warenkorbartikel() {
		
	}
	
	
	
	//Methoden
	


	public Warenkorbartikel(String name, int stueckzahl, Kunde k) {
		this.name = name;
		this.stueckzahl = stueckzahl;
		
	}

	public String toString() {
		
		return ("Artikelname: " + name + " / Artikelnummer: " + nummer + " / " + "Einzelpreis: " + preis + " € " + " / " + "Stückzahl: " + stueckzahl + " / " + "Gesamtpreis: " + (preis*stueckzahl)+ " € ");
	}
	
	
	
	
	public float getGesamtpreis() {
		return this.stueckzahl*this.preis;
	}
	
	
	public boolean equals(Object zweiteObjekt) {
    	if(zweiteObjekt instanceof Warenkorbartikel) {
    		Warenkorbartikel zweiteArtikel = (Warenkorbartikel) zweiteObjekt;
    		return ((this.name.equals(zweiteArtikel.name)) || (this.nummer == zweiteArtikel.nummer));
    	}
    	return false;
    	
    }
	
	public int getNummer(){
		return this.nummer;
	}
	
	public float getPreis() {
		return this.preis;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getStueckzahl() {
		return this.stueckzahl;
	}
	public void setStueckzahl(int stueckzahl) {
		this.stueckzahl = stueckzahl;
	}
	
	
}

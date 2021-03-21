package D_DatenObjekte;

public class Artikel {

//Attribute
	
	String  artikelName;
    int     artikelNummer;
    float   artikelPreis;
    int     artikelBestand;
    boolean artikelVerfuegbar;
    int 	artikelMassengut; 
	
//Konstruktor(en)
	
    public Artikel(String artikelName, int artikelNummer, float artikelPreis, int artikelBestand, int artikelMassengut, boolean artikelVerfuegbar){
        this.artikelNummer      =       artikelNummer;
        this.artikelName        =       artikelName;
        this.artikelPreis       =       artikelPreis;
        this.artikelBestand     =       artikelBestand;
        this.artikelVerfuegbar  =       artikelVerfuegbar;
        this.artikelMassengut 	=		artikelMassengut;
    }

    
    public Artikel(String artikelName, int artikelNummer, float artikelPreis, int artikelBestand, int artikelMassengut){
        this.artikelNummer      =       artikelNummer;
        this.artikelName        =       artikelName;
        this.artikelPreis       =       artikelPreis;
        this.artikelBestand     =       artikelBestand;
        this.artikelMassengut	=		artikelMassengut;
       
    }
	
    
    public Artikel(String artikelName) {
    	this.artikelName        =       artikelName;
	}
	
    public Artikel(String artikelName, int artikelNummer ) {
        this.artikelNummer      =       artikelNummer;
        this.artikelName        =       artikelName;
    }
    

    

    
//Methoden	

    
//Damit nicht der Verweis ausgegeben wird, den wir nicht lesen können, sondern der String
    



	public String toString() {
		String lagerbestand = istVerfuegbar() ? "ist auf Lager" : "nicht auf Lager";
		return ("Artikelname: " + artikelName + " / Artikelnummer: " + artikelNummer + " / " + "Preis: " + artikelPreis + " € " + " / " + "Bestand: " + artikelBestand + " / " + "Mindestbestellwert: " + artikelMassengut + " / " + lagerbestand);
	}
    
    
// Zwei Artikel sind gleich, wenn Name oder Nummer identisch sind
    
    public boolean equals(Object zweiteObjekt) {
    	if(zweiteObjekt instanceof Artikel) {
    		Artikel zweiteArtikel = (Artikel) zweiteObjekt;
    		return ((this.artikelName.equals(zweiteArtikel.artikelName)) || (this.artikelNummer == zweiteArtikel.artikelNummer));
    	}
    	return false;
    	
    }
    
    
    
    
    
    
//Getter/Setter
    	
    	public boolean istMassengut() {
    		if(this.artikelMassengut > 1) {
    			return true;
    		} else {
    			return false;
    		}
    	}
		public boolean istVerfuegbar(){
		if(this.artikelBestand > 0) {
			return true;
		} else {
			return false;
		}
    }

    
    public String getName(){
        return this.artikelName;
    }

    
    public int getNummer(){
        return this.artikelNummer;
    }

    
    public float getPreis(){
        return this.artikelPreis;
    }
    
    public int getPreisAlsInt( ) {
    	return Math.round((int)getPreis());
    }
    
    public void setBestand(int bestand) {
    	this.artikelBestand = bestand;
    }
    
    public int getBestand() {
    	return this.artikelBestand;
    }
    
    public int getMassengut() {
    	return this.artikelMassengut;
    }
    
    public void setMassengut(int massengut) {
    	this.artikelMassengut = massengut;
    }

	
 
    
}

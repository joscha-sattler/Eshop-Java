package D_DatenObjekte;

public class Mitarbeiter extends Person {

//Attribute
	
	private int mitarbeiterNr; 

	
//Konstruktor(en)
	
	public Mitarbeiter(String name, String nutzername, String passwort, int mitarbeiterNr) {
		super(name, nutzername, passwort);
		this.mitarbeiterNr = mitarbeiterNr;
	}

	
//Methoden	
	
	public int getMitarbeiterNr() {
		return mitarbeiterNr;
	}


	public void setMitarbeiterNr(int mitarbeiterNr) {
		this.mitarbeiterNr = mitarbeiterNr;
	}

	public String getMitarbeiterName() {
		return getName();
	}
	
	public void setMitarbeiterName(String name) {
		setName(name);
	}
	
	public String getMitarbeiterNutzername() {
		return getNutzername();
	}
	
	public void setMitarbeiterNutzername(String nutzername) {
		setNutzername(nutzername);
	}
	
	
	public String getMitarbeiterPasswort() {
		return getPasswort();
	}
	
	public void setMitarbeiterPasswort(String passwort) {
		setPasswort(passwort);
	}
	
}

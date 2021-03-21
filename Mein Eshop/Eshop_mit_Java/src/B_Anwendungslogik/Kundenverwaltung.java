package B_Anwendungslogik;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import C_Datenhaltung.FilePersistenceManager;
import C_Datenhaltung.PersistenceManager;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Mitarbeiter;
import E_Exceptions.BenutzerExistiertBereitsException;

public class Kundenverwaltung {
	
public static List<String> verlauf = new Vector<String>();
private List<Kunde> kundendatenbank = new Vector<Kunde>();
private List<Mitarbeiter> mitarbeiterdatenbank = new Vector<Mitarbeiter>();
private PersistenceManager pm = new FilePersistenceManager();
	
	



//KUNDEN



public void schreibeKundenDaten(String datei) throws IOException  {
	pm.openForWriting(datei);

	if (!kundendatenbank.isEmpty()) {
		Iterator<Kunde> iter = kundendatenbank.iterator();
		while (iter.hasNext()) {
			Kunde p = (Kunde) iter.next();
			pm.speichereKunde(p);				
		}
	}
	
	pm.close();
}

	public void liesAusKundenDaten(String datei) throws IOException {

	pm.openForReading(datei);

	Kunde einKunde;
	
	do {
		einKunde = pm.ladeKundenDaten();
		if (einKunde != null) {
		Kundeeinfuegen(einKunde);
		}
		
	} while (einKunde != null);

	pm.close();
}

	
	
	public void Kundeeinfuegen(Kunde einKunde) {
		
		//Exception ergänzen (Kunde existiert bereits)
				
				
				kundendatenbank.add(einKunde);
			}
	
	
	
	
	
	public void kundenRegistrierungsVerfahren(String name, String nutzername, String passwort, int kundenNr, String strasse, int plz, String wohnort) throws BenutzerExistiertBereitsException{
		Date date = new Date();
		String datum = date.toString();
		Kunde p = new Kunde(name, nutzername, passwort, kundenNr, strasse, plz, wohnort);
		
		//Throws Kunde existiert bereits Exception ergänzen!
		
		if(kundendatenbank.contains(p)) {
			throw new BenutzerExistiertBereitsException();
		}
		this.kundendatenbank.add(p);
		int ergebnis = kundendatenbank.size();
		System.out.println(ergebnis);
		verlauf.add("kunde: "+p.getNutzername()+" hat sich am "+datum+" registriert");
		
		}
	

	
	 public Kunde sucheNachKundenNutzername(String nutzername, String passwort ){
			Date date = new Date();
			String datum = date.toString();
			Kunde k = new Kunde();
			boolean passwortGefunden = false;
			Iterator<Kunde> it = kundendatenbank.iterator();
			if(kundendatenbank.isEmpty()) {
				System.out.println("Momentan existieren noch keine Registrierten Accounts");
				
			} else {
				
				while(it.hasNext()) {
					Kunde kunde = it.next();
					if(kunde.getNutzername().equals(nutzername) && kunde.getPasswort().equals(passwort)) {
						System.out.println("");
						System.out.println("<----------------------------------------------------------------->");
						System.out.println("Erfolgreich als Kunde" + " " +"'" + kunde.getNutzername()+"'" + " " + "eingeloggt!" );
						verlauf.add("kunde: "+kunde.getNutzername()+" hat sich am "+datum+" eingeloggt");
						
						return kunde;
					}
				} 				
			}
			
			if(passwortGefunden == false ){
				System.out.println("");
				System.out.println("<----------------------------------------------------------------->");
				System.out.println("Nutzername oder Passwort falsch");
			}	
			return k;
		}
		
	
	
	
	
// MITARBEITER	
	
	
	
	
	
	
public void mitarbeiterEinfuegen(Mitarbeiter einMitarbeiter) {
		
		//Exception ergänzen (Mitarbeiter existiert bereits)
								
				mitarbeiterdatenbank.add(einMitarbeiter);
			}
	
	
	
	
public boolean sucheNachMitarbeiterNutzername(String nutzername, String passwort ){
	Date date = new Date();
	String datum = date.toString();
	boolean passwortGefunden = false;
	Iterator<Mitarbeiter> it = mitarbeiterdatenbank.iterator();
	if(mitarbeiterdatenbank.isEmpty()) {
		System.out.println("Momentan existieren noch keine Registrierten Accounts");
		
	} else {
		
		while(it.hasNext()) {
			Mitarbeiter mitarbeiter = it.next();
			if(mitarbeiter.getNutzername().equals(nutzername) && mitarbeiter.getPasswort().equals(passwort)) {
				System.out.println("");
				System.out.println("<----------------------------------------------------------------->");
				System.out.println("Erfolgreich als Mitarbeiter" + " " +"'" + mitarbeiter.getMitarbeiterName()+"'" + " " + "eingeloggt!" );
				verlauf.add("Mitarbeiter:"+mitarbeiter.getNutzername()+" hat sich am"+datum+"eingeloggt");
				passwortGefunden = true;
			}
		} 				
	}
			
			if(passwortGefunden == false ){
				System.out.println("");
				System.out.println("<----------------------------------------------------------------->");
				System.out.println("Nutzername oder Passwort falsch");
			}	
			return passwortGefunden;
		}
	

	
	public void schreibeMitarbeiterDaten(String datei) throws IOException  {
		pm.openForWriting(datei);

		if (!mitarbeiterdatenbank.isEmpty()) {
			Iterator<Mitarbeiter> iter = mitarbeiterdatenbank.iterator();
			while (iter.hasNext()) {
				Mitarbeiter p = (Mitarbeiter) iter.next();
				pm.speichereMitarbeiter(p);				
			}
		}
		
		pm.close();
	}
	
	public void liesAusMitarbeiterDaten(String datei) throws IOException {

		pm.openForReading(datei);

		Mitarbeiter einMitarbeiter;
		
		do {
			einMitarbeiter = pm.ladeMitarbeiterDaten();
			if (einMitarbeiter != null) {
			mitarbeiterEinfuegen(einMitarbeiter);
			}
			
		} while (einMitarbeiter != null);

		pm.close();
	}
	
	public List<String> gibVerlaufsListeAus() {
		if (verlauf.isEmpty()) {
			System.out.println();
			System.out.println("Liste ist leer.");
			System.out.println();
		}
	return verlauf;
}
	
}

package C_Datenhaltung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import B_Anwendungslogik.Kundenverwaltung;
import B_Anwendungslogik.Rechnungsobjekt;
import D_DatenObjekte.Artikel;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Mitarbeiter;

public class FilePersistenceManager implements PersistenceManager{

//Attribute
	private Rechnungsobjekt meineRechnung = new Rechnungsobjekt();
	private BufferedReader reader = null;
	private PrintWriter writer = null;
	
//Konstrukor(en)
	

//Methoden
	
	public void openForReading(String datei) throws FileNotFoundException {
		
		reader = new BufferedReader(new FileReader(datei));
	}
	
	
	
	
	
	
	public void openForWriting(String datei) throws IOException{
		
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
		
	}
	
	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				
				return false;
			}
		}

		return true;
	}

	
	public Kunde ladeKundenDaten() throws IOException {
		
		String name = liesZeile();
		if (name == null) {
			return null;
		}
		
		String benutzerName = liesZeile();
		if(benutzerName == null) {
			return null;
		}
		String passwort = liesZeile();
		if(passwort == null) {
			return null;
		}
		
		String kundenNr = liesZeile();
		int kundenNummer = Integer.parseInt(kundenNr);
		
		String strasse = liesZeile();
		if(strasse == null) {
			return null;
		}

       String plzString = liesZeile();
       int plz = Integer.parseInt(plzString);
       
       String wohnort = liesZeile();
       if(wohnort == null) {
			return null;
		}
		
		return new Kunde(name, benutzerName, passwort, kundenNummer, strasse, plz, wohnort);
		
		
	}
	
	
	public Mitarbeiter ladeMitarbeiterDaten() throws IOException {
		String name = liesZeile();
		if (name == null) {
			return null;
		}
		
		String benutzerName = liesZeile();
		if(benutzerName == null) {
			return null;
		}
		String passwort = liesZeile();
		if(passwort == null) {
			return null;
		}
		
		String mitarbeiterNr = liesZeile();
		int mitarbeiternummer = Integer.parseInt(mitarbeiterNr);
		

		
		return new Mitarbeiter(name, benutzerName, passwort, mitarbeiternummer);
	}
	
	//Aus Textdatei Attribute einlesen und verarbeiten
	public Artikel ladeArtikel() throws IOException {

		String artikelName = liesZeile();
		if (artikelName == null) {
			return null;
		}
		
		String artikelNrString = liesZeile();		
	    int artikelNummer = Integer.parseInt(artikelNrString);
	    
	    
	
	   	String preisNrString = liesZeile();
	   	float artikelPreis = Float.parseFloat(preisNrString);
	
	   	String bestandNrString = liesZeile();
	   	int artikelBestand = Integer.parseInt(bestandNrString);
	   	
	   	String massengut = liesZeile();
	   	int artikelMassengut = Integer.parseInt(massengut);
	   	
	   	String verfuegbar = liesZeile(); 
	   	boolean istVerfuegbar = verfuegbar.equals("ist auf Lager") ? true : false;
	   	
	   	
	   	return new Artikel(artikelName, artikelNummer, artikelPreis, artikelBestand, artikelMassengut, istVerfuegbar );
	   	
}
	
	
	public boolean speichereArtikel(Artikel a) throws IOException {
		schreibeZeile(a.getName());
		schreibeZeile(a.getNummer() + "");
		schreibeZeile(a.getPreis() + "");
		schreibeZeile(a.getBestand() +"");
		schreibeZeile(a.getMassengut() + "");
		if (a.istVerfuegbar())
			schreibeZeile("ist auf Lager");
		else
			schreibeZeile("nicht auf Lager");
		return true;
		
	}
	
	
	public boolean speichereKunde(Kunde p) throws IOException {
		schreibeZeile(p.getName());
		schreibeZeile(p.getNutzername());
		schreibeZeile(p.getPasswort());
		schreibeZeile(p.getKundenNr() + "");
		schreibeZeile(p.getStrasse());
		schreibeZeile(p.getPlz() +"");
		schreibeZeile(p.getWohnort());
		return true;
	}
	
	
	
	public boolean speichereMitarbeiter(Mitarbeiter p) throws IOException {
		schreibeZeile(p.getName());
		schreibeZeile(p.getNutzername());
		schreibeZeile(p.getPasswort());
		schreibeZeile(p.getMitarbeiterNr() + "");

		return true;
	}
	

	
	
	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}
	
	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}
	
	public void verlaufSpeichern(List<String> list, String string) { 
        PrintWriter printWriter = null; 
        try { 
            printWriter = new PrintWriter(new FileWriter(string)); 
            Iterator<String> iter = list.iterator(); 
            while(iter.hasNext() ) { 
                Object o = iter.next(); 
                printWriter.println(o); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            if(printWriter != null) printWriter.close(); 
        } 
    }
	
	public void ladeVerlauf(String datName) { 

        File file = new File(datName); 

        if (!file.canRead() || !file.isFile()) 
            System.exit(0); 

            BufferedReader in = null; 
        try { 
            in = new BufferedReader(new FileReader(datName)); 
            String zeile = null; 
            while ((zeile = in.readLine()) != null) { 
                Kundenverwaltung.verlauf.add(zeile); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            if (in != null) 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                } 
        } 
    } 
	
	public void bestandsListeSpeichern(List<String> list, String string) { 
        PrintWriter printWriter = null; 
        try { 
            printWriter = new PrintWriter(new FileWriter(string)); 
            Iterator<String> iter = list.iterator(); 
            while(iter.hasNext() ) { 
                Object o = iter.next(); 
                printWriter.println(o); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            if(printWriter != null) printWriter.close(); 
        } 
    }
	
	public void ladeBestandsListe(String datName) { 

        File file = new File(datName); 

        if (!file.canRead() || !file.isFile()) 
            System.exit(0); 

            BufferedReader in = null; 
        try { 
            in = new BufferedReader(new FileReader(datName)); 
            String zeile = null; 
            while ((zeile = in.readLine()) != null) { 
                Rechnungsobjekt.bestandsVerlauf.add(zeile); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            if (in != null) 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                } 
        } 
        
    } 
}

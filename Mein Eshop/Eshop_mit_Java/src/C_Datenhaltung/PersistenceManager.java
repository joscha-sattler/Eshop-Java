package C_Datenhaltung;

import java.io.IOException;
import java.util.List;

import D_DatenObjekte.Artikel;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Mitarbeiter;


public interface PersistenceManager {

	public void openForReading(String datenquelle) throws IOException;
	
	public void openForWriting(String datenquelle) throws IOException;
	
	public boolean close();

	
	public Artikel ladeArtikel() throws IOException;
	
	
	public boolean speichereArtikel(Artikel a) throws IOException;
	
	public boolean speichereKunde(Kunde p)throws IOException;
	
	public Kunde ladeKundenDaten() throws IOException;
	
	public Mitarbeiter ladeMitarbeiterDaten() throws IOException;
	
	public boolean speichereMitarbeiter(Mitarbeiter p)throws IOException;

	public  void verlaufSpeichern(List<String> verlauf, String string);

	public void ladeVerlauf(String dateiName);

	public void ladeBestandsListe(String dateiName);

	public void bestandsListeSpeichern(List<String> bestandsVerlauf, String string);
	
	
}

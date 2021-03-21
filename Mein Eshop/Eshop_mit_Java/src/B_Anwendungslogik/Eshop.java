package B_Anwendungslogik;

import java.io.IOException;
import java.util.List;

import C_Datenhaltung.FilePersistenceManager;
import C_Datenhaltung.PersistenceManager;
import D_DatenObjekte.Artikel;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;
import E_Exceptions.ArtikelExistiertBereitsException;
import E_Exceptions.ArtikelExistiertNichtException;
import E_Exceptions.BenutzerExistiertBereitsException;
import E_Exceptions.BereitsImWarenkorbException;
import E_Exceptions.FalscheStückzahlFürMassenartikelException;
import E_Exceptions.ZuGeringerBestandException;
import Interfaces.EshopInterface;



public class Eshop implements EshopInterface {

//Attribute	

	private String datei = "";
	private PersistenceManager pm = new FilePersistenceManager();
	private Artikelverwaltung meineArtikel;
	private Kundenverwaltung meineKunden;
	private Rechnungsobjekt meineRechnung;
	
	
//Konstruktor(en)	
	public Eshop(String datei) throws IOException {
		this.datei = datei;
		bestandLaden();
		verlaufLaden();
		meineArtikel = new Artikelverwaltung();
		meineArtikel.liesDaten(datei+"_A.txt");
		meineKunden = new Kundenverwaltung();
		meineKunden.liesAusKundenDaten(datei+"_Kunden.txt");
		meineKunden.liesAusMitarbeiterDaten(datei+"_Mitarbeiter.txt");
		meineRechnung = new Rechnungsobjekt();
	}
	
//Methoden

	public List<Artikel> gibAlleArtikelName() {
		return meineArtikel.getArtikelBestandName();
	}

	public List<Artikel> gibAlleArtikelNummer(){
		 return meineArtikel.getArtikelBestandNummer();
	}
	

	public List<Artikel> sucheNachArtikelName(String name) throws ArtikelExistiertNichtException {
		
		return meineArtikel.sucheArtikelname(name);
	}
	

	public List<Artikel> sucheNachArtikelNummer(int nummer) {
				
		return meineArtikel.sucheArtikelnummer(nummer);
	}
	

	public Artikel fuegeArtikelEin(String artikelName, int artikelNummer, float artikelPreis, int artikelBestand, int artikelMassengut) {
		Artikel a = new Artikel(artikelName, artikelNummer, artikelPreis, artikelBestand, artikelMassengut);
		meineArtikel.einfuegen(a);
		return a;
	}

	public void loescheArtikel(String artikelName) throws ArtikelExistiertNichtException {
		Artikel einArtikel = new Artikel(artikelName);
		
			meineArtikel.loeschen(einArtikel);
		
	}

	public void artikelDatenSichern() throws IOException {
		meineArtikel.schreibeDaten(datei +"_A.txt");
	}

	public List<Artikel> artikelBestandaendern(String artikelName, int bestand) throws ArtikelExistiertNichtException {
		return meineArtikel.neuerBestand(artikelName, bestand);
	}

	public Kunde pruefeKundenLogin(String nutzername, String passwort) {
		return meineKunden.sucheNachKundenNutzername(nutzername, passwort);
	} 

	public boolean pruefeMitarbeiterLogin(String nutzername, String passwort) {
		return meineKunden.sucheNachMitarbeiterNutzername(nutzername, passwort);
	}

	public void kundenRegistrieren(String name, String nutzername, String passwort, int kundenNr, String strasse, int plz, String wohnort) throws BenutzerExistiertBereitsException {
		meineKunden.kundenRegistrierungsVerfahren(name, nutzername, passwort, kundenNr, strasse, plz, wohnort);
	}

	public void KundenDatenSichern() throws IOException {
		meineKunden.schreibeKundenDaten(datei + "_Kunden.txt");
	}

	public void MitarbeiterDatenSichern() throws IOException {
		meineKunden.schreibeMitarbeiterDaten(datei + "_Mitarbeiter.txt");
	}

	public List<Warenkorbartikel> gibWarenkorbAus(Kunde k) {
		return meineArtikel.getWarenkorb(k);
	}

	public void loescheArtikelWarenkorb(String artikelName, Kunde k) {
		Warenkorbartikel einArtikel = new Warenkorbartikel(artikelName);
		meineArtikel.loeschenWarenkorb(einArtikel, k);
	}

	public void warenkorbloeschen(Kunde k) {
		
		meineArtikel.loescheGesamtenWarenkorb(k);
	}

	public void stueckzahlAendern(String artikelName, int stueckzahl,Kunde k) throws ZuGeringerBestandException, ArtikelExistiertNichtException, FalscheStückzahlFürMassenartikelException {
		meineArtikel.neueStueckzahl(artikelName, stueckzahl, k );
	}

	public Float kaufenFertig(Kunde k) {
		 return meineRechnung.endpreisRechnen(k);
	}

	public void bestandUpdaten(Kunde k) {
		meineArtikel.aendereBestand(k);
	}

	public List<String> gibVerlaufAus(){
		return meineKunden.gibVerlaufsListeAus();
	}

	public List<Artikel> gibAlleArtikel(){
		return meineArtikel.getArtikelBestand();
	}

	public void sichereVerlauf() throws IOException {
		 	pm.verlaufSpeichern(Kundenverwaltung.verlauf, datei +"_Verlauf.txt");
	
}

	public void verlaufLaden() {
		 String dateiName = "SHOP_Verlauf.txt"; 
	        pm.ladeVerlauf(dateiName);
	 }
	 

	public void bestandLaden() {
		 String dateiName = "SHOP_BestandsVerlauf.txt"; 
	        pm.ladeBestandsListe(dateiName);
	 }
	 

	public void sichereBestandsListe() {
		 	pm.bestandsListeSpeichern(Rechnungsobjekt.bestandsVerlauf, datei +"_BestandsVerlauf.txt");
	 }

		public Warenkorbartikel einkaufen(String name, int stueckzahl, Kunde k) throws ZuGeringerBestandException, FalscheStückzahlFürMassenartikelException, BereitsImWarenkorbException, ArtikelExistiertNichtException {
			
			Warenkorbartikel wa = new Warenkorbartikel(name);
			
			meineArtikel.artikelKaufen(wa, stueckzahl, k);
			
			return wa;

			
		}

	public Artikel artikelHinzufuegen(String artikelName, int artikelNummer, float artikelPreis, int artikelBestand, int artikelMassengut) throws ArtikelExistiertBereitsException {
			Artikel a = new Artikel(artikelName, artikelNummer, artikelPreis, artikelBestand, artikelMassengut);
			meineArtikel.einfuegenArtikel(a);
			return a;
		}

	public List<Warenkorbartikel> rechnungErzeugen(Kunde k) {
		return meineRechnung.rechnungErstellen(k);
	}

	public void bestandsListe(Kunde k){
		meineRechnung.bestandsVerlauf(k);
	}

	public List<String> bestandsListeAusgeben(){
		return meineRechnung.bestandsListeGeben();
	}

	public List<String> bestandslisteSortieren(String sortierterArtikel){
		return meineRechnung.bestandSortieren(sortierterArtikel);
	}

	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}
}

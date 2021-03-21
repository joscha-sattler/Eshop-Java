package Interfaces;
import java.io.IOException;
import java.util.List;

import D_DatenObjekte.Artikel;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;
import E_Exceptions.ArtikelExistiertBereitsException;
import E_Exceptions.ArtikelExistiertNichtException;
import E_Exceptions.BenutzerExistiertBereitsException;
import E_Exceptions.BereitsImWarenkorbException;
import E_Exceptions.FalscheStückzahlFürMassenartikelException;
import E_Exceptions.ZuGeringerBestandException;

public interface EshopInterface {

	public abstract List<Artikel> gibAlleArtikelName();

	public abstract List<Artikel> gibAlleArtikelNummer();

	public abstract List<Artikel> sucheNachArtikelName(String name) throws ArtikelExistiertNichtException ;

	public abstract List<Artikel> sucheNachArtikelNummer(int nummer);

	public abstract Artikel fuegeArtikelEin(String artikelName, int artikelNummer, float artikelPreis,
			int artikelBestand, int artikelMassengut);

	public abstract void loescheArtikel(String artikelName) throws ArtikelExistiertNichtException;

	public abstract void artikelDatenSichern() throws IOException;

	public abstract List<Artikel> artikelBestandaendern(String artikelName, int bestand) throws ArtikelExistiertNichtException;

	public abstract Kunde pruefeKundenLogin(String nutzername, String passwort);

	public abstract boolean pruefeMitarbeiterLogin(String nutzername, String passwort);

	public abstract void kundenRegistrieren(String name, String nutzername, String passwort, int kundenNr,
			String strasse, int plz, String wohnort) throws BenutzerExistiertBereitsException;

	public abstract void KundenDatenSichern() throws IOException;

	public abstract void MitarbeiterDatenSichern() throws IOException;

	public abstract List<Warenkorbartikel> gibWarenkorbAus(Kunde k);

	public abstract void loescheArtikelWarenkorb(String artikelName, Kunde k);

	public abstract void warenkorbloeschen(Kunde k);

	public abstract void stueckzahlAendern(String artikelName, int stueckzahl, Kunde k) throws ZuGeringerBestandException, ArtikelExistiertNichtException, FalscheStückzahlFürMassenartikelException;

	public abstract Float kaufenFertig(Kunde k);

	public abstract void bestandUpdaten(Kunde k);

	public abstract List<String> gibVerlaufAus();

	public abstract List<Artikel> gibAlleArtikel();

	public abstract void sichereVerlauf() throws IOException;

	public abstract void verlaufLaden();

	public abstract void bestandLaden();

	public abstract void sichereBestandsListe();

	public abstract Warenkorbartikel einkaufen(String name, int stueckzahl, Kunde k) throws ZuGeringerBestandException, FalscheStückzahlFürMassenartikelException, BereitsImWarenkorbException, ArtikelExistiertNichtException;

	public abstract Artikel artikelHinzufuegen(String artikelName, int artikelNummer, float artikelPreis,
			int artikelBestand, int artikelMassengut) throws ArtikelExistiertBereitsException;

	public abstract List<Warenkorbartikel> rechnungErzeugen(Kunde k);

	public abstract void bestandsListe(Kunde k);

	public abstract List<String> bestandsListeAusgeben();

	public abstract List<String> bestandslisteSortieren(String sortierterArtikel);

	
	
	// Serververbindung beenden
	
	public abstract void disconnect() throws IOException;
}

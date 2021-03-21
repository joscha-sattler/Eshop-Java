package Interfaces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
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

public class EshopFassade implements EshopInterface {

	private Socket socket = null;
	private BufferedReader sin; // server-input stream
	private PrintStream sout; // server-output stream
	
	public EshopFassade(String host, int port) throws IOException {
		try {
			socket = new Socket(host, port);
			
			InputStream is = socket.getInputStream();
			sin = new BufferedReader(new InputStreamReader(is));
			sout = new PrintStream(socket.getOutputStream());
				
			
		} catch(IOException e) {
			System.err.println("Fehler beim Socket-Stream öffnen: " + e);
			// Wenn im "try"-Block Fehler auftreten, dann Socket schlieÃŸen:
			if (socket != null)
				socket.close();
			System.err.println("Error: Socket wurde geschlossen!");
			System.exit(0);
		}
		
		// Verbindung erfolgreich hergestellt: IP-Adresse und Port ausgeben
		System.err.println("Verbunden: " + socket.getInetAddress() + ":"
				+ socket.getPort());	

		// BegrÃ¼ÃŸungsmeldung vom Server lesen
		String message = sin.readLine();
		System.out.println(message);
			
		}
	

	
	
	
	
	
	
	@Override
	public List<Artikel> gibAlleArtikelName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artikel> gibAlleArtikelNummer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artikel> sucheNachArtikelName(String name) throws ArtikelExistiertNichtException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artikel> sucheNachArtikelNummer(int nummer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artikel fuegeArtikelEin(String artikelName, int artikelNummer, float artikelPreis, int artikelBestand,
			int artikelMassengut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loescheArtikel(String artikelName) throws ArtikelExistiertNichtException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void artikelDatenSichern() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Artikel> artikelBestandaendern(String artikelName, int bestand) throws ArtikelExistiertNichtException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kunde pruefeKundenLogin(String nutzername, String passwort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pruefeMitarbeiterLogin(String nutzername, String passwort) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void kundenRegistrieren(String name, String nutzername, String passwort, int kundenNr, String strasse,
			int plz, String wohnort) throws BenutzerExistiertBereitsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KundenDatenSichern() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MitarbeiterDatenSichern() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Warenkorbartikel> gibWarenkorbAus(Kunde k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loescheArtikelWarenkorb(String artikelName, Kunde k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warenkorbloeschen(Kunde k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stueckzahlAendern(String artikelName, int stueckzahl, Kunde k) throws ZuGeringerBestandException,
			ArtikelExistiertNichtException, FalscheStückzahlFürMassenartikelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Float kaufenFertig(Kunde k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bestandUpdaten(Kunde k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> gibVerlaufAus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artikel> gibAlleArtikel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sichereVerlauf() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verlaufLaden() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bestandLaden() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sichereBestandsListe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Warenkorbartikel einkaufen(String name, int stueckzahl, Kunde k) throws ZuGeringerBestandException,
			FalscheStückzahlFürMassenartikelException, BereitsImWarenkorbException, ArtikelExistiertNichtException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artikel artikelHinzufuegen(String artikelName, int artikelNummer, float artikelPreis, int artikelBestand,
			int artikelMassengut) throws ArtikelExistiertBereitsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Warenkorbartikel> rechnungErzeugen(Kunde k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bestandsListe(Kunde k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> bestandsListeAusgeben() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> bestandslisteSortieren(String sortierterArtikel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}
	

}

package A_UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import B_Anwendungslogik.Eshop;
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

public class EshopClientCUI {

//Attribute	

	private EshopInterface shop;
	private BufferedReader in;
	Kunde k = new Kunde();

//Konstrukor(en)

	public EshopClientCUI(String datei) throws IOException {

		// die Shop-Verwaltung erledigt die Aufgaben,
		// die nichts mit Ein-/Ausgabe zu tun haben
		shop = new Eshop(datei);

		// Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
		in = new BufferedReader(new InputStreamReader(System.in));
	}

//Methoden	

//---------------------ERSTMENÜ ANFANG-----------------------------------------------------------------------|	
	/*
	 * public void gibErstMenueAus() { System.out.println("Willkommen im EShop!");
	 * System.out.print("Befehle \n Alle Artikel anzeigen: 'a'");
	 * System.out.print("        \n Artikel über Namen suchen: 's'");
	 * System.out.print("        \n Artikel über Artikelnummer suchen: 'n'");
	 * System.out.print("        \n Einloggen/Registrieren: 'x'");
	 * System.out.print("        \n ---------------------");
	 * System.out.println("        \n Beenden:          'q'"); System.out.println();
	 * System.out.print("Hier Ihre Eingabe : "); System.out.flush();
	 * 
	 * }
	 * 
	 * 
	 * private void erstMenueVerarbeitung(String line) throws IOException { String
	 * nummer; int artikelNr; String artikelName; List<Artikel> liste;
	 * 
	 * //Hier Switch-Case für die jeweilige Eingabe
	 * 
	 * switch (line) { case "a": liste = shop.gibAlleArtikel();
	 * gibArtikelListeAus(liste); break;
	 * 
	 * case "s": System.out.print("Artikelname >"); artikelName = liesEingabe();
	 * liste = shop.sucheNachArtikelName(artikelName); gibArtikelListeAus(liste);
	 * break;
	 * 
	 * case "n" : System.out.print("Artikelnummer >"); nummer = liesEingabe();
	 * artikelNr = Integer.parseInt(nummer); liste =
	 * shop.sucheNachArtikelNummer(artikelNr); // Methode schreiben!
	 * gibArtikelListeAus(liste); break; case "x" : gibEinloggMenueAus(); break;
	 * case "q" :
	 * 
	 * } while(!input.contentEquals("q")); }
	 * 
	 * //---------------------ERSTMENÜ
	 * ENDE-------------------------------------------------------------------------
	 * --|
	 * 
	 */

//---------------------EINLOGMENÜ ANFANG-----------------------------------------------------------------------|	

	public void gibEinlogMenueAus() {

		System.out.println("<----------------------------------------------------------------->");
		System.out.println("");
		System.out.println("Menü zum Einloggen & Registrieren!");
		System.out.println();
		System.out.println("Befehle");
		System.out.println("---------------------");
		System.out.println(" Als Kunde Registrieren: 'r'");
		System.out.println(" Als Kunde Einloggen: 'l'");
		System.out.println(" Als Mitarbeiter Einloggen: 'm'");
		System.out.println("---------------------");
		System.out.println("Beenden: 'q'");
		System.out.println();
		System.out.print("Hier Ihre Eingabe : ");
		System.out.flush();

	}

	public void loginVerarbeitung(String input) throws IOException {
		// String eingabe;

		String name;
		String passwort;
		String nutzername;
		String nummer;
		String strassenname;
		String plzString;
		String wohnort;

		int intnummer;
		int plz;

		// kakcne

		switch (input) {
		case "l":
			System.out.println("Bitte geben sie ihren Nutzernamen ein");
			nutzername = liesEingabe();
			System.out.println("Bitte geben sie ihr Passwort ein:");
			passwort = liesEingabe();
			k = shop.pruefeKundenLogin(nutzername, passwort);
			if (k.getNutzername() != null) {
				do {
					gibKundenMenueAus();

					try {
						input = liesEingabe();
						kundenVerarbeitung(input);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} while (!input.contentEquals("q"));
			} else
				run();
			break;

		case "q":
			shop.artikelDatenSichern();

			shop.sichereVerlauf();
			shop.artikelDatenSichern();
			shop.sichereBestandsListe();

			break;

		case "r":
			System.out.println("Bitte geben sie ihren Namen ein: ");
			name = liesEingabe();
			System.out.println("Bitte geben sie ihren Benutzername ein : ");
			nutzername = liesEingabe();
			System.out.println("Bitte geben sie ihr Passwort ein: ");
			passwort = liesEingabe();
			System.out.println("Bitte geben sie ihre Kundennummer ein: ");
			nummer = liesEingabe();
			intnummer = Integer.parseInt(nummer);
			System.out.println("Bitte geben sie ihre Straße an: ");
			strassenname = liesEingabe();
			System.out.println("Bitte geben sie ihre Postleitzahl an: ");
			plzString = liesEingabe();
			plz = Integer.parseInt(plzString);
			System.out.println("Bitte geben sie ihren Wohnort an: ");
			wohnort = liesEingabe();

			try {
				shop.kundenRegistrieren(name, nutzername, passwort, intnummer, strassenname, plz, wohnort);
			} catch (BenutzerExistiertBereitsException e1) {
			
				e1.printStackTrace();
			}
			shop.KundenDatenSichern();

			do {
				gibEinlogMenueAus();

				try {
					input = liesEingabe();
					loginVerarbeitung(input);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} while (!input.contentEquals("q"));

			break;
		case "m":
			System.out.println("Bitte geben sie ihren Nutzernamen ein");
			nutzername = liesEingabe();
			System.out.println("Bitte geben sie ihr Passwort ein:");
			passwort = liesEingabe();
			if (shop.pruefeMitarbeiterLogin(nutzername, passwort)) {

				do {
					gibMitarbeiterMenueAus();

					try {
						input = liesEingabe();
						mitarbeiterVerarbeitung(input);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} while (!input.contentEquals("q"));
			} else
				run();
		}
		if (!input.contentEquals("q")) {

			run();
		}

	}

//---------------------EINLOGMENÜ ENDE-------------------------------------------------------------------------|	

//---------------------KUNDENMENÜ ANFANG-----------------------------------------------------------------------|	

	private void gibKundenMenueAus() {

		System.out.println("<----------------------------------------------------------------->");
		System.out.println("");
		System.out.println("Kundenmenü!");
		System.out.println();
		System.out.println("Befehle");
		System.out.println("---------------------");
		System.out.println(" Alphabetisch sortierte Artikelliste ausgeben: 'a'");
		System.out.println(" Artikelliste nach Nummern sortiert: 'd'");
		System.out.println(" Artikel über Namen suchen: 's'");
		System.out.println(" Artikel über Artikelnummer suchen: 'n'");
		System.out.println(" Artikel in den Warenkorb legen: 'k'");
		System.out.println(" Warenkorb anzeigen: 'w'");
		System.out.println(" Einen Artikel aus Warenkorb löschen: 't'");
		System.out.println(" Alle Artikel aus dem Warenkorb löschen 'x'");
		System.out.println(" Bestand eines Artikels im Warenkorb ändern: 'y'");
		System.out.println(" Jetzt kostenpflichtig kaufen: 'b'");
		System.out.println("---------------------");
		System.out.println(" Ausloggen: 'o'");
		System.out.println(" Beenden: 'q'");
		System.out.println();
		System.out.print("Hier Ihre Eingabe : ");
		System.out.flush();

	}

	private void kundenVerarbeitung(String line) throws IOException {
		String stueckzahl;
		int stueckzahlNr;
		String nummer;
		int artikelNr;
		String artikelName;
		List<Warenkorbartikel> rechnungsListe;
		List<Artikel> liste;
		List<Warenkorbartikel> waren;

//Hier Switch-Case für die jeweilige Eingabe

		switch (line) {
		case "a":
			liste = shop.gibAlleArtikelName();
			gibArtikelListeAus(liste);
			break;
		case "d":
			liste = shop.gibAlleArtikelNummer();
			gibArtikelListeAus(liste);
			break;

		case "s":
			System.out.print("Artikelname >");
			artikelName = liesEingabe();
			try {
				liste = shop.sucheNachArtikelName(artikelName);
				gibArtikelListeAus(liste);
			} catch (ArtikelExistiertNichtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "n":
			System.out.print("Artikelnummer >");
			nummer = liesEingabe();
			artikelNr = Integer.parseInt(nummer);
			liste = shop.sucheNachArtikelNummer(artikelNr);
			gibArtikelListeAus(liste);
			break;
		case "k":
			System.out.print("Artikelname >");
			artikelName = liesEingabe();

			System.out.print("Stückzahl >");
			stueckzahl = liesEingabe();
			stueckzahlNr = Integer.parseInt(stueckzahl);

			try {
				shop.einkaufen(artikelName, stueckzahlNr, k);
				System.out.println();
				System.out.println("Artikel wurde erfolgreich in den Warenkorb gelegt");
				System.out.println();

			} catch (ArtikelExistiertNichtException | ZuGeringerBestandException
					| FalscheStückzahlFürMassenartikelException | BereitsImWarenkorbException a) {
				System.out.println();
				System.out.println("Artikel konnte nicht dem Warenkorb hinzugefügt werden");
				System.out.println();

				a.printStackTrace();
				System.out.println();
			}
			break;

		case "w":
			waren = shop.gibWarenkorbAus(k);
			gibWarenkorb(waren);
			break;

		case "t":
			System.out.print("Artikelname >");
			artikelName = liesEingabe();

			shop.loescheArtikelWarenkorb(artikelName, k);
			break;
		case "o":
			System.out.println("Sie haben sich erfolgreich ausgeloggt");
			System.out.println();
			shop.sichereVerlauf();
			shop.artikelDatenSichern();
			shop.sichereBestandsListe();

			run();
			break;
		case "x":
			shop.bestandUpdaten(k);
			shop.warenkorbloeschen(k);
			break;

		case "y":
			System.out.print("Artikelname >");
			artikelName = liesEingabe();

			System.out.print("Neue Stückzahl>");
			stueckzahl = liesEingabe();
			stueckzahlNr = Integer.parseInt(stueckzahl);

			try {
				shop.stueckzahlAendern(artikelName, stueckzahlNr, k);

			} catch (ZuGeringerBestandException | ArtikelExistiertNichtException | FalscheStückzahlFürMassenartikelException a) {
				System.out.println();
				System.out.println("Artikelbestand konnte nicht im Warenkorb nicht geändert werden");
				System.out.println();

				
				a.printStackTrace();
				System.out.println();
			}
			break;

		case "b":
			rechnungsListe = shop.rechnungErzeugen(k);
			gibRechnungListeAus(rechnungsListe);
			shop.bestandsListe(k);
			System.out.println("");
			System.out.println("Kauf abgeschlossen, vielen Dank!");

			System.out.println("Endpreis: " + shop.kaufenFertig(k) + " €");
			shop.warenkorbloeschen(k);
			break;

		case "q":

			shop.artikelDatenSichern();

			shop.sichereVerlauf();
			shop.artikelDatenSichern();
			shop.sichereBestandsListe();

			break;

		}
	}

//---------------------KUNDENMENÜ ENDE------------------------------------------------------------------------------|	

//---------------------MITARBEITERMENÜ ANFANG-----------------------------------------------------------------------|	

	private void gibMitarbeiterMenueAus() {

		System.out.println("<----------------------------------------------------------------->");
		System.out.println("");
		System.out.println("Mitarbeitermenü!");
		System.out.println();
		System.out.println("Befehle");
		System.out.println("---------------------");
		System.out.println(" Alphabetisch sortierte Artikelliste ausgeben: 'a'"); // jop
		System.out.println(" Artikelliste nach Nummern sortiert: 'd'"); // jop
		System.out.println(" Artikel über Namen suchen: 's'"); // jop
		System.out.println(" Artikel über Artikelnummer suchen: 'n'"); // jop
		System.out.println(" Artikelbestand ändern: 'b'"); // jop
		System.out.println(" Artikel löschen: 'l'"); // jop
		System.out.println(" Artikel hinzufügen: 'h'"); // jop
		System.out.println(" Daten sichern: 'v'"); 
		System.out.println(" EreignisVerlauf ausgeben 'g'");
		System.out.println(" Bestands EreignisVerlauf von einem Artikel ausgeben 'z'");
		System.out.println("---------------------");
		System.out.println("Ausloggen: 'o'");
		System.out.println("Beenden: 'q'");
		System.out.println();
		System.out.print("Hier Ihre Eingabe : ");
		System.out.flush();

	}

	private void mitarbeiterVerarbeitung(String line) throws IOException {
		String nummer;
		int artikelNr;
		String bestand;
		int bestandNr;
		String preis;
		float preisNr;
		String artikelName;
		String mindestbestellwert;
		int artikelMassengut;
		List<Artikel> liste;
		List<String> bestandsListe;

//Hier Switch-Case für die jeweilige Eingabe

		switch (line) {
		case "a":
			liste = shop.gibAlleArtikelName();
			gibArtikelListeAus(liste);
			break;

		case "d":
			liste = shop.gibAlleArtikelNummer();
			gibArtikelListeAus(liste);
			break;

		case "s":
			System.out.print("Artikelname >");
			artikelName = liesEingabe();
			try {
				liste = shop.sucheNachArtikelName(artikelName);
				gibArtikelListeAus(liste);
			} catch (ArtikelExistiertNichtException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case "n":
			System.out.print("Artikelnummer >");
			nummer = liesEingabe();

			artikelNr = Integer.parseInt(nummer);
			liste = shop.sucheNachArtikelNummer(artikelNr);

			gibArtikelListeAus(liste);
			shop.sichereBestandsListe();
			break;
		case "b":
			System.out.print("Artikelname >");
			artikelName = liesEingabe();

			System.out.print("neuen Bestand eingeben>");
			bestand = liesEingabe();
			bestandNr = Integer.parseInt(bestand);

			try {
				shop.artikelBestandaendern(artikelName, bestandNr);
			} catch (ArtikelExistiertNichtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "l":

			System.out.print("Artikelname >");
			artikelName = liesEingabe();

			try {
				shop.loescheArtikel(artikelName);
			} catch (ArtikelExistiertNichtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "h":
			System.out.print("Artikelname >");
			artikelName = liesEingabe();

			System.out.print("Artikelnummer >");
			nummer = liesEingabe();
			artikelNr = Integer.parseInt(nummer);

			System.out.print("Preis >");
			preis = liesEingabe();
			preisNr = Float.parseFloat(preis);

			System.out.print("Bestand >");
			bestand = liesEingabe();
			bestandNr = Integer.parseInt(bestand);

			System.out.print("Mindestbestellwert >");
			mindestbestellwert = liesEingabe();
			artikelMassengut = Integer.parseInt(mindestbestellwert);

			try {
				shop.artikelHinzufuegen(artikelName, artikelNr, preisNr, bestandNr, artikelMassengut);
				System.out.println();
				System.out.println("Artikel wurde erfolgreich hinzugeügt");
				System.out.println();

			} catch (ArtikelExistiertBereitsException a) {
				System.out.println();
				System.out.println("Fehler beim Hinzufügen eines Artikels");
				System.out.println();

				a.printStackTrace();
				System.out.println();
			}
			break;

		case "q":

			shop.artikelDatenSichern();

			shop.sichereVerlauf();
			shop.artikelDatenSichern();
			shop.sichereBestandsListe();

			break;

		case "v":
			shop.artikelDatenSichern();

			shop.sichereVerlauf();
			shop.artikelDatenSichern();
			shop.sichereBestandsListe();

			break;
		case "o":
			System.out.println("Sie haben sich erfolgreich ausgeloggt");
			System.out.println();
			shop.sichereVerlauf();
			shop.artikelDatenSichern();
			shop.sichereBestandsListe();

			run();
			break;
		case "g":
			shop.gibVerlaufAus();
			shop.sichereVerlauf();

			break;

		case "z":
			String sortierterArtikel;
			System.out.println("Vom welchem Artikel möchten sie die Bestandsliste sehen?");
			sortierterArtikel = liesEingabe();
			bestandsListe = shop.bestandslisteSortieren(sortierterArtikel);
			gibBestandsListeAus(bestandsListe);

			break;

		}

	}

//---------------------MITARBEITERMENÜ ENDE-----------------------------------------------------------------------|	

	public void gibWarenkorb(List<Warenkorbartikel> waren) {
		if (waren.isEmpty()) {
			System.out.println();
			System.out.println("Liste ist leer.");
			System.out.println();
		} else {
			for (Warenkorbartikel artikel : waren) {
				System.out.println();
				System.out.println(artikel);
				System.out.println();
			}
		}
	}

	private void gibArtikelListeAus(List<Artikel> liste) {
		if (liste.isEmpty()) {
			System.out.println();
			System.out.println("Liste ist leer.");
			System.out.println();
		} else {
			for (Artikel artikel : liste) {
				System.out.println();
				System.out.println(artikel);
				System.out.println();
			}
		}
	}

	private void gibRechnungListeAus(List<Warenkorbartikel> rechnungsListe) {
		if (rechnungsListe.isEmpty()) {
			System.out.println();
			System.out.println("Liste ist leer.");
			System.out.println();
		} else {
			for (Warenkorbartikel artikel : rechnungsListe) {
				System.out.println();
				System.out.println(artikel);
				System.out.println();
			}
		}
	}

	public void gibBestandsListeAus(List<String> bestandsListe) {
		if (bestandsListe.isEmpty()) {
			System.out.println();
			System.out.println("Liste ist leer.");
			System.out.println();
		} else {
			for (String artikel : bestandsListe) {
				System.out.println();
				System.out.println(artikel);
				System.out.println();
			}
		}
	}

	private String liesEingabe() throws IOException {
		return this.in.readLine();

	}

//Methode zur Ausführung der Hauptschleife

	public void run() {
		String input = "";

		gibEinlogMenueAus();

		try {
			input = liesEingabe();
			loginVerarbeitung(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//Main-Methode    

	public static void main(String[] args) {

		EshopClientCUI cui;
		try {
			cui = new EshopClientCUI("SHOP");
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
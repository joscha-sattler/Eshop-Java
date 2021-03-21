package B_Anwendungslogik;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import C_Datenhaltung.FilePersistenceManager;
import C_Datenhaltung.PersistenceManager;
import D_DatenObjekte.Artikel;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;
import E_Exceptions.ArtikelExistiertBereitsException;
import E_Exceptions.ArtikelExistiertNichtException;
import E_Exceptions.BereitsImWarenkorbException;
import E_Exceptions.FalscheStückzahlFürMassenartikelException;
import E_Exceptions.ZuGeringerBestandException;

public class Artikelverwaltung {

//Attribute
	public boolean gefunden = false;
	public boolean bestandArtikelGefunden = false;
	public List<Warenkorbartikel> warenkorb = new Vector<Warenkorbartikel>();
	private List<Artikel> artikelBestand = new Vector<Artikel>();

	private PersistenceManager pm = new FilePersistenceManager();

//Konstruktor(en)	

//Methoden

	public void liesDaten(String datei) throws IOException {

		pm.openForReading(datei);

		Artikel einArtikel;

		do {
			einArtikel = pm.ladeArtikel();
			if (einArtikel != null) {
				einfuegen(einArtikel);
			}

		} while (einArtikel != null);

		pm.close();
	}

	public void schreibeDaten(String datei) throws IOException {
		pm.openForWriting(datei);

		if (!artikelBestand.isEmpty()) {
			Iterator<Artikel> iter = artikelBestand.iterator();
			while (iter.hasNext()) {
				Artikel a = iter.next();
				pm.speichereArtikel(a);
			}
		}

		pm.close();
	}

//Am Ende der Liste einen Artikel hinzufügen

	public void einfuegen(Artikel einArtikel) {
		Date date = new Date();

//Exception ergänzen (Artikel existiert bereits)

		artikelBestand.add(einArtikel);
	}

//Artikel löschen

	public void loeschen(Artikel einArtikel) throws ArtikelExistiertNichtException {
		Date date = new Date();
		Kundenverwaltung.verlauf.add("Der Artikel: " + einArtikel.getName() + " wurde am" + date + " gelöscht");
		if(artikelBestand.contains(einArtikel)) {
		artikelBestand.remove(einArtikel);
		} else {
			throw new ArtikelExistiertNichtException();
		}
	}

	public List<Artikel> getArtikelBestandName() {
		Collections.sort(artikelBestand, compareByName);
		return new Vector<Artikel>(artikelBestand);
	}

	public List<Artikel> getArtikelBestandNummer() {
		Collections.sort(artikelBestand, compareByNumber);
		return new Vector<Artikel>(artikelBestand);
	}

	public List<Artikel> getArtikelBestand() {
		return new Vector<Artikel>(artikelBestand);
	}

//Über Artikelname suchen

	public List<Artikel> sucheArtikelname(String name) throws ArtikelExistiertNichtException {
		List<Artikel> ergebnis = new Vector<Artikel>();
		Iterator<Artikel> it = artikelBestand.iterator();

		while (it.hasNext()) {
			Artikel artikel = it.next();
			if (artikel.getName().equals(name)) {
				ergebnis.add(artikel);
				gefunden = true;
			}
			
		} if(gefunden == false) {
			
			throw new ArtikelExistiertNichtException();
		}
 
		gefunden = false;
		
		return ergebnis;
	}

// Über Artikelnummer suchen

	public List<Artikel> sucheArtikelnummer(int nr) {
		List<Artikel> ergebnis = new Vector<Artikel>();
		Iterator<Artikel> it = artikelBestand.iterator();

		while (it.hasNext()) {
			Artikel artikel = it.next();
			if (artikel.getNummer() == nr) {
				ergebnis.add(artikel);
			}
		}

		return ergebnis;
	}

//Bestand eines Artikel mittels Namen, Nummer und neuem Bestand ändern

	public List<Artikel> neuerBestand(String name, int bestand) throws ArtikelExistiertNichtException {
		Date date = new Date();
		List<Artikel> ergebnis = new Vector<Artikel>();
		Iterator<Artikel> it = artikelBestand.iterator();

		while (it.hasNext()) {
			Artikel artikel = it.next();
			if (artikel.getName().equals(name)) {
				artikel.setBestand(bestand);
				Kundenverwaltung.verlauf.add("Der Bestand des Artikels: " + name + " wurde am" + date + " gändert");
				ergebnis.add(artikel);
				bestandArtikelGefunden = true;

			}
		}
		if (!bestandArtikelGefunden) {
			throw new ArtikelExistiertNichtException();
		}

		bestandArtikelGefunden = false;

		return ergebnis;
	}

	public void neueStueckzahl(String name, int stueckzahl, Kunde k) throws ZuGeringerBestandException,
			ArtikelExistiertNichtException, FalscheStückzahlFürMassenartikelException {

		int neuerBestand;

		Iterator<Artikel> iter = artikelBestand.iterator();
		Iterator<Warenkorbartikel> wArtikeliter = k.warenkorb.iterator();

		Artikel a = iter.next();
		Warenkorbartikel wa = wArtikeliter.next();

		
		if (name.equals(wa.getName())) {
			while (!a.getName().equals(wa.getName()) && iter.hasNext()) {
				a = iter.next();

			}

			if (a.istMassengut() && stueckzahl % a.getMassengut() != 0) {
				throw new FalscheStückzahlFürMassenartikelException(a);
			}
			else if (a.getBestand() + (wa.getStueckzahl() - stueckzahl) >= 0) {
				
				neuerBestand = a.getBestand() + (wa.getStueckzahl() - stueckzahl);
				a.setBestand(neuerBestand);
				wa.setStueckzahl(stueckzahl);
				
				gefunden = true;

			} else {
				throw new ZuGeringerBestandException(a);
			}
			
		}
		
		else
			while (!name.equals(wa.getName()) && wArtikeliter.hasNext()) {

				wa = wArtikeliter.next();

				while (!a.getName().equals(wa.getName()) && iter.hasNext()) {
					
					if(!a.getName().equals(wa.getName()) && !iter.hasNext()) {
						throw new ArtikelExistiertNichtException();
					}
					
					a = iter.next();
				}

				if (name.equals(wa.getName())) {

					gefunden = true;

					if (a.istMassengut() && stueckzahl % a.getMassengut() != 0) {
						throw new FalscheStückzahlFürMassenartikelException(a);
					} else if (a.getBestand() + (wa.getStueckzahl() - stueckzahl) >= 0) {

						neuerBestand = a.getBestand() + (wa.getStueckzahl() - stueckzahl);
						a.setBestand(neuerBestand);
						wa.setStueckzahl(stueckzahl);

					} else {
						throw new ZuGeringerBestandException(a);
					}

				}

			}
		
		if (gefunden == false) {
			throw new ArtikelExistiertNichtException();
		}

		gefunden = false;
	}

	
	
	// Alte Verion (geht nicht richtig)
	
//	public void neueStueckzahl(String name, int stueckzahl, Kunde k) throws ZuGeringerBestandException, ArtikelExistiertNichtException {
//		
//		int neuerBestand;
//		
//		Iterator<Artikel> iter = artikelBestand.iterator();
//		Iterator<Warenkorbartikel> it = k.warenkorb.iterator();
//		
//		Warenkorbartikel wa = it.next();
//		Artikel a = iter.next();
//		
//		if (name.equals(wa.getName())){
//			while( !a.getName().equals(wa.getName())){
//				a = iter.next();
//						
//			}			
//			
//			if(a.getBestand()+(wa.getStueckzahl()-stueckzahl) >= 0) {
//			neuerBestand = a.getBestand()+(wa.getStueckzahl()-stueckzahl);
//			a.setBestand(neuerBestand);
//			wa.setStueckzahl(stueckzahl);
//			} else {
//				throw new ZuGeringerBestandException(a);
//				}
//			
//		}else {
//			
//		while(it.hasNext()&& !name.equals(wa.getName())) {
//			wa = it.next();
//		
//		while( !a.getName().equals(wa.getName())){
//			a = iter.next();
//					
//		}
//			if(a.getBestand()+(wa.getStueckzahl()-stueckzahl) >= 0) {
//				neuerBestand = a.getBestand()+(wa.getStueckzahl()-stueckzahl);
//				a.setBestand(neuerBestand);
//				wa.setStueckzahl(stueckzahl);
//			} else {
//				throw new ZuGeringerBestandException(a);
//				}
//		}  throw new ArtikelExistiertNichtException();
//	}
//	}

	public void gibWarenkorb(List<Artikel> waren) {
		if (waren.isEmpty()) {
			System.out.println();
			System.out.println("Warenkorb ist leer.");
			System.out.println();
		} else {
			for (Artikel artikel : waren) {
				System.out.println();
				System.out.println(artikel);
				System.out.println();
			}
		}
	}

	public List<Warenkorbartikel> getWarenkorb(Kunde k) {
		return (k.warenkorb);
	}

	public void artikelKaufen(Warenkorbartikel wArtikel, int stueckzahl, Kunde k) throws ZuGeringerBestandException,
			FalscheStückzahlFürMassenartikelException, ArtikelExistiertNichtException, BereitsImWarenkorbException {
		Date date = new Date();
		Iterator<Artikel> it = artikelBestand.iterator();

		if (k.getWarenkorb().contains(wArtikel)) {

			throw new BereitsImWarenkorbException(wArtikel);

		} else {

			Warenkorbartikel b = wArtikel;

			while (it.hasNext()) {

				Artikel artikel = it.next();

				if (artikel.getName().equals(b.getName())) {

					if (artikel.istMassengut() && stueckzahl % artikel.getMassengut() != 0) {

						throw new FalscheStückzahlFürMassenartikelException(artikel);
					} else {

						if (artikel.getBestand() - stueckzahl >= 0) {
							artikel.setBestand(artikel.getBestand() - stueckzahl);
							b = new Warenkorbartikel(artikel.getName(), artikel.getNummer(), stueckzahl,
									artikel.getPreis());
							k.warenkorb.add(b);
							gefunden = true;
							Kundenverwaltung.verlauf.add(k.getName() + " hat den Artikel " + b.getName() + " am " + date
									+ " in den Warenkobr gelegt");

						} else {
							throw new ZuGeringerBestandException(artikel);

						}
					}

				}

			}

			if (gefunden == false) {

				throw new ArtikelExistiertNichtException();
			}

			gefunden = false;

		}
	}

	// Hier wird ein Artikel im Warenkorb gelöscht

	public void loeschenWarenkorb(Warenkorbartikel einArtikel, Kunde k) {
		int neuerBestand;
		Iterator<Artikel> iter = artikelBestand.iterator();
		Iterator<Warenkorbartikel> it = k.warenkorb.iterator();
		
		Warenkorbartikel wa = it.next();
		Artikel a = iter.next();
		
		if (einArtikel.getName().equals(wa.getName())) {
			while (!a.getName().equals(wa.getName())) {
				a = iter.next();

			}
			neuerBestand = a.getBestand() + wa.getStueckzahl();
			a.setBestand(neuerBestand);
			k.warenkorb.remove(einArtikel);

		} else {

			while (it.hasNext() && !einArtikel.getName().equals(wa.getName())) {
				wa = it.next();

				while (!a.getName().equals(wa.getName())) {
					a = iter.next();

				}
				neuerBestand = a.getBestand() + wa.getStueckzahl();
				a.setBestand(neuerBestand);
				k.warenkorb.remove(einArtikel);

			}
		}
	}

	public void loescheGesamtenWarenkorb(Kunde k) {

		k.getWarenkorb().clear();

	}

	// Hier wird der Bestand der Artikel angepasst, wenn der Warenkoprb komplett gelöscht wird

	public void aendereBestand(Kunde k) {
		
		int neuerBestand;
		
		Iterator<Artikel> artikeliter = artikelBestand.iterator();
		Iterator<Warenkorbartikel> wArtikeliter = k.warenkorb.iterator();

		Artikel a = artikeliter.next();
		Warenkorbartikel wa = wArtikeliter.next();
		
		if (a.getName().equals(wa.getName())) {
			
			neuerBestand = a.getBestand() + wa.getStueckzahl();
			a.setBestand(neuerBestand);
			
		} else	while (!a.getName().equals(wa.getName()) && artikeliter.hasNext()) {
				
				a = artikeliter.next();
				
				if (a.getName().equals(wa.getName())) {
					
					neuerBestand = a.getBestand() + wa.getStueckzahl();
					a.setBestand(neuerBestand);

			}
				
		while (wArtikeliter.hasNext()) {
			wa = wArtikeliter.next();
			
			if (a.getName().equals(wa.getName())) {
				
				neuerBestand = a.getBestand() + wa.getStueckzahl();
				a.setBestand(neuerBestand);
				
			} else	while (!a.getName().equals(wa.getName()) && artikeliter.hasNext()) {
					
					a = artikeliter.next();
					
					if (a.getName().equals(wa.getName())) {
						
						neuerBestand = a.getBestand() + wa.getStueckzahl();
						a.setBestand(neuerBestand);

				}
			
			
		}
			
	}			
			
			
			
}
			
			
			
			
			
			while (wArtikeliter.hasNext()) {
			
			wa = wArtikeliter.next();

			while (!a.getName().equals(wa.getName())) {
				a = artikeliter.next();
			}
			
			neuerBestand = a.getBestand() + wa.getStueckzahl();
			a.setBestand(neuerBestand);
		}

	}

	public void andereBestandWarenkorb(Warenkorbartikel einArtikel, Kunde k) {

	}

	Comparator<Artikel> compareByName = new Comparator<Artikel>() {
		@Override
		public int compare(Artikel o1, Artikel o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	Comparator<Artikel> compareByNumber = new Comparator<Artikel>() {
		@Override
		public int compare(Artikel o1, Artikel o2) {
			return Integer.valueOf(o1.getNummer()).compareTo(o2.getNummer());
		}
	};

	public void einfuegenArtikel(Artikel einArtikel) throws ArtikelExistiertBereitsException {
		Date date = new Date();

		if (artikelBestand.contains(einArtikel)) {
			throw new ArtikelExistiertBereitsException(einArtikel);
		}

		artikelBestand.add(einArtikel);
	}

}

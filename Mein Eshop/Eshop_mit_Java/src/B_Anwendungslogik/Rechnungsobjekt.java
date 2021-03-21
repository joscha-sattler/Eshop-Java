package B_Anwendungslogik;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;

public class Rechnungsobjekt {

	// Attribute
	public static List<String> bestandsVerlauf = new Vector<String>();
	public List<Warenkorbartikel> rechnung = new Vector<Warenkorbartikel>();
	// Methoden

	public Float endpreisRechnen(Kunde k) {
		Date date = new Date();
		Float ergebnis = 0f;
		Iterator<Warenkorbartikel> it = k.warenkorb.iterator();

		while (it.hasNext()) {

			Warenkorbartikel w = it.next();
			ergebnis = ergebnis + w.getGesamtpreis();
			Kundenverwaltung.verlauf
					.add(k.getName() + " hat am " + date + " Waren im Wert von " + ergebnis + "€ gekauft.");
		}
		return ergebnis;
	}

	public List<Warenkorbartikel> rechnungErstellen(Kunde k) {
		Iterator<Warenkorbartikel> it = k.warenkorb.iterator();
		while (it.hasNext()) {
			Warenkorbartikel wa = it.next();
			rechnung.add(wa);

		}
		return rechnung;
	}

	public void bestandsVerlauf(Kunde k) {
		Date date = new Date();
		Iterator<Warenkorbartikel> it = k.warenkorb.iterator();
		while (it.hasNext()) {
			Warenkorbartikel wa = it.next();
			bestandsVerlauf.add(wa.getName() + " wurde " + wa.getStueckzahl() + " mal am " + date + " gekauft.");
		}

	}

	public List<String> bestandsListeGeben() {
		return bestandsVerlauf;
	}

	public List<String> bestandSortieren(String sortierterArtikel) {
		Iterator<String> it = bestandsVerlauf.iterator();
		List<String> sortiert = new Vector<String>();
		
		while(it.hasNext()) {
			sortiert.add(it.next());
			
		}
		sortiert.removeIf(s -> !s.contains(sortierterArtikel));
		return sortiert;
	}
}

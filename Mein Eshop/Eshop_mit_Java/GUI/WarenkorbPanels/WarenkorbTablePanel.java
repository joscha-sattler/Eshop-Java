package WarenkorbPanels;

import java.util.Collections;

import javax.swing.JTable;

import D_DatenObjekte.Warenkorbartikel;

public class WarenkorbTablePanel extends JTable {

	public WarenkorbTablePanel(java.util.List<Warenkorbartikel> warenkorbartikel) {
		super();
		
		
		WarenkorbTableModel wTableModel = new WarenkorbTableModel(warenkorbartikel);
		
		setModel(wTableModel);
		
		updateWArtikelListeName(warenkorbartikel);
	
	}
	
	
	
	public void updateWArtikelListeName(java.util.List<Warenkorbartikel> warenkorbartikel) {

		// Sortierung (mit Lambda-Expression)
		Collections.sort(warenkorbartikel, (w1, w2) -> w1.getName().compareTo(w2.getName()));	// Sortierung nach Titel


		// ListModel von JList holen und ...
		WarenkorbTableModel wTableModel = (WarenkorbTableModel) getModel();
		// ... Inhalt aktualisieren
		wTableModel.setWarenkorbartikel(warenkorbartikel);
		}
	
	
	public void updateWArtikelListeNummer(java.util.List<Warenkorbartikel> warenkorbartikel) {

		// Sortierung (mit Lambda-Expression)
		
		Collections.sort(warenkorbartikel, (w1, w2) -> w1.getNummer() - w2.getNummer());	// Sortierung nach Nummer

		// ListModel von JList holen und ...
		WarenkorbTableModel wTableModel = (WarenkorbTableModel) getModel();
		// ... Inhalt aktualisieren
		wTableModel.setWarenkorbartikel(warenkorbartikel);
		}		
	

}

package MenüPanels;

import java.util.Collections;

import javax.swing.JTable;

import D_DatenObjekte.Artikel;

public class ArtikelTablePanel extends JTable{

	public ArtikelTablePanel(java.util.List<Artikel> artikel) {
		super();


		ArtikelTableModel tableModel = new ArtikelTableModel(artikel);

		setModel(tableModel);

		updateArtikelListeName(artikel);
	}
	
	


	public void updateArtikelListeName(java.util.List<Artikel> artikel) {

		
		Collections.sort(artikel, (a1, a2) -> a1.getName().compareTo(a2.getName()));	// Sortierung nach Titel


		
		ArtikelTableModel tableModel = (ArtikelTableModel) getModel();
		
		tableModel.setArtikel(artikel);
	}	
	
	
	public void updateArtikelListeNummer(java.util.List<Artikel> artikel) {

		
		
		Collections.sort(artikel, (a1, a2) -> a1.getNummer() - a2.getNummer());	// Sortierung nach Nummer

		
		ArtikelTableModel tableModel = (ArtikelTableModel) getModel();
		
		tableModel.setArtikel(artikel);
	}
	
	
	public void updateArtikelListePreis(java.util.List<Artikel> artikel) {

		
		
		Collections.sort(artikel, (a1, a2) -> a1.getPreisAlsInt() - a2.getPreisAlsInt());	// Sortierung nach Nummer

		
		ArtikelTableModel tableModel = (ArtikelTableModel) getModel();
		
		tableModel.setArtikel(artikel);
	}
	
}	

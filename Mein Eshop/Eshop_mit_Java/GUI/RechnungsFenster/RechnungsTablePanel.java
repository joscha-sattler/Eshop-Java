package RechnungsFenster;

import java.util.Collections;

import javax.swing.JTable;

import D_DatenObjekte.Warenkorbartikel;

public class RechnungsTablePanel extends JTable {

	public RechnungsTablePanel(java.util.List<Warenkorbartikel> rechnungsartikel) {
		super();


		RechnungsTableModel rechnungsListModel = new RechnungsTableModel(rechnungsartikel);

		setModel(rechnungsListModel);

		updateRechnung(rechnungsartikel);
		
		
	}
	
	public void updateRechnung(java.util.List<Warenkorbartikel> rechnungsartikel) {

		// Sortierung (mit Lambda-Expression)
		Collections.sort(rechnungsartikel, (wa1, wa2) -> wa1.getName().compareTo(wa2.getName()));
	

		// ListModel von JList holen und ...
		RechnungsTableModel rechnungsListModel = (RechnungsTableModel) getModel();
		// ... Inhalt aktualisieren
		
		rechnungsListModel.setRechnungsartikel(rechnungsartikel);
		
		}		
	}


package WarenkorbPanels;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import D_DatenObjekte.Warenkorbartikel;

public class WarenkorbTableModel extends AbstractTableModel {

	private List<Warenkorbartikel> warenkorbartikel;
	private String[] spaltenNamen = {"Artikelname", "Artikelnummer", "Einzelpreis", "Stückzahl", "Gesamtpreis"};
	
	public WarenkorbTableModel(List<Warenkorbartikel> aktuelleWarenkorbartikel) {
		super();
		
		warenkorbartikel = new Vector<Warenkorbartikel>();
		warenkorbartikel.addAll(aktuelleWarenkorbartikel);
	}
	
	
	
	public void setWarenkorbartikel(List<Warenkorbartikel> aktuelleWarenkorbartikel) {
		warenkorbartikel.clear();
		warenkorbartikel.addAll(aktuelleWarenkorbartikel);
		fireTableDataChanged();
	}
	
	
	
	@Override
	public int getColumnCount() {
		return spaltenNamen.length;
	}

	@Override
	public int getRowCount() {
		return warenkorbartikel.size();
	}
	
	@Override
    public String getColumnName(int col) {
        return spaltenNamen[col];
    }
	
	
	
	
	@Override
	public Object getValueAt(int row, int col) {
		Warenkorbartikel gewaehlterWarenkorbartikel = warenkorbartikel.get(row);
		
		switch(col) {
		case 0:	
			return gewaehlterWarenkorbartikel.getName();
		case 1:	
			return gewaehlterWarenkorbartikel.getNummer();
		case 2:	
			return gewaehlterWarenkorbartikel.getPreis() +" €";
		case 3:	
			return gewaehlterWarenkorbartikel.getStueckzahl();
		case 4:
			return gewaehlterWarenkorbartikel.getGesamtpreis() +" €";
		default:
			return null;
		}
	}

}

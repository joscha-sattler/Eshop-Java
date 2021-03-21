package RechnungsFenster;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import D_DatenObjekte.Warenkorbartikel;

public class RechnungsTableModel  extends AbstractTableModel{

	private List<Warenkorbartikel> rechnungsartikel;
	private String[] spaltenNamen = {"Artikel", "Nummer", "Einzelpreis", "Stückzahl", "Gesamtpreis"};
	
	
	public RechnungsTableModel(List<Warenkorbartikel> aktuelleRechnungsbartikel) {
	
		super();
		
		rechnungsartikel = new Vector<Warenkorbartikel>();
		rechnungsartikel.addAll(aktuelleRechnungsbartikel);
		
	}
	

	public void setRechnungsartikel(List<Warenkorbartikel> aktuelleRechnungsbartikel) {
		rechnungsartikel.clear();
		rechnungsartikel.addAll(aktuelleRechnungsbartikel);
		fireTableDataChanged();
	}


	@Override
	public int getColumnCount() {
		return spaltenNamen.length;
	}

	@Override
	public int getRowCount() {
		return rechnungsartikel.size();
	}
	
	@Override
    public String getColumnName(int col) {
        return spaltenNamen[col];
    }
	
	
	@Override
	public Object getValueAt(int row, int col) {
		Warenkorbartikel gewaehlterRechnungsartikel = rechnungsartikel.get(row);
		
		switch(col) {
		case 0:	
			return gewaehlterRechnungsartikel.getName();
		case 1:	
			return gewaehlterRechnungsartikel.getNummer();
		case 2:	
			return gewaehlterRechnungsartikel.getPreis() +" €";
		case 3:	
			return gewaehlterRechnungsartikel.getStueckzahl();
		case 4:
			return gewaehlterRechnungsartikel.getGesamtpreis() +" €";
		default:
			return null;
		}
	}
	

}

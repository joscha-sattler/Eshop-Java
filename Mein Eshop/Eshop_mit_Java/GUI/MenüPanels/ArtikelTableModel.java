package MenüPanels;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import D_DatenObjekte.Artikel;

public class ArtikelTableModel extends AbstractTableModel {

	
	private List<Artikel> artikel;
	private String[] spaltenNamen = {"Artikelname", "Artikelnummer", "Preis", "Bestand", "Packungsgröße", "Lager"};
	
	
	public ArtikelTableModel(List<Artikel> aktuelleArtikel) {
		super();
		
		artikel = new Vector<Artikel>();
		artikel.addAll(aktuelleArtikel);
		
	}

	
	
	public void setArtikel(List<Artikel> aktuelleArtikel) {
		artikel.clear();
		artikel.addAll(aktuelleArtikel);
		fireTableDataChanged();
	}
	
	
	
	
	
	
	
	
	
	@Override
	public int getColumnCount() {
		return spaltenNamen.length;
		
	}

	@Override
	public int getRowCount() {
		return artikel.size();
	}
	
	@Override
    public String getColumnName(int col) {
        return spaltenNamen[col];
    }

	@Override
	public Object getValueAt(int row, int col) {
		Artikel gewaehlterArtikel = artikel.get(row);
		
		switch(col) {
		case 0:	
			return gewaehlterArtikel.getName();
		case 1:	
			return gewaehlterArtikel.getNummer();
		case 2:	
			return gewaehlterArtikel.getPreis() +" €";
		case 3:	
			return gewaehlterArtikel.getBestand();
		case 4:
			return gewaehlterArtikel.getMassengut();
		case 5:
				if(gewaehlterArtikel.istVerfuegbar()) {
					
					return "auf Lager";
					
				} else
					
					return "nicht auf Lager";
			
	
		default:
			return null;
		}
	} 
}

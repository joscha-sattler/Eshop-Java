package E_Exceptions;

import D_DatenObjekte.Artikel;



public class ArtikelExistiertBereitsException extends Exception {
		
	private Artikel artikel;
	

	public ArtikelExistiertBereitsException(Artikel artikel) {
		super("Ein Artikel mit der Bezeichnung " + artikel.getName() + " und/oder die Artikelnummer " + artikel.getNummer() 
				+ " existiert bereits");
		this.artikel = artikel;
	}
	
	public Artikel getArtikel() {
		return artikel;
	}

}
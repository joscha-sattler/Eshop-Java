package E_Exceptions;

import D_DatenObjekte.Artikel;

public class FalscheStückzahlFürMassenartikelException extends Exception {

	public FalscheStückzahlFürMassenartikelException(Artikel artikel) {
		super("Sie können ihren gewünschten Artikel " + artikel.getName() + " nur in den Vielfachen von " + artikel.getMassengut() + " erwerben. Ihre gewünschte Stückzahl erfüllt nicht diesen Vorraussetzungen"  );
		
	}

	
	
	
}

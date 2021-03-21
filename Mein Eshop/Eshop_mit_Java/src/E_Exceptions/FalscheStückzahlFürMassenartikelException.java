package E_Exceptions;

import D_DatenObjekte.Artikel;

public class FalscheSt�ckzahlF�rMassenartikelException extends Exception {

	public FalscheSt�ckzahlF�rMassenartikelException(Artikel artikel) {
		super("Sie k�nnen ihren gew�nschten Artikel " + artikel.getName() + " nur in den Vielfachen von " + artikel.getMassengut() + " erwerben. Ihre gew�nschte St�ckzahl erf�llt nicht diesen Vorraussetzungen"  );
		
	}

	
	
	
}

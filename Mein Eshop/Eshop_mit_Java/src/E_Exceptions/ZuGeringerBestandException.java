package E_Exceptions;

import D_DatenObjekte.Artikel;


public class ZuGeringerBestandException extends Exception{

	public ZuGeringerBestandException(Artikel artikel) {

		super("Unser Bestand des Artikels " + artikel.getName() + " betr�gt lediglich " + artikel.getBestand() + " ! Damit der Artikel in den Warenkorb gelegt werden kann, darf ihre gew�nschte St�ckzahl unseren Bestand nicht �berschreiten !");
		
	}

	
	
	
}

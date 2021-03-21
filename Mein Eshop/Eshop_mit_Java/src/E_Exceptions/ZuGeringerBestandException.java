package E_Exceptions;

import D_DatenObjekte.Artikel;


public class ZuGeringerBestandException extends Exception{

	public ZuGeringerBestandException(Artikel artikel) {

		super("Unser Bestand des Artikels " + artikel.getName() + " beträgt lediglich " + artikel.getBestand() + " ! Damit der Artikel in den Warenkorb gelegt werden kann, darf ihre gewünschte Stückzahl unseren Bestand nicht überschreiten !");
		
	}

	
	
	
}

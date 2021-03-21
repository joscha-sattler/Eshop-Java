package E_Exceptions;


public class ArtikelExistiertNichtException extends Exception {

	
	public ArtikelExistiertNichtException() {
		super("Der von Ihnen eingegebene Artikelname Existiert nicht!");
	}
	
	
	
}

package E_Exceptions;

import D_DatenObjekte.Warenkorbartikel;

public class BereitsImWarenkorbException extends Exception {

	private Warenkorbartikel wArtikel;
	
	public BereitsImWarenkorbException(Warenkorbartikel wArtikel) {
		
		super("Der Artikel" + wArtikel.getName() + " ist bereits im Warenkorb! Ändern sie die Stückzahl im Warenkorb selbst!");
		
		this.wArtikel = wArtikel;
	}

	
	public Warenkorbartikel getWarenkorbArtikel() {
		return wArtikel;
	}

}

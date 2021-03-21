package E_Exceptions;

public class BenutzerExistiertBereitsException extends Exception {

	public BenutzerExistiertBereitsException() {
		super("Der von Ihnen gwünschte Benutzername und/oder die Kunden-nummer existiert bereits!");
	}

}

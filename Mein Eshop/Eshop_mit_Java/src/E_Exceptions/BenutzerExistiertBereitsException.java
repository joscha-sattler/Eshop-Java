package E_Exceptions;

public class BenutzerExistiertBereitsException extends Exception {

	public BenutzerExistiertBereitsException() {
		super("Der von Ihnen gw�nschte Benutzername und/oder die Kunden-nummer existiert bereits!");
	}

}

package D_DatenObjekte;

public class Person {

//Attribute

	private String name;
	private String nutzername;
	private String passwort;


//Konstruktor(en)
	
	public Person(String name, String nutzername, String passwort) {
	
		this.name = name;
		this.nutzername = nutzername;
		this.passwort = passwort;
	}
	
	public Person() {
		
	}
	
	
//Methoden
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public String getNutzername() {
		return nutzername;
	}
	
	public void setNutzername(String nutzername) {
		this.nutzername = nutzername;
	}
	
	
	
	public String getPasswort() {
		return passwort;
	}
	
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
}

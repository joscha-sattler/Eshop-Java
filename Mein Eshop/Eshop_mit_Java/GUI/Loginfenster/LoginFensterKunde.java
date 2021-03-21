package Loginfenster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import B_Anwendungslogik.Eshop;
import D_DatenObjekte.Kunde;
import Interfaces.EshopInterface;
import KundenMenüFenster.KundenMenueFenster;
import STARTFENSTER.StartFenster;
import net.miginfocom.swing.MigLayout;

public class LoginFensterKunde extends JFrame{

	private static final long serialVersionUID = 8944941537366463548L;

	
	
	private EshopInterface shop;
	
//Attribute
	
	private JLabel 			name, passwort;
	private JTextField 		eingabename;
	private JButton			login, abbruch;
	private JPasswordField 	eingabepasswort;
	private JPanel			mainPanel;
	
	public Kunde k;

//Konstruktor
	
	public LoginFensterKunde() {
		
		super("Login Kunde");
		
		try {
			shop = new Eshop("shop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initComponents();
		initListeners();
		
	}
	
//Methoden
	
	
	
	//Initialisierung und Positionierung der GUI-Elemente
	public void initComponents() {
		
		// Labels
		name = new JLabel("Benutzername: ");
		passwort = new JLabel("Passwort: ");
		
		
		//Textfelder
		eingabename = new JTextField(12);
		eingabepasswort = new JPasswordField(12);
		
		
		//Buttons
		login = new JButton("Login");
		abbruch = new JButton("Abbruch");
		
	
		//Panel
		mainPanel = new JPanel();
		this.add(mainPanel);
		mainPanel.setLayout(new MigLayout("", "20 [] 20", "15 [] 8"));
		
		
		mainPanel.add(name);
		mainPanel.add(eingabename, "wrap 5, pushx, growx");
		
		mainPanel.add(passwort);
		mainPanel.add(eingabepasswort, "pushx, growx, wrap 20");
		
		mainPanel.add(login, "skip, split2");
		mainPanel.add(abbruch);
						
		setVisible(true);
		this.pack();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
}
	
	
	
	//Ereignisverarbeitung
	public void initListeners() {
		this.abbruch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
				new StartFenster();
			}
			
		});
		
		
		
		// NACH LOGIN ZUM KUNDENMENÜ
		this.login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String getPasswort = String.valueOf(eingabepasswort.getPassword());
				
				k = shop.pruefeKundenLogin(eingabename.getText(), getPasswort);
				if (k.getNutzername() != null) {
					exit();
					new KundenMenueFenster(k, shop);
					} else {
                        JOptionPane.showMessageDialog(login, "Falsche Eingabe(n), versuch es erneut!");
                        eingabename.setText("");
                        eingabepasswort.setText("");
                    }
			}
		});
		
	}

	
	public void exit() {
		this.setVisible(false);
		this.dispose();
	}	

}

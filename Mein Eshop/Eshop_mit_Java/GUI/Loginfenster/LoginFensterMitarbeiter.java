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
import Interfaces.EshopInterface;
import MitarbeiterMenüFenster.MitarbeiterMenueFenster;
import STARTFENSTER.StartFenster;
import net.miginfocom.swing.MigLayout;

public class LoginFensterMitarbeiter extends JFrame {

	private static final long serialVersionUID = 8944941537366463548L;

	
//Attribute
	
	private JLabel 			id, passwort;
	private JTextField 		eingabeId;
	private JButton			login, abbruch;
	private JPasswordField 	eingabepasswort;
	private JPanel			mainPanel;
	
	private EshopInterface			shop;

	
//Konstruktor
	
	public LoginFensterMitarbeiter() {
		
		super("Login Mitarbeiter");
		
		try {
			shop = new Eshop("shop");
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		initComponents();
		initListeners();
		
	}
	
//Methoden
	
	

	
	
	//Initialisierung und Positionierung der GUI-Elemente
	public void initComponents() {
		
		// Labels
		id = new JLabel("Admin-ID: ");
		passwort = new JLabel("Passwort: ");
		
		
		//Textfelder
		eingabeId = new JTextField(12);
		eingabepasswort = new JPasswordField(12);
		
		
		//Buttons
		login = new JButton("Login");
		abbruch = new JButton("Abbruch");
		
	
		//Panel
		mainPanel = new JPanel();
		this.add(mainPanel);
		mainPanel.setLayout(new MigLayout("", "20[fill]20", "15[]8"));
		
		
		mainPanel.add(id);
		mainPanel.add(eingabeId, "wrap 5, pushx, growx");
		mainPanel.add(passwort);
		mainPanel.add(eingabepasswort, "pushx, growx, wrap 27");
		mainPanel.add(login, "skip, split2");
		mainPanel.add(abbruch);
		
		
		
		
		
		
		setVisible(true);
	//	setSize(400,200);
		this.pack();
	//	setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
		
		
		
//		GridBagLayout grid = new GridBagLayout(); 
//		this.setLayout(grid);
//		GridBagConstraints c = new GridBagConstraints();
//		
//
//
//		
//		// Erste Zeile //////////////////////////////////
//		
//		c.gridx = 0;
//		c.gridy = 0;
//		this.add(p1, c);
//		
//		this.p1.setLayout(new GridLayout(1,2));
//		this.p1.add(name);
//		this.p1.add(eingabename);
//		
//		
//		
//		
//		
//		
//		
//		// Zweite Zeile //////////////////////////////////
//		
//		c.gridx = 0;
//		c.gridy = 1;
//		c.insets = new Insets(10,0,0,0);  //top padding
//		this.add(p2, c);
//		
//		this.p2.setLayout(new GridLayout(1,2));
//		this.p2.add(passwort);
//		this.p2.add(eingabepasswort);
//		
//		
//		
//		
//		
//		
//		// Dritte Zeile //////////////////////////////////
//		
//		c.gridx = 0;
//		c.gridy = 2;
//		c.insets = new Insets(25,0,0,0);  //top padding
//		this.add(p3, c);
//		
//		this.p3.setLayout(new FlowLayout(FlowLayout.CENTER));
//		this.p3.add(login);
//		this.p3.add(abbruch);
//	
//		

		
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
		
		this.login.addActionListener(new ActionListener() {

	
			
			
			
		
			@Override
			public void actionPerformed(ActionEvent e) {
	
				String getPasswort = String.valueOf(eingabepasswort.getPassword());
			
				if(shop.pruefeMitarbeiterLogin(eingabeId.getText(), getPasswort )) {
					exit();
					new MitarbeiterMenueFenster();
					} else {
                        JOptionPane.showMessageDialog(login, "Falsche Eingabe(n), versuch es erneut!");
                        eingabeId.setText("");
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

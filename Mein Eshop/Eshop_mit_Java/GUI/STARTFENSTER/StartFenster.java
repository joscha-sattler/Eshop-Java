package STARTFENSTER;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Loginfenster.LoginFensterKunde;
import Loginfenster.LoginFensterMitarbeiter;
import RegistrierenFenster.RegistrierenFenster;
import net.miginfocom.swing.MigLayout;

public class StartFenster extends JFrame {

	//Attribute
	
//	private EshopInterface shop;
	
	private JPanel 	mainPanel;
	
	private JButton registrieren, kundenLogin, mitarbeiterLogin;
	
	private JLabel  titel;
	
//Konstruktor
	public StartFenster() {
		super("STARTFENSTER");
		
		
		
		initComponents();
		initListeners();
		
	}	  
	
	
// METHODEN
	
	
	public void initComponents() {
		

// MIGLAYOUT			
		
		//Panel
		mainPanel = new JPanel();
		this.add(mainPanel);
		mainPanel.setLayout(new MigLayout("", "30[]30", ""));
		
		
		//Label
		titel = new JLabel("Willkommen auf der Startseite unseres Eshops!");
		titel.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		
		//Buttons
		registrieren = new JButton("Als neuer Kunde registrieren");
		kundenLogin = new JButton("Als Kunde einloggen");
		mitarbeiterLogin = new JButton("Als Mitarbeiter einloggen");		
		
// KOMPONENTEN AUF PANEL ADDEN ////////////////////////////////////////	
		
		this.mainPanel.add(titel, "wrap 15, span, align center");
		this.mainPanel.add(registrieren, "wrap 5, pushx, growx");
		this.mainPanel.add(kundenLogin, "wrap 25, pushx, growx");
		this.mainPanel.add(mitarbeiterLogin, "wrap 15, pushx, growx");
	
		
// DARSTELLEN	///////////////////////////////////////////////////////	
		
//		setSize(650,650);
//		setResizable(false);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.pack();
		
		

		
		
// ALTE GRIDBAG-LAYOUT	
		
//		  GridBagLayout grid = new GridBagLayout();
//		  this.setLayout(grid);
//		  GridBagConstraints c = new GridBagConstraints();
//		  c.fill = GridBagConstraints.HORIZONTAL;
//		  c.gridy = 0;
//		  
//		  
//		  
//		  c.weighty = 0.9;
//		  // Begrüßung titel = new
//		  JLabel("Willkommen auf der Startseite unseres Eshops!");
//		  c.gridx = 0;
//		  c.gridy = 0;
//		  titel.setFont(new Font("Calibri", Font.PLAIN, 16));
//		  this.add(titel, c);
//		  
//		  
//		  // Button registrieren registrieren = new
//		  JButton("als neuer Kunde Registrieren");
//		  c.gridx = 0;
//		  c.gridy = 2;
//		  this.add(registrieren, c);
//		  
//		  
//		  
//		  
//		  // Button zum Einloggen für den Kunden kunde = new
//		  JButton("Als Kunde einloggen");
//		  c.gridx = 0;
//		  c.gridy = 3;
//		  this.add(kunde, c);
//		  
//		  c.weighty = 5;
//		  
//		  // Button zum Einloggen für Mitarbeiter mitarbeiter = new
//		  JButton("Als Mitarbeiter einloggen");
//		  c.gridx = 0;
//		  c.gridy = 4;
//		  this.add(mitarbeiter, c);		
	}
	
	
	public void initListeners() {
		
		//KUNDENLOGIN ÖFFNEN		
		this.kundenLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginFensterKunde();
				exit();
				
			}
			
		});
		
		//REGISTRIERENFENSTER ÖFFNEN
		this.registrieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RegistrierenFenster();
				exit();
			}
			
		});
		
		
		//MITARBEITERLOGIN ÖFFNEN
		this.mitarbeiterLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginFensterMitarbeiter();
				exit();
			}
			
		});
	
		
		
		
	}
	
	public void exit() {
		this.setVisible(false);
		this.dispose();
	}
	

	
//Main	
	public static void main(String[] args) {
		new StartFenster();
	}
	
}

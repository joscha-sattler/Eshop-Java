package RegistrierenFenster;

import java.awt.Color;
import java.awt.Font;
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
import E_Exceptions.BenutzerExistiertBereitsException;
import Interfaces.EshopInterface;
import STARTFENSTER.StartFenster;
import net.miginfocom.swing.MigLayout;

public class RegistrierenFenster extends JFrame {
	
	
		private EshopInterface shop;
	
//Attribute:
	
		private JLabel 			titel, name, benutzername, passwort, ppasswort, strasse, plz, ort, kundenNr;
		private JTextField 		beingabename, eingabename, bstrasse, bplz, bort, bkundenNr ;
		private JButton			registrieren, abbruch;
		private JPasswordField 	eingabepasswort, eingabepruefung;
		private JPanel			p1;
		
//Konstruktor:
		
		public RegistrierenFenster() {
			
			super("Registrieren");
			
			
			try {
				shop = new Eshop("shop");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			initComponents();
			initListeners();
			
		}
	
		
		
		
//Methoden:
		

//Initialisierung und Positionierung der GUI-Elemente:
		
		public void initComponents() {
			
			// Labels
			
			titel = new JLabel("Zum Registrieren bitte folgende Daten angeben!");
			
			name = new JLabel("Name: ");
			benutzername = new JLabel("Benutzername: ");
			
			passwort = new JLabel("Passwort: ");
			ppasswort = new JLabel("Passwort wiederholen: ");
			
			kundenNr = new JLabel("Kundennummer: ");
			
			strasse = new JLabel("Straße: ");
			plz = new JLabel("Plz: ");
			ort = new JLabel("Wohnort: ");
			
			

			
			//Textfelder
			beingabename = new JTextField(12);
			eingabename = new JTextField(12);
			
			bkundenNr = new JTextField(12);
			
			bstrasse = new JTextField(12);
			bplz = new JTextField(12);
			bort = new JTextField(12);
			
			
			//Passwortfelder
			eingabepasswort = new JPasswordField(12);
			eingabepruefung = new JPasswordField(12);
			
			
			//Buttons
			registrieren = new JButton("Registrieren");
			abbruch = new JButton("Abbruch");

			// Panel
			p1 = new JPanel();
			
			this.add(p1);
			p1.setLayout(new MigLayout(""));
			
	// LABEL - TITEL
			
			p1.add(titel, "wrap 15, align center, span");
			titel.setFont(new Font("Calibri", Font.PLAIN, 16));
			
			
	//NAME ////////////////////////////////////////////
			
			p1.add(name, "wrap");
			p1.add(eingabename, "wrap, growx");
			
			
	//BENUTZERNAME ////////////////////////////////////////////
			
			p1.add(benutzername, "wrap");
			p1.add(beingabename, "wrap, growx");
			
			
	//PASSWORT ////////////////////////////////////////////
			
			p1.add(passwort, "wrap");
			p1.add(eingabepasswort, "wrap, growx");
			
			
	//PASSWORT WIEDERHOLEN ////////////////////////////////
			
			p1.add(ppasswort, "wrap");
			p1.add(eingabepruefung, "wrap, growx");
	
	// KUNDENNUMMER /////////////////////////////////////		

			p1.add(kundenNr, "wrap");
			p1.add(bkundenNr, "wrap, growx");
			
			
	//STRASSE ////////////////////////////////////////////
			
			p1.add(strasse, "wrap");
			p1.add(bstrasse, "wrap, growx");
			
			
	//PLZ ////////////////////////////////////////////
			
			
			p1.add(plz, "wrap");
			p1.add(bplz, "wrap, growx");

			
	//WOHNORT ////////////////////////////////////////////
			
			p1.add(ort, "wrap");
			p1.add(bort, "wrap 15, growx");
			
			
	// BUTTONS ////////////////////////////////////////////
			
			p1.add(registrieren, "split2");
			p1.add(abbruch);
			
			
			
			
//		ALTES GRIDLAYOUT
			
////Layout und Zusammenstellung:			
//			
//			GridLayout g = new GridLayout(19, 1);
//			this.setLayout(g);
//
//
//// TITEL: //////////////////////////////////		
//			
//			this.add(titel);
//			this.add(fuell2);
//			
//			
//// NAME: //////////////////////////////////
//									
//			this.add(name);
//			this.add(eingabename);
//			
//
//// BENUTZERNAME: ///////////////////////////////
//			
//			this.add(benutzername);
//			this.add(beingabename);
//			
//			
//// PASSWORT: //////////////////////////////////
//
//			this.add(passwort);
//			this.add(eingabepasswort);
//			
//			
//// PASSWORT WIEDERHOLEN: ////////////////////////
//
//			this.add(ppasswort);
//			this.add(eingabepruefung);	
//			
//			
//// STRAßE: ////////////////////////			
//			
//			this.add(strasse);
//			this.add(bstrasse);
//			
//		
//// PLZ: ////////////////////////			
//			
//			this.add(plz);
//			this.add(bplz);
//			
//			
//// WOHNORT: ////////////////////////			
//	
//			this.add(ort);
//			this.add(bort);
//			
//			this.add(fuell);
//			
//// BUTTONS: ////////////////////////			
//
//		
//			GridBagLayout gridb = new GridBagLayout();
//			GridBagConstraints c = new GridBagConstraints();
//			
//			this.add(p1);
//			this.p1.setLayout(gridb);
//			
//			c.insets = new Insets (0, 0, 2, 0);
//			c.gridx = 0;
//			c.gridy = 0;
//			this.p1.add(registrieren, c);
//			
//			c.insets = new Insets (0, 35, 2, 0);
//			c.gridx = 1;
//			c.gridy = 0;
//			this.p1.add(abbruch, c);
//			
//			
//			c.gridx = 0;
//			c.gridy = 1;
//			this.p1.add(fuell3, c);
			

//FRAME-EIGENSCHAFTEN: /////////////
			
			this.setVisible(true);
			this.pack();
		//	this.setSize(400,480);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
		}
		
		
		
//Ereignisverarbeitung:
		public void initListeners() {
			
			// Abbruch
			this.abbruch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					exit();
					new StartFenster();
				}
				
			});
			
			//Login
			this.registrieren.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					String name = eingabename.getText();
					String nutzername = beingabename.getText();
					
					
					String getPasswort = String.valueOf(eingabepasswort.getPassword());
					String pruefePasswort = String.valueOf(eingabepruefung.getPassword());
					
					
					String kundenNummer = bkundenNr.getText();
					
					String strasse = bstrasse.getText();
					String plz = bplz.getText();
					String ort = bort.getText();
					
					
					
					if(!name.isEmpty() && !nutzername.isEmpty() && !getPasswort.isEmpty() && !pruefePasswort.isEmpty() && !kundenNummer.isEmpty() && !strasse.isEmpty() && !plz.isEmpty() && !ort.isEmpty()) {	
							
						if(!(getPasswort.equals(pruefePasswort))) {
							eingabepasswort.setText("");							
							passwort.setForeground(Color.red);
							passwort.setText("Passwort: (erneut eingeben)");
							
							eingabepruefung.setText("");
							ppasswort.setText("Passwort wiederholen: (erneut eingeben)");
							ppasswort.setForeground(Color.red);
							
							
							benutzername.setText("Benutzername: ");
							benutzername.setForeground(null);
							
							kundenNr.setText("Kundennummer: ");
							kundenNr.setForeground(null);
							
						} else {
							
							passwort.setForeground(null);
							passwort.setText("Passwort: ");
							
							ppasswort.setForeground(null);
							ppasswort.setText("Passwort wiederholen: ");
						int kundenNummerAlsInt = Integer.parseInt(kundenNummer);
						int plzAlsInt = Integer.parseInt(plz);
						
						try {
							shop.kundenRegistrieren(name, nutzername, getPasswort, kundenNummerAlsInt, strasse , plzAlsInt, ort);
							
							JOptionPane.showMessageDialog(registrieren, "Erfolgreich registriert!");
							
							exit();
							new StartFenster();
							
						} catch (BenutzerExistiertBereitsException e2) {
							benutzername.setText("Benutzername: (existiert bereits) und/oder ↓ ");
							benutzername.setForeground(Color.red);
							
							kundenNr.setText("Kundennummer: (existiert bereits)");
							
							kundenNr.setForeground(Color.red);
							System.err.println(e2.getLocalizedMessage());
						}
						
						
					
					try {
						shop.KundenDatenSichern();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

			
					
					}
				 }
					
				}
				
			});
			
		}

		
		public void exit() {
			this.setVisible(false);
			this.dispose();
		}	

		
	}



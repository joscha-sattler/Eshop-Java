package MitarbeiterMenüPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import D_DatenObjekte.Artikel;
import E_Exceptions.ArtikelExistiertNichtException;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class BestandAendern extends JFrame {

	public interface BestandListener {
		public void onBestandAendern(List<Artikel> artikelListe4);
	}
	
	private EshopInterface shop = null;
	private BestandListener bestandListener = null;
	
	
	
	JPanel  		mainPanel;
	JLabel			name, neuerBestand, titel;
	JTextField		gibName, gibNeuerBestand;
	JButton			bestandAendernBtn, abbruch;
	
	
	
	
	public BestandAendern (EshopInterface eshop, BestandListener listener) {
		
		shop = eshop;
		bestandListener = listener;
		
		
		initComp();
		initListener();
		
	}
	
	
	private void initComp() {
		
		this.mainPanel = new JPanel();
		
		
		this.titel = new JLabel("Bestand ändern!");
		titel.setFont((new Font("Calibri", Font.PLAIN, 16)));
		
		this.name = new JLabel("Artikelname: ");
		this.neuerBestand = new JLabel("Neuer Bestand: ");
		
		this.gibName = 		   new JTextField();
		this.gibNeuerBestand = new JTextField();
		
		this.bestandAendernBtn = new JButton("Ändern!");
		this.abbruch = new JButton("Zurück");
		
		
		
		
		
		this.mainPanel.setLayout(new MigLayout("wrap1"));
		this.add(mainPanel);
		
		this.mainPanel.add(titel, "span, align center");
		
		this.mainPanel.add(name);
		this.mainPanel.add(gibName, "growx, pushx");
		
		this.mainPanel.add(neuerBestand);
		this.mainPanel.add(gibNeuerBestand, "growx, pushx");
		
		this.mainPanel.add(bestandAendernBtn, "wrap 10, growx");
		this.mainPanel.add(abbruch, "growx");
		
		
		this.setSize(230, 230);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
	}
	
	
	private void initListener() {
		
		this.bestandAendernBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String artikelName = gibName.getText();
				String anzahl = gibNeuerBestand.getText();
				
				
				
				if(!artikelName.isEmpty() && !anzahl.isEmpty()) {
					
					int anzahlAlsInt = Integer.parseInt(anzahl);
					
				try {
					shop.artikelBestandaendern(artikelName, anzahlAlsInt);
					java.util.List<Artikel> liste = shop.gibAlleArtikel();
					bestandListener.onBestandAendern(liste);
					JOptionPane.showMessageDialog(bestandAendernBtn, "Erfolgreich Bestand geändert!");
					try {
						shop.artikelDatenSichern();
						System.out.println("Der Bestand vom Artikel " + "'" + gibName.getText() + "'" + " wurde erfolgreich auf " + "'" + gibNeuerBestand.getText() + "'" + " geändert!");
						gibName.setText("");
						gibNeuerBestand.setText("");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

					
					exitFenster();
				} catch (NumberFormatException nfe) {
					System.err.println("Bitte eine Zahl im neuen Bestand eingeben.");
					
				} catch(ArtikelExistiertNichtException a) {
					name.setText("Artikelname: (falsche Eingabe) ");
					name.setForeground(Color.red);
					neuerBestand.setText("Neuer Bestand: ");
					neuerBestand.setForeground(null);
					System.err.println("Ihre Eingabe " + "'" + gibName.getText() + "'" + " existiert nicht in unserer Artikelauswahl!");
				}
				

				
			}  else if(artikelName.isEmpty() && anzahl.isEmpty()) {
				
				name.setText("Artikelname: (falsche Eingabe) ");
				name.setForeground(Color.red);
				
				neuerBestand.setText("Neuer Bestand: (falsche Eingabe) ");
				neuerBestand.setForeground(Color.red);
				
				
			}	else if(!artikelName.isEmpty() && anzahl.isEmpty()) { 
				
				name.setText("Artikelname: ");
				name.setForeground(null);
				
				neuerBestand.setText("Neuer Bestand: (falsche Eingabe) ");
				neuerBestand.setForeground(Color.red);
		
				
				
			} else if(artikelName.isEmpty() && !anzahl.isEmpty()) { 
				
				name.setText("Artikelname: (falsche Eingabe) ");
				name.setForeground(Color.red);
				
				neuerBestand.setText("Neuer Bestand: ");
				neuerBestand.setForeground(null);
				

			}
		}
	});
		
		
		this.abbruch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exitFenster();
				
			}
			
			
		});
		
		
		
	}

	public void exitFenster() {
		this.setVisible(false);
		this.dispose();
	}
	
}

	
	


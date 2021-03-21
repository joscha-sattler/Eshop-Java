package MitarbeiterMenüPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import D_DatenObjekte.Artikel;
import E_Exceptions.ArtikelExistiertBereitsException;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class ArtikelHinzufügenPanel extends JPanel {

	public interface AddArtikelListener {
		public void onArtikelAdded(Artikel artikel);
	}
	
	
		private EshopInterface shop = null;
		private AddArtikelListener addListener = null;
	
	
	//Attribute, Komponenten
		
		private JPanel 				mainPanel;
		
		private JLabel				name, nummer, preis, bestand, mindestbestellwert;
		private	JTextField			gibName, gibNummer, gibPreis, gibBestand, gibMindestbestellwert;
		private	JButton				hinzufuegen;
		
	//Konstruktor	
		public ArtikelHinzufügenPanel(EshopInterface eshop, AddArtikelListener listener) {
			shop = eshop;
			addListener = listener;
			
			initComp();
			initListener();
		}
		
		
	//Methoden
		
		
		private void initComp() {
			
	// ERZEUGEN ////////////////////////////////////////////////////////////////////////////////////////
			
//		Panel
			
			mainPanel = new JPanel();
			
			
//		Labels
			
			name 			   = 	new JLabel("Artikelname: ");
			nummer 			   = 	new JLabel("Artikelnummer: ");
			preis 			   =	new JLabel("Preis: ");
			bestand            = 	new JLabel("Bestand: ");
			mindestbestellwert =	new JLabel("Mindestbestellwert: ");	
					
					
//		Textfelder
			
			gibName   = new JTextField(10);
			gibNummer = new JTextField(10);
			gibPreis = new JTextField(10);
			gibBestand = new JTextField(10);
			gibMindestbestellwert = new JTextField(10);
			
//		Button
			
			hinzufuegen = new JButton("Hinzufügen!");
			
			
//		DARSTELLEN ////////////////////////////////////////////////////////////////////////////////////
			
	//Panel & Layout
			this.add(mainPanel);
			this.mainPanel.setLayout(new MigLayout("wrap1", "5[fill]5", "5[fill]5" ));
			
			
	// Rahmen
			
			TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("Artikel hinzufügen");
			borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
			
			this.mainPanel.setBorder(borderHinzufuegen);
			

	// Name	
			
			this.mainPanel.add(name);
			this.mainPanel.add(gibName);

			
	// Nummer	
			
			this.mainPanel.add(nummer);
			this.mainPanel.add(gibNummer);

			
	// Preis
			
			this.mainPanel.add(preis);
			this.mainPanel.add(gibPreis);
			

	// Bestand
			
			this.mainPanel.add(bestand);
			this.mainPanel.add(gibBestand);
			
	// Mindestbestellwert
			
			this.mainPanel.add(mindestbestellwert);
			this.mainPanel.add(gibMindestbestellwert);
			
	// Button		
			
			this.mainPanel.add(hinzufuegen);
			
			
}
	
	private void initListener() {
		
		this.hinzufuegen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				artikelHinzufuegen();
		
				
			}
			
			
			
		});
		
		
	}
	
	
	private void artikelHinzufuegen() {
		
		String name = gibName.getText();
		String nummer = gibNummer.getText();
		String preis = gibPreis.getText();
		String bestand = gibBestand.getText();
		String mindestbestellwert = gibMindestbestellwert.getText();
		
		
		if(!name.isEmpty() && !nummer.isEmpty() && !preis.isEmpty() && !bestand.isEmpty() && !mindestbestellwert.isEmpty()) {
			
			try {
				int nummerAlsInt = Integer.parseInt(nummer);
				float preisAlsInt = Float.parseFloat(preis);
				int bestandAlsInt = Integer.parseInt(bestand);
				int mindestbestellwertAlsInt = Integer.parseInt(mindestbestellwert);
				
				Artikel artikel = shop.artikelHinzufuegen(name, nummerAlsInt, preisAlsInt, bestandAlsInt, mindestbestellwertAlsInt);
				JOptionPane.showMessageDialog(hinzufuegen, "Artikel erfolgreich hinzugefügt!", "Information", JOptionPane.INFORMATION_MESSAGE);
				
				try {
					shop.artikelDatenSichern();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				gibName.setText("");
				gibNummer.setText("");
				gibPreis.setText("");
				gibBestand.setText("");
				gibMindestbestellwert.setText("");
				
				addListener.onArtikelAdded(artikel);
				
				
			} catch (NumberFormatException nfe) {
				
				System.err.println("Bitte eine Zahl eingeben.");
				JOptionPane.showMessageDialog(hinzufuegen, "Probieren Sie überall Zahlen einzusetzen, wenn erforderlich!", "Error", JOptionPane.ERROR_MESSAGE);
				gibNummer.setText("");
				gibPreis.setText("");
				gibBestand.setText("");
				gibMindestbestellwert.setText("");
				
			} catch (ArtikelExistiertBereitsException a) {
				
				System.err.println(a.getMessage());
				JOptionPane.showMessageDialog(hinzufuegen, "Der Artikelname und/oder die Artikelnummer existiert bereits!", "Error", JOptionPane.ERROR_MESSAGE);
				
				gibNummer.setText("");
				gibName.setText("");
			}
			
		} else { 
			JOptionPane.showMessageDialog(hinzufuegen, "Es müssen alle Felder ausgefüllt sein!", "Error", JOptionPane.ERROR_MESSAGE);
		}
			
		
		
		
	}
	

}

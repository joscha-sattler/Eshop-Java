package MitarbeiterMenüPanels;

import java.awt.Color;
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

import E_Exceptions.ArtikelExistiertNichtException;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class ArtikelLöschenPanel extends JPanel {
	
	public interface AddArtikelListener2 {
		public void onArtikelDelete(String artikel);
	}

	private EshopInterface shop = null;
	private AddArtikelListener2 addListener2 = null;
	
	
	//Attribute, Komponenten
	
		private JPanel 				mainPanel;
		
		private JLabel				name, nummer;
		private	JTextField			gibname, gibnummer;
		private	JButton				loeschen;
		
	//Konstruktor	
		public ArtikelLöschenPanel(EshopInterface eshop, AddArtikelListener2 listener) {
		
			shop = eshop;
			addListener2 = listener;

			
			initComp();
			initListener();
		}
		
		
	//Methoden
		
		
		private void initComp() {
			
	// ERZEUGEN ////////////////////////////////////////////////////////////////////////////////////////
			
//		Panel
			
			mainPanel = new JPanel();
			
			
//		Labels
			
			name = 	 new JLabel("Artikelname: ");
			nummer = new JLabel("Artikelnummer: ");
					
//		Textfelder
			
			gibname   = new JTextField(10);
			gibnummer = new JTextField(10);
			
			
//		Button
			
			loeschen = new JButton("Löschen!");
			
			
//		DARSTELLEN ////////////////////////////////////////////////////////////////////////////////////
			
	//Panel & Layout
			this.add(mainPanel);
			this.mainPanel.setLayout(new MigLayout("wrap1", "5[fill]5", "5[fill]5" ));
			
			
	// Rahmen
			
			TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("Artikel löschen");
			borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
			
			this.mainPanel.setBorder(borderHinzufuegen);
			

	// Name	
			
			this.mainPanel.add(name);
			this.mainPanel.add(gibname);

			
	// Nummer	
			
//			this.mainPanel.add(nummer);
//			this.mainPanel.add(gibnummer);

			
	// Button		
			
			this.mainPanel.add(loeschen);
			
			
		}
		
		private void initListener() {
			
			this.loeschen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					artikelLoeschen();
						
				}
				
				
			});
			
		}
		
		
	private void artikelLoeschen() {
		
		String name1 = gibname.getText();
		
		
		if(!name1.isEmpty()) {
				
			    try {
			    	
					shop.loescheArtikel(name1);
					
					this.name.setText("Artikelname: ");
					this.name.setForeground(null);
				    gibname.setText("");
				    
				    JOptionPane.showMessageDialog(loeschen, "Der Artikel " + "'" + name1 + "'" + " wurde erfolgreich gelöscht!", "Information", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (ArtikelExistiertNichtException e1) {

					this.name.setText("Artikel existiert nicht! ");
					this.name.setForeground(Color.red);
				}

			    
			    try {
					shop.artikelDatenSichern();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
			    addListener2.onArtikelDelete(name1);
			
		} else {
			this.name.setText("Name erforderlich!");
			this.name.setForeground(Color.red);
		}
	}	

}

	


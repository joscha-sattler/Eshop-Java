package WarenkorbPanels;

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

import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;
import E_Exceptions.ArtikelExistiertNichtException;
import E_Exceptions.BereitsImWarenkorbException;
import E_Exceptions.FalscheStückzahlFürMassenartikelException;
import E_Exceptions.ZuGeringerBestandException;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class ArtikelInDenWarenkorbPanel extends JPanel  {

	public interface AddWarenkorbartikelListener {
		public void onWarenkorbartikelAdded(Warenkorbartikel name);
	}
	
	
	private EshopInterface shop = null;
	private AddWarenkorbartikelListener addWarenkorbListener = null;
	private Kunde						kunde;
	
//Attribute, Komponenten	
	
	JPanel mainPanel;
	
	JLabel	   name1, stueckzahl1;
	
	JTextField gibName, gibStueckzahl;
	
	JButton inWarenkorb;
	
	
// Konstruktor(en)	
	public ArtikelInDenWarenkorbPanel(EshopInterface eshop, AddWarenkorbartikelListener warenlistener, Kunde kunde) {
		this.kunde = kunde;

		shop = eshop;
		addWarenkorbListener = warenlistener;
		
		initComp();
		initListener();
		
	}

// Methoden	

	private void initComp() {
		
		 name1       	=	new JLabel("Artikelname: ");
		 stueckzahl1    = 	new JLabel("Stückzahl: ");
		
		 gibName       = 		new JTextField();
		 gibStueckzahl =  		new JTextField();
		
		inWarenkorb	 =		new JButton("In den Warenkorb!");
		
		mainPanel 		 =		new JPanel();
		
		
		
//Layout & rahmen		
		TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("In Warenkorb legen");
		borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
		mainPanel.setBorder(borderHinzufuegen);
		
		this.add(mainPanel);
		mainPanel.setLayout(new MigLayout("wrap1"));

		
// Name	
		
		mainPanel.add(name1);
		mainPanel.add(gibName, "growx");
		
		
// Stückzahl	
		
		mainPanel.add(stueckzahl1);
		mainPanel.add(gibStueckzahl, "growx");
		
		
// Button
		
		mainPanel.add(inWarenkorb);
	
		
		
	}
	
	
	

	private void initListener() {

		this.inWarenkorb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
						warenkorbartikelHinzufuegen();

			}
		});
	}
	
	
	private void warenkorbartikelHinzufuegen() {
		
		String name = gibName.getText();
		String nummer = gibStueckzahl.getText();
		
		
		if(!name.isEmpty() && !nummer.isEmpty()) {

			int nummerAlsInt2 = Integer.parseInt(nummer);
			
			try {
					
			Warenkorbartikel wa =	shop.einkaufen(name, nummerAlsInt2, kunde);
			
			JOptionPane.showMessageDialog(inWarenkorb, "Erfolgreich in den Warenkorb gelegt!");
				
				
				gibName.setText("");
				gibStueckzahl.setText("");
				
				name1.setText("Artikelname: ");
				name1.setForeground(null);
				
				stueckzahl1.setText("Stückzahl: ");
				stueckzahl1.setForeground(null);

				try {
					shop.artikelDatenSichern();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				addWarenkorbListener.onWarenkorbartikelAdded(wa);
				
				
				} catch (ZuGeringerBestandException e) {
					
					name1.setText("Artikelname: ");
					name1.setForeground(null);
					
					stueckzahl1.setText("zu wenig auf Lager!");
					stueckzahl1.setForeground(Color.red);

					
				} catch (FalscheStückzahlFürMassenartikelException e) {
					
					name1.setText("Artikelname: ");
					name1.setForeground(null);
					
					stueckzahl1.setText("Falsche Stückzahl für Massengutartikel!");
					stueckzahl1.setForeground(Color.red);
					
					System.err.println(e.getMessage());
					
				} catch (ArtikelExistiertNichtException e) {
					
					name1.setText("Artikel Existiert nicht!");
					name1.setForeground(Color.red);
					
					stueckzahl1.setText("Stückzahl: ");
					stueckzahl1.setForeground(null);
					
					System.err.println(e.getMessage());

				} catch (BereitsImWarenkorbException wa) {
					
					
					name1.setText("Artikel ist bereits im Warenkorb!");
					name1.setForeground(Color.red);
					
					stueckzahl1.setText("Stückzahl: ");
					stueckzahl1.setForeground(null);
					
				}

		} else if(name.isEmpty() && nummer.isEmpty()) {
			
			name1.setText("Artikelname: (falsche Eingabe)");
			name1.setForeground(Color.red);
			
			stueckzahl1.setText("Stückzahl: (falsche Eingabe)");
			stueckzahl1.setForeground(Color.red);
			
		} else if(!name.isEmpty() && nummer.isEmpty()) {
			
			name1.setText("Artikelname: ");
			name1.setForeground(null);
			
			stueckzahl1.setText("Stückzahl: (falsche Eingabe)");
			stueckzahl1.setForeground(Color.red);
			
		} else if(name.isEmpty() && !nummer.isEmpty()) {
			
			name1.setText("Artikelname: (falsche Eingabe)");
			name1.setForeground(Color.red);
			
			stueckzahl1.setText("Stückzahl: ");
			stueckzahl1.setForeground(null);
			
		}
		
	}


}

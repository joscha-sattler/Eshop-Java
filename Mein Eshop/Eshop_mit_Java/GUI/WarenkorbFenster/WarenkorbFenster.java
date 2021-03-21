package WarenkorbFenster;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import D_DatenObjekte.Artikel;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;
import Interfaces.EshopInterface;
import KundenMenüFenster.KundenMenueFenster;
import RechnungsFenster.RechnungsListeFenster;
import WarenkorbPanels.ArtikelInDenWarenkorbPanel.AddWarenkorbartikelListener;
import WarenkorbPanels.StueckzahlAendern;
import WarenkorbPanels.StueckzahlAendern.StueckzahlAndern;
import WarenkorbPanels.WarenkorbArtikelLoeschenPanel;
import WarenkorbPanels.WarenkorbArtikelLoeschenPanel.DeleteWarenkorbartikel;
import WarenkorbPanels.WarenkorbArtikelLoeschenPanel.DeleteWholeWarenkorb;
import WarenkorbPanels.WarenkorbTablePanel;
import net.miginfocom.swing.MigLayout;

public class WarenkorbFenster extends JFrame implements AddWarenkorbartikelListener, DeleteWarenkorbartikel, DeleteWholeWarenkorb, StueckzahlAndern {

	
	private EshopInterface shop;
	
	
// Attribute, Komponenten
	
	private JPanel 							mglPanel; 
	
	private JScrollPane	                    scrollPane;
	
	private WarenkorbTablePanel 			liste;
	
	private WarenkorbArtikelLoeschenPanel   loeschen;
	
	private StueckzahlAendern				neueStueckzahl;
	
	private Kunde							kunde;
	
//Konstruktor
	
	public WarenkorbFenster(EshopInterface shop, Kunde kunde) {
		super("Warenkorb");
		
		this.kunde = kunde;
		this.shop = shop;

		
		initComp();
		initListener();
		
	}

//Methoden	
	
	private void initComp() {
		
		//FENSTER LAYOUT
		this.setLayout(new BorderLayout());
		
		

		
		
		java.util.List<Warenkorbartikel> warenkorbartikel = shop.gibWarenkorbAus(kunde);
		
		liste = new WarenkorbTablePanel(warenkorbartikel);
		scrollPane = new JScrollPane(liste);
		
		neueStueckzahl = new StueckzahlAendern(shop, kunde, this);
		
		loeschen = new WarenkorbArtikelLoeschenPanel(shop, kunde, this, this);
		
		mglPanel = new WeitereWMöglichkeiten();
		
		this.add(mglPanel, BorderLayout.SOUTH );
		this.add(neueStueckzahl, BorderLayout.WEST);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(loeschen, BorderLayout.EAST);
		
		
		
		
		setLocationRelativeTo(null);
		this.pack();
	//	this.setSize(450, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
			
	}
		
	private void initListener() {
		
	}

	
	
	
	
	
// INNERE KLASSE WEITERE W MÖGLICHKEITEN /////////////////////////////////////	
	
	class WeitereWMöglichkeiten extends JPanel {
		
		// Attribute, Komponenten
		
		private JButton abbruch, kaufen;
		private JPanel mainPanel;
		
		//Konstruktor
			
			public WeitereWMöglichkeiten() {
				super();
				
				this.initComp();
				this.initListener();
				
			}

		//Methoden
			
		private void initComp() {
			
			
			
			kaufen = new JButton("Kostenpflichtig kaufen!");
			abbruch = new JButton("Abbruch");
			
			
			
			mainPanel = new JPanel();
			
			
			TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("Weitere Optionen");
			borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
			this.mainPanel.setBorder(borderHinzufuegen);
			
			this.add(mainPanel);
			this.mainPanel.setLayout(new MigLayout());
			
			
			
			mainPanel.add(kaufen);
			mainPanel.add(abbruch);
			
			
			this.setVisible(true);
			
				
		}
			
		private void initListener() {
			this.abbruch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					exitFenster();
					new KundenMenueFenster(kunde, shop);
					
				}
				
			});
			
			this.kaufen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if(kunde.getWarenkorb().isEmpty()) {
						
						JOptionPane.showMessageDialog(kaufen, "Warenkorb ist leer, kein Kauf möglich!", "Error", JOptionPane.INFORMATION_MESSAGE);
						
					} else {
						jetztkaufen();
					  }
				}
				
				
			});
			
			
		}
		
		public void exitFenster() {
			setVisible(false);
			dispose();
		}
		
		
		private void jetztkaufen () {
			
			
			int optionen = JOptionPane.showConfirmDialog(kaufen, "endgültig kaufen?", "Kaufen", JOptionPane.YES_NO_OPTION); 
			
			// Ja = 0 , Nein = 1
			
			if(optionen == 0) {
			
			new RechnungsListeFenster(shop, kunde);
			
			shop.warenkorbloeschen(kunde);
			
			dispose();
			}
			
		}
		
		
		
		
		
	}






@Override
public void onWarenkorbartikelAdded(Warenkorbartikel warenkorbartikel) {
	java.util.List<Warenkorbartikel> wArtikel = shop.gibWarenkorbAus(kunde);
	liste.updateWArtikelListeName(wArtikel);
	
	
	
}

@Override
public void onDeleteWarenkorbartikel(String wArtikelname) {
	java.util.List<Warenkorbartikel> wArtikel = shop.gibWarenkorbAus(kunde);
	liste.updateWArtikelListeName(wArtikel);
}

@Override
public void OnDeleteWholeWarenkorb() {
	java.util.List<Warenkorbartikel> wArtikel = shop.gibWarenkorbAus(kunde);
	liste.updateWArtikelListeName(wArtikel);
}

@Override
public void OnAenderStueckzahl() {
	
	java.util.List<Warenkorbartikel> wArtikel = shop.gibWarenkorbAus(kunde);
	liste.updateWArtikelListeName(wArtikel);
	
}


	
}

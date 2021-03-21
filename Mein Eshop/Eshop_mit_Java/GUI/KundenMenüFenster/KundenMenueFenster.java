package KundenMenüFenster;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import B_Anwendungslogik.Eshop;
import D_DatenObjekte.Artikel;
import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;
import Interfaces.EshopInterface;
import MenüPanels.ArtikelSuchen_SortierenPanel;
import MenüPanels.ArtikelSuchen_SortierenPanel.SortierenNameListener;
import MenüPanels.ArtikelSuchen_SortierenPanel.SortierenNummerListener;
import MenüPanels.ArtikelSuchen_SortierenPanel.SortierenPreisListener;
import MenüPanels.ArtikelSuchen_SortierenPanel.SuchergebnisListener;
import MenüPanels.ArtikelTablePanel;
import STARTFENSTER.StartFenster;
import WarenkorbFenster.WarenkorbFenster;
import WarenkorbPanels.ArtikelInDenWarenkorbPanel;
import WarenkorbPanels.ArtikelInDenWarenkorbPanel.AddWarenkorbartikelListener;
import WarenkorbPanels.WarenkorbTablePanel;
import net.miginfocom.swing.MigLayout;

public class KundenMenueFenster extends JFrame 
implements SuchergebnisListener, SortierenNameListener, SortierenNummerListener, SortierenPreisListener, AddWarenkorbartikelListener {

	private EshopInterface shop;
	
// Attribute , Komponenten	
	
	private ArtikelSuchen_SortierenPanel 	artikelSuchen_SortierenPanel;
	private ArtikelTablePanel				tablePanel;
	private WeitereMöglichkeitenPanel		mglPanel;
	private JScrollPane						scrollPane;
	private ArtikelInDenWarenkorbPanel		inWarenkorb;
	private WarenkorbTablePanel   			wTablePanel;
	private Kunde							kunde;

	
// Konstruktor(en)	
	public KundenMenueFenster(Kunde k, EshopInterface shop) {
		super("Kunden-Menü");
		this.kunde = k;
		this.shop = shop;
		
		initComp();
		initListener();
	}
	
	
// Methoden
	
	
	
	private void initComp() {
		


		
		artikelSuchen_SortierenPanel = new ArtikelSuchen_SortierenPanel(shop, this, this, this, this);

		mglPanel = new WeitereMöglichkeitenPanel();

		inWarenkorb = new ArtikelInDenWarenkorbPanel(shop, this, kunde);
		
		
		// LISTE 
		
		java.util.List<Artikel> artikel = shop.gibAlleArtikel();
		tablePanel = new ArtikelTablePanel(artikel);
		scrollPane = new JScrollPane(tablePanel);	
		

		java.util.List<Warenkorbartikel> warenkorbartikel = shop.gibWarenkorbAus(kunde);
		wTablePanel = new WarenkorbTablePanel(warenkorbartikel);

		
		
//Darstellen
		
		this.add(artikelSuchen_SortierenPanel, BorderLayout.NORTH);
		this.add(inWarenkorb, BorderLayout.WEST);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(mglPanel, BorderLayout.SOUTH);
		
		
		
		this.setSize(640, 480);
//		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void initListener() {

	}
	



	public void exit() {
		this.setVisible(false);
		this.dispose();
		
	}
	
	

	
	
	
// INNERE KLASSE WEITERE MÖGLICHKEITEN ////////////////////////////////////////////
	

	class WeitereMöglichkeitenPanel extends JPanel {

//Attribute, Komponenten
		
		private JButton warenkorbAnzeigen, ausloggen;	
		private JPanel  mainPanel;
		
		
		
		
		public WeitereMöglichkeitenPanel() {
			super();
			
			this.initComp();
			this.initListener();
		}
	 
		
		private void initComp() {
			
// ERZEUGEN /////////////////////////////////////////////////////////////////////////////////////

// Panel
			mainPanel = 		new JPanel();
			
// Buttons			
			
			warenkorbAnzeigen = new JButton("Warenkorb anzeigen");
			ausloggen = 		new JButton ("Ausloggen");


			

// DARSTELLEN ////////////////////////////////////////////////////////////////////////////////////
			

// mainPanel & Layout
			
			TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("Weitere Optionen");
			borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
			this.mainPanel.setBorder(borderHinzufuegen);
			
			mainPanel.setLayout(new MigLayout());
			
			this.add(mainPanel);

			
// Buttons	
			
			
			mainPanel.add(warenkorbAnzeigen);
			mainPanel.add(ausloggen);

			
			this.setVisible(true);
			
			
		}
		
		public void initListener() {
			this.ausloggen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					int optionen = JOptionPane.showConfirmDialog(ausloggen, "wirklich ausloggen?", "Logout", JOptionPane.YES_NO_OPTION); 
					
					// Ja = 0 , Nein = 1
					
					if(optionen == 0) {
					
					if(!kunde.getWarenkorb().isEmpty()) {	
					shop.bestandUpdaten(kunde);	
					shop.warenkorbloeschen(kunde);	
					}
					try {
						shop.artikelDatenSichern();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					exitFenster();
					new StartFenster();
					}
					
				}
				
			});
			
			this.warenkorbAnzeigen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					exitFenster();
					new WarenkorbFenster(shop, kunde);
					
				}
				
			});
			
		}
		
		
		public void exitFenster() {
			setVisible(false);
			dispose();
		}
		
	}
		



	@Override
	public void suchergebnis(List<Artikel> artikelListe) {
		tablePanel.updateArtikelListeName(artikelListe);
		
	}


	@Override
	public void sortierenNameergebnis(List<Artikel> artikelListe2) {
		tablePanel.updateArtikelListeName(artikelListe2);
		
	}


	@Override
	public void sortierenNummergebnis(List<Artikel> artikelListe3) {
		tablePanel.updateArtikelListeNummer(artikelListe3);
		
	}


	@Override
	public void onWarenkorbartikelAdded(Warenkorbartikel warenkorbartikel) {
		java.util.List<Warenkorbartikel> wartikel = shop.gibWarenkorbAus(kunde);
		wTablePanel.updateWArtikelListeName(wartikel);
		
		java.util.List<Artikel> artikel = shop.gibAlleArtikel();
		tablePanel.updateArtikelListeName(artikel);
		
	}


	@Override
	public void sortierenPreisergebnis(List<Artikel> artikelListe4) {
		tablePanel.updateArtikelListePreis(artikelListe4);
		
	}	
	
}

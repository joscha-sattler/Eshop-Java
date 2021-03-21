package MitarbeiterMen�Fenster;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
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

import ArtikelVerlauf.AbfrageFenster;
import B_Anwendungslogik.Eshop;
import D_DatenObjekte.Artikel;
import EreignisVerlauf.VerlaufFenster;
import Interfaces.EshopInterface;
import Men�Panels.ArtikelSuchen_SortierenPanel;
import Men�Panels.ArtikelSuchen_SortierenPanel.SortierenNameListener;
import Men�Panels.ArtikelSuchen_SortierenPanel.SortierenNummerListener;
import Men�Panels.ArtikelSuchen_SortierenPanel.SortierenPreisListener;
import Men�Panels.ArtikelSuchen_SortierenPanel.SuchergebnisListener;
import Men�Panels.ArtikelTablePanel;
import MitarbeiterMen�Panels.ArtikelHinzuf�genPanel;
import MitarbeiterMen�Panels.ArtikelHinzuf�genPanel.AddArtikelListener;
import MitarbeiterMen�Panels.ArtikelL�schenPanel;
import MitarbeiterMen�Panels.ArtikelL�schenPanel.AddArtikelListener2;
import MitarbeiterMen�Panels.BestandAendern;
import MitarbeiterMen�Panels.BestandAendern.BestandListener;
import STARTFENSTER.StartFenster;
import net.miginfocom.swing.MigLayout;

public class MitarbeiterMenueFenster extends JFrame
	implements AddArtikelListener, AddArtikelListener2, SuchergebnisListener, SortierenNameListener, SortierenPreisListener, SortierenNummerListener, BestandListener{


	
	
	private EshopInterface shop;
	
//Attribute , Komponenten	
	
	private ArtikelSuchen_SortierenPanel 	artikelSuchen_SortierenPanel;
	private ArtikelTablePanel				tablePanel;
	private WeitereM�glichkeitenPanel		mglPanel;
	private JScrollPane	                    scrollPane;
	private ArtikelHinzuf�genPanel			artikelHinzuf�genPanel;
	private ArtikelL�schenPanel				artikelL�schenPanel;
	
//Konstruktor(en)	
	public MitarbeiterMenueFenster() {
		super("Mitarbeiter-Men�");
		
		try {
			shop = new Eshop("shop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initComp();
		initListener();
	}
	
	
//Methoden
	
	
	
	private void initComp() {
		

		artikelSuchen_SortierenPanel = new ArtikelSuchen_SortierenPanel(shop, this, this, this, this);

		mglPanel = new WeitereM�glichkeitenPanel();

		artikelHinzuf�genPanel = new ArtikelHinzuf�genPanel(shop, this);
		
		artikelL�schenPanel = new ArtikelL�schenPanel(shop, this);
		
		// LISTE --> geht nicht NullPointerException
		
		java.util.List<Artikel> artikel = shop.gibAlleArtikel();
		tablePanel = new ArtikelTablePanel(artikel);
		scrollPane = new JScrollPane(tablePanel);	
		

		
		
		//Darstellen
		
		this.add(artikelSuchen_SortierenPanel, BorderLayout.NORTH);
		
		this.add(artikelHinzuf�genPanel, BorderLayout.WEST);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.add(mglPanel, BorderLayout.SOUTH);
		
		this.add(artikelL�schenPanel, BorderLayout.EAST);
		
		
		
		
//		this.pack();
		this.setSize(830, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
private void initListener() {

	}
	
	
// "Normale" Methoden	

	public void exit() {
		this.setVisible(false);
		this.dispose();
		
	}
	
	

// Updates der Tabelle
	
// Artikel hinzuf�gen
	
	@Override
	public void onArtikelAdded(Artikel artikel) {
		java.util.List<Artikel> artikel1 = shop.gibAlleArtikel();
		tablePanel.updateArtikelListeName(artikel1);
		
	}


//Artikel l�schen
	
	@Override
	public void onArtikelDelete(String artikelname) {
		java.util.List<Artikel> artikel1 = shop.gibAlleArtikel();
		tablePanel.updateArtikelListeName(artikel1);
		
	}


// Artikel suchen	
	
	@Override
	public void suchergebnis(List<Artikel> artikelListe) {
		tablePanel.updateArtikelListeName(artikelListe);
		
	}


	
// Artikel nach Namen sortieren
	
	@Override
	public void sortierenNameergebnis(List<Artikel> artikelListe2) {
		tablePanel.updateArtikelListeName(artikelListe2);
		
	}

//Artikel nach Nummer sortieren
	
	@Override
	public void sortierenNummergebnis(List<Artikel> artikelListe3) {
		tablePanel.updateArtikelListeNummer(artikelListe3);
		
	}
		

// Artikelbestand �ndern
	
	@Override
	public void onBestandAendern(List<Artikel> artikelListe4) {
		tablePanel.updateArtikelListeName(artikelListe4);
		
	}
		
	

	@Override
	public void sortierenPreisergebnis(List<Artikel> artikelListe4) {
		tablePanel.updateArtikelListePreis(artikelListe4);
		
	}


	
	
	
	// INNERE KLASSE WEITERE M�GLICHKEITEN ////////////////////////////////////////////
	

	class WeitereM�glichkeitenPanel extends JPanel {

	//Attribute, Komponenten
		
		private JButton bestandAendern, gesamtVerlauf, einzelVerlauf, ausloggen;	
		private JPanel  mainPanel;
		
		
		
		
		public WeitereM�glichkeitenPanel() {
			super();
			
			this.initComp();
			this.initListener();
		}
	 
		
		private void initComp() {
			
			
			bestandAendern = new JButton("Bestand �ndern");
			
			einzelVerlauf = new JButton("Artikel Ereignisse ausgeben");
			gesamtVerlauf = new JButton("Alle Ereignisse ausgeben");
			
			ausloggen = new JButton ("Ausloggen");

			mainPanel = new JPanel();
			
			TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("Weitere Optionen");
			borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
			
			this.mainPanel.setBorder(borderHinzufuegen);
			this.add(mainPanel);
			mainPanel.setLayout(new MigLayout());
			
		
			mainPanel.add(einzelVerlauf);
			mainPanel.add(gesamtVerlauf);
			mainPanel.add(bestandAendern);
			mainPanel.add(ausloggen);

			
			setVisible(true);
			
			
		}
		
		public void initListener() {
			this.ausloggen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					int optionen = JOptionPane.showConfirmDialog(ausloggen, "wirklich ausloggen?", "Logout", JOptionPane.YES_NO_OPTION); 
					
					// Ja = 0 , Nein = 1
					
					if(optionen == 0) {
					exitFenster();
					new StartFenster();
					}
					
					
				}
				
			});
			
			this.bestandAendern.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new BestandAendern(shop, MitarbeiterMenueFenster.this);
				}
				
				
			});
			
			
			this.gesamtVerlauf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						shop.sichereVerlauf();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new VerlaufFenster(shop);				
				}
				
				
			});
			
			
			this.einzelVerlauf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					new AbfrageFenster(shop);
					
				}
				
			});
			
						
		}
		
		
		public void exitFenster() {
			setVisible(false);
			dispose();
		}
		
	}

	
}


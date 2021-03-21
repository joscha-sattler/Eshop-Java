package MenüPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import D_DatenObjekte.Artikel;
import E_Exceptions.ArtikelExistiertNichtException;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class ArtikelSuchen_SortierenPanel extends JPanel {

	public interface SuchergebnisListener {
		public void suchergebnis(List<Artikel> artikelListe);
	}

	public interface SortierenNameListener {
		public void sortierenNameergebnis(List<Artikel> artikelListe2);
	}

	public interface SortierenNummerListener {
		public void sortierenNummergebnis(List<Artikel> artikelListe3);
	}

	public interface SortierenPreisListener {
		public void sortierenPreisergebnis(List<Artikel> artikelListe4);
	}

	private EshopInterface shop = null;
	private SuchergebnisListener suchListener = null;
	private SortierenNameListener sortierenNameListener = null;
	private SortierenNummerListener sortierenNummerListener = null;
	private SortierenPreisListener sortierenPreisListener = null;

//Attribute, Komponenten

	private JLabel artikelSuchen, sortieren;
	private JTextField suchbegriff;
	private JButton suchButton, nameSortieren, nummerSortieren, preisSortieren;
	private JPanel mainPanel;

	public ArtikelSuchen_SortierenPanel(EshopInterface eshop, SuchergebnisListener listener, SortierenNameListener listener2,
			SortierenNummerListener listener3, SortierenPreisListener listener4) {

		shop = eshop;
		suchListener = listener;
		sortierenNameListener = listener2;
		sortierenNummerListener = listener3;
		sortierenPreisListener = listener4;

		initComp();
		initListener();
	}

	private void initComp() {

		artikelSuchen = new JLabel("Artikel suchen: ");
		artikelSuchen.setFont((new Font("Calibri", Font.PLAIN, 16)));

		sortieren = new JLabel("Sortieren nach: ");
		sortieren.setFont((new Font("Calibri", Font.PLAIN, 16)));

		suchbegriff = new JTextField("Artikelname eingeben...", 25);

		suchButton = new JButton("Suchen!");

		nameSortieren = new JButton("Alphabet");
		nummerSortieren = new JButton("Nummer");
		preisSortieren = new JButton("Preis");

		mainPanel = new JPanel();

		
//Rahmen --> sieht nicht so gut aus, oder?		
		
//		TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("Suchen & Sortieren");
//		borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
//		
//		this.mainPanel.setBorder(borderHinzufuegen);

		this.add(mainPanel);
		this.mainPanel.setLayout(new MigLayout());

		this.mainPanel.add(artikelSuchen, "align left");
		this.mainPanel.add(suchbegriff);
		this.mainPanel.add(suchButton, "wrap 10");

		this.mainPanel.add(sortieren);
		this.mainPanel.add(nameSortieren, "split");
		this.mainPanel.add(nummerSortieren);
		this.mainPanel.add(preisSortieren);

		this.setVisible(true);

		// ALTE GRIDBAG-LAYOUT

//		  GridBagLayout grid = new GridBagLayout();
//		  GridBagConstraints c = new GridBagConstraints();
//		  c.fill = GridBagConstraints.HORIZONTAL;
//		  c.anchor = GridBagConstraints.EAST;

//		  this.setLayout(grid);
//		  this.add(panel1, c);
//		  
//		  this.panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
//		  
//		  c.gridx = 0;
//		  c.gridy = 0;
//
//
//		  
//		  this.panel1.add(artikelSuchen);
//		  
//
//		  this.panel1.add(suchbegriff);
//		  
//
//		  this.panel1.add(suchButton);
//		  
//		  
//  
//		  
//		  //ZWEITE ZEILE
//		  
//		  
//		  c.gridx = 0;
//		  c.gridy = 1;
//		  this.add(panel2, c);
//		  this.panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
//		  
//		  this.panel2.add(sortieren);
//		  this.panel2.add(nameSortieren);
//		  this.panel2.add(nummerSortieren);

	}

	private void initListener() {

		this.suchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String eingabebegriff = suchbegriff.getText();
				// String eingabenummer = suchbegriff.getText();

				java.util.List<Artikel> suchErgebnis;

				if (eingabebegriff.isEmpty() || eingabebegriff.contentEquals("Artikelname eingeben...")) {

					suchErgebnis = shop.gibAlleArtikel();

					artikelSuchen.setText("Artikel suchen: ");
					artikelSuchen.setForeground(null);

					suchListener.suchergebnis(suchErgebnis);

				} else {

					try {

						suchErgebnis = shop.sucheNachArtikelName(eingabebegriff);

						artikelSuchen.setText("Artikel suchen: ");
						artikelSuchen.setForeground(null);

						suchListener.suchergebnis(suchErgebnis);

					} catch (ArtikelExistiertNichtException e1) {

						artikelSuchen.setText("Artikel Existiert nicht!");
						artikelSuchen.setForeground(Color.red);
					}

				}

			}
		});

		this.nameSortieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				java.util.List<Artikel> name = shop.gibAlleArtikel();

				sortierenNameListener.sortierenNameergebnis(name);
			}

		});

		this.nummerSortieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				java.util.List<Artikel> nummer = shop.gibAlleArtikel();

				sortierenNummerListener.sortierenNummergebnis(nummer);
			}

		});

		this.preisSortieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				java.util.List<Artikel> preis = shop.gibAlleArtikel();

				sortierenPreisListener.sortierenPreisergebnis(preis);
			}

		});

	}

}

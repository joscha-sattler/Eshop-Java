package RechnungsFenster;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import D_DatenObjekte.Kunde;
import D_DatenObjekte.Warenkorbartikel;
import Interfaces.EshopInterface;
import KundenMenüFenster.KundenMenueFenster;
import net.miginfocom.swing.MigLayout;

public class RechnungsListeFenster extends JFrame {

	private EshopInterface shop;
	private Kunde kunde;
	
	private RechnungsTablePanel rechnung;	
	private JPanel				endpreisPanel;
	
	private JButton				beenden;
	
	private JLabel				kundendaten, name, nummer, strasse, plz, ort,
								getname, getnummer, getstrasse, getplz, getort;
	
	
	public RechnungsListeFenster(EshopInterface shop, Kunde kunde) {
		super("Rechnung");
		
		this.kunde = kunde;
		this.shop = shop;
		
		initComp();
		initListener();
		
	}
	
	
	private void initComp() {
		
		java.util.List<Warenkorbartikel> rechnungsListe = shop.rechnungErzeugen(kunde);
		
		rechnung = new RechnungsTablePanel(rechnungsListe);
		JScrollPane scrollPane = new JScrollPane(rechnung);
		
		beenden = new JButton("Zum Kunden-Menü");
		
		JLabel	endpreis = new JLabel("Endpreis: " + shop.kaufenFertig(kunde) + " €");
		JLabel  titel = new JLabel("Vielen Dank " + "'" + kunde.getName() + "'" +  " für Ihren Einkauf bei uns!");
		
		
		kundendaten = new JLabel("Ihre ausgewählten Artikel werden an folgende Daten gesendet!");
		name		= new JLabel("Name des Käufers:  ");
		nummer		= new JLabel("Kundennummer:  ");
		strasse		= new JLabel("Straße:  ");
		plz			= new JLabel("Postleitzahl:  ");
		ort			= new JLabel("Ort: ");
		
		
		getname			= new JLabel(kunde.getName());
		getnummer		= new JLabel(kunde.getKundenNr() + "");
		getstrasse		= new JLabel(kunde.getStrasse());
		getplz			= new JLabel(kunde.getPlz() + "");
		getort			= new JLabel(kunde.getWohnort());
		
		
		
		
		endpreisPanel = new JPanel();

		endpreisPanel.setLayout(new MigLayout());
		
		
		this.add(endpreisPanel);
		
		
		
		endpreisPanel.add(titel, "wrap 15, span, align center");
		titel.setFont((new Font("Calibri", Font.PLAIN, 16)));
		endpreisPanel.add(scrollPane, "wrap 10, span, growx, pushx");
		endpreisPanel.add(endpreis, "wrap 20, span, align right");
		
		
		
		
		// Lieferadresse etc.
		
		endpreisPanel.add(kundendaten, "wrap 20, span, align left");
		
		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		
		kundendaten.setFont((new Font("Calibri", Font.PLAIN, 16).deriveFont(fontAttributes)));
		

		
		endpreisPanel.add(name, "split, align left");
		name.setFont((new Font("Calibri", Font.PLAIN, 16)));
		endpreisPanel.add(getname, "wrap 15");
		
		endpreisPanel.add(nummer,  "split, align left");
		nummer.setFont((new Font("Calibri", Font.PLAIN, 16)));
		endpreisPanel.add(getnummer, "wrap 15");
		
		endpreisPanel.add(strasse, "split, align left");
		strasse.setFont((new Font("Calibri", Font.PLAIN, 16)));
		endpreisPanel.add(getstrasse, "wrap 15");
		
		endpreisPanel.add(plz,  "split, align left");
		plz.setFont((new Font("Calibri", Font.PLAIN, 16)));
		endpreisPanel.add(getplz, "wrap 15");
		
		endpreisPanel.add(ort,  "split, align left");
		ort.setFont((new Font("Calibri", Font.PLAIN, 16)));
		endpreisPanel.add(getort, "wrap 30");
		
		endpreisPanel.add(beenden, "span, align left");

		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		
		
		
	}
	
	
	private void initListener() {
		this.beenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			exitFenster();
			
			new KundenMenueFenster(kunde, shop);
				
			}
			
			
		});
	}
	
	
	private void exitFenster() {
		setVisible(false);
		dispose();
	}
	

}

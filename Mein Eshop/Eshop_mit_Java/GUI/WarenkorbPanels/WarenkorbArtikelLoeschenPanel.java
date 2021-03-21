package WarenkorbPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import D_DatenObjekte.Kunde;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class WarenkorbArtikelLoeschenPanel extends JPanel {
	
	public interface DeleteWarenkorbartikel {
			public void onDeleteWarenkorbartikel(String wArtikelname);
	}
	
	public interface DeleteWholeWarenkorb {
		public void OnDeleteWholeWarenkorb();
	}
	
	private JPanel							mainPanel, p1, p2;
	
	private JLabel							name;
	private JTextField						gibname;
	
	private JButton							artikelLoeschen, allesLoeschenBtn;
	
	private DeleteWarenkorbartikel 			loescheWArtikel;
	private DeleteWholeWarenkorb			loescheAlleWArtikel;
	private EshopInterface 							shop;
	private Kunde							kunde;
	
	public WarenkorbArtikelLoeschenPanel(EshopInterface shop,Kunde kunde, DeleteWarenkorbartikel loescheListener, DeleteWholeWarenkorb loescheallesListener) {
		super();
		
		this.kunde = kunde;
		this.shop = shop;
		this.loescheWArtikel = loescheListener;
		this.loescheAlleWArtikel = loescheallesListener;
		
		initComp();
		initListener();
	}

	
	
	private void initComp() {
		
		
		mainPanel = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		
		
		name = new JLabel("Artikelname: ");
		gibname = new JTextField();
		
		allesLoeschenBtn = new JButton("Alles Löschen!");
		
		
		artikelLoeschen = new JButton("Löschen!");
		
		
		TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("löschen");
		borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
		this.p1.setBorder(borderHinzufuegen);
		
		this.add(mainPanel);
		this.mainPanel.setLayout(new MigLayout("wrap1"));
		
		
		mainPanel.add(p1);
		mainPanel.add(p2);

		this.p1.setLayout(new MigLayout("wrap1"));
		
		p1.add(name);
		p1.add(gibname, "growx");
		p1.add(artikelLoeschen, "wrap 20, growx");
		p1.add(allesLoeschenBtn);

		
		
	}
	
	
	private void initListener() {
		
		this.artikelLoeschen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				warenkobrArtikelLöschen();
				
			}
			
		});
		
		this.allesLoeschenBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				shop.bestandUpdaten(kunde);
				shop.warenkorbloeschen(kunde);
				
				try {
					shop.artikelDatenSichern();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				loescheAlleWArtikel.OnDeleteWholeWarenkorb();
				
			}
			
		});
		
	}
	
	
	private void warenkobrArtikelLöschen() {
		
		String name = gibname.getText();
		
		if(!name.isEmpty()) {
			
			shop.loescheArtikelWarenkorb(name, kunde);
			
			try {
				shop.artikelDatenSichern();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gibname.setText("");
			loescheWArtikel.onDeleteWarenkorbartikel(name);
			
		}
		

		
	}

}

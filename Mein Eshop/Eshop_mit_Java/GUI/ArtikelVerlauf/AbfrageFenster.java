	package ArtikelVerlauf;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import ArtikelVerlauf.VerlaufSuchenPanel.SuchListener;
import Interfaces.EshopInterface;

public class AbfrageFenster extends JFrame implements SuchListener {

	

	private VerlaufSuchenPanel suchenPanel;
	private ArtikelVerlaufListe verlaufliste;
	
	
	private Interfaces.EshopInterface shop = null;
	
	
	public AbfrageFenster(EshopInterface shop) {
		super("Artikel-Verlauf-Ausgabe");
		
		this.shop = (Interfaces.EshopInterface) shop;
		
		initComp();
		initListener();
	}





	private void initComp() {
		
		
		suchenPanel = new VerlaufSuchenPanel(shop, this);
		
		java.util.List<String> test = shop.bestandslisteSortieren("");
		
		
		verlaufliste = new ArtikelVerlaufListe(test);
		
		
		this.setLayout(new BorderLayout());
		
		this.add(suchenPanel, BorderLayout.NORTH);
		this.add(verlaufliste, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(400, 450);
		
	}

	
	
	private void initListener() {

	}





	@Override
	public void ergebnis(List<String> suchergebnis) {

		verlaufliste.updateArtikelVerlaufList(suchergebnis);
	}
	

	
}

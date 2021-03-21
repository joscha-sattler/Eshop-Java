package ArtikelVerlauf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ArtikelVerlauf.VerlaufSuchenPanel.SuchListener;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class VerlaufSuchenPanel extends JPanel {
	
	public interface SuchListener {
		public void ergebnis(List<String> verlaufergebnis);
}
	
	private EshopInterface shop = null;
//	private Eshop shop;
	private SuchListener suchlistener;
	
	
	private JPanel		mainPanel;
	private JLabel		name;
	private JTextField	gibname;
	private JButton		ausgeben;
	
	
	public VerlaufSuchenPanel(EshopInterface shop, SuchListener listener) {
		super();
		
		this.shop = shop;
		this.suchlistener = listener;
		
		initComp();
		initListener();
		
		
	}



	private void initComp() {
		
		
mainPanel = new JPanel();
		
		name = new JLabel("Artikelname: ");
		
		gibname = new JTextField(10);
		
		ausgeben = new JButton("Ausgeben!");

		
		this.add(mainPanel);
		
		mainPanel.setLayout(new MigLayout());
		
		mainPanel.add(name, "split");
		mainPanel.add(gibname);
		mainPanel.add(ausgeben, "wrap");
		
		
	
		
		
	}



	private void initListener() {

		
		this.ausgeben.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String artikelname = gibname.getText();
				java.util.List<String> ergebnis2 = null;
				
				if(!artikelname.isEmpty()) {
					
				ergebnis2 = shop.bestandslisteSortieren(artikelname);
	
					
				} else if(artikelname.isEmpty()) {
					
				ergebnis2 =	shop.bestandslisteSortieren("");
					
					
					
				}
				
				suchlistener.ergebnis(ergebnis2);
				
			}
			
			
		});		
	}


}

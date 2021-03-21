package EreignisVerlauf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class VerlaufFenster extends JFrame {

	
	private VerlaufListModel   verlaufsListe;
	
	private JButton			   beenden;
	
	private JPanel			   mainPanel;
	
	private JScrollPane       scrollpane;
	
	private EshopInterface			   shop;
	
//Konstruktor	
	public VerlaufFenster(EshopInterface shop) {
		super("EreignisVerlauf");
		
		this.shop = shop;
		
		initComp();
		initListener();
		
		
		
	}

//Methoden
	
	
	private void initComp() {
		
		mainPanel = new JPanel();

		beenden = new JButton("Beenden");
		
		
		java.util.List<String> verlauf = shop.gibVerlaufAus();
		verlaufsListe = new VerlaufListModel(verlauf);
		scrollpane = new JScrollPane(verlaufsListe);
		
		
		this.add(mainPanel);
		mainPanel.setLayout(new MigLayout());
		
		
		mainPanel.add(scrollpane,"wrap, growy, pushy");
		
		mainPanel.add(beenden, "span, align center");
		
		
		this.setVisible(true);
		this.setSize(550, 400);
		this.setLocationRelativeTo(null);
		
	}

	
	private void initListener() {
		this.beenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
			}
			
			
		});
		
	}
	
}

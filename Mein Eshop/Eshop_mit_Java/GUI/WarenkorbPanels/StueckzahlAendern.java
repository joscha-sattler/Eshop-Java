package WarenkorbPanels;

import java.awt.Color;
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
import E_Exceptions.ArtikelExistiertNichtException;
import E_Exceptions.FalscheStückzahlFürMassenartikelException;
import E_Exceptions.ZuGeringerBestandException;
import Interfaces.EshopInterface;
import net.miginfocom.swing.MigLayout;

public class StueckzahlAendern extends JPanel {

public interface StueckzahlAndern {
	public void OnAenderStueckzahl();
}
	
	
	private JPanel		mainPanel;
	private JLabel		name, neueStueckzahl;
	private JTextField  gibname, gibstueckzahl;
	private JButton    	aendernBtn;
	
	private EshopInterface shop;
	private Kunde kunde;
	
	private StueckzahlAndern idk;
	
	
	public StueckzahlAendern(EshopInterface shop, Kunde kunde, StueckzahlAndern listener) {
		
		this.shop = shop;
		this.kunde = kunde;
		this.idk = listener;
		
		initComp();
		initListener();
		
	}

	
	private void initComp() {
		
		mainPanel = new JPanel();
		
		name = new JLabel("Artikelname: ");
		neueStueckzahl = new JLabel("Neue Stückzahl: ");
		
		gibname = new JTextField();
		gibstueckzahl = new JTextField();
		
		aendernBtn = new JButton("Ändern!");
		
		
		TitledBorder borderHinzufuegen = BorderFactory.createTitledBorder("Neue Stückzahl");
		borderHinzufuegen.setTitleFont((new Font("Calibri", Font.PLAIN, 16)));
		this.mainPanel.setBorder(borderHinzufuegen);
		
		this.add(mainPanel);
		mainPanel.setLayout(new MigLayout("wrap1"));
		
		mainPanel.add(name);
		mainPanel.add(gibname, "growx");
		
		mainPanel.add(neueStueckzahl);
		mainPanel.add(gibstueckzahl, "growx");
		
		mainPanel.add(aendernBtn, "growx");
		
	}
	
	private void initListener() {
		
		this.aendernBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aenderStueckzahl();
				
				try {
					shop.artikelDatenSichern();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
		});
		
	}
	
	
	
	private void aenderStueckzahl() {
		
		if(!gibname.getText().isEmpty() && !gibstueckzahl.getText().isEmpty()) {
			
			int gibstueckzahlAlsInt = Integer.parseInt(gibstueckzahl.getText());
			
			try {
				shop.stueckzahlAendern(gibname.getText(), gibstueckzahlAlsInt, kunde);
				
				gibname.setText("");
				gibstueckzahl.setText("");
				
				
				name.setText("Artikelname: ");
				name.setForeground(null);
				
				neueStueckzahl.setForeground(null);
				neueStueckzahl.setText("Neue Stückzahl: ");
				
				idk.OnAenderStueckzahl();
				
			} catch (ZuGeringerBestandException e) {
				
				neueStueckzahl.setText("Zu wenig auf Lager");
				neueStueckzahl.setForeground(Color.red);
				
				name.setText("Artikelname: ");
				name.setForeground(null);

				
			} catch (ArtikelExistiertNichtException a) {
				
				name.setText("Artikel existiert nicht");
				name.setForeground(Color.red);
				
				neueStueckzahl.setText("Neue Stückzahl: ");
				neueStueckzahl.setForeground(null);
				
			} catch (FalscheStückzahlFürMassenartikelException a) {
				
				neueStueckzahl.setText("Massengutartikel! Falsche Stückzahl!");
				neueStueckzahl.setForeground(Color.red);
				
				name.setText("Artikelname: ");
				name.setForeground(null);
				
			}
			
		} 	else if(gibname.getText().isEmpty() && gibstueckzahl.getText().isEmpty()) {
			
			name.setText("Artikelname: (falsche Eingabe)");
			name.setForeground(Color.red);
			
			neueStueckzahl.setText("Neue Stückzahl: (falsche Eingabe)");
			neueStueckzahl.setForeground(Color.red);
			
			}
		}
		
}
	


package ArtikelVerlauf;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ArtikelVerlaufListe extends JList<String> {

	public ArtikelVerlaufListe(java.util.List<String> artikelverlauf) {
		super();
		
		DefaultListModel<String> listModel = new DefaultListModel<>();

		setModel(listModel);

		updateArtikelVerlaufList(artikelverlauf);
		
	}
	
	
	
	public void updateArtikelVerlaufList(java.util.List<String> artikelverlauf) {


		// ListModel von JList holen und ...
		DefaultListModel<String> listModel = (DefaultListModel<String>) getModel();
		// ... Inhalt aktualisieren
		listModel.clear();
		for (String s: artikelverlauf) {		
			listModel.addElement(s);
		}		
	}
	
	
	
	

}

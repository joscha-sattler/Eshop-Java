package EreignisVerlauf;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class VerlaufListModel extends JList<String> {

	public VerlaufListModel(java.util.List<String> verlauf) {
		

		// ListModel erzeugen ...
		DefaultListModel<String> listModel = new DefaultListModel<>();
		// ... bei JList "anmelden" und ...
		setModel(listModel);
		
		 updateVerlaufList(verlauf);

	}

	
	
	public void updateVerlaufList(java.util.List<String> verlauf) {


		// ListModel von JList holen und ...
		DefaultListModel<String> listModel = (DefaultListModel<String>) getModel();
		// ... Inhalt aktualisieren
		listModel.clear();
		for (String s: verlauf) {		
			listModel.addElement(s);
		}		
	}

}

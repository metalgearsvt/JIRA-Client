package processors;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import listeners.ListListener;

public class ArrayListToJList {
	public static JList<String> asString(ArrayList<String> al) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(int i = 0; i < al.size(); i++) {
			listModel.addElement(al.get(i));
		}
		JList<String> list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.addListSelectionListener(new ListListener());
		return list;
	}
	public static JList<String> asStringNoLsn(ArrayList<String> al, int display) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(int i = 0; i < al.size(); i++) {
			listModel.addElement(al.get(i));
		}
		JList<String> list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(display);
		return list;
	}
	
	public static JList<String> asStringNoLsn(ArrayList<String> al) {
		return asStringNoLsn(al, 8);
	}
}

package processors;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ArrayListToJList {
	public static JList<String> asString(ArrayList<String> al) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(int i = 0; i < al.size(); i++) {
			listModel.addElement(al.get(i));
		}
		JList<String> list = new JList<String>(listModel);
		return list;
	}
}

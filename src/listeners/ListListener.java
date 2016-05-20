package listeners;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import api.Group;
import api.Search;
import gui.Jira;
import processors.ArrayListToJList;

public class ListListener implements ListSelectionListener {

	public static JList<String> project;
	
	public ListListener() {
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			ListListener.project = (JList<String>)e.getSource();
			Search srch = new Search();
			JList<String> li = ArrayListToJList.asStringNoLsn(srch.getIssuesFromProject(Group.keyFromGroup(project.getSelectedValue())), 12);
			JScrollPane jsp = new JScrollPane(li);
			ExpandProj(jsp);
			Jira.refresh();
		}
	}
	
	public void ExpandProj(JScrollPane jsp) {
		
		Jira.infoPanel.removeAll();
		
		GridBagLayout gL = new GridBagLayout();
		
		Jira.infoPanel.setLayout(gL);
		gL.layoutContainer(Jira.infoPanel);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 0.1;
		c.insets = new Insets(0, 10, 0, 0);
		
		Jira.infoPanel.add(jsp, c);
		Jira.infoPanel.repaint();
		Jira.refresh();
	}

}

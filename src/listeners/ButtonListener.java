package listeners;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JScrollPane;

import api.Group;
import api.Search;
import gui.Jira;
import processors.ArrayListToJList;

public class ButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(NavButtonListener.go)) {
			
			Search srch = new Search();
			JList<String> li = ArrayListToJList.asStringNoLsn(srch.getIssuesFromProject(Group.keyFromGroup(NavButtonListener.projectList.getSelectedValue())));
			JScrollPane jsp = new JScrollPane(li);
			
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

}

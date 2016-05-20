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
			JList<String> li = ArrayListToJList.asStringNoLsn(srch.getIssuesFromProject(Group.keyFromGroup(NavButtonListener.projectList.getSelectedValue())), 24);
			li.addListSelectionListener(new IssueListListener());
			
			JScrollPane jsp = new JScrollPane(li);
			
			Jira.infoPanel.removeAll();
			
			GridBagLayout gL = new GridBagLayout();
			
			Jira.infoPanel.setLayout(gL);
			gL.layoutContainer(Jira.infoPanel);
			
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.NORTHWEST;
			c.fill = GridBagConstraints.NONE;
			c.insets = new Insets(0, 10, 0, 0);
			
			Jira.infoPanel.add(jsp, c);
			
			GridBagConstraints g = new GridBagConstraints();
			g.anchor = GridBagConstraints.NORTHWEST;
			g.weightx = 0.7;
			g.gridwidth = GridBagConstraints.REMAINDER;
			g.fill = GridBagConstraints.BOTH;
			
			Jira.infoPanel.add(Jira.infDisP, g);
			
			Jira.infoPanel.repaint();
			Jira.refresh();
		}
	}

}

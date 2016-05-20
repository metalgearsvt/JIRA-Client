package listeners;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import api.Issue;
import gui.Jira;

public class IssueListListener implements ListSelectionListener {

	public IssueListListener() {
		super();
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			JList<String> issueList = (JList<String>)e.getSource();
			String iss = Issue.stringIssue(issueList.getSelectedValue());
			
			JTextArea tb = new JTextArea(iss);
			tb.setOpaque(false);
			
			Jira.infDisP.removeAll();
			GridBagLayout p = new GridBagLayout();
			Jira.infDisP.setLayout(p);
			p.layoutContainer(Jira.infDisP);
			
			GridBagConstraints h = new GridBagConstraints();
			h.anchor = GridBagConstraints.NORTH;
			h.weighty = 0.01;
			
			Jira.infDisP.add(tb, h);
			Jira.refresh();
		}
	}

}

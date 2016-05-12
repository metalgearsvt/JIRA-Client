package listeners;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

import api.Group;
import gui.Jira;
import processors.ArrayListToJList;

public class NavButtonListener implements ActionListener {

	private JComboBox<String> jBox;
	public static JList<String> projectList;
	public static JButton go;
	
	public NavButtonListener(JComboBox<String> jBox) {
		this.jBox = jBox;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(jBox.getSelectedItem().toString().equals(Jira.DEFAULT_ACTIONS[1])) {
			
			Jira.infoPanel.removeAll();
			
			projectList = ArrayListToJList.asStringNoLsn(Group.groupRetrieval());
			JScrollPane projectPane = new JScrollPane(projectList);
			GridBagLayout gL = new GridBagLayout();
			
			Jira.infoPanel.setLayout(gL);
			gL.layoutContainer(Jira.infoPanel);
			
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.weightx = 0.1;
			c.insets = new Insets(0, 10, 0, 0);
			
			Jira.infoPanel.add(projectPane, c);
			
			go = new JButton("->");
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 0.9;
			go.addActionListener(new ButtonListener());
			Jira.infoPanel.add(go, c);
			
			Jira.infoPanel.repaint();
			Jira.refresh();
		}
	}

}

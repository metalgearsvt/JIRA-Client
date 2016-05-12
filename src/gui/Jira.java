package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.NavButtonListener;
import oAuth.Auth;
import oAuth.PassManager;

public class Jira {
	
	public static String title = "JIRA Manager";
	public static String iconLocation = "/img/favicon.png";
	
	public static Status statBar = new Status();
	
	public static final String[] NO_ACTIONS = { "Invalid Credentials" };
	public static final String[] DEFAULT_ACTIONS = { "Select an action...", "Projects..." };
	
	public static JFrame mainFrame = new JFrame();
	public static JComboBox<String> navBox = new JComboBox<String>(NO_ACTIONS);
	public static JPanel displayPanel = new JPanel();
	public static JPanel infoPanel = new JPanel();
	
	public static Auth auth = new Auth();
	public static PassManager pMan = new PassManager();
	
	public static int w = 750;
	public static int h = 500;
	
	public static void main(String[] args) {
		
		boolean loggedIn = auth.createSession(pMan.getUsername(), pMan.getPassword());
		
		GridBagLayout gridLay = new GridBagLayout();
		
		displayPanel.setLayout(gridLay);
		
		mainFrame.setSize(w, h);
		mainFrame.setPreferredSize(new Dimension(w, h));
		mainFrame.setTitle(title);
		
		URL iconURL = Jira.class.getClass().getResource(iconLocation);
		ImageIcon icon = new ImageIcon(iconURL);
		mainFrame.setIconImage(icon.getImage());
		
		mainFrame.getContentPane().add(statBar, BorderLayout.SOUTH);

		if(loggedIn) {
			navBox = new JComboBox<String>(DEFAULT_ACTIONS);
		}
		
		mainFrame.getContentPane().add(displayPanel);
		
		JPanel actionBarPanel = new JPanel();
		
		GridBagLayout actionBarLay = new GridBagLayout();
		GridBagConstraints con = new GridBagConstraints();
		actionBarPanel.setLayout(actionBarLay);
		actionBarLay.layoutContainer(actionBarPanel);
		
		con.anchor = GridBagConstraints.NORTHWEST;
		con.fill = GridBagConstraints.NONE;
		con.weightx = 0.1;
		con.weighty = 1;
		con.insets = new Insets(10, 10, 0, 0);
		
		actionBarPanel.add(navBox, con);
		
		JButton goButton = new JButton("Go ->");
		goButton.addActionListener(new NavButtonListener(navBox));
		con.weightx = 0.9;
		con.insets = new Insets(10, 0, 0, 0);
		actionBarPanel.add(goButton, con);
		
		GridBagConstraints mainItems = new GridBagConstraints();

		mainItems.anchor = GridBagConstraints.NORTH;
		mainItems.fill = GridBagConstraints.HORIZONTAL;
		mainItems.gridwidth = GridBagConstraints.REMAINDER;
		mainItems.weightx = 1;
		mainItems.weighty = 0.2;
		
		displayPanel.add(actionBarPanel, mainItems);
		
		mainItems.anchor = GridBagConstraints.FIRST_LINE_START;
		mainItems.fill = GridBagConstraints.HORIZONTAL;
		mainItems.gridwidth = GridBagConstraints.REMAINDER;
		mainItems.weightx = 1;
		mainItems.weighty = 0.8;
		
		displayPanel.add(infoPanel, mainItems);
		gridLay.layoutContainer(displayPanel);
		
		mainFrame.setVisible(true);
		
		Menu topMenu = new Menu(true);
		
		mainFrame.setJMenuBar(topMenu.getMenu());
		mainFrame.pack();
		mainFrame.repaint();
		mainFrame.getContentPane().repaint();
				
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public static void loggedIn() {
		navBox.setModel(new JComboBox<String>(DEFAULT_ACTIONS).getModel());
		navBox.repaint();
		mainFrame.pack();
		mainFrame.repaint();
		mainFrame.getContentPane().repaint();
	}
	
	public static void refresh() {
		mainFrame.pack();
		mainFrame.repaint();
		mainFrame.getContentPane().repaint();
	}
}

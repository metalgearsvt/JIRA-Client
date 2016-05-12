package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import oAuth.Auth;
import oAuth.PassManager;

public class Menu implements ActionListener {
	private JMenuBar menuBar;
	private final String QUIT = "Quit";
	private final String LOGIN = "Login";
	private final String SAVE = "Save";
	private final String CHECK = "Check Connection";
	private JTextField usr = new JTextField();
	private JPasswordField pas = new JPasswordField();
	private JFrame loginFrame;
	
	public Menu() {
		menuBar = new JMenuBar();
	}
	
	public Menu(boolean buildDefault) {
		menuBar = new JMenuBar();
		if(buildDefault) {
			this.buildDefault();
		}
	}
	
	public void addMenuItem(String name, ArrayList<String> subItems) {
		JMenu menu = new JMenu(name);
		menuBar.add(menu);
		
		for(int i = 0; i < subItems.size(); i++) {
			JMenuItem menuItem = new JMenuItem(subItems.get(i));
			menuItem.addActionListener(this);
			menu.add(menuItem);
		}
	}
	
	public JMenuBar getMenu() {
		return menuBar;
	}
	
	private void buildDefault() {
		ArrayList<String> options = new ArrayList<String>();
		ArrayList<String> authMenu = new ArrayList<String>();
		
		options.add("Refresh");
		options.add(QUIT);
		
		authMenu.add(LOGIN);
		authMenu.add(CHECK);
		authMenu.add("Delete Credentials");
		
		this.addMenuItem("File", options);
		this.addMenuItem("Auth", authMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(QUIT)) {
			System.exit(0);
		} else 
		if (e.getActionCommand().equals(LOGIN)) {
			loginFrame = new JFrame(LOGIN);
			loginFrame.setSize(200, 75);
			loginFrame.setAutoRequestFocus(true);
			loginFrame.setMinimumSize(new Dimension(250, 150));
			loginFrame.setResizable(false);
			
			loginFrame.setLayout(new GridLayout(3, 2));
			
			loginFrame.add(new JLabel("Username: "));
			loginFrame.add(usr);
			loginFrame.add(new JLabel("Password: "));
			loginFrame.add(pas);
			
			JButton conf = new JButton(SAVE);
			conf.addActionListener(this);
			
			loginFrame.add(new JLabel(""));
			loginFrame.add(conf);
			
			loginFrame.pack();
			loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			loginFrame.setVisible(true);
		} else
		if(e.getActionCommand().equals(SAVE)) {
			savePassword();
		} else
		if(e.getActionCommand().equals(CHECK)) {
			Jira.statBar.setMessage("Loading...");
			PassManager pman = new PassManager();
			Auth auth = new Auth();
			if(auth.createSession(pman.getUsername(), pman.getPassword())) {
				Jira.statBar.setMessage("Credentials are valid!");
				Jira.loggedIn();
			} else {
				Jira.statBar.setMessage("Invalid credentials!");
			}
		}
	}
	
	private void savePassword() {
		String u = usr.getText();
		String p = "";
		char[] pw = pas.getPassword();
		for(int i = 0; i < pw.length; i++) {
			p += pw[i];
		}
		PassManager pMan = new PassManager();
		pMan.setUsername(u);
		pMan.setPassword(p);
		pMan.save();
		loginFrame.dispose();
		Jira.statBar.setMessage("Saved login info for " + u);
	}
	
	
}

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import oAuth.Auth;
import oAuth.PassManager;

public class Jira {
	public static String title = "JIRA Manager";
	public static String iconLocation = "/img/favicon.png";
	public static Status statBar = new Status();
	public static int w = 750;
	public static int h = 500;
	
	public static void main(String[] args) {
		
		Auth auth = new Auth();
		PassManager pMan = new PassManager();
		
		auth.createSession(pMan.getUsername(), pMan.getPassword());
		
		JFrame mainFrame = new JFrame();
		
		mainFrame.setSize(w, h);
		mainFrame.setPreferredSize(new Dimension(w, h));
		mainFrame.setTitle(title);
		
		URL iconURL = Jira.class.getClass().getResource(iconLocation);
		ImageIcon icon = new ImageIcon(iconURL);
		mainFrame.setIconImage(icon.getImage());
		
		mainFrame.getContentPane().add(statBar, BorderLayout.SOUTH);
		
		mainFrame.setVisible(true);
		
		Menu topMenu = new Menu(true);
		
		mainFrame.setJMenuBar(topMenu.getMenu());
		mainFrame.pack();
		mainFrame.repaint();
		mainFrame.getContentPane().repaint();
				
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}

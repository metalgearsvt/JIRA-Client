package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Status extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel msg = new JLabel("");

	public Status() {
		super();
		super.setPreferredSize(new Dimension(Jira.w, 16));
		super.setBorder(new BevelBorder(BevelBorder.LOWERED));
		super.setLayout(null);
		super.add(msg);
		msg.setBounds(new Rectangle(4, 0, 300, 16));
		msg.setFont(new Font("Serif", Font.PLAIN, 10));
		setMessage("");
	}
	
	public void setMessage(String s) {
		msg.setText(s);
		msg.repaint();
		super.repaint();
	}
}

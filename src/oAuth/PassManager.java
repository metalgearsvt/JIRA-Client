package oAuth;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import gui.Jira;

public class PassManager implements Serializable {

	private static final long serialVersionUID = -7497757625750001283L;
	private final String fileName = "jiradat";
	private String username = "";
	private String password = "";
	
	public PassManager() {
		boolean loaded = this.load();
		if(!loaded) {
			this.save();
		}
	}
	
	public boolean load() {
		try {
			FileInputStream fi = new FileInputStream(fileName);
			ObjectInputStream oi = new ObjectInputStream(fi);
			PassManager temp = (PassManager)oi.readObject();
			this.setPassword(temp.getPassword());
			this.setUsername(temp.getUsername());
			oi.close();
			Jira.statBar.setMessage("Loaded credentials.");
			return true;
		} catch(Exception e) {
			Jira.statBar.setMessage("Failed to load login credentials!");
		}
		return false;
	}
	
	public boolean save() {
		try {
			FileOutputStream fo = new FileOutputStream(fileName);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(this);
			oo.close();
			Jira.statBar.setMessage("Loaded credentials.");
			return true;
		} catch(Exception e) {
			Jira.statBar.setMessage("Failed to save login credentials!");
			e.getMessage();
		}
		return false;
	}
	
	public void setUsername(String username) {
		this.username = username;
		this.save();
	}
	
	public void setPassword(String password) {
		this.password = password;
		this.save();
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}

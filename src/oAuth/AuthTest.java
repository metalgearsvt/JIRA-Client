package oAuth;

import java.util.ArrayList;
import java.util.Scanner;

import api.Issue;
import api.Search;

@SuppressWarnings("unused")
public class AuthTest {

	public static void main(String[] args) {
		Auth auth = new Auth();
		
		PassManager pman = new PassManager();
		auth.createSession(pman.getUsername(), pman.getPassword());
		
		Search search = new Search();
		ArrayList<String> keys = search.getKeysFromSearch(search.perfectProcureView(auth));
		for(int i = 0; i < keys.size(); i++) {
			Issue.printIssue(auth, keys.get(i));
		}
	}
	
}

package oAuth;

import java.util.Scanner;

public class PassManagerTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		PassManager pman = new PassManager();
		Scanner in = new Scanner(System.in);
		System.out.print("User: ");
		String u = in.nextLine();
		System.out.print("Pass: ");
		String p = in.nextLine();
		pman.setUsername(u);
		pman.setPassword(p);
		if(pman.save()) {
			System.out.println("Credentials saved successfully!");
		} else {
			System.out.println("Error saving credentials.");
		}
	}
}

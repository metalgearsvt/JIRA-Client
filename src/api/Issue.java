package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import constants.Constants;
import gui.Jira;
import oAuth.GetClient;

public class Issue {
	public static void printIssue(String key) {
		String issueJson = GetClient.getResponse(Jira.auth, Constants.ISSUE_API + key);
		Gson gson = new Gson();
		String summary = gson.fromJson(issueJson, JsonObject.class).getAsJsonObject("fields").getAsJsonObject("summary").get("value").getAsString();
		System.out.println(key + ":\t" + summary);
	}
	
	public static String stringIssue(String key) {
		String issueJson = GetClient.getResponse(Jira.auth, Constants.ISSUE_API + key);
		Gson gson = new Gson();
		String summary = gson.fromJson(issueJson, JsonObject.class).getAsJsonObject("fields").getAsJsonObject("summary").get("value").getAsString();
		return summary;
	}
}

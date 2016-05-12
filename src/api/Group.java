package api;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import constants.Constants;
import gui.Jira;
import oAuth.GetClient;

public class Group {
	static Gson gson = new Gson();
	public static ArrayList<String> groupRetrieval() {
		ArrayList<String> projects = new ArrayList<String>();
		String json = GetClient.getResponse(Jira.auth, Constants.GROUP_API);
		JsonArray projList = gson.fromJson(json, JsonArray.class);
		for(int i = 0; i < projList.size(); i++) {
			projects.add(projList.get(i).getAsJsonObject().get("name").getAsString());
		}
		return projects;
	}
	
	public static String keyFromGroup(String group) {
		String json = GetClient.getResponse(Jira.auth, Constants.GROUP_API);
		JsonArray projList = gson.fromJson(json, JsonArray.class);
		for(int i = 0; i < projList.size(); i++) {
			if(projList.get(i).getAsJsonObject().get("name").getAsString().equals(group)) {
				return projList.get(i).getAsJsonObject().get("key").getAsString();
			}
		}
		return "";
	}
}

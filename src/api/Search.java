package api;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import constants.Constants;
import gui.Jira;
import oAuth.Auth;
import oAuth.GetClient;

public class Search {
	public static final String PERFPRO_VIEW = "?jql=project%20%3D%20PP%20AND%20resolution%20%3D%20Unresolved%20ORDER%20BY%20severity%20ASC%2C%20priority%20DESC%2C%20created%20DESC";
	public static final String PROJ_FILTER_PRE = "?jql=project%20%3D%20";
	public static final String PROJ_FILTER_SUF = "%20ORDER%20BY%20severity%20DESC";
	public Search() {
		
	}
	
	public ArrayList<String> getIssuesFromProject(String proj) {
		String URL = Constants.SEARCH_API + PROJ_FILTER_PRE + proj + PROJ_FILTER_SUF;
		return getKeysFromSearch(GetClient.getResponse(Jira.auth, URL));
	}
	
	public String perfectProcureView(Auth auth) {
		String searchURL = Constants.SEARCH_API + PERFPRO_VIEW;
		return GetClient.getResponse(auth, searchURL);
	}
	
	public ArrayList<String> getKeysFromSearch(String json) {
		ArrayList<String> keys = new ArrayList<String>();
		Gson gson = new Gson();
		JsonArray issues = gson.fromJson(json, JsonObject.class).get("issues").getAsJsonArray();
		for(int i = 0; i < issues.size(); i++) {
			keys.add(issues.get(i).getAsJsonObject().get("key").getAsString());
		}
		return keys;
	}
	
	public void printArrayKeys(ArrayList<String> keys) {
		for(int i = 0; i < keys.size(); i++) {
			System.out.println(keys.get(i));
		}
	}
}

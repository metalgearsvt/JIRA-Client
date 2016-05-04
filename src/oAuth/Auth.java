package oAuth;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import constants.Constants;

public class Auth {
	private String jsonResponse = "";
	private String sessionId = "";
	private String cookieName = "";
	
	private void parseResponse() {
		Gson gson = new Gson();
		JsonObject jsono = gson.fromJson(this.jsonResponse, JsonObject.class);
		this.sessionId = jsono.get("session").getAsJsonObject().get("value").getAsString();
		this.cookieName = jsono.get("session").getAsJsonObject().get("name").getAsString();
	}
	
	public boolean createSession(String username, String password) {
		HttpClient httpC = HttpClients.createDefault();
		HttpPost httpP = new HttpPost(Constants.AUTH_URL);

		try {
			StringEntity postArgs = new StringEntity("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");
			httpP.addHeader(Constants.H_1, Constants.H_2);
			httpP.setEntity(postArgs);
			HttpResponse res = httpC.execute(httpP);
			HttpEntity ent = res.getEntity();
			if(ent != null) {
				InputStream inStream = ent.getContent();
				try {
					int i;
					String jsonEle = "";
					while((i = inStream.read()) !=-1 ) {
						char c = (char)i;
						jsonEle += c;
					}
					this.jsonResponse = jsonEle;
					if(res.getStatusLine().getStatusCode() == 200) {
						this.parseResponse();
						return true;
					} else {
						System.err.println("Could not log in!");
					}
					
				} finally {
					inStream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getSessionId() {
		return this.sessionId;
	}
	
	public String getCookieName() {
		return this.cookieName;
	}
	
	public String getCookie() {
		return this.cookieName + "=" + this.sessionId;
	}
	
	public HttpGet createGetClient(String URL) {
		HttpGet httpG = new HttpGet(URL);
		httpG.addHeader("Cookie", this.getCookie());
		httpG.addHeader(Constants.H_1, Constants.H_2);
		return httpG;
	}
}

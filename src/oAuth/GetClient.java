package oAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class GetClient {
	public static String getResponse(Auth auth, String URL) {
		HttpClient httpC = HttpClients.createDefault();
		HttpGet httpG = auth.createGetClient(URL);
		try {
			HttpResponse res = httpC.execute(httpG);
			HttpEntity ent = res.getEntity();
			if(ent != null) {
				InputStream inStream = ent.getContent();
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
					String responseLine = "";
					String jsonEle = "";
					while((responseLine = br.readLine()) != null) {
						jsonEle += responseLine;
					}
					return jsonEle;
				} finally {
					inStream.close();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}

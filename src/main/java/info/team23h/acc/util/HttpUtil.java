package info.team23h.acc.util;

import info.team23h.acc.vo.common.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

@Slf4j
public class HttpUtil {

	/**
	 * https request
	 *
	 * @param httpRequest the http request
	 * @return the hash map
	 */
	public static HashMap<String, Object> httpsRequest(HttpRequest httpRequest) throws IOException, ParseException {

		String url = httpRequest.getUrl();
		HashMap<String,Object> param = httpRequest.getParam();
		if(!param.isEmpty()){
			url = url + "?";
			for(String s : param.keySet()){
				if(param.get(s)!= null){
					String value = String.valueOf(param.get(s));
					url +=s+"="+value+"&";
				}
			}
			url = url.substring(0,url.length()-1);
		}
		HttpsURLConnection conn = (HttpsURLConnection) new URL(url.trim()).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String inputLine;
		while((inputLine = in.readLine()) != null){
			sb.append(inputLine);
		}
		in.close();
		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		jsonObject = (JSONObject) parser.parse(sb.toString());
		return new HashMap<>(jsonObject);
	}
}

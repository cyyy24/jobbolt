package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class rpcHelper {
	// Writes a JSONArray to http response.
		public static void writeJsonArray(HttpServletResponse response, JSONArray array, HttpServletRequest request) throws IOException{
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
			response.setHeader("Access-Control-Allow-Credentials", "true");
			out.println(array);
			out.close();
		}

	    // Writes a JSONObject to http response.
		public static void writeJsonObject(HttpServletResponse response, JSONObject obj, HttpServletRequest request) throws IOException {		
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
			response.setHeader("Access-Control-Allow-Credentials", "true");
			PrintWriter out = response.getWriter();
			out.print(obj);
			out.close();
		}
		
		
		public static JSONObject readJSONObject(HttpServletRequest request) {
			StringBuilder sBuilder = new StringBuilder();
			try(BufferedReader reader = new BufferedReader(request.getReader())){
				String line = null;
				while((line = reader.readLine()) != null) {
					sBuilder.append(line);
				}
				return new JSONObject(sBuilder.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return new JSONObject();
		}
	

}

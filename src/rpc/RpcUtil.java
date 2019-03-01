package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class RpcUtil {
	public static void writeJSONArray(HttpServletResponse response, JSONArray array,HttpServletRequest request) throws IOException{
		response.setContentType("application/json"); // content type
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true"); // set access permission origin list
		PrintWriter writer = response.getWriter();
		
		try {
			writer.print(array.toString(4));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		writer.close();
	}
	
	public static void writeJSONObject(HttpServletResponse response, JSONObject obj, HttpServletRequest request) throws IOException{
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true"); // set access permission origin list, "*" means no limit
		PrintWriter writer = response.getWriter();
		
		try {
			writer.print(obj.toString(4));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		writer.close();
	}
	
	public static JSONObject readJSONObject(HttpServletRequest request) {
		StringBuilder sBuilder = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}
			return new JSONObject(sBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}
}

package rpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Job;

/**
 * Servlet implementation class SavedJob
 */
@WebServlet("/save")
public class SavedJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavedJob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session == null) {
			response.setStatus(403);
			return;
		}
		
		if (session.getAttribute("user_id") == null) {
			System.out.println("session has no user_id");
		}
		String userId = session.getAttribute("user_id").toString(); 
		DBConnection conn = new DBConnectionFactory().getConnection();
		JSONArray array = new JSONArray();
		
		try {
			Set<Job> list = conn.getSavedJobs(userId);
			for(Job job : list) {
				JSONObject obj = job.toJSONObject();
				obj.append("saved", "true");
				array.put(obj);
			}
			
			rpcHelper.writeJsonArray(response, array, request);
			
		} catch(JSONException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.setStatus(403);
			return;
		}
		
		DBConnection connection = DBConnectionFactory.getConnection();
	   	 try {
	   		 JSONObject input = rpcHelper.readJSONObject(request);
	   		 String userId = input.getString("user_id");
	   		 JSONArray array = input.getJSONArray("saved");
	   		 List<String> jobIds = new ArrayList<>();
	   		 for(int i = 0; i < array.length(); ++i) {
	   			 jobIds.add(array.getString(i));
	   		 }
	   		 connection.setSaveJobs(userId, jobIds);
	   		 
	   		 JSONArray array2 = input.getJSONArray("unsaved");
	   		 List<String> jobIds2 = new ArrayList<>();
	   		 for(int i = 0; i < array2.length(); ++i) {
	   			 jobIds2.add(array2.getString(i));
	   		 }
	   		 connection.unsetSaveJobs(userId, jobIds2);
	   		 rpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"), request);
	   		 
	   	 } catch (Exception e) {
	   		 e.printStackTrace();
	   	 } finally {
	   		 connection.close();
	   	 }

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

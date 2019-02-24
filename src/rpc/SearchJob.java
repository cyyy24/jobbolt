package rpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Job;

/**
 * Servlet implementation class SearchJob
 */
@WebServlet("/search")
public class SearchJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchJob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session == null) {
			response.setStatus(403);
			return;
		}
		DBConnection conn = DBConnectionFactory.getConnection();
	   	 try {
	   		 JSONObject input = rpcHelper.readJSONObject(request);
	   		 
	   		String location = input.getString("location");
			String jobTitle = input.getString("keyword");
			String company = input.getString("company");
			List<Job> results = conn.searchJobs(location, jobTitle, company);
			JSONArray array = new JSONArray();
			for (Job job : results) {
				array.put(job.toJSONObject());
			}
	   		 
			rpcHelper.writeJsonArray(response, array); 
	   		 
	   		
	   	 } catch (Exception e) {
	   		 e.printStackTrace();
	   	 } finally {
	   		 conn.close();
	   	 }
	}

}

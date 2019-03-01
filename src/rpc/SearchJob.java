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
import db.mysql.MySQLConnection;
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
		
//		HttpSession session = request.getSession(false);
//		if(session == null) {
//			response.setStatus(403);
//			return;
//		}
		
		String location = request.getParameter("location");
		String jobTitle = request.getParameter("keyword");
		String company = request.getParameter("company");
		
		DBConnection conn = new MySQLConnection();
		List<Job> results = conn.searchJobs(location, jobTitle, company);
		JSONArray array = new JSONArray();
		for (Job item : results) {
			array.put(item.toJSONObject());
		}
		
		RpcUtil.writeJSONArray(response, array, request);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.DBConnection;
import entity.Job;
import entity.Job.JobBuilder;
import jobClient.glassdoorAPI.GlassdoorAPI;
import jobClient.IndeedAPI.IndeedAPI;

public class MySQLConnection implements DBConnection {

	private Connection conn;
	private static boolean saveToTableJobs = true;
	private static boolean saveToTableCategories = true;

	public MySQLConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection(MySQLDBUtil.URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void setSaveJobs(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {

			String sql = "SELECT job_id FROM history WHERE user_id = ? AND job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			for (String jobId : jobIds) {
				stmt.setString(2, jobId);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					sql = "UPDATE history SET saved = true WHERE user_id = ? AND job_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, userId);

					ps.setString(2, jobId);
					ps.execute();

				} else {
					sql = "INSERT IGNORE INTO history(user_id, job_id, saved, status) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, userId);

					ps.setString(2, jobId);
					ps.setBoolean(3, true);
					ps.setString(4, null);
					ps.execute();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void unsetSaveJobs(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {

			String sql = "UPDATE history SET saved = false WHERE user_id = ? AND job_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			for (String jobId : jobIds) {
				ps.setString(2, jobId);

				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void setJobPending(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {

			String sql = "SELECT job_id FROM history WHERE user_id = ? AND job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			for (String jobId : jobIds) {
				stmt.setString(2, jobId);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					sql = "UPDATE history SET status = ? WHERE user_id = ? AND job_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, "Pending");
					ps.setString(2, userId);
					ps.setString(3, jobId);
					ps.execute();

				} else {
					sql = "INSERT IGNORE INTO history(user_id, job_id, status) VALUES (?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, userId);

					ps.setString(2, jobId);
					ps.setString(3, "Pending");
					ps.execute();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void setJobSubmitted(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {
			String sql = "UPDATE history SET status = ? WHERE user_id = ? AND job_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Submitted");
			ps.setString(2, userId);
			for (String jobId : jobIds) {
				ps.setString(3, jobId);
				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public Set<String> getSavedJobIds(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<String> savedJobs = new HashSet<>();

		try {
			String sql = "SELECT job_id FROM history WHERE user_id = ? AND saved = true";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("job_id");
				savedJobs.add(jobId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return savedJobs;

	}


	@Override
	public Set<Job> getSavedJobs(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<Job> savedJobs = new HashSet<>();
		Set<String> jobIds = getSavedJobIds(userId);

		try {
			String sql = "SELECT * FROM jobs WHERE job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (String jobId : jobIds) {
				stmt.setString(1, jobId);

				ResultSet rs = stmt.executeQuery();

				JobBuilder builder = new JobBuilder();

				while (rs.next()) {
					builder.setJobId(rs.getString("job_id"));
					builder.setPlatform(rs.getString("platform"));
					builder.setJobTitle(rs.getString("job_title"));
					builder.setCompany(rs.getString("company"));
					builder.setUrl(rs.getString("url"));
					builder.setLocation(rs.getString("location"));
					builder.setCategory(rs.getString("category"));
					builder.setDescription(rs.getString("description"));
					savedJobs.add(builder.build());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return savedJobs;

	}


	@Override
	public Set<String> getAppliedJobIds(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<String> appliedJobs = new HashSet<>();

		try {
			String sql = "SELECT job_id FROM history WHERE user_id = ? AND status = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, "Submitted");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("job_id");
				appliedJobs.add(jobId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return appliedJobs;
	}


	@Override
	public Set<Job> getAppliedJobs(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<Job> appliedJobs = new HashSet<>();
		Set<String> jobIds = getAppliedJobIds(userId);

		try {
			String sql = "SELECT * FROM jobs WHERE job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (String jobId : jobIds) {
				stmt.setString(1, jobId);

				ResultSet rs = stmt.executeQuery();

				JobBuilder builder = new JobBuilder();

				while (rs.next()) {
					builder.setJobId(rs.getString("job_id"));
					builder.setPlatform(rs.getString("platform"));
					builder.setJobTitle(rs.getString("job_title"));
					builder.setCompany(rs.getString("company"));
					builder.setUrl(rs.getString("url"));
					builder.setLocation(rs.getString("location"));
					builder.setCategory(rs.getString("category"));
					builder.setDescription(rs.getString("description"));
					appliedJobs.add(builder.build());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return appliedJobs;
	}


	/*
	 * @Override public List<Job> searchJobs(double lat, double lon, String
	 * jobtitle) { // TODO Auto-generated method stub IndeedAPI inAPI = new
	 * IndeedAPI(); List<Job> jobs = IndeedAPI.search(lat, lon, jobtitle); for(Job
	 * job : jobs) { saveJob(job); }
	 *
	 * return jobs; }
	 */


	@Override
	public String getFullname(String userId) {
		if (conn == null) {
			return "";
		}
		String name = "";

		try {
			String sql = "SELECT first_name, last_name FROM users WHERE user_Id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				name = rs.getString("first_name") + " " + rs.getString("last_name");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return name;
	}


	@Override
	public boolean verifyLogin(String userId, String password) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return false;
		}

		try {
			String sql = "SELECT user_id FROM users WHERE user_id = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}


	@Override
	public boolean registerUser(String userId, String password, String email, String firstname, String lastname) {
		if (conn == null) {
			return false;
		}
		try {
			String sql = "SELECT user_id FROM users WHERE user_id = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				sql = "INSERT IGNORE INTO users VALUES(?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, userId);
				ps.setString(2, password);
				ps.setString(3, email);
				ps.setString(4, firstname);
				ps.setString(5, lastname);
				ps.execute();
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}


//	@Override
//	public List<Job> searchJobs(String location, String keyword, String company) {
//
//		GlassdoorAPI ga = new GlassdoorAPI();
//		//IndeedAPI id = new IndeedAPI();
//		List<Job> results = ga.search(location, keyword, company);
//		System.out.println("Searching!!! Found " + results.size() + " jobs!");
//		for (Job job : results) {
//			saveJob(job);
//		}
//
//		if (this.saveToTableJobs) {
//			System.out.println("new records inserted into the table [jobs]");
//		}
//		if (this.saveToTableCategories) {
//			System.out.println("new records inserted into the table [categories]");
//		}
//
//		return results;
//	}

	public List<Job> searchJobs(String location, String keyword, String company, String platform) {
		List<Job> a = null;

		if (platform == "glassdor") {
			GlassdoorAPI ga = new GlassdoorAPI();
			List<Job> results = ga.search(location, keyword, company);
			System.out.println("Searching!!! Found " + results.size() + " jobs!");
			for (Job job : results) {
				saveJob(job);
			}

			if (this.saveToTableJobs) {
				System.out.println("new records inserted into the table [jobs]");
			}
			if (this.saveToTableCategories) {
				System.out.println("new records inserted into the table [categories]");
			}

			return results;
		}
		else if (platform == "indeed"){
			IndeedAPI id = new IndeedAPI();
			List<Job> results = id.search(location, keyword, company);
			System.out.println("Searching!!! Found " + results.size() + " jobs!");
			for (Job job : results) {
				saveJob(job);
			}

			if (this.saveToTableJobs) {
				System.out.println("new records inserted into the table [jobs]");
			}
			if (this.saveToTableCategories) {
				System.out.println("new records inserted into the table [categories]");
			}

			return results;
		}
		else {
			System.out.println("Please enter a vaild platform");
		}
		return a;
	}



	// prepared for the search function
	@Override
	public void saveJob(Job job) {
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {
			String sql;
			PreparedStatement ps;
			// insert into table "jobs"
			if (this.saveToTableJobs) {
				sql = "INSERT IGNORE INTO jobs VALUES(?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, job.getJobId());
				ps.setString(2, job.getPlatform());
				ps.setString(3, job.getJobTitle());
				ps.setString(4, job.getCompany());
				ps.setString(5, job.getUrl());
				ps.setString(6, job.getLocation());
				ps.setString(7, job.getCategory());
				ps.setString(8, job.getDescription());
				ps.execute();
			}

			if (this.saveToTableCategories) {
				/*do this step is not decided,if recommendation need table of
				 * category, it need to be done
				 * */
				sql = "INSERT IGNORE INTO categories VALUES(?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, job.getJobId());
				ps.setString(2, job.getJobTitle());
				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// for the use of recommendation, get the job title of given a jobId.
	@Override
	public String getJobTitle(String jobId) {
		if (conn == null) {
			return null;
		}
		String jobTitle = "";
		try {
			String sql = "SELECT job_title from categories WHERE job_id = ? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, jobId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				jobTitle = rs.getString("job_title");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return jobTitle;
	}


	@Override
	public Set<String> getAllJobIds(String userId) {
		if (conn == null) {
			return new HashSet<>();
		}

		Set<String> allJobIds = new HashSet<>();
		try {
			String sql = "SELECT job_id FROM history WHERE user_id = ? AND (saved = ? OR status = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setBoolean(2, true);
			stmt.setString(3, "Submitted");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("job_id");
				allJobIds.add(jobId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allJobIds;
	}

}
package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.DBConnection;
import entity.Job;
import entity.Job.JobBuilder;
import jobClient.glassdoorAPI.GlassdoorAPI;

public class MySQLConnection implements DBConnection {

	private Connection conn;
	private static boolean saveToTableJobs = true;
	private static boolean saveToTableCategories = true;

	public MySQLConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection(MySQLDBUtil.URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public void setSaveJobs(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {

			String sql = "SELECT job_id FROM history WHERE user_id = ? AND job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			for (String jobId : jobIds) {
				stmt.setString(2, jobId);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					sql = "UPDATE history SET saved = true WHERE user_id = ? AND job_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, userId);

					ps.setString(2, jobId);
					ps.execute();

				} else {
					sql = "INSERT IGNORE INTO history(user_id, job_id, saved, status) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, userId);

					ps.setString(2, jobId);
					ps.setBoolean(3, true);
					ps.setString(4, null);
					ps.execute();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void unsetSaveJobs(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {

			String sql = "UPDATE history SET saved = false WHERE user_id = ? AND job_id = ?";
	   		 PreparedStatement ps = conn.prepareStatement(sql);
	   		 ps.setString(1, userId);
	   		 for (String jobId : jobIds) {
	   			 ps.setString(2, jobId);
	   			 
	   			 ps.execute();
	   		 }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void setJobPending(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {

			String sql = "SELECT job_id FROM history WHERE user_id = ? AND job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			for (String jobId : jobIds) {
				stmt.setString(2, jobId);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					sql = "UPDATE history SET status = ? WHERE user_id = ? AND job_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, "Pending");
					ps.setString(2, userId);
					ps.setString(3, jobId);
					ps.execute();

				} else {
					sql = "INSERT IGNORE INTO history(user_id, job_id, status) VALUES (?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, userId);

					ps.setString(2, jobId);
					ps.setString(3, "Pending");
					ps.execute();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void setJobSubmitted(String userId, List<String> jobIds) {
		// TODO Auto-generated method stub
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {
			String sql = "UPDATE history SET status = ? WHERE user_id = ? AND job_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Submitted");
			ps.setString(2, userId);
			for (String jobId : jobIds) {
				ps.setString(3, jobId);
				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public Set<String> getSavedJobIds(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<String> savedJobs = new HashSet<>();

		try {
			String sql = "SELECT job_id FROM history WHERE user_id = ? AND saved = true";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("job_id");
				savedJobs.add(jobId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return savedJobs;

	}

	
	@Override
	public Set<Job> getSavedJobs(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<Job> savedJobs = new HashSet<>();
		Set<String> jobIds = getSavedJobIds(userId);

		try {
			String sql = "SELECT * FROM jobs WHERE job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (String jobId : jobIds) {
				stmt.setString(1, jobId);

				ResultSet rs = stmt.executeQuery();

				JobBuilder builder = new JobBuilder();

				while (rs.next()) {
					builder.setJobId(rs.getString("job_id"));
					builder.setPlatform(rs.getString("platform"));
					builder.setJobTitle(rs.getString("job_title"));
					builder.setCompany(rs.getString("company"));
					builder.setUrl(rs.getString("url"));
					builder.setLocation(rs.getString("location"));
					builder.setCategory(rs.getString("category"));
					builder.setDescription(rs.getString("description"));
					savedJobs.add(builder.build());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return savedJobs;

	}

	
	@Override
	public Set<String> getAppliedJobIds(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<String> appliedJobs = new HashSet<>();

		try {
			String sql = "SELECT job_id FROM history WHERE user_id = ? AND status = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, "Submitted");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("job_id");
				appliedJobs.add(jobId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return appliedJobs;
	}

	
	@Override
	public Set<Job> getAppliedJobs(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return new HashSet<>();
		}

		Set<Job> appliedJobs = new HashSet<>();
		Set<String> jobIds = getAppliedJobIds(userId);

		try {
			String sql = "SELECT * FROM jobs WHERE job_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (String jobId : jobIds) {
				stmt.setString(1, jobId);

				ResultSet rs = stmt.executeQuery();

				JobBuilder builder = new JobBuilder();

				while (rs.next()) {
					builder.setJobId(rs.getString("job_id"));
					builder.setPlatform(rs.getString("platform"));
					builder.setJobTitle(rs.getString("job_title"));
					builder.setCompany(rs.getString("company"));
					builder.setUrl(rs.getString("url"));
					builder.setLocation(rs.getString("location"));
					builder.setCategory(rs.getString("category"));
					builder.setDescription(rs.getString("description"));
					appliedJobs.add(builder.build());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return appliedJobs;
	}

	
	/*
	 * @Override public List<Job> searchJobs(double lat, double lon, String
	 * jobtitle) { // TODO Auto-generated method stub IndeedAPI inAPI = new
	 * IndeedAPI(); List<Job> jobs = IndeedAPI.search(lat, lon, jobtitle); for(Job
	 * job : jobs) { saveJob(job); }
	 * 
	 * return jobs; }
	 */
	
	
	@Override
	public String getFullname(String userId) {
		if (conn == null) {
			return "";
		}
		String name = "";

		try {
			String sql = "SELECT first_name, last_name FROM users WHERE user_Id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				name = rs.getString("first_name") + " " + rs.getString("last_name");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return name;
	}

	
	@Override
	public boolean verifyLogin(String userId, String password) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return false;
		}

		try {
			String sql = "SELECT user_id FROM users WHERE user_id = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	
	@Override
	public boolean registerUser(String userId, String password, String email, String firstname, String lastname) {
		if (conn == null) {
			return false;
		}
		try {
			String sql = "SELECT user_id FROM users WHERE user_id = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				sql = "INSERT IGNORE INTO users VALUES(?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, userId);
				ps.setString(2, password);
				ps.setString(3, email);
				ps.setString(4, firstname);
				ps.setString(5, lastname);
				ps.execute();
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	

	@Override
	public List<Job> searchJobs(String location, String keyword, String company) {
		
		GlassdoorAPI ga = new GlassdoorAPI();
		List<Job> results = ga.search(location, keyword, company);
		System.out.println("Searching!!! Found " + results.size() + " jobs!");
		for (Job job : results) {
			saveJob(job);
		}
		
		if (this.saveToTableJobs) {
			System.out.println("new records inserted into the table [jobs]");
		}
		if (this.saveToTableCategories) {
			System.out.println("new records inserted into the table [categories]");
		}
		
		return results;
	}
	
	
	// prepared for the search function
	@Override
	public void saveJob(Job job) {
		if (conn == null) {
			System.out.println("DB connection failed");
			return;
		}
		try {
			String sql;
			PreparedStatement ps;
			// insert into table "jobs"
			if (this.saveToTableJobs) {
				sql = "INSERT IGNORE INTO jobs VALUES(?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, job.getJobId());
				ps.setString(2, job.getPlatform());
				ps.setString(3, job.getJobTitle());
				ps.setString(4, job.getCompany());
				ps.setString(5, job.getUrl());
				ps.setString(6, job.getLocation());
				ps.setString(7, job.getCategory());
				ps.setString(8, job.getDescription());
				ps.execute();
			}
				
			if (this.saveToTableCategories) {
				/*do this step is not decided,if recommendation need table of 
				 * category, it need to be done
				 * */
				sql = "INSERT IGNORE INTO categories VALUES(?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, job.getJobId());
				ps.setString(2, job.getJobTitle());
				ps.execute();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	// for the use of recommendation, get the job title of given a jobId.
	@Override
	public String getJobTitle(String jobId) {
		if (conn == null) {
			return null;
		}
		String jobTitle = "";
		try {
			String sql = "SELECT job_title from categories WHERE job_id = ? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, jobId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				jobTitle = rs.getString("job_title");
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return jobTitle;
	}
		
	
	@Override
	public Set<String> getAllJobIds(String userId) {
		if (conn == null) {
			return new HashSet<>();
		}

		Set<String> allJobIds = new HashSet<>();
		try {
			String sql = "SELECT job_id FROM history WHERE user_id = ? AND (saved = ? OR status = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setBoolean(2, true);
			stmt.setString(3, "Submitted");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String jobId = rs.getString("job_id");
				allJobIds.add(jobId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allJobIds;
	}

}

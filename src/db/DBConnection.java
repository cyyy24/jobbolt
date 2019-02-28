package db;

import java.util.List;
import java.util.Set;

import entity.Job;

public interface DBConnection {
	//close the connection
	public void close();
	
	//insert the job user ask for save 
	public void setSaveJobs(String userId, List<String> jobIds);
	
	//delete the job user has saved
	public void unsetSaveJobs(String userId, List<String> jobIds);
	
	//insert the job that user has applied, change the status to pending
	public void setJobPending(String userId, List<String> jobIds);
	
	//make the apply status to submitted
	public void setJobSubmitted(String userId, List<String> jobIds);
	
	//get the job id user has saved
	public Set<String> getSavedJobIds(String userId);
	
	//get the job(json file) user has saved
	public Set<Job> getSavedJobs(String userId);
	
	//get the job id user has applied
	public Set<String> getAppliedJobIds(String userId);
	
	//get the job(json file) user has applied
	public Set<Job> getAppliedJobs(String userId);
	
	//search jobs near the geolocation and jobtitle()
	//public List<Job> searchJobs(double lat, double lon, String jobtitle);
	
	//get full name of a user, for login get method
	public String getFullname(String userId);
	
	//for register servlet
	public boolean registerUser(String userId, String password, String email, String firstname, String lastname);
	
	//return whether the credential is correct, for login post method
	public boolean verifyLogin(String userId, String password);

	//search jobs use location , keyword(jobTitle), company
	public List<Job> searchJobs(String location, String keyword, String company);
	
	//save job into db
	public void saveJob(Job job);
	
	//get categories (job title) of a given job id
	public String getJobTitle(String jobId);
	
	//get all job ids a user has either saved or submitted (for recommendation)
	public Set<String> getAllJobIds(String userId);

}

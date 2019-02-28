package recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Job;

public class JobRecommendation {
	private DBConnection conn = DBConnectionFactory.getConnection();
	
	public List<Job> recommendJobs(String userId) {
		
		// Step 1: get all visited & saved & applied job_id.
		Set<String> allVisitedJobIds = conn.getAllJobIds(userId);  // for de-dup of recommended jobs.
		
		Set<String> allSavedJobIds = conn.getSavedJobIds(userId);
		Set<String> allAppliedJobIds = conn.getAppliedJobIds(userId);
	
		// Step 2: get job titles of saved and applied jobs respectively while recording the count of each job title.
		Map<String, Integer> allSavedJobTitles = getAllJobTitles(allSavedJobIds);
		Map<String, Integer> allAppliedJobTitles = getAllJobTitles(allAppliedJobIds);
		
		// Step 3: sort the job titles according to their count.
		List<Entry<String, Integer>> allSavedJobTitlesList = new ArrayList<>(allSavedJobTitles.entrySet());
		List<Entry<String, Integer>> allAppliedJobTitlesList = new ArrayList<>(allAppliedJobTitles.entrySet());
		sortJobTitlesList(allSavedJobTitlesList);
		sortJobTitlesList(allAppliedJobTitlesList);
		
		// Step 4: search more jobs based on saved and applied job titles, filter out already visited jobs.
		return getRecommJobs(allSavedJobTitlesList, allAppliedJobTitlesList, allVisitedJobIds);	
	}
	
	
	// ****** Class private helper methods: ******
	
	private Map<String, Integer> getAllJobTitles(Set<String> jobIds) {
		Map<String, Integer> allJobTitles = new HashMap<>();
		for (String jobId : jobIds) {
			String jobTitle = conn.getJobTitle(jobId);
			allJobTitles.put(jobTitle, allJobTitles.getOrDefault(jobTitle, 0) + 1);			
		}
		return allJobTitles;
	}
	
	private void sortJobTitlesList(List<Entry<String, Integer>> jobTitlesList) {
		Collections.sort(jobTitlesList, (Entry<String, Integer> e1, Entry<String, Integer> e2) -> {
			return Integer.compare(e2.getValue(), e1.getValue());
		});
	}
	
	private List<Job> getRecommJobs(List<Entry<String, Integer>> savedJobTitleList, List<Entry<String, Integer>> appliedJobTitleList, Set<String> allVisitedJobIds) {
		List<Job> recommendedJobs = new ArrayList<>();
		Set<String> seenJobIds = new HashSet<>();  // in case returned search results have duplicated jobs.
		getSearchResult(savedJobTitleList, allVisitedJobIds, seenJobIds, recommendedJobs);
		getSearchResult(appliedJobTitleList, allVisitedJobIds, seenJobIds, recommendedJobs);
		return recommendedJobs;
	}
	
	private void getSearchResult(List<Entry<String, Integer>> jobTitlesList, Set<String> allVisitedJobIds, Set<String> seenJobIds, List<Job> recommendedJobs) {
		for (Entry<String, Integer> titleEntry : jobTitlesList) {
			List<Job> jobs = conn.searchJobs("", titleEntry.getKey(), "");
			for (Job job : jobs) {
				if (!allVisitedJobIds.contains(job.getJobId()) && !seenJobIds.contains(job.getJobId())) {
					recommendedJobs.add(job);
					seenJobIds.add(job.getJobId());
				}
			}
		}
	}
}

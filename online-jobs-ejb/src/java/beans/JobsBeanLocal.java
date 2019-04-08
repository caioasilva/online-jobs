/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import model.Provider;
import model.Job;
import model.JobKeyword;
import model.JobOffer;

/**
 *
 * @author caio
 */
@Local
public interface JobsBeanLocal {
    List<Job> getAllJobs();
    List<Job> getJobsDescLimit(int start, int quant);
    Job getJobById(int id);

    List<Job> getJobsByKeywords(List<String> keywords);

    int createJob(Job j);
    
    List<JobKeyword> getKeywordsById(int id);
    
    void updateJobKeywords(int id, List<JobKeyword> keys);

    Job updateJob(Job job);

    void deleteJob(int id);

    void acceptFreelancer(int jobId, int freelancerId);

    Collection<Job> getJobsByProviderId(int providerId);
    
    List<Job> getAllOpenJobs();
    
    public void deleteJobAdmin(int id);
    
    boolean hasFreelancerOfferedToJob(int freelancerId, int jobId);

    void offerToJob(int freelancerId, int jobId);
    
    void revokeOfferToJob(int freelancerId, int jobId);

    void completeJob(int jobId);
    
    List<JobOffer> getJobsByFreelancerId(int freelancerId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.JobsBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import model.FreelancerSkill;
import model.Job;
import model.JobKeyword;
import model.JobOffer;

/**
 *
 * @author caio
 */
@Named(value = "jobBean")
@SessionScoped
public class JobBean implements Serializable {

    @Resource(mappedName = "java:app/LogQueue")
    private Queue java_appLogQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @EJB
    private JobsBeanLocal jobsBean;

    @Inject
    LoginBean loginBean;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private Job job = new Job();

//    public void setJob(Job job) {
//        this.job = job;
//    }
    public Job getJob() {
        return job;
    }

    /**
     * Creates a new instance of JobBean
     */
    public JobBean() {
    }

    public void init() {
//        user = userService.find(id);
        job = jobsBean.getJobById(id);

    }

    public void newJob() {
        job = new Job();
        id = 0;
    }

    public List<Job> getRecentJobs() {
        List<Job> l = jobsBean.getJobsDescLimit(0, 10);
        return l;
    }

    public String createJob() {
        job.setProviderId(loginBean.getUser().getProvider());
        id = jobsBean.createJob(job);
        jobsBean.updateJobKeywords(id, generateKeywordsList(keywordsString));
        return "/pages/employer-jobs.xhtml?faces-redirect=true";
    }

    public String updateJob() {
        jobsBean.updateJob(job);
        jobsBean.updateJobKeywords(id, generateKeywordsList(keywordsString));
        loginBean.refresh();
        return "/pages/employer-jobs.xhtml?faces-redirect=true";
    }

    public String getKeywordsString() {
        List<JobKeyword> skills = jobsBean.getKeywordsById(id);
        keywordsString = skills.stream().map((f) -> f.getJobKeywordPK().getKeyword()).collect(Collectors.joining(","));
        return keywordsString;
    }

    private List<JobKeyword> keywords_list;

    private String keywordsString;

    public void setKeywordsString(String keywordsString) {
        this.keywordsString = keywordsString;
    }

    public List<JobKeyword> generateKeywordsList(String keywords) {
        String[] sk = keywords.split(",");
        keywords_list = new ArrayList();
        for (String s : sk) {
            JobKeyword jk = new JobKeyword(id, s);
            keywords_list.add(jk);
        }
        return keywords_list;
//        freelancersBean.updateFreelancerSkills(user.getFreelancer().getId(), l_skills);
    }

    public void deleteJob(int id) {
        jobsBean.deleteJob(id);
        loginBean.refresh();

    }
    
    public void completeJob(int id) {
        jobsBean.completeJob(id);
        Message m = context.createMessage();
        try {
            m.setIntProperty("jobId", id);
            m.setIntProperty("userId", loginBean.getUser().getId());
            m.setStringProperty("description", "Marked as Completed");
            sendJMSMessageToLogQueue(m);
        } catch (JMSException ex) {
            Logger.getLogger(JobBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginBean.refresh();
    }

    public void deleteJobAdmin(int id) {
        jobsBean.deleteJobAdmin(id);
        loginBean.refresh();
    }

    public String acceptCandidate(int jobId, int candidateId) {
        jobsBean.acceptFreelancer(jobId, candidateId);
        Message m = context.createMessage();
        try {
            m.setIntProperty("jobId", jobId);
            m.setIntProperty("userId", loginBean.getUser().getId());
            m.setStringProperty("description", "Accepted candidate id " + String.valueOf(candidateId) + " and closed job");
            sendJMSMessageToLogQueue(m);
        } catch (JMSException ex) {
            Logger.getLogger(JobBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginBean.refresh();
        return "/pages/employer-jobs.xhtml?faces-redirect=true";
    }

    public void apply(int jobId) {
        if (!jobsBean.hasFreelancerOfferedToJob(loginBean.getUser().getFreelancer().getId(), jobId)) {
            jobsBean.offerToJob(loginBean.getUser().getFreelancer().getId(), jobId);
            Message m = context.createMessage();
            try {
                m.setIntProperty("jobId", jobId);
                m.setIntProperty("userId", loginBean.getUser().getId());
                m.setStringProperty("description", "Offered to undertake job");
                sendJMSMessageToLogQueue(m);
            } catch (JMSException ex) {
                Logger.getLogger(JobBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void revoke(int jobId) {
        jobsBean.revokeOfferToJob(loginBean.getUser().getFreelancer().getId(), jobId);
        Message m = context.createMessage();
        try {
            m.setIntProperty("jobId", jobId);
            m.setIntProperty("userId", loginBean.getUser().getId());
            m.setStringProperty("description", "Revoked to undertake job");
            sendJMSMessageToLogQueue(m);
        } catch (JMSException ex) {
            Logger.getLogger(JobBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean hasFreelancerOfferedToJob(int freelancerId, int jobId) {
        return jobsBean.hasFreelancerOfferedToJob(freelancerId, jobId);
    }

    private void sendJMSMessageToLogQueue(Message messageData) {
        context.createProducer().send(java_appLogQueue, messageData);
    }
    
    public List<JobOffer> getJobsByFreelancerId(int id){
        return jobsBean.getJobsByFreelancerId(id);
    }

}

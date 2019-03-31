/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.FreelancersBeanLocal;
import beans.JobsBeanLocal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.Freelancer;
import model.JobDescription;

/**
 *
 * @author caio
 */
@Named(value = "indexBean")
@Dependent
public class IndexBean {

    @EJB
    private FreelancersBeanLocal freelancersBean;
    
    @EJB
    private JobsBeanLocal jobsBean;
    
    
    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
    }
    
    
    public List<JobDescription> getRecentJobs(){
        List<JobDescription> l = jobsBean.getJobsDescLimit(0, 10);
        return l;
    }
    
    public List<Freelancer> getSomeFreelancers(){
        return freelancersBean.getFreelancersLimit(0, 4);
    }
}

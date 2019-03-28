/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.beans;

import beans.JobsBeanLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import model.JobDescription;

/**
 *
 * @author caio
 */
@Named(value = "jobBean")
@RequestScoped
public class JobBean {

    @EJB
    private JobsBeanLocal jobsBean;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    private JobDescription job;

    public JobDescription getJob() {
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
    
}

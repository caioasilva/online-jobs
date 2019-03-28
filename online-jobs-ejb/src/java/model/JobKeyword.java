/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caio
 */
@Entity
@Table(name = "JOBS_KEYWORDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobKeyword.findAll", query = "SELECT j FROM JobKeyword j")
    , @NamedQuery(name = "JobKeyword.findByJobId", query = "SELECT j FROM JobKeyword j WHERE j.jobKeywordPK.jobId = :jobId")
    , @NamedQuery(name = "JobKeyword.findByKeyword", query = "SELECT j FROM JobKeyword j WHERE j.jobKeywordPK.keyword = :keyword")
    , @NamedQuery(name = "JobKeyword.findByKeywordIgnoreCase", query = "SELECT j FROM JobKeyword j WHERE lower(j.jobKeywordPK.keyword) like lower(:keyword)")})
public class JobKeyword implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JobKeywordPK jobKeywordPK;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Job job;

    public JobKeyword() {
    }

    public JobKeyword(JobKeywordPK jobKeywordPK) {
        this.jobKeywordPK = jobKeywordPK;
    }

    public JobKeyword(int jobId, String keyword) {
        this.jobKeywordPK = new JobKeywordPK(jobId, keyword);
    }

    public JobKeywordPK getJobKeywordPK() {
        return jobKeywordPK;
    }

    public void setJobKeywordPK(JobKeywordPK jobKeywordPK) {
        this.jobKeywordPK = jobKeywordPK;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobKeywordPK != null ? jobKeywordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobKeyword)) {
            return false;
        }
        JobKeyword other = (JobKeyword) object;
        if ((this.jobKeywordPK == null && other.jobKeywordPK != null) || (this.jobKeywordPK != null && !this.jobKeywordPK.equals(other.jobKeywordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.JobKeyword[ jobKeywordPK=" + jobKeywordPK + " ]";
    }
    
}
